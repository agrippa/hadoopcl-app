package pipeline;

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

public class TextToTokens {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("usage: java TextToTokens input-dir output-dir");
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
        int nThreads = 12;
        int chunkSize = (nSeqFiles + nThreads - 1) / nThreads;
        Runnable[] runners = new Runnable[nThreads];
        Thread[] threads = new Thread[nThreads];
        Configuration conf = new Configuration();

        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t + 1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;

            runners[t] = new TextToTokensRunner(t, start, end, inputDirName, outputDirName, conf, allFiles);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }
        for(int t = 0; t < nThreads; t++) {
            threads[t].join();
        }
    }

    public static class TextToTokensRunner implements Runnable {
        private final int tid;
        private final int start;
        private final int end;
        private final int length;
        private final Configuration conf;
        private final FileSystem fs;
        private final String inputDir;
        private final String outputDir;
        private final File[] files;

        public TextToTokensRunner(int setTid, int setStart, int setEnd,
                String setInputDir, String setOutputDir, Configuration setConf,
                File[] setFiles) {
            this.tid = setTid;
            this.start = setStart;
            this.end = setEnd;
            this.length = this.end-this.start;
            this.inputDir = setInputDir;
            this.outputDir = setOutputDir;
            this.conf = setConf;
            try {
                this.fs = FileSystem.get(this.conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            this.files = setFiles;
        }

        public void run() {
            final Text inputKey = new Text();
            final Text inputVal = new Text();

            try {

                for(int i = start; i < end; i++) {
                    final File currentFile = this.files[i];
                    Path inputPath = new Path(currentFile.getAbsolutePath());
                    Path outputPath = new Path(this.outputDir+"/"+currentFile.getName());

                    SequenceFile.Reader reader = new SequenceFile.Reader(this.fs, inputPath, this.conf);
                    SequenceFile.Writer writer = SequenceFile.createWriter(this.fs, this.conf, outputPath,
                            org.apache.hadoop.io.Text.class, org.apache.mahout.common.StringTuple.class);
                    while(reader.next(inputKey, inputVal)) {
                        final StringTuple outputVal = new StringTuple(inputVal.toString().split("\\s+"));
                        writer.append(inputKey, outputVal);
                    }
                    reader.close();
                    writer.close();

                    if ((i+1) % 100 == 0) {
                        System.out.format("Thread %d completed (%2.2f)\n",this.tid,(((double)(i-start+1)/(double)this.length) * 100.0));
                    }
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
    }
}
