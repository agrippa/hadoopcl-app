package pipeline;

import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import java.nio.charset.Charset;
import java.net.URI;
import org.apache.mahout.utils.io.ChunkedWriter;
import org.apache.hadoop.fs.Path;
import javax.xml.parsers.*;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.apache.hadoop.io.*;
import java.util.concurrent.*;
import org.apache.mahout.common.StringTuple;

public class MergeWordCount {
    public static void main(String[] args) {
        System.out.println(args.length);
        if(args.length < 2) {
            System.out.println("usage: java MergeWordCount output-file input-file-1 input-file-2 ...");
            return;
        }

        String outputFileName = args[0];

        File[] allFiles = new File[args.length - 1];
        for (int i = 0; i < allFiles.length; i++) {
            allFiles[i] = new File(args[1+i]);
        }

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final HashMap<String, MutableLong> counts = new HashMap<String, MutableLong>();

        for (int i = 0; i < allFiles.length; i++) {
            System.out.print("Aggregating from "+allFiles[i].toString());
            File curr = allFiles[i];
            final Path inputPath = new Path(curr.getAbsolutePath());
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                continue;
            }
            final Text key = new Text();
            final LongWritable val = new LongWritable();
            try {
                while(reader.next(key, val)) {
                    final String str = key.toString();
                    if(counts.containsKey(str)) {
                        counts.get(str).incr(val.get());
                    } else {
                        counts.put(str, new MutableLong(val.get()));
                    }
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            System.out.println(" DONE");
        }

        final Path outputFilePath = new Path(outputFileName);
        SequenceFile.Writer writer;
        try {
                writer = SequenceFile.createWriter(fs, conf, 
                    outputFilePath,
                    org.apache.hadoop.io.Text.class,
                    org.apache.hadoop.io.LongWritable.class);
            final Text outKey = new Text();
            final LongWritable outVal = new LongWritable();
            for (final Map.Entry<String, MutableLong> e : counts.entrySet()) {
                outKey.set(e.getKey());
                outVal.set(e.getValue().get());
                writer.append(outKey, outVal);
            }
            writer.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static class MutableLong {
        private long val;
        public MutableLong(long setVal) {
            this.val = setVal;
        }
        public void incr(long i) {
            this.val += i;
        }
        public void incr() {
            val++;
        }
        public long get() {
            return val;
        }
    }
}
