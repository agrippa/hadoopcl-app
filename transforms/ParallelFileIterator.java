import java.util.List;
import java.util.ArrayList;
import java.io.File;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;

public class ParallelFileIterator {
    private final List<File> inputFiles;
    private final int nInputFiles;
    private final Configuration conf;
    private final FileSystem fs;
    
    public ParallelFileIterator(List<File> inputFiles, Configuration conf,
            FileSystem fs) {
        this.inputFiles = inputFiles;
        this.nInputFiles = this.inputFiles.size();
        this.conf = conf;
        this.fs = fs;
    }

    public ParallelFileIterator(File[] inputFiles, Configuration conf,
            FileSystem fs) {
        this(Arrays.asList(inputFiles), conf, fs);
    }

    public ParallelFileIterator(File inputFolder, Configuration conf,
            FileSystem fs, Class keyClass, Class valClass) {
        this.conf = conf;
        this.fs = fs;

        File[] files = inputFolder.listFiles();
        FilterNonSeqFiles[] runners = new FilterNonSeqFiles[12];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new FilterNonSeqFiles(keyClass, valClass);
        }
        ParallelFileIterator child = new ParallelFileIterator(files, conf, fs);
        child.run(runners);

        List<File> accumFiles = new ArrayList<File>(files.length);
        for (int t = 0; t < runners.length; t++) {
            accumFiles.addAll(runners[t].getFiltered());
        }

        this.inputFiles = accumFiles;
        this.nInputFiles = this.inputFiles.size();
    }

    public void run(ParallelFileRunner[] runners) {
        final int nThreads = runners.length;
        final Thread[] threads = new Thread[nThreads];
        final int chunkSize = (nInputFiles + nThreads - 1) / nThreads;

        try {
            for (int t = 0; t < nThreads; t++) {
                int start = chunkSize * t;
                int end = start + chunkSize;
                if (end > this.nInputFiles) end = this.nInputFiles;

                runners[t].init(t, nThreads, start, end, this.inputFiles,
                        this.conf, this.fs);
                threads[t] = new Thread(runners[t]);
                threads[t].start();
            } 
            for (int t = 0; t < nThreads; t++) {
                threads[t].join();
            }
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public static abstract class ParallelFileRunner implements Runnable {
        protected Configuration conf;
        protected FileSystem fs;
        protected List<File> files;
        protected int start, end, length;
        private int tid, nThreads;
        private int iter;
        public void init(int tid, int nThreads,
                int start, int end, List<File> files,
                Configuration conf, FileSystem fs) {
            this.tid = tid; this.nThreads = nThreads;
            this.start = start; this.end = end; this.length = end - start;
            this.files = files;
            this.conf = conf; this.fs = fs;
        }

        protected void print(String s, int interval) {
            if (((this.iter+1) % interval) == 0) {
                this.print(s);
            }
        }

        protected void print(String s) {
            System.out.println("T"+this.tid+"/"+this.nThreads+" ("+
                (this.iter+1)+"/"+this.length+" files) | "+s);
        }

        @Override
        public void run() {
            for (this.iter = start; this.iter < end; this.iter++) {
                this.callback(this.files.get(this.iter));
            }
        }

        protected abstract void callback(File f);
    }

    public static class FilterNonSeqFiles extends ParallelFileRunner {
        private final List<File> filtered = new ArrayList<File>();
        private final Writable key;
        private final Writable val;

        public FilterNonSeqFiles(Class keyClass, Class valClass) {
            try {
                this.key = (Writable)keyClass.newInstance();
                this.val = (Writable)valClass.newInstance();
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        public void callback(File f) {
            boolean filter = false;
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(this.fs,
                        new Path(f.getAbsolutePath()), this.conf);
                reader.next(this.key, this.val);
            } catch(Exception e) {
                filter = true;
            }

            if (!filter) {
                filtered.add(f);
            }
            this.print("filtering", 40);
        }

        public List<File> getFiltered() {
            return this.filtered;
        }
    }
}
