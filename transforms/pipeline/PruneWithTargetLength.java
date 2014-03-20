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

public class PruneWithTargetLength {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("usage: java PruneWithTargetLength existing-pruned-dir my-pruned-dir counts-file target-length");
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
        int targetLength = Integer.parseInt(args[3]);

        // try {
        //     FileUtils.cleanDirectory(new File(newDir));
        // } catch(IOException io) {
        //     throw new RuntimeException(io);
        // }

        final TreeSet<TokenCount> sortedTokens = new TreeSet<TokenCount>();
        final int nThreads = ParallelFileIterator.nCores;
        final int nChunks = nThreads;

        // Generate partial dumps of token counts
        if (!new File(countsFolder+"/counts-0").exists() &&
                !new File(countsFolder+"/all-counts").exists()) {

            TakePartialCounts.run(existing, countsFolder, nChunks);
            return;
        }

        // Merge partial counts into a global count file
        if (!new File(countsFolder+"/all-counts").exists()) {
            MyPruner.mergeTokenCounts(countsFolder, nChunks);
            return;
        }

        // Initialize sortedTokens from the global counts file
        // created in the previous step.
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

        // At this point, we have a sorted set of tokens and their counts.
        // We can start pruning now.
        System.out.println("Starting pruning");
        PruneVectors[] runners = new PruneVectors[nChunks];
        for (int t = 0; t < nChunks; t++) {
            runners[t] = new PruneVectors(sortedTokens, newDir, targetLength);
        }

        ParallelFileIterator executor = new ParallelFileIterator(new File(existing),
            conf, fs, org.apache.hadoop.io.Text.class,
            org.apache.mahout.math.VectorWritable.class);
        executor.run(runners);
    }

    public static class PruneVectors extends
            ParallelFileIterator.ParallelFileRunner {
        private final TreeSet<TokenCount> sortedTokens;
        private final String outputFolder;
        private final int targetLength;

        public PruneVectors(TreeSet<TokenCount> sortedTokens,
                String outputFolder, int targetLength) {
            this.sortedTokens = sortedTokens;
            this.outputFolder = outputFolder;
            this.targetLength = targetLength;
        }

        @Override
        protected void callback(File f) {
            String fileName = f.getName();

            File out = new File(this.outputFolder+"/"+fileName);
            if (out.exists()) {
                System.out.println("Skipping "+this.outputFolder+"/"+fileName);
                return;
            } else {
                System.out.println("Working on "+this.outputFolder+"/"+fileName);
            }

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
                    if (originalVec.getNumNonZeroElements() <= this.targetLength) {
                        writer.append(key, val);
                    } else {
                        final org.apache.mahout.math.Vector newVec =
                            new RandomAccessSparseVector(originalVec.size(), 
                                    this.targetLength);
                        int count = 0;
                        Iterator<TokenCount> descendingIterator =
                            this.sortedTokens.descendingIterator();
                        while (count < this.targetLength) {
                            TokenCount tc = descendingIterator.next();
                            double v = originalVec.get(tc.token());
                            if (v != 0.0) {
                                newVec.set(tc.token(), v);
                                count++;
                            }
                        }
                        outputVal.set(newVec);
                        writer.append(key, outputVal);
                    }
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

