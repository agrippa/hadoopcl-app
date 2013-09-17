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

public class GenerateUniqueWordIDs {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("usage: java GenerateUniqueWordIDs input-file output-file");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Path inputPath = new Path(inputFileName);
        Path outputPath = new Path(outputFileName);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, inputPath, conf);
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
                outputPath, org.apache.hadoop.io.Text.class, 
                org.apache.hadoop.io.IntWritable.class);

        final Text key = new Text();
        final LongWritable inputVal = new LongWritable();
        final IntWritable outputVal = new IntWritable();

        int count = 0;
        while(reader.next(key, inputVal)) {
            outputVal.set(count);
            writer.append(key, outputVal);
            count++;
        }

        reader.close();
        writer.close();
    }
}
