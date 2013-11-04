package merge;

import org.apache.hadoop.io.Text;
import org.apache.mahout.math.VectorWritable;

public class MergeTextVector extends FileMerger<Text, VectorWritable> {
    protected Text instantiateKey() { return new Text(); }
    protected VectorWritable instantiateValue() { return new VectorWritable(); }
    public MergeTextVector(String[] args) {
        super("MergeTextVector", args);
    }

    public static void main(String[] args) {
        new MergeTextVector(args).doMerge();
    }
}
