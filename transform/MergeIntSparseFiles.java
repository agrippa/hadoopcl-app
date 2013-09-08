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

public class MergeIntSparseFiles extends FileMerger<IntWritable, SparseVectorWritable> {

    protected IntWritable instantiateKey() { return new IntWritable(); }
    protected SparseVectorWritable instantiateValue() {
        return new SparseVectorWritable();
    }
    public MergeIntSparseFiles(String[] args) {
        super("MergeIntSparseFiles", args);
    }

    public static void main(String[] args) {
        new MergeIntSparseFiles(args).doMerge();
    }
}
