package merge;

import org.apache.hadoop.io.IntWritable;
import org.apache.mahout.math.VectorWritable;

public class MergeIntVector extends FileMerger<IntWritable, VectorWritable> {
    protected IntWritable instantiateKey() { return new IntWritable(); }
    protected VectorWritable instantiateValue() { return new VectorWritable(); }
    public MergeIntVector(String[] args) {
        super("MergeIntVector", args);
    }

    public static void main(String[] args) {
        new MergeIntVector(args).doMerge();
    }
}
