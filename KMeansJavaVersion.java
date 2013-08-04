import java.io.IOException;
import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;

public class KMeansJavaVersion {

    private static class Point {
        private final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double x() {
            return this.x;
        }

        public double y() {
            return this.y;
        }
    }

    private static boolean contains(int[] arr, int val) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == val) return true;
        }
        return false;
    }

    private static List<Point> getStartingCentroids(int N, Configuration conf) throws IOException {
        Random rand = new Random();
        List<Point> points = new ArrayList<Point>();

        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("kmeans.input/file.0"), conf);

        DoubleWritable X = new DoubleWritable();
        DoubleWritable Y = new DoubleWritable();

        int index = 0;
        while(reader.next(X, Y)) {
            points.add(new Point(X.get(), Y.get()));
            index++;
            if(index >= N) break;
        }

        return points;
    }

    private static void setCentroidProperties(List<Point> points, Configuration conf) {
        int index = 0;
        for(Point point : points) {
            conf.set("opencl.properties."+index, Double.toString(point.x()));
            conf.set("opencl.properties."+(index+1), Double.toString(point.y()));
            index = index + 2;
        }
    }
/*
    private static List<Point> getNewCentroidsFromFS(Configuration conf, int iter, int nIters) throws IOException {
        List<Point> result = new ArrayList<Point>();
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("kmeans.output/part-r-00000"), conf);

        DoubleWritable X = new DoubleWritable();
        DoubleWritable Y = new DoubleWritable();

        while(reader.next(X, Y)) {
            result.add(new Point(X.get(), Y.get()));
        }

        if(iter < nIters-1) {
            Path outputDirectory = new Path("kmeans.output");
            fs.delete(outputDirectory, true);
        }
        return result;
    }
    */

    public static class KMeansJavaReducer extends Reducer<IntWritable, PairWritable, DoubleWritable, DoubleWritable> {

        private double dist(double x1, double y1, double x2, double y2) {
            double xdiff = x2 - x1;
            double ydiff = y2 - y1;
            return Math.sqrt(xdiff*xdiff + ydiff*ydiff);
        }

        public void reduce(IntWritable cluster, Iterable<PairWritable> values, Context context) 
                throws IOException, InterruptedException {
            double sumX = 0.0;
            double sumY = 0.0;
            int count = 0;

            List<Double> Xs = new ArrayList<Double>();
            List<Double> Ys = new ArrayList<Double>();

            for(PairWritable arr : values) {
                double a = arr.getVal1();
                double b = arr.getVal2();
                sumX += a;
                sumY += b;
                Xs.add(a);
                Ys.add(b);
                count = count + 1;
            }
            //System.err.println("DIAGNOSTICS: In reducer, "+count+" values for cluster "+cluster.get());
           
           
            double avgX = sumX / (double)count;
            double avgY = sumY / (double)count;

            double closest_x = -1.0;
            double closest_y = -1.0;
            double closest_dist = 1000000.0;

            count = 0;
            for(int index = 0; index < Xs.size(); index++) {
                double dist = dist(avgX, avgY, Xs.get(index), Ys.get(index));
                if(dist < closest_dist) {
                    closest_dist = dist;
                    closest_x = Xs.get(index); closest_y = Ys.get(index);
                }
                count = count + 1;
            }
            
            context.write(new DoubleWritable(closest_x), new DoubleWritable(closest_y));
        }
    }

    public static class KMeansJavaMapper extends Mapper<DoubleWritable, DoubleWritable, IntWritable, PairWritable> {

        private static List<Point> currentCentroids = new ArrayList<Point>();

        private double dist(double x1, double y1, double x2, double y2) {
            double xdiff = x2 - x1;
            double ydiff = y2 - y1;
            return Math.sqrt(xdiff*xdiff + ydiff*ydiff);
        }

        public void map(DoubleWritable X, DoubleWritable Y, Context context) 
                throws IOException, InterruptedException {

            if(currentCentroids.isEmpty()) {
                Configuration conf = context.getConfiguration();
                String str1 = null;
                int index = 0;
                while((str1 = conf.get("opencl.properties."+index)) != null) {
                    String str2 = conf.get("opencl.properties."+(index+1));
                    currentCentroids.add(new Point(Double.parseDouble(str1), Double.parseDouble(str2)));
                    index = index + 2;
                }
            }

            double x = X.get();
            double y = Y.get();

            int closest_index = 0;
            double closest_dist = dist(x, y, currentCentroids.get(0).x(), currentCentroids.get(0).y());

            for(int i = 1; i < currentCentroids.size(); i++) {
                double dist = dist(x, y, currentCentroids.get(i).x(), currentCentroids.get(i).y());
                if(dist < closest_dist) {
                    closest_index = i;
                    closest_dist = dist;
                }
            }

            context.write(new IntWritable(closest_index), new PairWritable(x, y));
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
        //conf.set("mapred.compress.map.output", "true");
        //conf.set("mapred.map.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");

        int nIters = 1;
        int N = 20000;
        List<Point> centroids = getStartingCentroids(N, conf);
        setCentroidProperties(centroids, conf);

        //for(int i = 0; i < nIters; i++) 
        {

            Job job = new Job(conf, "kmeans");
            job.setJarByClass(KMeansJavaVersion.class);

            job.setOutputKeyClass(DoubleWritable.class);
            job.setOutputValueClass(DoubleWritable.class);

            job.setMapOutputKeyClass(IntWritable.class);
            job.setMapOutputValueClass(PairWritable.class);

            job.setMapperClass(KMeansJavaMapper.class);
            job.setReducerClass(KMeansJavaReducer.class);

            job.setInputFormatClass(SequenceFileInputFormat.class);
            job.setOutputFormatClass(SequenceFileOutputFormat.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            long start = System.currentTimeMillis();
            job.waitForCompletion(true);
            long stop = System.currentTimeMillis();
            System.err.println("Execution Time "+(stop-start)+" ms");

            //centroids = getNewCentroidsFromFS(conf, i, nIters);
            //setCentroidProperties(centroids, conf);
        }
    }

}
