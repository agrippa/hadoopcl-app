package pipeline;

import java.util.Iterator;
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

public class ConvertPairwiseSimilarity {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java ConvertPairwiseSimilarity input-folder output-folder");
            return;
        }

        String input = args[0];
        String output = args[1];

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        int nThreads = ParallelFileIterator.nCores;
        PairwiseSimilarityRunner[] runners =
            new PairwiseSimilarityRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new PairwiseSimilarityRunner(output);
        }
        ParallelFileIterator executor = new ParallelFileIterator(
                new File(input), conf, fs,
                org.apache.hadoop.io.IntWritable.class,
                org.apache.mahout.math.VectorWritable.class);
        executor.run(runners);
    }

    public static class PairwiseSimilarityRunner extends
            ParallelFileIterator.ParallelFileRunner {
        private final String outputFolder;

        public PairwiseSimilarityRunner(String outputFolder) {
            this.outputFolder = outputFolder;
        }

        protected void callback(File currentFile) {
            try {
                SequenceFile.Reader reader = new SequenceFile.Reader(fs,
                        new Path(currentFile.getAbsolutePath()), conf);
                SequenceFile.Writer writer = SequenceFile.createWriter(
                        fs, conf,
                        new Path(this.outputFolder+"/"+currentFile.getName()),
                        org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.FSparseVectorWritable.class);
                final IntWritable key = new IntWritable();
                final org.apache.mahout.math.VectorWritable val = 
                    new org.apache.mahout.math.VectorWritable();
                final FSparseVectorWritable outputVal =
                    new FSparseVectorWritable();

                while (reader.next(key, val)) {
                    final org.apache.mahout.math.Vector originalVec = val.get();
                    int N = originalVec.getNumNonZeroElements();

                    int[] indices = new int[N];
                    float[] vals = new float[N];
                    int index = 0;
                    Iterator<org.apache.mahout.math.Vector.Element> iter =
                        originalVec.nonZeroes().iterator();
                    while (iter.hasNext()) {
                        org.apache.mahout.math.Vector.Element ele = iter.next();
                        indices[index] = ele.index();
                        vals[index] = (float)ele.get();
                        index++;
                    }

                    outputVal.set(indices, vals);
                    writer.append(key, outputVal);
                }

                reader.close();
                writer.close();

            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        protected void finish() { }
    }
}
