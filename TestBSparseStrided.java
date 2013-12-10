import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntDoubleIntDoubleHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntBsvecIntDoubleHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLDoubleValueIterator;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;
import org.apache.mahout.math.Vector;
import org.apache.mahout.clustering.iterator.ClusterWritable;

public class TestBSparseStrided {

    public static class TestBSparseStridedMapper extends IntBsvecIntDoubleHadoopCLMapperKernel {
        protected void map(int key, int[] indices, double[] vals, int len) {
            // double sum = 0.0;
            // for (int iter = 0; iter < 1000; iter++) {
            //     for (int i = 0 ; i < len; i++) {
            //         sum = sum + vals[i];
            //     }
            // }
            // write(key, sum);
            write(key, (double)indices[1]);
        }
        public int getOutputPairsPerInput() { return 1; }
        public Device.TYPE[] validDevices() { return null; }
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }
    }

    public static class TestBSparseStridedReducer extends IntDoubleIntDoubleHadoopCLReducerKernel {
        protected void reduce(int key, HadoopCLDoubleValueIterator valIter) {
            valIter.seekTo(0);
            write(key, valIter.get());
        }
        public int getOutputPairsPerInput() { return 1; }
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }
        public Device.TYPE[] validDevices() {
            return new Device.TYPE[] { Device.TYPE.JAVA };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException,
           ClassNotFoundException {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       FileSystem fs = FileSystem.get(conf);

       Job job = new Job(conf, "test-bsparse-strided");
       job.setJarByClass(TestBSparseStrided.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(DoubleWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(DoubleWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(TestBSparseStridedMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(TestBSparseStridedReducer.class);

       job.setInputFormatClass(SequenceFileInputFormat.class);
       job.setOutputFormatClass(SequenceFileOutputFormat.class);

       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));

       long start = System.currentTimeMillis();
       job.waitForCompletion(true);
       long stop = System.currentTimeMillis();
       System.err.println("Execution Time "+(stop-start)+" ms");
   }
}
