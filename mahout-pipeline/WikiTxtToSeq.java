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
        if (args.length != 3) {
            System.out.println("usage: java WikiTxtToSeq input-dirname output-dirname n-input-files");
            return;
        }

        String inputDirName = args[0];
        String outputDirName = args[1];
        if(outputDirName.charAt(outputDirName.length()-1) == '/') {
            outputDirName = outputDirName.substring(0, outputDirName.length()-1);
        }
        int nInputFiles = Integer.parseInt(args[2]);
        System.out.println("input=\""+inputDirName+"\", output=\""+outputDirName+"\", nfiles="+nInputFiles);

        Configuration conf = new Configuration();
        Charset charset = Charset.forName("UTF-8");

        int nThreads = 12;
        int chunkSize = (nInputFiles + 12 - 1) / 12;
        Runnable[] runners = new Runnable[nThreads];
        Thread[] threads = new Thread[nThreads];

        for(int t = 0; t < nThreads; t++) {
            int start = t * chunkSize;
            int end = (t + 1) * chunkSize;
            if(end > nInputFiles) end = nInputFiles;

            runners[t] = new WikiRunner(t, start, end, inputDirName, outputDirName+Integer.toString(t)+"/", charset, conf);
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
        private final ChunkedWriter writer;

        public WikiRunner(int setTid, int setStart, int setEnd, String setInputDir, String setOutputDir, Charset setCharset,
                Configuration setConf) {
            this.start = setStart; this.end = setEnd; this.nFiles = this.end - this.start;
            this.inputDirName = setInputDir;
            this.outputDirName = setOutputDir;
            this.charset = setCharset;
            this.tid = setTid;
            this.conf = setConf;
            ChunkedWriter tmpWriter = null;
            try {
                tmpWriter = new ChunkedWriter(conf, 5, new Path(outputDirName));
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
            this.writer = tmpWriter;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                String contents;
                try {
                    contents = FileUtils.readFileToString(new File(inputDirName+"/"+i+".txt"), charset);
                    writer.write(i+".txt", contents);
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }

                if ((i+1) % 1000 == 0) {
                    System.out.format("Thread %d completed (%2.2f)\n",tid, (((double)(i-start+1)/(double)this.nFiles) * 100.0));
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
