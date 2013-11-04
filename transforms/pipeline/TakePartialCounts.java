package pipeline;

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
import common.*;

public class TakePartialCounts {
    public static void run(String existing, String countsFolder, int nChunks) {

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        CountTokens[] runners = new CountTokens[nChunks];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new CountTokens(countsFolder);
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(existing),
            conf, fs, org.apache.hadoop.io.Text.class,
            org.apache.mahout.math.VectorWritable.class);
        executor.run(runners);

        System.out.println("Done dumping partial token counts");
    }

    public static class CountTokens extends
            ParallelFileIterator.ParallelFileRunner {
        final private HashMap<Integer, MutableLong> tokenCounts =
            new HashMap<Integer, MutableLong>();
        final private String countsFolder;

        public CountTokens(String countsFolder) { this.countsFolder = countsFolder; }

        protected void callback(File currentFile) {
            final Text key = new Text();
            final org.apache.mahout.math.VectorWritable val =
                new org.apache.mahout.math.VectorWritable();

            try {
                final SequenceFile.Reader reader =
                    new SequenceFile.Reader(fs, new Path(currentFile.getAbsolutePath()), conf);
                while (reader.next(key, val)) {
                    final org.apache.mahout.math.Vector vec = val.get();
                    Iterator<org.apache.mahout.math.Vector.Element> iter = 
                        vec.nonZeroes().iterator();
                    while(iter.hasNext()) {
                        org.apache.mahout.math.Vector.Element ele = iter.next();
                        if (!this.tokenCounts.containsKey(ele.index())) {
                            tokenCounts.put(ele.index(), new MutableLong());
                        }
                        tokenCounts.get(ele.index()).incr();
                    }
                }
                this.print("Done");

                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }

        protected void finish() {
            final IntWritable outputKey = new IntWritable();
            final LongWritable outputVal = new LongWritable();

            this.print("Dumping partial results");

            Iterator<Map.Entry<Integer, MutableLong>> it = this.tokenCounts.entrySet().iterator();
            try {
                SequenceFile.Writer writer = SequenceFile.createWriter(this.fs, this.conf,
                        new Path(this.countsFolder+"/counts-"+this.tid),
                        org.apache.hadoop.io.IntWritable.class,
                        org.apache.hadoop.io.LongWritable.class);
                while (it.hasNext()) {
                    Map.Entry<Integer, MutableLong> pair = it.next();
                    outputKey.set(pair.getKey().intValue());
                    outputVal.set(pair.getValue().get());
                    writer.append(outputKey, outputVal);
                }
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            this.print("Done dumping partials");
        }
    }
}
