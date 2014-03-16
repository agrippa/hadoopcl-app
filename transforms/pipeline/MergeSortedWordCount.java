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

public class MergeSortedWordCount {

    private static String findLeast(Text[] arr) {
        String least = null;
        int leastIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null && (least == null || arr[i].toString().compareTo(least) < 0)) {
                least = arr[i].toString();
                leastIndex = i;
            }
        }
        return least;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(args.length);
        if(args.length < 2) {
            System.out.println("usage: java MergeSortedWordCount output-file input-file-1 input-file-2 ...");
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

        final Path outputFilePath = new Path(outputFileName);
        SequenceFile.Writer writer;
        writer = SequenceFile.createWriter(fs, conf, 
                outputFilePath,
                org.apache.hadoop.io.Text.class,
                org.apache.hadoop.io.LongWritable.class);
        final Text outKey = new Text();
        final LongWritable outVal = new LongWritable();

        SequenceFile.Reader[] readers = new SequenceFile.Reader[allFiles.length];
        Text[] currentKeys = new Text[readers.length];
        LongWritable[] currentVals = new LongWritable[readers.length];
        for (int i = 0; i < readers.length; i++) {
            File curr = allFiles[i];
            final Path inputPath = new Path(curr.getAbsolutePath());
            readers[i] = new SequenceFile.Reader(fs, inputPath, conf);
            currentKeys[i] = new Text();
            currentVals[i] = new LongWritable();

            readers[i].next(currentKeys[i], currentVals[i]);
        }


        String least = findLeast(currentKeys);
        while (least != null) {
            long sum = 0;
            for (int i = 0 ; i < readers.length; i++) {
                if (currentKeys[i] != null && currentKeys[i].toString().equals(least)) {
                    sum += currentVals[i].get();
                    if (!readers[i].next(currentKeys[i], currentVals[i])) {
                        readers[i].close();
                        currentKeys[i] = null;
                        currentVals[i] = null;
                    }
                }
            }
            outKey.set(least);
            outVal.set(sum);
            writer.append(outKey, outVal);
            least = findLeast(currentKeys);
        }
        writer.close();
    }
}
