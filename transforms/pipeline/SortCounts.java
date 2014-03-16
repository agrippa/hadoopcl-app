package pipeline;

import java.util.TreeSet;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import java.io.*;

public class SortCounts {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java SortCounts file");
            return;
        }

        Path inputPath = new Path(args[0]);
        Path outputFilePath = new Path(args[0]+".sorted");

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        SequenceFile.Reader reader;
        TreeSet<TextLong> sorted = new TreeSet<TextLong>();
        try {
            reader = new SequenceFile.Reader(fs, inputPath, conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        final Text key = new Text();
        final LongWritable val = new LongWritable();
        try {
            while(reader.next(key, val)) {
                sorted.add(new TextLong(key.toString(), val.get()));
            }
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        SequenceFile.Writer writer;
        try {
                writer = SequenceFile.createWriter(fs, conf, 
                    outputFilePath,
                    org.apache.hadoop.io.Text.class,
                    org.apache.hadoop.io.LongWritable.class);
            final Text outKey = new Text();
            final LongWritable outVal = new LongWritable();

            for (TextLong t : sorted) {
                outKey.set(t.str);
                outVal.set(t.l);
                writer.append(outKey, outVal);
            }
            writer.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    static class TextLong implements Comparable<TextLong> {
        public final String str;
        public final long l;
        public TextLong(String str, long l) {
            this.str = str;
            this.l = l;
        }
        @Override
        public int compareTo(TextLong other) {
            return this.str.compareTo(other.str);
        }
    }
}
