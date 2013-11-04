package checks;

import java.io.IOException;
import common.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;

public class QuickCheckDuplicates {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java QuickCheckDuplicates folder");
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

        HashSet<SparseVectorWritable> merged = null;
        int index = 0;
        for (DuplicateRunner r : runners) {
            System.out.println("Merging in "+index);
            if (merged == null) {
                merged = r.counts();
            } else {
                for (SparseVectorWritable val : r.counts()) {
                    if (merged.contains(val)) {
                        System.out.println("Duplicate vector "+val.toString()+" detected");
                    } else {
                        merged.add(val.cloneSparse());
                    }
                }
            }
            System.out.println("Done merging in "+index);
            index++;
        }
    }

    public static class DuplicateRunner extends ParallelFileIterator.ParallelFileRunner {
        private HashSet<SparseVectorWritable> counts = new HashSet<SparseVectorWritable>();

        public HashSet<SparseVectorWritable> counts() {
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
                    if (counts.contains(val)) {
                        System.out.println("Duplicate vector "+val.toString()+" detected");
                    }
                    counts.add(val.cloneSparse());
                }
                reader.close();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            this.print("DONE");
        }

        protected void finish() { }
    }
}
