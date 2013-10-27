package checks;

import java.io.IOException;
import common.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;

public class CheckDuplicates {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java CheckDuplicates folder");
            return;
        }

        String input = args[0];
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nChunks = ParallelFileIterator.nCores * 3;
        DuplicateRunner[] runners = new DuplicateRunner[nChunks];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new DuplicateRunner();
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.SparseVectorWritable.class);
        executor.run(runners);

        HashMap<SparseVectorWritable, MutableInteger> merged = null;
        int index = 0;
        for (DuplicateRunner r : runners) {
            System.out.println("Merging in "+index);
            if (merged == null) {
                merged = r.counts();
            } else {
                for (Map.Entry<SparseVectorWritable, MutableInteger> item :
                        r.counts().entrySet()) {
                    SparseVectorWritable key = item.getKey();
                    MutableInteger val = item.getValue();
                    if (merged.containsKey(key)) {
                        merged.get(key).incr(val.get());
                    } else {
                        merged.put(key, val);
                    }
                }
            }
            System.out.println("Done merging in "+index);
            index++;
        }

        for (Map.Entry<SparseVectorWritable, MutableInteger> item : merged.entrySet()) {
            SparseVectorWritable key = item.getKey();
            MutableInteger val = item.getValue();

            System.out.println("Count = "+val.get()+", vector = "+key.toString());
            if (val.get() > 1) {
                System.out.println("  DUPLICATE!");
            }
        }
    }

    public static class DuplicateRunner extends ParallelFileIterator.ParallelFileRunner {
        private HashMap<SparseVectorWritable, MutableInteger> counts =
            new HashMap<SparseVectorWritable, MutableInteger>();

        public HashMap<SparseVectorWritable, MutableInteger> counts() {
            return this.counts;
        }

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
                int count = 0;
                while(reader.next(key, val)) {
                    if (counts.containsKey(val)) {
                        counts.get(val).incr(1);
                    } else {
                        SparseVectorWritable clone = val.cloneSparse();
                        counts.put(clone, new MutableInteger(1));
                    }
                }
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            this.print("DONE");
        }

        protected void finish() { }
    }

    static class MutableInteger {
        private int val;
        public MutableInteger(int val) {
            this.val = val;
        }
        public int get() { return this.val; }
        public void incr(int s) { this.val += s; }
    }
}
