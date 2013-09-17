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

/**
 * Converts a directory of <Text,VectorWritable> sequence files to
 * a directory of <Integer,SparseVectorWritable> sequence files with a special
 * sequence file in the output directory that matches the original Text keys
 * to the new Integer keys
 */

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

        AtomicInteger fileId = new AtomicInteger(0);
        int nThreads = 12;
        TransformRunner[] runners = new TransformRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new TransformRunner(hadoopclDirName, fileId);
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(tfidfDirName),
                conf, fs, org.apache.hadoop.io.Text.class,
                org.apache.mahout.math.VectorWritable.class);
        executor.run(runners);

        final Path fileMappingPath = new Path(hadoopclDirName+"/filemapping");
        final SequenceFile.Writer fileMappingWriter;
        try {
            fileMappingWriter = SequenceFile.createWriter(fs, conf, 
                fileMappingPath, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.Text.class);

            for(int t = 0; t < nThreads; t++) {
                HashMap<Integer, String> mapping = runners[t].getFileMapping();
                for (Map.Entry<Integer, String> pair : mapping.entrySet()) {
                    Integer id = pair.getKey();
                    String filename = pair.getValue();
                    fileMappingWriter.append(new IntWritable(id.intValue()),
                            new Text(filename));
                }
            }

            fileMappingWriter.close();
        } catch(IOException io) {
            throw new RuntimeException(io);
        }
    }

    public static class TransformRunner extends
            ParallelFileIterator.ParallelFileRunner {
        private final AtomicInteger fileId;
        private final HashMap<Integer, String> fileMapping;
        private final String outputDirName;

        public TransformRunner(String outputDirName, AtomicInteger fileId) {
            this.fileId = fileId;
            this.outputDirName = outputDirName;
            this.fileMapping = new HashMap<Integer, String>();
        }

        public HashMap<Integer, String> getFileMapping() {
            return this.fileMapping;
        }

        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final Path outputPath = new Path(outputDirName+"/"+inputPath.getName());
            final SequenceFile.Reader reader;
            final SequenceFile.Writer writer;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
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
            this.print("Done");
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
