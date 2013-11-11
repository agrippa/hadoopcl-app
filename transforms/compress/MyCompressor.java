package compress;

import common.*;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;


public abstract class MyCompressor {
    public abstract Class<? extends Writable>  getKeyClass();
    public abstract Class<? extends Writable> getValueClass();

    public void run(CompressionCodec codec, String inputFolder, String outputFolder) {
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = ParallelFileIterator.nCores;
        CompressionRunner[] runners = new CompressionRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new CompressionRunner(outputFolder, codec, getKeyClass(), getValueClass());
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(inputFolder),
                conf, fs, getKeyClass(), getValueClass());
        executor.run(runners);
    }

    public static class CompressionRunner extends ParallelFileIterator.ParallelFileRunner {
        private final String outputPath;
        private final CompressionCodec codec;
        private final Class<? extends Writable> keyClass;
        private final Class<? extends Writable> valClass;

        public CompressionRunner(String output, CompressionCodec codec,
                Class<? extends Writable> keyClass,
                Class<? extends Writable> valClass) {
            this.outputPath = output;
            this.codec = codec;
            this.keyClass = keyClass;
            this.valClass = valClass;
        }

        protected void callback(File f) {
            final SequenceFile.Reader reader;
            final SequenceFile.Writer writer;
            try {
                final Path inputPath = new Path(f.getAbsolutePath());
                reader = new SequenceFile.Reader(fs, inputPath, conf);
                writer = SequenceFile.createWriter(fs, conf,
                        new Path(this.outputPath+"/"+inputPath.getName()),
                        keyClass, valClass,
                        SequenceFile.CompressionType.BLOCK, codec);

                final Writable key = keyClass.newInstance();
                final Writable val = valClass.newInstance();
                while (reader.next(key, val)) {
                    writer.append(key, val);
                }
                reader.close();
                writer.close();
            } catch(Exception io) {
                throw new RuntimeException(io);
            }
        }
        protected void finish() { }
    }
}
