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
import org.apache.mahout.math.*;

public class PruneTF {
    private static HashMap<Integer, Long> readDFDictionary(SequenceFile.Reader reader) {
        final HashMap<Integer, Long> dict = new HashMap<Integer, Long>();
        final IntWritable key = new IntWritable();
        final LongWritable val = new LongWritable();

        try {
            while(reader.next(key, val)) {
                dict.put(key.get(), val.get());
            }
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        return dict;
    }

    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("usage: java PruneTF tf-dir df-file output-dir vector-count");
            return;
        }

        String tfDirName = args[0];
        String dfFileName = args[1];
        String outputDirName = args[2];

        File folder = new File(tfDirName);
        File[] allFiles = folder.listFiles();
        int nSeqFiles = 0;
        for(int i = 0; i < allFiles.length; i++) {
            String name = allFiles[i].getName();
            if(name.indexOf("crc") == -1) {
                allFiles[nSeqFiles++] = allFiles[i];
            }
        }
        System.out.println("Got "+nSeqFiles+" sequence files in \""+tfDirName+"\"");

        final long vectorCount = Long.parseLong(args[3]);
        final long minDF = 1;
        final long maxDF = (long)(vectorCount * (85.0f / 100.0f));
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        final HashMap<Integer, Long> dfDict;
        try {
            dfDict = readDFDictionary(new SequenceFile.Reader(fs, new Path(dfFileName),
                        conf));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = 12;
        Thread[] threads = new Thread[nThreads];
        Runnable[] runners = new Runnable[nThreads];
        int chunkSize = (nSeqFiles + nThreads - 1) / nThreads;
        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t+1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;

            runners[t] = new PruneRunner(t, start, end, dfDict, minDF, maxDF,
                    allFiles, outputDirName, conf, fs);
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
    }

    public static class PruneRunner implements Runnable {
        private final int start;
        private final int end;
        private final int len;
        private final int tid;
        private final HashMap<Integer, Long> dfDict;
        private final long minDF;
        private final long maxDF;
        private final File[] allFiles;
        private final String outputDirName;
        private final Configuration conf;
        private final FileSystem fs;

        public PruneRunner(int tid, int start, int end, HashMap<Integer, Long> dfDict,
                long minDF, long maxDF, File[] allFiles, String outputDirName,
                Configuration conf, FileSystem fs) {
            this.tid = tid; this.start = start; this.end = end;
            this.dfDict = dfDict; this.minDF = minDF; this.maxDF = maxDF;
            this.allFiles = allFiles; this.outputDirName = outputDirName;
            this.len = (end-start);
            this.conf = conf; this.fs = fs;
        }

        public void run() {
            for(int i = start; i < end; i++) {
                final File currentFile = allFiles[i];
                final SequenceFile.Reader reader;
                try {
                    reader = new SequenceFile.Reader(fs,
                            new Path(currentFile.getAbsolutePath()), conf);
                } catch(IOException io) {
                    continue;
                }
                final SequenceFile.Writer writer;
                try {
                    writer = SequenceFile.createWriter(fs, conf,
                            new Path(this.outputDirName+"/"+currentFile.getName()),
                            org.apache.hadoop.io.Text.class,
                            org.apache.mahout.math.VectorWritable.class);
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }

                try {
                    final Text key = new Text();
                    final VectorWritable val = new VectorWritable();
                    while(reader.next(key, val)) {
                        org.apache.mahout.math.Vector value = val.get();
                        org.apache.mahout.math.Vector clone = value.clone();
                        for(org.apache.mahout.math.Vector.Element e : value.nonZeroes()) {
                            if(!dfDict.containsKey(e.index())) {
                                clone.setQuick(e.index(), 0.0);
                            } else {
                                long df = dfDict.get(e.index());
                                if(df > maxDF || df < minDF) {
                                    clone.setQuick(e.index(), 0.0);
                                }
                            }
                        }
                        writer.append(key, new VectorWritable(clone));
                    }

                    reader.close();
                    writer.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
                System.out.println("thread "+tid+" completed "+(i-start+1)+"/"+this.len);
            }
        }
    }
}
