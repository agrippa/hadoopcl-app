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

public class GenerateDFVectors {

    private static int dimension = Integer.MAX_VALUE;
    private static HashMap<String, Integer> readDictionary(SequenceFile.Reader reader) {
        final HashMap<String, Integer> dict = new HashMap<String, Integer>();
        final Text key = new Text();
        final IntWritable val = new IntWritable();

        int count = 0;
        try {
            while(reader.next(key, val)) {
                dict.put(key.toString(), val.get());
                count++;
            }
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        GenerateDFVectors.dimension = count;
        return dict;
    }

    private static VectorWritable processInputPair(StringTuple value,
            HashMap<String, Integer> dictionary) {
        final org.apache.mahout.math.Vector vector = new RandomAccessSparseVector(GenerateDFVectors.dimension, value.length());

        for(final String term : value.getEntries()) {
            if(!term.isEmpty() && dictionary.containsKey(term)) {
                int termid = dictionary.get(term);
                vector.setQuick(termid, vector.getQuick(termid) + 1);
            }
        }

        if (vector.getNumNondefaultElements() > 0) {
            return new VectorWritable(vector);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java GenerateDFVectors input-dir output-dir dict-file");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[1];
        String dictName = args[2];

        File inputFolder = new File(inputDirName);
        File[] files = inputFolder.listFiles();
        int nSeqFiles = 0;
        for(int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if(name.indexOf("crc") == -1) {
                files[nSeqFiles++] = files[i];
            }
        }

        final Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        System.out.print("Preparing dictionary... ");
        final HashMap<String, Integer> dictionary;
        try {
            dictionary = readDictionary(
                new SequenceFile.Reader(fs, new Path(dictName), conf));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        System.out.println("DONE");

        int nThreads = 12;
        int chunkSize = (nSeqFiles + nThreads - 1) / nThreads;
        Thread[] threads = new Thread[nThreads];
        Runnable[] runners = new Runnable[nThreads];

        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t + 1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;

            runners[t] = new GenerateDFRunner(conf, fs, outputDirName, files, start, end,
                    dictionary, t);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }

        for(int t = 0; t < nThreads; t++) {
            try {
                threads[t].join();
            } catch(InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }
    }

    public static class GenerateDFRunner implements Runnable {
        private final Configuration conf;
        private final FileSystem fs;
        private final String outputDirName;
        private final File[] files;
        private final HashMap<String, Integer> dictionary;
        private final int start;
        private final int end;
        private final int len;
        private final int tid;

        public GenerateDFRunner(Configuration conf, FileSystem fs, String outputDirName, File[] files,
                int start, int end, HashMap<String, Integer> dictionary, int tid) {
            this.conf = conf; this.fs = fs; this.outputDirName = outputDirName;
            this.files = files;
            this.tid = tid;
            this.start = start;
            this.end = end;
            this.len = end-start;
            this.dictionary = dictionary;
        }

        public void run() {
            for(int i = start; i < end; i++) {
                final File currentFile = files[i];
                final Path currentPath = new Path(currentFile.getAbsolutePath());
                final Path outputPath = new Path(outputDirName+"/"+currentFile.getName());
                SequenceFile.Reader reader;
                try {
                    reader = new SequenceFile.Reader(fs, currentPath, conf);
                } catch(IOException io) {
                    continue;
                }
                try {
                    final SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                            outputPath, org.apache.hadoop.io.Text.class,
                            org.apache.mahout.math.VectorWritable.class);

                    final Text key = new Text();
                    final StringTuple val = new StringTuple();
                    while(reader.next(key, val)) {
                        final VectorWritable vectorWritable = processInputPair(val, dictionary);
                        if(vectorWritable != null) {
                            writer.append(key, vectorWritable);
                        }
                    }
                    reader.close();
                    writer.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
                System.out.format("thread %d completed %d/%d\n",tid,i-start+1,len);
            }
        }
    }
}
