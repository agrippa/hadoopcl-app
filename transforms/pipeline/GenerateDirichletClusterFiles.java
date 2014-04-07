package pipeline;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.BSparseVectorWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.dirichlet.UncommonDistributions;

public class GenerateDirichletClusterFiles {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("usage: java GenerateDirichletClusterFiles input-clusters output-file");
            return;
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(args[0]), conf);
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, new Path(args[1]),
                IntWritable.class, BSparseVectorWritable.class);

        IntWritable key = new IntWritable();
        BSparseVectorWritable val = new BSparseVectorWritable();
        while (reader.next(key, val)) {
            int[] radiusIndices = new int[val.size()];
            double[] radiusVals = new double[val.size()];

            for (int i = 0; i < val.size(); i++) {
                radiusIndices[i] = val.indices()[i];
                radiusVals[i] = UncommonDistributions.rNorm(0, 1);
            }

            writer.append(key, new BSparseVectorWritable(radiusIndices, radiusVals));
        }

        reader.close();
        writer.close();
    }
}
