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

public class TFtoDF {

    private static long featureCount = -1;

    private static HashMap<String, Integer> readDictionary(SequenceFile.Reader reader) {
        final HashMap<String, Integer> dict = new HashMap<String, Integer>();
        final Text key = new Text();
        final IntWritable val = new IntWritable();

        long count = 0;
        try {
            while(reader.next(key, val)) {
                dict.put(key.toString(), val.get());
                count++;
            }
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        TFtoDF.featureCount = count;
        return dict;
    }

    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("usage: java TFtoDF final-counts-path dict-file-path tf-vectors-path output-file");
            return;
        }

        String finalCountsName = args[0];
        String dictFileName = args[1];
        String tfVectorsDir = args[2];
        String outputName = args[3];

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        System.out.print("Preparing dictionary... ");
        final HashMap<String, Integer> dictionary;
        try {
            dictionary = readDictionary(
                new SequenceFile.Reader(fs, new Path(dictFileName), conf));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        System.out.println("DONE");

        final SequenceFile.Reader wordCountReader;
        final SequenceFile.Writer termCountWriter;
        try {
            wordCountReader = new SequenceFile.Reader(fs, new Path(finalCountsName),
                    conf);
            termCountWriter = SequenceFile.createWriter(fs, conf, new Path(outputName),
                    org.apache.hadoop.io.IntWritable.class,
                    org.apache.hadoop.io.LongWritable.class);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        System.out.print("Translating to term-counts... ");
        final long featureCount = dictionary.keySet().size();
        final IntWritable outputKey = new IntWritable();
        final Text key = new Text();
        final LongWritable val = new LongWritable();
        try {
            while(wordCountReader.next(key, val)) {
                Integer termId = dictionary.get(key.toString());
                if(termId != null) {
                    outputKey.set(termId.intValue());
                    termCountWriter.append(outputKey, val);
                }
            }
            termCountWriter.close();
            wordCountReader.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
        System.out.println("DONE");

        System.out.println("Counting documents... ");
        File folder = new File(tfVectorsDir);
        File[] allFiles = folder.listFiles();
        int nSeqFiles = 0;
        for(int i = 0; i < allFiles.length; i++) {
            String name = allFiles[i].getName();
            if(name.indexOf("crc") == -1) {
                allFiles[nSeqFiles++] = allFiles[i];
            }
        }

        long vectorCount = 0;
        for(int i = 0; i < nSeqFiles; i++) {
            final File currentFile = allFiles[i];
            final Path currentPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, currentPath, conf);
            } catch(IOException io) {
                continue;
            }
            try {
                final Text tfKey = new Text();
                final VectorWritable tfVal = new VectorWritable();
                while(reader.next(tfKey, tfVal)) {
                    vectorCount++;
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            System.out.println("done "+(i+1)+" / "+nSeqFiles);
        }
        System.out.println("vectorCount = "+vectorCount);
        System.out.println("featureCount = "+featureCount);
    }
}
