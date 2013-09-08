import org.apache.hadoop.io.*;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import java.io.File;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.util.LinkedList;

public class FileMerger<KeyType extends Writable, ValueType extends Writable> {
    final private String inputFolder;
    final private String outputFolder;
    final private int nOutputFiles;

    protected abstract KeyType instantiateKey();
    protected abstract ValueType instantiateValue();

    protected FileMerger(String callingClass, String[] args) {
        if (args.length != 3) {
            usage(callingClass);
        }

        this.inputFolder = args[0];
        this.outputFolder = args[1];
        this.nOutputFiles = Integer.parseInt(args[2]);

        FileUtils.cleanDirectory(new File(outputFolder));
    }

    protected void usage(String className) {
        System.out.println("usage: java "+className+
                " input-dir output-dir n-output-files");
        System.exit(1);
    }

    protected long countPairs(List<File> files) throws IOException {
        System.out.println("Counting inputs");
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
            final KeyType key = instantiateKey();
            final ValueType val = instantiateValue();

            try {
                while (reader.next(key, val)) {
                    count++;
                }
            } catch(IOException io) {
                throw new RuntimeException(io);
            }

            reader.close();
            countFiles++;
            System.out.println("File "+f.getAbsolutePath()+" ("+countFiles+"/"+
                    files.size()+") has "+(count-atStart)+" pairs, "+count+
                    " so far");
        }
        return count;
    }

    protected List<File> filterOutNonSeqFiles(File[] inputs) {
        final KeyType key = instantiateKey();
        final ValueType val = instantiateValue();

        List<File> filtered = new LinkedList<File>();
        SequenceFile.Reader reader;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        for (File f : inputs) {
            boolean filter = false;
            try {
                reader = new SequenceFile.Reader(fs,
                        new Path(f.getAbsolutePath()), conf);
                reader.next(key, val);
            } catch(Exception e) {
                filter = true;
            }

            if (!filter) {
                filtered.add(f);
            }
        }
        return filtered;
    }
}
