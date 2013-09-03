import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;
import org.apache.mahout.common.StringTuple;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.atomic.*;

public class TransformMahoutInput {
    private static List<File> getInputFiles(String folderName) {
        List<File> inputFiles = new ArrayList<File>();
        File folder = new File(folderName);

        File[] containedFiles = folder.listFiles();
        for(File f : containedFiles) {
            if(f.getName().indexOf("part-r-") == 0) {
                inputFiles.add(f);
            }
        }
        return inputFiles;
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("usage: java TransformMahoutInput tfidf-folder hadoopcl-folder");
            return;
        }

        String tfidfDirName = args[0];
        String hadoopclDirName = args[1];

        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        File folder = new File(tfidfDirName);
        File[] files = folder.listFiles();
        int nSeqFiles = 0;
        for(int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if(name.indexOf("crc") == -1) {
                files[nSeqFiles++] = files[i];
            }
        }

        AtomicInteger fileId = new AtomicInteger(0);
        int nThreads = 12;
        int chunkSize = (nSeqFiles + nThreads - 1) / nThreads;
        Thread[] threads = new Thread[nThreads];
        TransformRunner[] runners = new TransformRunner[nThreads];
        for(int t = 0; t < nThreads; t++) {
            int start= t * chunkSize;
            int end = (t+1) * chunkSize;
            if(end > nSeqFiles) end = nSeqFiles;

            runners[t] = new TransformRunner(start, end, t, conf, fs, files,
                    hadoopclDirName, fileId);
            threads[t] = new Thread(runners[t]);
            threads[t].start();
        }
        try {
            for(int t = 0; t < nThreads; t++) {
                threads[t].join();
            }
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }

        final Path fileMappingPath = new Path(hadoopclDirName+"/filemapping");
        final SequenceFile.Writer fileMappingWriter;
        try {
            fileMappingWriter = SequenceFile.createWriter(fs, conf, 
                fileMappingPath, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.Text.class);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }


        try {
            for(int t = 0; t < nThreads; t++) {
                HashMap<Integer, String> mapping = runners[t].getFileMapping();
                for(Integer id : mapping.keySet()) {
                    String filename = mapping.get(id);
                    fileMappingWriter.append(new IntWritable(id), new Text(filename));
                }
            }

            fileMappingWriter.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static class TransformRunner implements Runnable {
        private final int start;
        private final int end;
        private final int len;
        private final int tid;
        private final Configuration conf;
        private final FileSystem fs;
        private final File[] files;
        private final String outputDirName;
        private final HashMap<Integer, String> fileMapping;
        private final AtomicInteger fileId;

        public TransformRunner(int start, int end, int tid, Configuration conf,
                FileSystem fs, File[] files, String outputDirName, AtomicInteger fileId) {
            this.start = start;
            this.end = end;
            this.len = end - start;
            this.tid = tid;
            this.conf = conf; this.fs = fs; this.files = files;
            this.outputDirName = outputDirName;
            this.fileMapping = new HashMap<Integer, String>();
            this.fileId = fileId;
        }

        public HashMap<Integer, String> getFileMapping() {
            return this.fileMapping;
        }

        public void run() {
            for(int i = start; i < end; i++) {
                final File currentFile = files[i];
                final Path inputPath = new Path(currentFile.getAbsolutePath());
                final Path outputPath = new Path(outputDirName+"/"+inputPath.getName());
                final SequenceFile.Reader reader;
                final SequenceFile.Writer writer;
                try {
                    reader = new SequenceFile.Reader(fs, inputPath, conf);
                } catch(IOException io) {
                    continue;
                }
                try {
                    writer = SequenceFile.createWriter(fs, conf, outputPath, 
                            org.apache.hadoop.io.IntWritable.class,
                            org.apache.hadoop.io.SparseVectorWritable.class);
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }

                final Text inputKey = new Text();
                final org.apache.mahout.math.VectorWritable inputVal = 
                    new org.apache.mahout.math.VectorWritable();

                try {
                    while(reader.next(inputKey, inputVal)) {
                        final org.apache.mahout.math.Vector vec = inputVal.get();
                        final int length = vec.getNumNonZeroElements();
                        final TreeSet<IntDoublePair> agg = new TreeSet<IntDoublePair>();
                        Iterator<org.apache.mahout.math.Vector.Element> iter = 
                            vec.nonZeroes().iterator();
                        int countEles = 0;
                        while(iter.hasNext()) {
                            org.apache.mahout.math.Vector.Element ele = iter.next();
                            agg.add(new IntDoublePair(ele.index(), ele.get()));
                            countEles++;
                        }
                        final int[] indices = new int[countEles];
                        final double[] vals = new double[countEles];
                        countEles = 0;
                        for(IntDoublePair p : agg) {
                            indices[countEles] = p.i();
                            vals[countEles] = p.d();
                            countEles++;
                        }
                        int thisFileId = fileId.getAndAdd(1);
                        writer.append(new IntWritable(thisFileId),
                                new SparseVectorWritable(indices, vals));
                        fileMapping.put(thisFileId, inputKey.toString());
                    }
                    writer.close();
                    reader.close();
                } catch(IOException io) {
                    throw new RuntimeException(io);
                }
                System.out.println("Done with "+(i-start+1)+"/"+len);
            }
        }

        public static class IntDoublePair implements Comparable<IntDoublePair> {
            private final int i;
            private final double d;

            public IntDoublePair(int i, double d) {
                this.i = i; this.d = d;
            }

            public int i() { return this.i; }
            public double d() { return this.d; }
            @Override
            public int compareTo(IntDoublePair other) {
                if (this.i() < other.i()) {
                    return -1;
                } else if (this.i() > other.i()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
