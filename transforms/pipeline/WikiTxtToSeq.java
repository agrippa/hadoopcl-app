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

/*
 * Total wiki files = 13638329
 */
public class WikiTxtToSeq {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("usage: java WikiTxtToSeq input-dirname output-dirname");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[1];
        if(outputDirName.charAt(outputDirName.length()-1) == '/') {
            outputDirName = outputDirName.substring(0, outputDirName.length()-1);
        }

        File folder = new File(inputDirName);
        File[] inputFiles = folder.listFiles();

        Configuration conf = new Configuration();
        Charset charset = Charset.forName("UTF-8");

        int nThreads = 12;
        int chunkSize = (inputFiles.length + 12 - 1) / 12;
        Runnable[] runners = new Runnable[nThreads];
        Thread[] threads = new Thread[nThreads];

        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t + 1) * chunkSize;
            if(end > inputFiles.length) end = inputFiles.length;

            runners[t] = new WikiRunner(t, start, end, inputDirName,
                    outputDirName+"/"+Integer.toString(t), charset, conf, inputFiles);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }

        for(int t = 0; t < nThreads; t++) {
            threads[t].join();
        }
    }

    public static class WikiRunner implements Runnable {
        private final Configuration conf;
        private final int start;
        private final int end;
        private final int nFiles;
        private final String inputDirName;
        private final String outputDirName;
        private final Charset charset;
        private final int tid;
        private final SequenceFile.Writer writer;
        private final File[] inputFiles;

        public WikiRunner(int setTid, int setStart, int setEnd,
                String setInputDir, String setOutputDir, Charset setCharset,
                Configuration setConf, File[] inputFiles) {
            this.start = setStart; this.end = setEnd; this.nFiles = this.end - this.start;
            this.inputDirName = setInputDir;
            this.outputDirName = setOutputDir;
            this.charset = setCharset;
            this.tid = setTid;
            this.conf = setConf;
            try {
                this.writer = new SequenceFile.Writer(FileSystem.get(this.conf),
                        this.conf, new Path(outputDirName), Text.class, Text.class);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
            this.inputFiles = inputFiles;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                String contents;
                try {
                    String[] tokens = inputFiles[i].getName().split("\\.");
                    final int base = Integer.parseInt(tokens[1]);
                    int count = 0;
                    contents = FileUtils.readFileToString(
                           inputFiles[i], charset);
                    String[] lines = contents.split(System.getProperty("line.separator"));
                    for (String l : lines) {
                        writer.append(new Text(Integer.toString(base + count)), new Text(l));
                        count++;
                    }
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
            }
            try {
                writer.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
    }
}
