package pipeline;

/*
 * Remove all zero length vectors from a collection of sequence files
 */

import java.util.concurrent.ConcurrentHashMap;
import common.ParallelFileIterator;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
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
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector;
import java.util.Map;

public class ClassifyPoints {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java ClassifyPoints points-folder clusters-folder output-folder");
            return;
        }

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = 12;
        ConcurrentHashMap<Integer, ClusterWritable> clusters =
            new ConcurrentHashMap<Integer, ClusterWritable>();
        ClusterReader[] clusterReaders = new ClusterReader[nThreads];
        for (int t = 0; t < clusterReaders.length; t++) {
            clusterReaders[t] = new ClusterReader(clusters);
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(args[1]),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                ClusterWritable.class);
        executor.run(clusterReaders);

        ClassifyRunner[] runners = new ClassifyRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new ClassifyRunner(clusters, args[2]);
        }
        executor = new ParallelFileIterator(new File(args[0]),
                conf, fs, org.apache.hadoop.io.Text.class,
                VectorWritable.class);
        executor.run(runners);
    }

    public static class ClassifyRunner extends ParallelFileIterator.ParallelFileRunner {
        private final ConcurrentHashMap<Integer, ClusterWritable> clusters;
        private final String outputFolder;
        public ClassifyRunner(ConcurrentHashMap<Integer, ClusterWritable> c,
                String outputFolder) {
            this.clusters = c;
            this.outputFolder = outputFolder;
        }

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            final SequenceFile.Writer writer;
            try {
                writer = SequenceFile.createWriter(fs, conf,
                        new Path(outputFolder+"/"+inputPath.getName()),
                        org.apache.hadoop.io.IntWritable.class,
                        VectorWritable.class);
                reader = new SequenceFile.Reader(fs, inputPath, conf);
                final Text key = new Text();
                final IntWritable outKey = new IntWritable();
                final VectorWritable point = new VectorWritable();
                while (reader.next(key, point)) {
                    double minDist = -1.0;
                    int minCluster = -1;
                    for (Map.Entry<Integer, ClusterWritable> c : clusters.entrySet()) {
                        final Vector diff = c.getValue().getValue().getCenter().minus(point.get());
                        double dist = 0.0;
                        for (Vector.Element e : diff.nonZeroes()) {
                            dist += (e.get() * e.get());
                        }
                        if (minCluster == -1 || dist < minDist) {
                            minDist = dist;
                            minCluster = c.getKey().intValue();
                        }
                    }
                    outKey.set(minCluster);
                    writer.append(outKey, point);
                }
                reader.close();
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            print("DONE");
        }
        protected void finish() { }
    }

    public static class ClusterReader extends ParallelFileIterator.ParallelFileRunner {
        private final ConcurrentHashMap<Integer, ClusterWritable> clusters;
        public ClusterReader(ConcurrentHashMap<Integer, ClusterWritable> c) {
            this.clusters = c;
        }
        
        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
                final IntWritable key = new IntWritable();
                ClusterWritable cluster = new ClusterWritable();
                while (reader.next(key, cluster)) {
                    clusters.put(key.get(), cluster);
                    cluster = new ClusterWritable();
                }
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }
}
