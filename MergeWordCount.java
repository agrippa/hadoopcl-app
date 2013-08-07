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
        if(args.length != 1) {
            System.out.println("usage: java MergeWordCount dir");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[0];

        File folder = new File(inputDirName);
        int nInputFiles = 0;
        File[] allFiles = folder.listFiles();
        for(int i = 0; i < allFiles.length; i++) {
            String name = allFiles[i].getName();
            if(name.indexOf("counts-") == 0) {
                allFiles[nInputFiles] = allFiles[i];
                nInputFiles++;
            }
        }

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final HashMap<String, MutableLong> counts = new HashMap<String, MutableLong>();

        for(int i = 0; i < nInputFiles; i++) {
            System.out.print("Aggregating from "+i);
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

        final Path outputFilePath = new Path(outputDirName+"/final-counts");
        SequenceFile.Writer writer;
        try {
                writer = SequenceFile.createWriter(fs, conf, 
                    outputFilePath,
                    org.apache.hadoop.io.Text.class,
                    org.apache.hadoop.io.LongWritable.class);
            for(String s : counts.keySet()) {
                writer.append(new Text(s), new LongWritable(counts.get(s).get()));
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
