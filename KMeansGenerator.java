import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.mahout.math.VectorWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.RandomAccessSparseVector;

public class KMeansGenerator {
    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 4) {
            System.err.println("usage: java KMeansGenerator output-folderninput-files inputs-per-file cluster-file");
            return;
        }

        String outputFolder = args[0];
        final int nFiles = Integer.parseInt(args[1]);
        final int perFile = Integer.parseInt(args[2]);
        String clusterFile = args[3];

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        List<Vector> clusters = new LinkedList<Vector>();
        final IntWritable clusterId = new IntWritable();
        VectorWritable cluster = new VectorWritable();
        Reader reader = new Reader(fs, new Path(clusterFile), conf);
        while (reader.next(clusterId, cluster)) {
            clusters.add(cluster.get());
            cluster = new VectorWritable();
        }

        Random rand = new Random(2340);
        int count = 0;
        for (int f = 0; f < nFiles; f++) {
            Writer writer = SequenceFile.createWriter(fs, conf, new Path(outputFolder+"/part-"+f),
                    IntWritable.class, VectorWritable.class);
            for (int i = 0; i < perFile; i++) {
                int memberCluster = rand.nextInt(clusters.size());
                Vector c = clusters.get(memberCluster);
                double cx = c.get(0);
                double cy = c.get(1);

                Vector v = new RandomAccessSparseVector(2);
                double x = (rand.nextDouble() * 2.0) - 1.0;
                double y = (rand.nextDouble() * 2.0) - 1.0;
                double magnitude = Math.sqrt(x * x + y * y);
                double length = Math.abs(rand.nextGaussian()) * 250;
                x = x / magnitude;
                y = y / magnitude;
                x = x * length;
                y = y * length;

                // double x = rand.nextGaussian() * (250);
                // double y = rand.nextGaussian() * (250);
                // while (Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0)) > 250) {
                //     x = rand.nextGaussian() * (250);
                //     y = rand.nextGaussian() * (250);
                // }

                v.set(0, x);
                v.set(1, y);
                Vector offset = c.plus(v);
                writer.append(new IntWritable(count), new VectorWritable(offset));
                count++;
            }
            writer.close();
        }
    }
}
