import org.apache.hadoop.io.*;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import java.io.File;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.util.LinkedList;

/**
 * From a directory of <Int,SparseVector> files, this class merges those files
 * into a set number of files in another directory. Hadoop seems to do most of
 * its chunking based on the number of files you put, so chunking more files
 * together into fewer but larger files seems to have a massive effect on
 * performance.
 */

public class MergeIntSparseFiles {
    private static long countPairs(List<File> files) throws IOException {
        System.out.println("Counting files");
        long count = 0;
        int countFiles = 0;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        for (File f : files) {
            long atStart = count;
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
            } catch(IOException io) {
                continue;
            }
            final IntWritable key = new IntWritable();
            final SparseVectorWritable val = new SparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    count++;
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            reader.close();
            countFiles++;
            System.out.println("File "+f.getAbsolutePath()+" ("+countFiles+"/"+files.size()+") has "+(count-atStart)+" pairs, "+count+" so far");
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("usage: java MergeIntSparseFiles input-dir output-dir n-output-files");
            return;
        }

        String inputFolder = args[0];
        String outputFolder = args[1];
        int nOutputFiles = Integer.parseInt(args[2]);

        FileUtils.cleanDirectory(new File(outputFolder));

        File input = new File(inputFolder);
        File[] files = input.listFiles();
        List<File> actualFiles = new LinkedList<File>();
        for (File f : files) {
            if (f.getName().indexOf("crc") == -1) {
                actualFiles.add(f);
            }
        }
        long totalPairs = countPairs(actualFiles);
        long elementsPerFile = (totalPairs + nOutputFiles - 1) / nOutputFiles;
        System.out.println(totalPairs+" total, "+elementsPerFile+" per file");

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        long currentOutputFileIndex = 0;
        long nWrittenToCurrentFile = 0;
        SequenceFile.Writer writer = null;
        try {
            writer = SequenceFile.createWriter(fs, conf,
                new Path(outputFolder+"/merge-"+currentOutputFileIndex),
                IntWritable.class, SparseVectorWritable.class);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        for (File f : actualFiles) {
            SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
            } catch(IOException io) {
                System.out.println("Skipping "+f.getAbsolutePath());
                continue;
            }

            final IntWritable key = new IntWritable();
            final SparseVectorWritable val = new SparseVectorWritable();

            try {
                while (reader.next(key, val)) {
                    writer.append(key, val);
                    nWrittenToCurrentFile++;

                    if (nWrittenToCurrentFile == elementsPerFile) {
                        nWrittenToCurrentFile = 0;
                        currentOutputFileIndex++;

                        writer.close();
                        writer = SequenceFile.createWriter(fs, conf,
                                new Path(outputFolder+"/merge-"+currentOutputFileIndex),
                                IntWritable.class, SparseVectorWritable.class);
                    }
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
        writer.close();
    }
}
