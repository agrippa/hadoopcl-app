package pipeline;


import common.ParallelFileIterator;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.mahout.common.StringTuple;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.atomic.*;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.math.VectorWritable;

public class BSparseToVector {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("java BSparseToVector input-path output-path cardinality");
            return;
        }
        String input = args[0];
        String output = args[1];
        int cardinality = Integer.parseInt(args[2]);

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = 12;
        Runner[] runners = new Runner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new Runner(output, cardinality);
        }
        ParallelFileIterator exec = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.BSparseVectorWritable.class);
        exec.run(runners);
    }

    public static class Runner extends ParallelFileIterator.ParallelFileRunner {
        private final String outFolder;
        private final int cardinality;
        public Runner(String outFolder, int cardinality) {
            this.outFolder = outFolder;
            this.cardinality = cardinality;
        }
        protected void callback(File curr) {
            final Path inputPath = new Path(curr.getAbsolutePath());
            final SequenceFile.Writer writer;
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
                writer = SequenceFile.createWriter(fs, conf,
                        new Path(outFolder+"/"+inputPath.getName()),
                        org.apache.hadoop.io.Text.class,
                        VectorWritable.class);
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
            final IntWritable inKey = new IntWritable();
            final Text outKey = new Text();
            final VectorWritable outVal = new VectorWritable();
            final org.apache.hadoop.io.BSparseVectorWritable inVal =
                new org.apache.hadoop.io.BSparseVectorWritable();
            try {
                while (reader.next(inKey, inVal)) {
                    outKey.set(Integer.toString(inKey.get()));
                    RandomAccessSparseVector vec = new RandomAccessSparseVector(cardinality);
                    for (int i = 0; i < inVal.size(); i++) {
                        vec.set(inVal.indices()[i], inVal.vals()[i]);
                    }
                    outVal.set(vec);
                    writer.append(outKey, outVal);
                }
                reader.close();
                writer.close();
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }
}
