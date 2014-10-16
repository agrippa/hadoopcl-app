import java.util.*;
import java.lang.*;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.ByteBuffer;
import com.amd.aparapi.Kernel.TaskType;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.map.OpenIntIntHashMap;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;
import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntPairDoubleDoubleHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.DoubleDoubleIntPairHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLPairValueIterator;
import org.apache.hadoop.mapreduce.HadoopOpenCLContext;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;
import org.apache.mahout.math.Vector;
import org.apache.mahout.clustering.iterator.ClusterWritable;

public class KMeansHCL2 {
    public static class KMeansHCL2Mapper extends DoubleDoubleIntPairHadoopCLMapperKernel {
        public KMeansHCL2Mapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

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

            write(closest_index, x, y);
        }

        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }
        public Device.TYPE[] validDevices() {
            return null;
        }
        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "old_kmeans.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "old_kmeans.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class KMeansHCL2Reducer extends
            IntPairDoubleDoubleHadoopCLReducerKernel {
        public KMeansHCL2Reducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        private double dist(double x1, double y1, double x2, double y2) {
            double xdiff = x2 - x1;
            double ydiff = y2 - y1;
            return Math.sqrt(xdiff*xdiff + ydiff*ydiff);
        }

        public void reduce(int cluster, HadoopCLPairValueIterator valIter /*double[] xValues, double[] yValues, int startOffset, int stopOffset*/) {
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

            valIter.seekTo(0);
            for(int i = 0; i < valIter.nValues(); i++) {
                dist = dist(avgX, avgY, valIter.getVal1(), valIter.getVal2());
                if(dist < closest_dist) {
                    closest_dist = dist;
                    closest_x = valIter.getVal1();
                    closest_y = valIter.getVal2();
                }
                valIter.next();
            }

            write(closest_x, closest_y);
        }

        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }
        public Device.TYPE[] validDevices() {
          return null;
        }
        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "old_kmeans.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "old_kmeans.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       int N = 20000;
       List<Point> centroids = getStartingCentroids(N, conf);
       setCentroidProperties(centroids, conf);

       Job job = new Job(conf, "oldkmeans");
       // Davinci
       ((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/KMeansHCL2.jar");

       job.setOutputKeyClass(DoubleWritable.class);
       job.setOutputValueClass(DoubleWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(PairWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(KMeansHCL2Mapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(KMeansHCL2Reducer.class);

       job.setInputFormatClass(SequenceFileInputFormat.class);
       job.setOutputFormatClass(SequenceFileOutputFormat.class);

       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));

       long start = System.currentTimeMillis();
       job.waitForCompletion(true);
       long stop = System.currentTimeMillis();
       System.err.println("Execution Time "+(stop-start)+" ms");
    }

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
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("old_kmeans.input/file.0"), conf);

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

}
