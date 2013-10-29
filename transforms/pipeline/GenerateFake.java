package pipeline;

import org.apache.mahout.math.RandomAccessSparseVector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import common.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;

public class GenerateFake {

    private static int[] weightedTokenArray(String inputFile) {
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final HashMap<Integer, Long> tokenCounts = new HashMap<Integer, Long>();
        int totalCount = 0;
        try {
            final SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(inputFile),
                    conf);
            final IntWritable key = new IntWritable();
            final LongWritable val = new LongWritable();
            while (reader.next(key, val)) {
                totalCount += val.get();
                tokenCounts.put(key.get(), val.get());
            }
            reader.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        int sofar = 0;
        int[] weighted = new int[totalCount];
        for (Map.Entry<Integer, Long> entry : tokenCounts.entrySet()) {
            for (int i = sofar; i < sofar + entry.getValue().longValue(); i++) {
                weighted[i] = entry.getKey().intValue();
            }
            sofar += entry.getValue().longValue();
        }
        return weighted;
    }

    private static String fileName(int fileid, int targetLength) {
        String fileid_str = Integer.toString(fileid);
        while (fileid_str.length() < targetLength) {
            fileid_str = "0" + fileid_str;
        }
        return fileid_str;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("usage: java GenerateFake token-counts-file output-folder output-vector-length num-output-vectors num-output-files");
            return;
        }
        int[] weightedArr = weightedTokenArray(args[0]);
        String outputFolder = args[1];
        final int outputVectorLength = Integer.parseInt(args[2]);
        final int numOutputVectors = Integer.parseInt(args[3]);
        final int numOutputFiles = Integer.parseInt(args[4]);
        final int vectorsPerFile = (numOutputVectors + numOutputFiles - 1) /
            numOutputFiles;

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        for (int i = 0; i < numOutputFiles; i++) {
            File f = new File(outputFolder+"/part-"+fileName(i, 6));
            f.createNewFile();
        }
        int nThreads = ParallelFileIterator.nCores;
        int[] threadSeeds = new int[nThreads];
        Random seedRand = new Random(923842);
        for (int i = 0; i < threadSeeds.length; i++) {
            threadSeeds[i] = seedRand.nextInt();
        }

        AtomicInteger fileid = new AtomicInteger(0);

        OutputGenerator[] runners = new OutputGenerator[nThreads];
        for (int i = 0; i < runners.length; i++) {
            runners[i] = new OutputGenerator(threadSeeds[i], fileid, vectorsPerFile, outputVectorLength, weightedArr);
        }
        ParallelFileIterator executor = new
            ParallelFileIterator(new File(outputFolder).listFiles(), conf, fs);
        executor.run(runners);
    }

    public static class OutputGenerator extends
            ParallelFileIterator.ParallelFileRunner {
        private final Random rand;
        private final AtomicInteger fileIdCounter;
        private final int nVectorsPerFile;
        private final int vectorLength;
        private final int[] weightedArr;

        public OutputGenerator(int seed, AtomicInteger fileIdCounter,
                int nVectorsPerFile, int vectorLength, int[] weightedArr) {
            this.rand = new Random(seed);
            this.fileIdCounter = fileIdCounter;
            this.nVectorsPerFile = nVectorsPerFile;
            this.vectorLength = vectorLength;
            this.weightedArr = weightedArr;
        }

        protected void callback(File currentFile) {
            try {
                SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                        new Path(currentFile.getAbsolutePath()),
                        org.apache.hadoop.io.Text.class,
                        org.apache.mahout.math.VectorWritable.class);
                final Text key = new Text();
                final org.apache.mahout.math.VectorWritable val = new
                    org.apache.mahout.math.VectorWritable();

                for (int i = 0; i < nVectorsPerFile; i++) {
                    HashSet<Integer> addIndices = new HashSet<Integer>();
                    final org.apache.mahout.math.Vector newVec =
                        new RandomAccessSparseVector(124993853, this.vectorLength);
                    while (addIndices.size() < vectorLength) {
                        int newIndex = this.weightedArr[rand.nextInt(this.weightedArr.length)];
                        addIndices.add(newIndex);
                        newVec.set(newIndex, rand.nextDouble() * 1000.0);
                    }
                    key.set(Integer.toString(fileIdCounter.getAndIncrement()));
                    val.set(newVec);
                    writer.append(key, val);
                }
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }

        protected void finish() { } 
    }
}
