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
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: java WordCountFromTokenized input-dir output-dir");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[1];

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

        int nChunks = 10;
        for(int i = 0; i < nChunks; i++) {
            int chunkSize = (nSeqFiles + nChunks - 1) / nChunks;
            int start = i * chunkSize;
            int end = (i + 1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;

            Path outputFilePath = new Path(outputDirName+"/counts-"+i);
            SequenceFile.Writer writer;
            try {
                writer = SequenceFile.createWriter(fs, conf, outputFilePath,
                        org.apache.hadoop.io.Text.class, org.apache.hadoop.io.LongWritable.class);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            processChunk(i, start, end, allFiles, conf, fs, writer);
            try {
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
    }

    private static void processChunk(int chunkid, int start, int end, File[] allFiles,
            Configuration conf, FileSystem fs, SequenceFile.Writer writer) {
        int len = end - start;
        int nThreads = 12;
        int chunkSize = (len + nThreads - 1) / nThreads;
        WordCountRunner[] runners = new WordCountRunner[nThreads];
        Thread[] threads = new Thread[nThreads];

        for(int t = 0; t < nThreads; t++) {
            int threadStart = chunkSize * t;
            int threadEnd = chunkSize * (t+1);
            if(end > len) end = len;

            threadStart += start;
            threadEnd += start;

            runners[t] = new WordCountRunner(t, threadStart, threadEnd, conf, fs, allFiles);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }

        try {
            for(int t = 0; t < nThreads; t++) {
                threads[t].join();
            }
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        final HashMap<String, MutableLong> agg = runners[0].getMap();

        for(int t= 1; t < nThreads; t++) {
            System.out.print("Merging thread "+(t+1)+"/"+nThreads);
            final HashMap<String, MutableLong> curr = runners[t].getMap();
            for(String s : curr.keySet()) {
                if(agg.containsKey(s)) {
                    agg.get(s).incr(curr.get(s).get());
                } else {
                    agg.put(s, curr.get(s));
                }
            }
            System.out.println("DONE");
        }

        try {
            for(String s : agg.keySet()) {
                writer.append(new Text(s), new LongWritable(agg.get(s).get()));
            }
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static class WordCountRunner implements Runnable {
        final private int tid;
        final private int start;
        final private int end;
        final private int len;
        final private Configuration conf;
        final private FileSystem fs;
        final private File[] allFiles;
        final private HashMap<String, MutableLong> myMap;

        public WordCountRunner(int setTid, int setStart, int setEnd, Configuration setConf,
                FileSystem setFs, File[] setFiles) {
            this.tid = setTid; this.start = setStart; this.end = setEnd; this.len = this.end-this.start;
            this.conf = setConf; this.fs = setFs; this.allFiles = setFiles;
            this.myMap = new HashMap<String, MutableLong>();
        }

        public HashMap<String, MutableLong> getMap() {
            return this.myMap;
        }

        public void run() {
            final Text inputKey = new Text();
            for(int i = start; i < end; i++) {
                final File currentFile = allFiles[i];
                final Path inputPath = new Path(currentFile.getAbsolutePath());
                final SequenceFile.Reader reader;
                try {
                    reader = new SequenceFile.Reader(fs, inputPath, conf);
                } catch(IOException io) {
                    continue;
                }

                final StringTuple inputVal = new StringTuple();
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
                System.out.println("Thread "+tid+": "+(i+1-start)+"/"+this.len);
                //if ((i+1-start) % 100 == 0) {
                //    System.out.format("Thread %d completed (%2.2f)\n",this.tid, (((double)(i+1-start)/(double)this.len) * 100.0));
                //}
            }
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
