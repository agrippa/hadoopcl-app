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

public class KMeans {

    private static final String CLUSTERS_PATH = "/user/jmg3/clusters";

    public static class Map extends Mapper<IntWritable, VectorWritable, IntWritable, VectorWritable> {

        private HashMap<IntWritable, VectorWritable> clusters = new HashMap<IntWritable, VectorWritable>();

        public void setup(Context context) throws IOException, InterruptedException {
            Reader in = new Reader(FileSystem.get(context.getConfiguration()), new Path(CLUSTERS_PATH), context.getConfiguration());
            IntWritable clusterId = new IntWritable();
            VectorWritable clusterCentroid = new VectorWritable();
            while (in.next(clusterId, clusterCentroid)) {
                clusters.put(clusterId, clusterCentroid);
                clusterId = new IntWritable();
                clusterCentroid = new VectorWritable();
            }
            in.close();
        }

        @Override
        public void map(IntWritable unused, VectorWritable pointWrapper, Context context) throws IOException, InterruptedException {
            Vector point = pointWrapper.get();
            Iterator<Entry<IntWritable, VectorWritable>> iter = clusters.entrySet().iterator();
            Entry<IntWritable, VectorWritable> ele = iter.next(); // assume at least one cluster
            double minDistance = point.getDistanceSquared(ele.getValue().get());
            int minCluster = ele.getKey().get();

            while (iter.hasNext()) {
                ele = iter.next();
                double distance = point.getDistanceSquared(ele.getValue().get());
                if (distance < minDistance) {
                    minDistance = distance;
                    minCluster = ele.getKey().get();
                }
            }

            context.write(new IntWritable(minCluster), pointWrapper);
        }
    }

    public static class Reduce extends Reducer<IntWritable, VectorWritable, IntWritable, VectorWritable> {

        @Override
        public void reduce(IntWritable clusterId, Iterable<VectorWritable> points, Context context) throws IOException, InterruptedException {
            Iterator<VectorWritable> iter = points.iterator();
            Vector newCentroid = iter.next().get().clone();
            int countPoints = 1;

            while (iter.hasNext()) {
                newCentroid.assign(iter.next().get(), Functions.PLUS);
                countPoints++;
            }

            context.write(clusterId, new VectorWritable(newCentroid.divide(countPoints)));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        int nClusters = 10;

        if (args.length > 0) nClusters = Integer.parseInt(args[0]);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Job job = new Job(conf, "kmeans");
        job.setJarByClass(KMeans.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(VectorWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(VectorWritable.class);

        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path("input"));
        FileOutputFormat.setOutputPath(job, new Path("output"));

        job.waitForCompletion(true);
    }
}
