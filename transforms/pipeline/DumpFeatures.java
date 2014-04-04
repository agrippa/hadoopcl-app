package pipeline;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import common.ParallelFileIterator;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;
import org.apache.mahout.common.StringTuple;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.atomic.*;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.clustering.kmeans.Kluster;

public class DumpFeatures {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            System.err.println("usage: java DumpFeatures input-folder output-file");
            return;
        }

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final ParallelFileIterator executor = new ParallelFileIterator(new File(args[0]),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.BSparseVectorWritable.class);

        int nThreads = 12;
        DumpRunner[] runners = new DumpRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new DumpRunner();
        }
        executor.run(runners);

        TreeSet<Integer> all = null;
        for (int t = 0; t < runners.length; t++) {
            if (all == null) {
                all = runners[t].features;
            } else {
                all.addAll(runners[t].features);
            }
        }

        int[] serialized = new int[all.size()];
        int index = 0;
        for (Integer i : all) {
            serialized[index++] = i.intValue();
        }

        ByteBuffer bb = ByteBuffer.allocate(all.size() * 4);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(serialized);

        DataOutputStream os = new DataOutputStream(new FileOutputStream(
                    args[1]));
        os.write(bb.array());
        os.close();
    }

    public static class DumpRunner extends ParallelFileIterator.ParallelFileRunner {
        public TreeSet<Integer> features = new TreeSet<Integer>();

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch (IOException io) {
                throw new RuntimeException(io);
            }

            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.BSparseVectorWritable val =
                new org.apache.hadoop.io.BSparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    for (int i = 0; i < val.size(); i++) {
                        features.add(val.indices()[i]);
                    }
                }
                reader.close();
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }
}
