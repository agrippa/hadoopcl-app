package pipeline;

import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.conf.*;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile;

public class SubsetClusters {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("usage: java SubsetClusters input-file output-file n");
            return;
        }

        int N = Integer.parseInt(args[2]);
        int count = 0;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(args[0]);
        Path outPath = new Path(args[1]);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, outPath,
                Text.class, ClusterWritable.class);
        Text key = new Text();
        ClusterWritable val = new ClusterWritable();
        while (reader.next(key, val) && count < N) {
            writer.append(key, val);
            count++;
        }
        System.err.println("Wrote "+count+" kv-pairs");
        reader.close();
        writer.close();
    }
}
