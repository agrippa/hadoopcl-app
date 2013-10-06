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

public class PruneWithTargetLength {

    static private List<File> filterOutNonSeqFiles(File[] inputs, Configuration conf,
            FileSystem fs, Writable expectedKey, Writable expectedValue) {

        int fileIndex = 0;
        List<File> accumFiltered = new ArrayList<File>(inputs.length);
        for (File f : inputs) {
            boolean filter = false;
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
                reader.next(expectedKey, expectedValue);
            } catch(Exception e) {
                filter = true;
            }

            if (!filter) {
                accumFiltered.add(f);
            }

            if (((fileIndex + 1) % 100) == 0) {
                System.out.println("Done filtering "+(fileIndex+1)+"/"+inputs.length);
            }
            fileIndex++;
        }
        return accumFiltered;
    }

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

        try {
            FileUtils.cleanDirectory(new File(newDir));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        List<File> inputFiles = filterOutNonSeqFiles(allFiles, conf, fs,
                new Text(), new org.apache.mahout.math.VectorWritable());

        TreeSet<TokenCount> sortedTokens = new TreeSet<TokenCount>();
        int nThreads = 12;
        int chunkSize = (inputFiles.size() + nThreads - 1) / nThreads;

        // Generate partial dumps of token counts
        if (!new File(countsFolder+"/counts-0").exists() &&
                !new File(countsFolder+"/all-counts").exists()) {
            Thread[] threads = new Thread[nThreads];
            Thread[] dumpThread = new Thread[nThreads];
            CountTokens[] runners = new CountTokens[nThreads];
            DumpPartials[] dumpRunners = new DumpPartials[nThreads];
            for (int t = 0; t < nThreads; t++) {
                int start = chunkSize * t;
                int end = start + chunkSize;
                if (end > inputFiles.size()) end = inputFiles.size();
                runners[t] = new CountTokens(inputFiles, start, end, end-start, 
                        t, conf, fs);
                threads[t] = new Thread(runners[t]);
                threads[t].start();

                dumpRunners[t] = new DumpPartials(t, threads[t], runners[t],
                        conf, fs, countsFolder);
                dumpThread[t] = new Thread(dumpRunners[t]);
                dumpThread[t].start();
            }


            try {
                for (int t = 0; t < nThreads; t++) {
                    dumpThread[t].join();
                    System.out.println("Done dumping results from thread "+t);
                }
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Done dumping partial token counts");
            return;
        }

        if (!new File(countsFolder+"/all-counts").exists()) {
            final HashMap<Integer, MutableLong> accum = new HashMap<Integer, MutableLong>();
            try {
                final IntWritable inputKey = new IntWritable();
                final LongWritable inputVal = new LongWritable();
                for (int t = 0 ; t < nThreads; t++) {
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
                final Iterator<Map.Entry<Integer, MutableLong>> it = accum.entrySet().iterator();
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

        System.out.println("Starting pruning");
        Thread[] threads = new Thread[nThreads];
        PruneVectors[] runners = new PruneVectors[nThreads];
        for (int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = start + chunkSize;
            if (end > inputFiles.size()) end = inputFiles.size();

            runners[t] = new PruneVectors(inputFiles, start, end, t, conf, fs,
                    sortedTokens, targetLength, newDir);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class CountTokens implements Runnable {
        final private List<File> inputFiles;
        final private int start, end, length;
        final private int tid;
        final private Configuration conf;
        final private FileSystem fs;
        final private HashMap<Integer, MutableLong> tokenCounts;

        public HashMap<Integer, MutableLong> tokenCounts() {
            return this.tokenCounts;
        }

        public CountTokens(List<File> inputFiles, int start, int end, int length,
                int tid, Configuration conf, FileSystem fs) {
            this.inputFiles = inputFiles;
            this.start = start;
            this.end = end;
            this.length = length;
            this.tid = tid;
            this.conf = conf;
            this.fs = fs;
            this.tokenCounts = new HashMap<Integer, MutableLong>();
        }

        @Override
        public void run() {
            final Text key = new Text();
            final org.apache.mahout.math.VectorWritable val =
                new org.apache.mahout.math.VectorWritable();

            for (int i = start; i < end; i++) {
                File f = this.inputFiles.get(i);
                SequenceFile.Reader reader;
                try {
                    reader = new SequenceFile.Reader(fs, new Path(f.getAbsolutePath()),
                            conf);
                    while (reader.next(key, val)) {
                        final org.apache.mahout.math.Vector vec = val.get();
                        Iterator<org.apache.mahout.math.Vector.Element> iter = 
                            vec.nonZeroes().iterator();
                        // System.out.println("Thread "+tid+" processing vector of length "+vec.getNumNonZeroElements()+" from file "+f.getAbsolutePath());
                        while(iter.hasNext()) {
                            org.apache.mahout.math.Vector.Element ele = iter.next();
                            if (!this.tokenCounts.containsKey(ele.index())) {
                                tokenCounts.put(ele.index(), new MutableLong());
                            }
                            tokenCounts.get(ele.index()).incr();
                        }
                    }
                    int offset = i - start;
                    if (((offset + 1) % 1) == 0) {
                        System.out.println("Thread "+tid+
                                " done counting tokens for "+(offset+1)+"/"+
                                this.length+" files: "+f.getAbsolutePath());
                    }

                    reader.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
            }
        }
    }

    public static class PruneVectors implements Runnable {
        private final List<File> files;
        private final int start, end, length, tid;
        private final Configuration conf;
        private final FileSystem fs;
        private final TreeSet<TokenCount> sortedTokens;
        private final String outputFolder;
        private final int targetLength;

        public PruneVectors(List<File> files, int start, int end, int tid,
                Configuration conf, FileSystem fs, TreeSet<TokenCount> sortedTokens,
                int targetLength, String outputFolder) {
            this.files = files;
            this.start = start; this.end = end; this.length = this.end-this.start;
            this.tid = tid;
            this.conf = conf;
            this.fs = fs;
            this.sortedTokens = sortedTokens;
            this.outputFolder = outputFolder;
            this.targetLength = targetLength;
        }

        private int getNewVectorSize(final org.apache.mahout.math.Vector vec) {
            if (vec.getNumNonZeroElements() < this.targetLength) {
                return vec.getNumNonZeroElements();
            } else {
                return this.targetLength;
            }
        }

        @Override
        public void run() {
            int i = start;
            while (i < end) {
                File f = files.get(i);
                if (!f.exists()) break;
                i++;
            }
            i--;
            for (; i < end; i++) {
                File f = files.get(i);
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
                        int length = getNewVectorSize(originalVec);
                        if (length == this.targetLength) {
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
                        } else {
                            writer.append(key, val);
                        }
                    }

                    reader.close();
                    writer.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }

                int offset = i - start + 1;
                if (((offset) % 1) == 0) {
                    System.out.println("Thread "+tid+" processed "+offset+"/"+this.length);
                }
            }
        }
    }

    public static class DumpPartials implements Runnable {
        private final Thread toWaitOn;
        private final CountTokens toGetFrom;
        private final int tid;
        private final Configuration conf;
        private final FileSystem fs;
        private final String countsFolder;

        public DumpPartials(int tid, Thread t, CountTokens r, Configuration conf,
                FileSystem fs, String countsFolder) {
            this.tid = tid;
            this.toWaitOn = t;
            this.toGetFrom = r;
            this.conf = conf;
            this.fs = fs;
            this.countsFolder = countsFolder;
        }

        @Override
        public void run() {
            try {
                toWaitOn.join();
            } catch(InterruptedException ie) {
                throw new RuntimeException(ie);
            }
            final IntWritable outputKey = new IntWritable();
            final LongWritable outputVal = new LongWritable();

            System.out.println("Dumping results from thread "+tid);
            HashMap<Integer, MutableLong> partials = toGetFrom.tokenCounts();
            Iterator<Map.Entry<Integer, MutableLong>> it = partials.entrySet().iterator();
            try {
                SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                        new Path(countsFolder+"/counts-"+this.tid),
                        org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.LongWritable.class);
                while (it.hasNext()) {
                    Map.Entry<Integer, MutableLong> pair = it.next();
                    outputKey.set(pair.getKey().intValue());
                    outputVal.set(pair.getValue().get());
                    writer.append(outputKey, outputVal);
                }
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
    }

    public static class MutableLong {
        private long val;
        public MutableLong() {
            this.val = 0;
        }
        public MutableLong(long set) {
            this.val = set;
        }
        public void incr() {
            this.val++;
        }
        public long get() {
            return this.val;
        }
        public void add(MutableLong other) {
            this.val += other.val;
        }
        public void add(long l) {
            this.val += l;
        }
    }

    public static class TokenCount implements Comparable<TokenCount> {
        private final int token;
        private final long count;
        public TokenCount(int token, long count) {
            this.token = token; this.count = count;
        }
        public int token() { return this.token; }
        public long count() { return this.count; }
        @Override
        public int compareTo(TokenCount other) {
            if (this.count < other.count) {
                return -1;
            } else if(this.count > other.count) {
                return 1;
            } else {
                if (this.token < other.token) {
                    return -1;
                } else if (this.token > other.token) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TokenCount) {
                TokenCount other = (TokenCount)obj;
                return this.token == other.token && this.count == other.count;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return this.token;
        }

    }
}

