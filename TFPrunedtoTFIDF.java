import org.apache.lucene.search.similarities.DefaultSimilarity;
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

public class TFPrunedtoTFIDF {
    private static HashMap<Integer, Long> readDFDictionary(SequenceFile.Reader reader) {
        final HashMap<Integer, Long> dict = new HashMap<Integer, Long>();
        final IntWritable key = new IntWritable();
        final LongWritable val = new LongWritable();

        try {
            while(reader.next(key, val)) {
                dict.put(key.get(), val.get());
            }
            reader.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        return dict;
    }

    public static void main(String[] args) {
        if(args.length != 5) {
            System.out.println("usage: java TFPrunedtoTFIDF pruned-dir df-file output-dir feature-count vector-count");
            return;
        }

        String prunedDirName = args[0];
        String dfFileName = args[1];
        String outputDirName = args[2];
        final long featureCount = Long.parseLong(args[3]);
        final long vectorCount = Long.parseLong(args[4]);
        final long minDF = 1;
        final long maxDF = (long)(vectorCount * (85.0f / 100.0f));

        File folder = new File(prunedDirName);
        File[] allFiles = folder.listFiles();
        int nSeqFiles = 0;
        for(int i = 0; i < allFiles.length; i++) {
            String name = allFiles[i].getName();
            if(name.indexOf("crc") == -1) {
                allFiles[nSeqFiles++] = allFiles[i];
            }
        }

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

        final int nThreads = 12;
        Thread[] threads = new Thread[nThreads];
        Runnable[] runners = new Runnable[nThreads];
        final int chunkSize = (nSeqFiles + nThreads - 1) / nThreads;
        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t+1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;
            runners[t] = new TFIDFRunner(t, start, end, dfDict, allFiles,
                    outputDirName, featureCount, vectorCount, conf, fs,
                    minDF, maxDF);
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

    public static class TFIDFRunner implements Runnable {
        private final int start;
        private final int end;
        private final int tid;
        private final int len;
        private final HashMap<Integer, Long> dfDict;
        private final File[] files;
        private final String outputDirName;
        private final long featureCount;
        private final long vectorCount;
        private final Configuration conf;
        private final FileSystem fs;
        private final long minDF;
        private final long maxDF;

        public TFIDFRunner(int tid, int start, int end, HashMap<Integer, Long> dfDict,
                File[] files, String outputDirName, long featureCount, long vectorCount,
                Configuration conf, FileSystem fs, long minDF, long maxDF) {
            this.start = start; this.end = end; this.len = (end-start);
            this.tid = tid;
            this.dfDict = dfDict;
            this.files = files; this.outputDirName = outputDirName;
            this.featureCount = featureCount; this.vectorCount = vectorCount;
            this.conf = conf; this.fs = fs;
            this.minDF = minDF; this.maxDF = maxDF;
        }

        private static final DefaultSimilarity sim = new DefaultSimilarity();

        private double tfidf_calculate(int tf, int df, int length, int numDocs) {
            return sim.tf(tf) * sim.idf(df, numDocs);
        }

        public void run() {
            for(int i = start; i < end; i++) {
                File currentFile = files[i];
                final SequenceFile.Reader reader;
                try {
                    reader = new SequenceFile.Reader(fs,
                            new Path(currentFile.getAbsolutePath()),
                            conf);
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
                    final VectorWritable inputVal = new VectorWritable();
                    while(reader.next(key, inputVal)) {
                        org.apache.mahout.math.Vector value = inputVal.get();
                        org.apache.mahout.math.Vector vector =
                            new RandomAccessSparseVector((int) featureCount,
                                    value.getNumNondefaultElements());
                        for(org.apache.mahout.math.Vector.Element e : value.nonZeroes()) {
                            if(!dfDict.containsKey(e.index())) {
                                continue;
                            }
                            long df = dfDict.get(e.index());
                            if (maxDF > -1 && (100.0 * df) / vectorCount > maxDF) {
                                continue;
                            }
                            if (df < minDF) {
                                df = minDF;
                            }
                            vector.setQuick(e.index(), tfidf_calculate((int) e.get(), (int) df, (int) featureCount, (int) vectorCount));
                        }
                        writer.append(key, new VectorWritable(vector));
                    }
                    writer.close();
                    reader.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
                System.out.println("thread "+tid+" completed "+(i-start+1)+"/"+this.len);
            }
        }
    }
}
