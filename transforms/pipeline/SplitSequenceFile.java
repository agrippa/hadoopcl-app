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

public class SplitSequenceFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 3) {
            System.out.println("usage: java SplitSequenceFile file split output-folder");
            return;
        }

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        String input = args[0];
        int splits = Integer.parseInt(args[1]);
        String outputFolder = args[2];

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(input), conf);
        Text key = new Text();
        org.apache.mahout.math.VectorWritable val = 
            new org.apache.mahout.math.VectorWritable();
        final List<Pair<Text, org.apache.mahout.math.VectorWritable>> all =
            new ArrayList<Pair<Text, org.apache.mahout.math.VectorWritable>>();
        while (reader.next(key, val)) {
            all.add(new Pair<Text, org.apache.mahout.math.VectorWritable>(key, val));
            key = new Text();
            val = new org.apache.mahout.math.VectorWritable();
        }
        reader.close();

        Runner[] runners = new Runner[12];
        Thread[] threads = new Thread[runners.length];
        int chunkSize = (all.size() + runners.length - 1) / runners.length;
        for (int i = 0 ;i < runners.length; i++) {
            int start = chunkSize * i;
            int end = chunkSize * (i+1);
            if (end > all.size()) end = all.size();
            runners[i] = new Runner(all, start, end, SequenceFile.createWriter(fs, conf,
                        new Path(outputFolder + "/"+(new File(input).getName())+"."+i),
                        org.apache.hadoop.io.Text.class,
                        org.apache.mahout.math.VectorWritable.class), i);
            threads[i] = new Thread(runners[i]);
            threads[i].start();
        }

        for (int i = 0; i < runners.length; i++) {
            threads[i].join();
        }
    }

    public static class Runner implements Runnable {
        private final List<Pair<Text, org.apache.mahout.math.VectorWritable>> all;
        private final int start;
        private final int end;
        private final int tid;
        private final SequenceFile.Writer writer;

        public Runner(List<Pair<Text, org.apache.mahout.math.VectorWritable>> all,
                int start, int end, SequenceFile.Writer writer, int tid) {
            this.all = all;
            this.start = start;
            this.end = end;
            this.writer = writer;
            this.tid = tid;
        }

        public void run() {
            try {
                int len = end - start;
                for (int i = start; i < end; i++) {
                    writer.append(all.get(i).a, all.get(i).b);
                    if ((i - start) % 10000 == 0) {
                        System.out.println("Thread "+tid+" done with "+(i-start)+"/"+len);
                    }
                }
                System.out.println("Thread "+tid+" Done");
                writer.close();
            } catch (IOException io) {
                throw new RuntimeException(io);
            }
        }
    }

    public static class Pair<A, B> {
        public final A a;
        public final B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}
