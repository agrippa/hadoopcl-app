import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.Job;
import org.apache.mahout.math.RandomAccessSparseVector;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import org.apache.mahout.math.VectorWritable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.function.Functions;
import org.apache.hadoop.fs.Path;
import java.util.Iterator;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class KMeansClusterGenerator {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            System.out.println("usage: java KMeansClusterGenerator nclusters output-file");
            return;
        }

        int nClusters = Integer.parseInt(args[0]);
        Path outPath = new Path(args[1]);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Random rand = new Random();
        Writer out = SequenceFile.createWriter(fs, conf, outPath,
                IntWritable.class, VectorWritable.class);
        for (int i = 0; i < nClusters; i++) {
            Vector center = new RandomAccessSparseVector(2);
            center.set(0, rand.nextDouble() * KMeansUtil.maxMagnitude);
            center.set(1, rand.nextDouble() * KMeansUtil.maxMagnitude);
            System.out.println(center.get(0)+" "+center.get(1));
            out.append(new IntWritable(i), new VectorWritable(center));
        }
        out.close();
    }
}
