import org.apache.hadoop.io.*;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import java.io.File;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class merges sequence files together to produce better chunking of Hadoop
 * mappers/reducers. It produces a specified number of output files (i.e. its
 * chunking is not based on specifying a desired output file size).
 */
public abstract class FileMerger<KeyType extends Writable, ValueType extends Writable> {
    final private String inputFolder;
    final private String outputFolder;
    final private String outputFilePrefix;
    final private int nOutputFiles;

    protected abstract KeyType instantiateKey();
    protected abstract ValueType instantiateValue();

    protected FileMerger(String callingClass, String[] args) {
        if (args.length != 4) {
            usage(callingClass);
        }

        this.inputFolder = args[0];
        this.outputFolder = args[1];
        this.nOutputFiles = Integer.parseInt(args[2]);
        this.outputFilePrefix = args[3];

        try {
            FileUtils.cleanDirectory(new File(outputFolder));
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    protected void usage(String className) {
        System.out.println("usage: java "+className+
                " input-dir output-dir n-output-files output-file-prefix");
        System.exit(1);
    }

    /**
     * Counts the total number of <key, value> pairs included in all sequence
     * files in the provided input directory.
     */
    protected long countPairs(List<File> files) {

        long accumCount = 0;
        final int nthreads = 12;
        Thread[] threads = new Thread[nthreads];
        CountPairsRunner[] runners = new CountPairsRunner[nthreads];
        for (int i = 0; i < nthreads; i++) {
            runners[i] = new CountPairsRunner(files, i, nthreads,
                    this);
            threads[i] = new Thread(runners[i]);
            threads[i].start();
        }
        try {
            for (int i = 0; i < nthreads; i++) {
                threads[i].join();
                accumCount += runners[i].getCount();
            }
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        return accumCount;
    }

    /**
     * Filters out any non-sequence files in the input directory. These could be
     * some user files, Hadoop CRC files, etc. It does this by trying to open each
     * file as a sequence file and read a single input pair from it of the correct
     * type.
     */
    protected List<File> filterOutNonSeqFiles(File[] inputs) {

        List<File> accumFiltered = new ArrayList<File>(inputs.length);
        final int nthreads = 12;
        Thread[] threads = new Thread[nthreads];
        FilterNonSeqFilesRunner[] runners = new FilterNonSeqFilesRunner[nthreads];
        for (int i = 0; i < nthreads; i++) {
            runners[i] = new FilterNonSeqFilesRunner(inputs, i, nthreads,
                    this);
            threads[i] = new Thread(runners[i]);
            threads[i].start();
        }
        try {
            for (int i = 0; i < nthreads; i++) {
                threads[i].join();
                accumFiltered.addAll(runners[i].getFiltered());
            }
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        return accumFiltered;
    }

    protected void doMerge() {
        File input = new File(this.inputFolder);
        File[] allInputFiles = input.listFiles();
        List<File> seqFiles = filterOutNonSeqFiles(allInputFiles);
        long totalPairs = countPairs(seqFiles);
        long elementsPerOutputFile = (totalPairs + this.nOutputFiles - 1) / 
            this.nOutputFiles;
        System.out.println(totalPairs + " pairs total, " + elementsPerOutputFile+
                " per output file");

        Configuration conf = new Configuration();
        FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        long currentOutputFileIndex = 0;
        long nWrittenToCurrentFile = 0;

        final KeyType key = instantiateKey();
        final ValueType val = instantiateValue();

        SequenceFile.Writer writer;
        try {
            writer = SequenceFile.createWriter(fs, conf,
                    new Path(outputFolder+"/"+this.outputFilePrefix+
                        String.format("%06d", currentOutputFileIndex)),
                    key.getClass(), val.getClass());
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int countFiles = 0;
        for (File f : seqFiles) {
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
            } catch(IOException io) {
                System.out.println("Skipping "+f.getAbsolutePath());
                continue;
            }

            try {
                while (reader.next(key, val)) {
                    writer.append(key, val);
                    nWrittenToCurrentFile++;

                    if (nWrittenToCurrentFile == elementsPerOutputFile) {
                        nWrittenToCurrentFile = 0;
                        currentOutputFileIndex++;

                        try {
                            writer.close();
                        } catch(IOException io) {
                            throw new RuntimeException(io);
                        }
                        writer = SequenceFile.createWriter(fs, conf,
                                new Path(outputFolder+"/"+this.outputFilePrefix+
                                    String.format("%6d", currentOutputFileIndex)),
                                key.getClass(), val.getClass());
                    }
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            countFiles++;
            if (((countFiles+1) % 20) == 0) {
                System.out.println("Completed merging "+(countFiles+1)+"/"+seqFiles.size());
            }
        }
        try {
            writer.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    /**
     * A useful parent class for multi-threaded processing of multiple files
     * that handles a lot of boring set up and such
     */
    public abstract static class FileRunner<KeyType extends Writable,
           ValueType extends Writable> implements Runnable {
        final protected Configuration conf = new Configuration();
        final protected FileSystem fs;
        final private List<File> files;
        final protected int tid;
        final protected int nthreads;
        final protected int start;
        final protected int end;
        final protected int nFiles;
        final protected KeyType key;
        final protected ValueType value;

        protected abstract void callback(File f, int fileIndex);

        protected FileRunner(File[] files, int tid, int nthreads,
                FileMerger<KeyType,ValueType> parent) {
            this(Arrays.asList(files), tid, nthreads, parent);
        }

        protected FileRunner(List<File> files, int tid, int nthreads,
                FileMerger<KeyType,ValueType> parent) {
            this.tid = tid;
            this.nthreads = nthreads;
            this.files = files;
            int chunkSize = (files.size() + nthreads - 1) / nthreads;
            this.start = tid * chunkSize;
            this.end = (this.start + chunkSize > files.size() ? files.size() :
                    this.start + chunkSize);
            this.nFiles = end - start;

            this.key = parent.instantiateKey();
            this.value = parent.instantiateValue();

            try {
                fs = FileSystem.get(conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                File f = files.get(i);
                callback(f, i - start);
            }
        }
    }

    public static class CountPairsRunner<KeyType extends Writable,ValueType extends Writable>
            extends FileRunner<KeyType,ValueType> {
        private long count = 0;
        private int countFiles = 0;

        public CountPairsRunner(List<File> files, int tid, int nthreads,
                FileMerger<KeyType,ValueType> parent) {
            super(files, tid, nthreads, parent);
        }

        @Override
        protected void callback(File f, int fileIndex) {
            long atStart = this.count;
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
            } catch(IOException io) {
                return;
            }

            try {
                while (reader.next(this.key, this.value)) {
                    count++;
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            try {
                reader.close();
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            countFiles++;
            System.out.println("File "+f.getAbsolutePath()+" ("+countFiles+"/"+
                    this.nFiles+") has "+(count-atStart)+" pairs, "+count+
                    " so far");

        }

        public long getCount() { return count; }
    }

    public static class FilterNonSeqFilesRunner<KeyType extends Writable,ValueType extends Writable>
            extends FileRunner<KeyType, ValueType> {

        private final List<File> filtered = new ArrayList<File>(this.nFiles);

        public FilterNonSeqFilesRunner(File[] files, int tid, int nthreads,
                FileMerger<KeyType,ValueType> parent) {
            super(files, tid, nthreads, parent);
        }

        @Override
        protected void callback(File f, int fileIndex) {
            boolean filter = false;
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
                reader.next(key, value);
            } catch(Exception e) {
                filter = true;
            }

            if (!filter) {
                filtered.add(f);
            }
            if (((fileIndex+1) % 100) == 0) {
                System.out.println("Thread "+this.tid+" done filtering "+
                        (fileIndex+1)+"/"+this.nFiles);
            }
        }

        public List<File> getFiltered() {
            return this.filtered;
        }
    }
}
