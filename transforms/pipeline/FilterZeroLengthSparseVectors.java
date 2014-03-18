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

public class FilterZeroLengthSparseVectors {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java FilterZeroLengthSparseVectors folder");
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

        int nThreads = 12;
        FilterRunner[] runners = new FilterRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new FilterRunner();
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(input),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.BSparseVectorWritable.class);
        executor.run(runners);
    }

    public static class FilterRunner extends
            ParallelFileIterator.ParallelFileRunner {

        protected void finish() { }
        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final Path outputPath = new Path(currentFile.getAbsolutePath()+".copy");
            final SequenceFile.Reader reader;
            final SequenceFile.Writer writer;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            try {
                writer = SequenceFile.createWriter(fs, conf, outputPath, 
                        org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.BSparseVectorWritable.class);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.BSparseVectorWritable val =
                new org.apache.hadoop.io.BSparseVectorWritable();

            try {
                while(reader.next(key, val)) {
                    if (val.size() > 0) {
                        writer.append(key, val);
                    }
                }

                writer.close();
                reader.close();

                new File(outputPath.toString()).renameTo(new File(inputPath.toString()));

            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            this.print("Done");
        }
    }
}
