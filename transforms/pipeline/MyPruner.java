package pipeline;

import org.apache.lucene.search.similarities.DefaultSimilarity;
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import java.nio.charset.Charset;
import java.net.URI;
import org.apache.mahout.utils.io.ChunkedWriter;
import org.apache.hadoop.fs.Path;
import javax.xml.parsers.*;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.apache.hadoop.io.*;
import java.util.concurrent.*;
import org.apache.mahout.common.StringTuple;
import org.apache.mahout.math.*;
import common.*;

public class MyPruner {

    public static void mergeTokenCounts(String countsFolder, int nChunks) {
        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final HashMap<Integer, MutableLong> accum = new HashMap<Integer, MutableLong>();
        try {
            final IntWritable inputKey = new IntWritable();
            final LongWritable inputVal = new LongWritable();

            for (int t = 0 ; t < nChunks; t++) {
                SequenceFile.Reader reader = new SequenceFile.Reader(fs,
                        new Path(countsFolder+"/counts-"+t), conf);
                System.out.println("Merging from "+t);
                while (reader.next(inputKey, inputVal)) {
                    if (!accum.containsKey(inputKey.get())) {
                        accum.put(inputKey.get(), new MutableLong(inputVal.get()));
                    } else {
                        accum.get(inputKey.get()).add(inputVal.get());
                    }
                }
                reader.close();
                System.out.println("Done merging from "+t);
            }

            SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                    new Path(countsFolder+"/all-counts"),
                    org.apache.hadoop.io.IntWritable.class,
                    org.apache.hadoop.io.LongWritable.class);
            final Iterator<Map.Entry<Integer, MutableLong>> it =
                accum.entrySet().iterator();
            final IntWritable outputKey = new IntWritable();
            final LongWritable outputVal = new LongWritable();
            while (it.hasNext()) {
                Map.Entry<Integer, MutableLong> pair = it.next();
                outputKey.set(pair.getKey().intValue());
                outputVal.set(pair.getValue().get());
                writer.append(outputKey, outputVal);
            }
            writer.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Done dumping global token counts to "+
                countsFolder+"/all-counts");
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("usage: java MyPruner existing-pruned-dir my-pruned-dir counts-file perc-or-count percent");
            return;
        }

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        String existing = args[0];
        String newDir = args[1];
        String countsFolder = args[2];
        
        File existingFolder = new File(existing);
        File[] allFiles = existingFolder.listFiles();
        if (!args[3].equals("p") && !args[3].equals("c")) {
            System.out.println("Invalid argument for perc-or-count, must be either p (percent) or c (count)");
            return;
        }

        try {
            FileUtils.cleanDirectory(new File(newDir));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final TreeSet<TokenCount> sortedTokens = new TreeSet<TokenCount>();
        final int nThreads = ParallelFileIterator.nCores;
        final int nChunks = nThreads * 3;

        // Generate partial dumps of token counts
        if (!new File(countsFolder+"/counts-0").exists() &&
                !new File(countsFolder+"/all-counts").exists()) {

            TakePartialCounts.run(existing, countsFolder, nChunks);
            return;
        }

        if (!new File(countsFolder+"/all-counts").exists()) {
            mergeTokenCounts(countsFolder, nChunks);
            return;
        }

        System.out.println("Reading global counts from "+countsFolder+"/all-counts");
        SequenceFile.Reader totalCountsReader;
        try {
            totalCountsReader = new SequenceFile.Reader(fs,
                    new Path(countsFolder+"/all-counts"), conf);
            final IntWritable inputKey = new IntWritable();
            final LongWritable inputVal = new LongWritable();
            while (totalCountsReader.next(inputKey, inputVal)) {
                sortedTokens.add(new TokenCount(inputKey.get(), inputVal.get()));
            }
            totalCountsReader.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        System.out.println("Done reading global counts");

        // At this point, we have a sorted set of tokens and their counts
        int nToUse = -1;
        if (args[3].equals("p")) {
            double perc = Double.parseDouble(args[4]) * 0.01;
            nToUse = (int)(perc * ((double)sortedTokens.size()));
        } else if (args[3].equals("c")) {
            nToUse = Integer.parseInt(args[4]);
        }
        System.out.println("Using "+nToUse+" out of "+sortedTokens.size());
        int index = 0;
        Set<Integer> tokensUsed = new HashSet<Integer>();
        Iterator<TokenCount> tcIter = sortedTokens.descendingIterator();
        while (tcIter.hasNext()) {
            TokenCount tc = tcIter.next();
            tokensUsed.add(tc.token());
            // System.out.println("Using token "+tc.token()+" with count "+tc.count());
            index++;
            if (index >= nToUse) break;
        }

        System.out.println("Starting pruning");
        PruneVectors[] runners = new PruneVectors[nChunks];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new PruneVectors(tokensUsed, newDir);
        }

        ParallelFileIterator executor = new ParallelFileIterator(new File(existing),
            conf, fs, org.apache.hadoop.io.IntWritable.class,
            org.apache.hadoop.io.SparseVectorWritable.class);
        executor.run(runners);
    }

    public static class PruneVectors extends ParallelFileIterator.ParallelFileRunner {
        private final Set<Integer> tokensUsed;
        private final String outputFolder;

        public PruneVectors(Set<Integer> tokensUsed, String outputFolder) {
            this.tokensUsed = tokensUsed;
            this.outputFolder = outputFolder;
        }

        private int getNewVectorSize(final org.apache.mahout.math.Vector vec) {
            int count = 0;
            Iterator<org.apache.mahout.math.Vector.Element> iter = 
                vec.nonZeroes().iterator();
            while (iter.hasNext()) {
                org.apache.mahout.math.Vector.Element ele = iter.next();
                if (this.tokensUsed.contains(ele.index())) {
                    count++;
                }
            }
            return count;
        }

        @Override
        protected void callback(File f) {
            String fileName = f.getName();

            SequenceFile.Reader reader;
            SequenceFile.Writer writer;
            try {
                reader = new SequenceFile.Reader(fs, new Path(f.getAbsolutePath()),
                        conf);
                writer = SequenceFile.createWriter(fs, conf,
                        new Path(this.outputFolder+"/"+fileName),
                        org.apache.hadoop.io.Text.class,
                        org.apache.mahout.math.VectorWritable.class);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            final Text key = new Text();
            final org.apache.mahout.math.VectorWritable val = 
                new org.apache.mahout.math.VectorWritable();
            final org.apache.mahout.math.VectorWritable outputVal =
                new org.apache.mahout.math.VectorWritable();
            try {
                while (reader.next(key, val)) {
                    final org.apache.mahout.math.Vector originalVec = val.get();
                    int newSize = getNewVectorSize(originalVec);
                    if (newSize == 0) continue;

                    final org.apache.mahout.math.Vector newVec =
                        new RandomAccessSparseVector(originalVec.size(), 
                                newSize);

                    Iterator<org.apache.mahout.math.Vector.Element> iter = 
                        originalVec.nonZeroes().iterator();
                    while (iter.hasNext()) {
                        org.apache.mahout.math.Vector.Element ele = iter.next();
                        if (this.tokensUsed.contains(ele.index())) {
                            newVec.set(ele.index(), ele.get());
                        }
                    }
                    outputVal.set(newVec);
                    writer.append(key, outputVal);
                }

                reader.close();
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            this.print("Done");
        }

        protected void finish() { }
    }
}

