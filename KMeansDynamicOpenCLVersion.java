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
import com.amd.aparapi.device.Device;

public class KMeansDynamicOpenCLVersion {

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
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("kmeansdyn.input/file.0"), conf);

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
        for(Point point : points) {
            conf.addHadoopCLGlobal(new int[] { 0, 1 }, new double[] { point.x(), point.y() });
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

    public static class KMeansOpenCLReducer extends IntPairDoubleDoubleHadoopCLReducerKernel {

        private double dist(double x1, double y1, double x2, double y2) {
            double xdiff = x2 - x1;
            double ydiff = y2 - y1;
            return Math.sqrt(xdiff*xdiff + ydiff*ydiff);
        }

        public void reduce(int cluster, HadoopCLPairValueIterator valIter) {
            double sumX = 0.0;
            double sumY = 0.0;

            for(int i = 0; i < valIter.nValues(); i++) {
                sumX += valIter.getVal1();
                sumY += valIter.getVal2();
                valIter.next();
            }
           
            double avgX = sumX / (double)valIter.nValues();
            double avgY = sumY / (double)valIter.nValues();

            double closest_x = -1.0;
            double closest_y = -1.0;
            double closest_dist = 1000000.0;
            double dist;

            for(int i = 0; i < valIter.nValues(); i++) {
                dist = dist(avgX, avgY, valIter.getVal1(), valIter.getVal2());
                if(dist < closest_dist) {
                    closest_dist = dist;
                    closest_x = valIter.getVal1(); closest_y = valIter.getVal2();
                }
                valIter.next();
            }

            write(closest_x, closest_y);
        }

        @Override
        public int getOutputPairsPerInput() {
            return 1;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return null;
        }
    }

    public static class KMeansOpenCLMapper extends DoubleDoubleIntPairHadoopCLMapperKernel {

        private double dist(double x1, double y1, double x2, double y2) {
            double xdiff = x2 - x1;
            double ydiff = y2 - y1;
            return Math.sqrt(xdiff*xdiff + ydiff*ydiff);
        }

        public void map(double x, double y) {
            int closest_index = 0;
            double closest_dist = dist(x, y, getGlobalVals(0)[0], getGlobalVals(0)[1]);
            double dist;

            for(int i = 1; i < nGlobals(); i++) {
                dist = dist(x, y, getGlobalVals(i)[0], getGlobalVals(i)[1]);
                if(dist < closest_dist) {
                    closest_index = i;
                    closest_dist = dist;
                }
            }
            //if(!reserveOutput()) return;
            write(closest_index, x, y);
        }

        @Override
        public int getOutputPairsPerInput() {
            return 1;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return null;
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

            Job job = new Job(conf, "kmeansdyn");
            job.setJarByClass(KMeansDynamicOpenCLVersion.class);

            job.setOutputKeyClass(DoubleWritable.class);
            job.setOutputValueClass(DoubleWritable.class);

            job.setMapOutputKeyClass(IntWritable.class);
            job.setMapOutputValueClass(PairWritable.class);

            job.setMapperClass(OpenCLMapper.class);
            job.setOCLMapperClass(KMeansOpenCLMapper.class);

            job.setReducerClass(OpenCLReducer.class);
            job.setOCLReducerClass(KMeansOpenCLReducer.class);

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
