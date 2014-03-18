package pipeline;

/*
 * Remove all zero length vectors from a collection of sequence files
 */

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

public class SelectRandomSeeds {
    private static ClusterWritable sparseToCluster(BSparseVectorWritable sparse,
            int id) {
        int[] indices = sparse.indices();
        double[] vals = sparse.vals();
        int length = sparse.size();

        /*
         * If this isn't the correct cardinality for your data set, Mahout 
         * will throw exceptions which actually tell you the correct cardinality.
         * So just run a simple Mahout test on some clusters generated with this
         * script to generate the error messages and get the correct value to
         * insert here
         *
         * wiki = 149053452
         * asf = 124993853
         */
        RandomAccessSparseVector center = new RandomAccessSparseVector(124993853);
        for (int i = 0; i < length; i++) {
            center.setQuick(indices[i], vals[i]);
        }
        Kluster cluster = new Kluster(center, id, new CosineDistanceMeasure());

        return new ClusterWritable(cluster);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("java SelectRandomSeeds input-folder output-file n-seeds");
            return;
        }

        String input = args[0];
        String output = args[1];
        int nSeeds = Integer.parseInt(args[2]);
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = 12;
        CountRunner[] countRunners = new CountRunner[nThreads];
        for (int t = 0; t < countRunners.length; t++) {
            countRunners[t] = new CountRunner();
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.BSparseVectorWritable.class);
        executor.run(countRunners);

        int nPairs = 0;
        for (CountRunner r : countRunners) {
            nPairs += r.count();
        }
        System.out.println("Counted "+nPairs+" total pairs");

        HashSet<Integer> selections = new HashSet<Integer>();
        Random rand = new Random(923842);
        while (selections.size() < nSeeds) {
            selections.add(rand.nextInt(nPairs));
        }

        int pre = 0;
        FindRunner[] findRunners = new FindRunner[nThreads];
        for (int t = 0; t < findRunners.length; t++) {
            findRunners[t] = new FindRunner(selections, nSeeds, pre);
            pre += countRunners[t].count();
        }
        executor = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.BSparseVectorWritable.class);
        executor.run(findRunners);

        List<BSparseVectorWritable> seeds = new LinkedList<BSparseVectorWritable>();
        for (FindRunner r : findRunners) {
            seeds.addAll(r.selected());
        }

        Path outputPath = new Path(output+"-sparse");
        SequenceFile.Writer writer;
        try {
            writer = SequenceFile.createWriter(fs, conf, outputPath,
                    org.apache.hadoop.io.IntWritable.class,
                    org.apache.hadoop.io.BSparseVectorWritable.class);
            for (int i = 0; i < seeds.size(); i++) {
                writer.append(new IntWritable(i), seeds.get(i));
            }
            writer.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        outputPath = new Path(output+"-cluster");
        try {
            writer = SequenceFile.createWriter(fs, conf, outputPath,
                    org.apache.hadoop.io.Text.class,
                    org.apache.mahout.clustering.iterator.ClusterWritable.class);
            for (int i = 0; i < seeds.size(); i++) {
                writer.append(new Text(Integer.toString(i)), sparseToCluster(seeds.get(i), i));
            }
            writer.close();

        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static class CountRunner extends
            ParallelFileIterator.ParallelFileRunner {
        private int count = 0;
        public int count() { return this.count; }

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.BSparseVectorWritable val =
                new org.apache.hadoop.io.BSparseVectorWritable();

            try {
                while(reader.next(key, val)) {
                    count++;
                }
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }

        protected void finish() { }
    }

    public static class FindRunner extends
            ParallelFileIterator.ParallelFileRunner {
        private final HashSet<Integer> selections;
        private final List<BSparseVectorWritable> selected;
        private int vectorIndex;

        public FindRunner(HashSet<Integer> selections, int nSeeds,
                int pre) {
            this.selections = selections;
            this.selected = new ArrayList<BSparseVectorWritable>(nSeeds);
            this.vectorIndex = pre;
        }

        public List<BSparseVectorWritable> selected() { return this.selected; }

        protected void callback(File current) {
            final Path inputPath = new Path(current.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.BSparseVectorWritable val =
                new org.apache.hadoop.io.BSparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    if (selections.contains(vectorIndex)) {
                        selected.add(val.cloneSparse());
                    }
                    vectorIndex++;
                }
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }

        protected void finish() { }
    }
}
