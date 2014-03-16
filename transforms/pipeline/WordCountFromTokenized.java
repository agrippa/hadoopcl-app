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

public class WordCountFromTokenized {
    public static void main(String[] args) throws IOException {
        if(args.length != 4) {
            System.out.println("usage: java WordCountFromTokenized input-dir output-dir start-index end-index-not-inclusive");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[1];
        int startIndex = Integer.parseInt(args[2]);
        int endIndex = Integer.parseInt(args[3]);

        File folder = new File(inputDirName);
        int nSeqFiles = 0;
        System.out.print("Listing files...");
        File[] allFiles = folder.listFiles();
        System.out.println("DONE");

        System.out.print("Filtering files...");
        for(int i = 0; i < allFiles.length; i++) {
            String name = allFiles[i].getName();
            if(name.indexOf(".crc") == -1) {
                allFiles[nSeqFiles] = allFiles[i];
                nSeqFiles++;
            }
        }
        System.out.println("DONE");
        System.out.println("Retrieved "+nSeqFiles+" actual sequence files");

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        for (int i = startIndex; i < endIndex; i++) {
            final File currentFile = allFiles[i];
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                continue;
            }

            final HashMap<String, MutableLong> myMap = new HashMap<String, MutableLong>();
            final StringTuple inputVal = new StringTuple();
            final Text inputKey = new Text();
            try {
                while(reader.next(inputKey, inputVal)) {
                    for(final String s : inputVal.getEntries()) {
                        if(myMap.containsKey(s)) {
                            myMap.get(s).incr();
                        } else {
                            myMap.put(s, new MutableLong(1));
                        }
                    }
                }
                reader.close();
            } catch(IOException io) {
                continue;
            }

            Path outputFilePath = new Path(outputDirName+"/counts-"+i);
            SequenceFile.Writer writer;
            try {
                writer = SequenceFile.createWriter(fs, conf, outputFilePath,
                        org.apache.hadoop.io.Text.class, org.apache.hadoop.io.LongWritable.class);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            for (String s : myMap.keySet()) {
                writer.append(new Text(s), new LongWritable(myMap.get(s).get()));
            }
            writer.close();

            System.out.println("Done with file "+(i+1)+"/"+nSeqFiles);
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
