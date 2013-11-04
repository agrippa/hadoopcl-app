package checks;

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

public class NumUniqueTokens {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: java NumUniqueTokens folder counts? [dump-file]");
            return;
        }

        if (!args[1].equals("true") && !args[1].equals("false")) {
            System.out.println("counts? must be 'true' or 'false'");
            return;
        }

        String input = args[0];
        boolean getCounts = Boolean.parseBoolean(args[1]);
        if (getCounts && args.length < 3) {
            System.out.println("Must include a dump file when getting counts");
            return;
        }

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nChunks = ParallelFileIterator.nCores;
        ParallelFileIterator executor = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.SparseVectorWritable.class);

        if (getCounts) {
            String output = args[2];
            GatherUniquesWithCounts[] runners = new GatherUniquesWithCounts[nChunks];
            for (int t = 0; t < runners.length; t++) {
                runners[t] = new GatherUniquesWithCounts();
            }
            executor.run(runners);

            HashMap<Integer, MutableLong> global = runners[0].uniqueTokens();
            for (int i = 1; i < runners.length; i++) { 
                HashMap<Integer, MutableLong> tmp = runners[i].uniqueTokens();
                for (Map.Entry<Integer, MutableLong> entry : tmp.entrySet()) {
                    int token = entry.getKey();
                    MutableLong count = entry.getValue();
                    if (global.containsKey(token)) {
                        global.get(token).add(count.get());
                    } else {
                        global.put(token, count);
                    }
                }
            }

            try {
                SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                        new Path(output), org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.LongWritable.class);
                final IntWritable key = new IntWritable();
                final LongWritable val = new LongWritable();

                System.out.println(global.size()+" unique tokens in "+input);
                for (Map.Entry<Integer, MutableLong> entry : global.entrySet()) {
                    key.set(entry.getKey());
                    val.set(entry.getValue().get());
                    writer.append(key, val);
                    System.out.println("  "+entry.getKey()+" : "+entry.getValue().get());
                }

                writer.close();
            } catch(Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            GatherUniques[] runners = new GatherUniques[nChunks];
            for (int t = 0; t < runners.length; t++) {
                runners[t] = new GatherUniques();
            }
            executor.run(runners);

            HashSet<Integer> global = runners[0].uniqueTokens();
            for (int i = 1; i < runners.length; i++) { 
                HashSet<Integer> tmp = runners[i].uniqueTokens();
                for (Integer token : tmp) {
                    global.add(token);
                }
            }
            System.out.println(global.size()+" unique tokens in "+input);
        }
    }

    public static class GatherUniquesWithCounts extends
            ParallelFileIterator.ParallelFileRunner {

        HashMap<Integer, MutableLong> uniqueTokens = new HashMap<Integer, MutableLong>();
        public HashMap<Integer, MutableLong> uniqueTokens() { return this.uniqueTokens; }
        public int nUniqueTokens() { return this.uniqueTokens.size(); }

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.SparseVectorWritable val =
                new org.apache.hadoop.io.SparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    for (int i = 0; i < val.size(); i++) {
                        int ind = val.indices()[i];
                        if (uniqueTokens.containsKey(ind)) {
                            uniqueTokens.get(ind).incr();
                        } else {
                            MutableLong count = new MutableLong(1);
                            uniqueTokens.put(ind, count);
                        }
                    }
                }
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }

    public static class GatherUniques extends
            ParallelFileIterator.ParallelFileRunner {

        HashSet<Integer> uniqueTokens = new HashSet<Integer>();
        public HashSet<Integer> uniqueTokens() { return this.uniqueTokens; }
        public int nUniqueTokens() { return this.uniqueTokens.size(); }

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.SparseVectorWritable val =
                new org.apache.hadoop.io.SparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    for (int i = 0; i < val.size(); i++) {
                        uniqueTokens.add(val.indices()[i]);
                    }

                }
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }
}
