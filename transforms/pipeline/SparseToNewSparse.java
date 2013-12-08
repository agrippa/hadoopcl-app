package pipeline;

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

public class SparseToNewSparse {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("java SparseToNewSparse input-folder output-folder");
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


    int nThreads = 4;
    SparseRunner[] runners = new SparseRunner[nThreads];
    for (int t = 0; t < runners.length; t++) {
      runners[t] = new SparseRunner(output);
    }
    ParallelFileIterator executor = new ParallelFileIterator(new File(input),
        conf, fs, org.apache.hadoop.io.IntWritable.class,
        org.apache.hadoop.io.SparseVectorWritable.class);
    executor.run(runners);

  }

  public static class SparseRunner extends
    ParallelFileIterator.ParallelFileRunner {
      private final String outputFolder;

      public SparseRunner(String outputFolder) {
        this.outputFolder = outputFolder;
      }

      private NewSparseVectorWritable convert(SparseVectorWritable vec) {
        return new NewSparseVectorWritable(vec);
      }

      protected void callback(File currentFile) {
        final Path inputPath = new Path(currentFile.getAbsolutePath());
        final Path outputPath = new Path(outputFolder+"/"+currentFile.getName());

        final SequenceFile.Reader reader;
        final SequenceFile.Writer writer;
        try {
          reader = new SequenceFile.Reader(fs, inputPath, conf);
          writer = SequenceFile.createWriter(fs, conf, outputPath,
              org.apache.hadoop.io.IntWritable.class,
              org.apache.hadoop.io.NewSparseVectorWritable.class);
        } catch(IOException io) {
          throw new RuntimeException(io);
        }

        final IntWritable key = new IntWritable();
        final org.apache.hadoop.io.SparseVectorWritable val =
            new org.apache.hadoop.io.SparseVectorWritable();

        try {
          while (reader.next(key, val)) {
            final NewSparseVectorWritable outputVal = convert(val);
            writer.append(key, outputVal);
          }
          reader.close();
          writer.close();
        } catch(IOException io) {
          throw new RuntimeException(io);
        }
      }

      protected void finish() { }
  }
}
