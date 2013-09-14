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

public class MyPruner {

    static private String getFileNameOnly(File f) {
        throw new RuntimeException("Need to implement getFileNameOnly");
    }

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
            System.out.println("usage: java MyPruner existing-pruned-dir my-pruned-dir counts-file percent");
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
        String countsFile = args[2];
        
        File existingFolder = new File(existing);
        File[] allFiles = existingFolder.listFiles();
        double perc = Double.parseDouble(args[3]) * 0.01;

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

        if (new File(countsFile).exists()) {
            System.out.println("Using pre-existing tokens file "+countsFile);
            SequenceFile.Reader tokenCountReader;
            try {
                tokenCountReader = new SequenceFile.Reader(fs,
                        new Path(countsFile), conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            final IntWritable token = new IntWritable();
            final LongWritable count = new LongWritable();
            int countTokens = 0;
            try {
                while (tokenCountReader.next(token, count)) {
                    // allTokenCounts.put(token.get(), new MutableLong(count.get()));
                    sortedTokens.add(new TokenCount(token.get(), count.get()));
                    countTokens++;
                    if (((countTokens + 1) % 1000000) == 0) {
                        System.out.println("Done reading "+(countTokens+1)+" from "+countsFile);
                    }
                }
                tokenCountReader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            System.out.println("Done reading from tokens count file");
        } else {
            Thread[] threads = new Thread[nThreads];
            CountTokens[] runners = new CountTokens[nThreads];
            for (int t = 0; t < nThreads; t++) {
                int start = chunkSize * t;
                int end = start + chunkSize;
                if (end > inputFiles.size()) end = inputFiles.size();
                runners[t] = new CountTokens(inputFiles, start, end, end-start, 
                        t, conf, fs);
                threads[t] = new Thread(runners[t]);
                threads[t].start();
            }

            HashMap<Integer, MutableLong> allTokenCounts =
                new HashMap<Integer, MutableLong>();
            try {
                for (int t = 0; t < nThreads; t++) {
                    threads[t].join();
                    if (t == 0) {
                        System.out.println("Merging results...");
                    }
                    HashMap<Integer, MutableLong> partials = runners[t].tokenCounts();
                    for (Integer key : partials.keySet()) {
                        if (!allTokenCounts.containsKey(key)) {
                            allTokenCounts.put(key, new MutableLong());
                        }
                        allTokenCounts.get(key).add(partials.get(key));
                    }
                }
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Done merging, "+allTokenCounts.keySet().size()+
                    " total tokens");
            SequenceFile.Writer tokenCountWriter;
            try {
                tokenCountWriter = SequenceFile.createWriter(fs, conf,
                        new Path(countsFile), org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.LongWritable.class);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            System.out.println("Writing token counts to disk...");
            final IntWritable key = new IntWritable();
            final LongWritable val = new LongWritable();
            try {
                for (Integer token : allTokenCounts.keySet()) {
                    long count = allTokenCounts.get(token).get();
                    key.set(token);
                    val.set(count);
                    tokenCountWriter.append(key, val);
                    sortedTokens.add(new TokenCount(token.intValue(), count));
                }
                tokenCountWriter.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            System.out.println("Done writing token counts");
        }

        // At this point, we have a sorted set of tokens and their counts
        // int lowPercIndex = (int)(lowPerc * ((double)sortedTokens.size()));
        // int highPercIndex = (int)(highPerc * ((double)sortedTokens.size()));
        // System.out.println("Using tokens from index "+lowPercIndex+
        //         " to "+highPercIndex+", "+(highPercIndex-lowPercIndex)+" total");
        // Set<Integer> tokensUsed = new HashSet<Integer>(); // For fast lookup
        // int index = 0;
        // for (TokenCount tc : sortedTokens) {
        //     if (index >= lowPercIndex) {
        //         tokensUsed.add(tc.token());
        //     }
        //     if (index >= highPercIndex) {
        //         break;
        //     }
        //     index++;
        // }
        int nToUse = (int)(perc * ((double)sortedTokens.size()));
        System.out.println("Using "+nToUse+" out of "+sortedTokens.size());
        int index = 0;
        Set<Integer> tokensUsed = new HashSet<Integer>();
        Iterator<TokenCount> tcIter = sortedTokens.descendingIterator();
        while (tcIter.hasNext()) {
            TokenCount tc = tcIter.next();
            tokensUsed.add(tc.token());
            index++;
            if (index >= nToUse) break;
        }

        Thread[] threads = new Thread[nThreads];
        PruneVectors[] runners = new PruneVectors[nThreads];
        for (int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = start + chunkSize;
            if (end > inputFiles.size()) end = inputFiles.size();

            runners[t] = new PruneVectors(inputFiles, start, end, t, conf, fs,
                    tokensUsed, newDir);
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
                        while(iter.hasNext()) {
                            org.apache.mahout.math.Vector.Element ele = iter.next();
                            if (!this.tokenCounts.containsKey(ele.index())) {
                                tokenCounts.put(ele.index(), new MutableLong());
                            }
                            tokenCounts.get(ele.index()).incr();
                        }
                        int offset = i - start;
                        if (((offset + 1) % 100) == 0) {
                            System.out.println("Thread "+tid+
                                    " done counting tokens for "+(offset+1)+"/"+
                                    this.length+" files");
                        }
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
        private final Set<Integer> tokensUsed;
        private final String outputFolder;
        public PruneVectors(List<File> files, int start, int end, int tid,
                Configuration conf, FileSystem fs, Set<Integer> tokensUsed,
                String outputFolder) {
            this.files = files;
            this.start = start; this.end = end; this.length = this.end-this.start;
            this.tid = tid;
            this.conf = conf;
            this.fs = fs;
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
        public void run() {
            for (int i = start; i < end; i++) {
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
                        final org.apache.mahout.math.Vector newVec =
                            new RandomAccessSparseVector(originalVec.size(), 
                                    getNewVectorSize(originalVec));

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

                int offset = i - start + 1;
                if (((offset) % 1) == 0) {
                    System.out.println("Thread "+tid+" processed "+offset+"/"+this.length);
                }
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
                return 0;
            }
        }
    }
}

