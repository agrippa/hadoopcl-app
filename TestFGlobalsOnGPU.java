import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntIntIntIntHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntIntIntIntHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLIntValueIterator;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;
import org.apache.mahout.math.Vector;

public class TestFGlobalsOnGPU {
    public static class TestMapper extends IntIntIntIntHadoopCLMapperKernel {
        protected void map(int key, int val) {
            int[] indices = this.getGlobalIndices(0);
            float[] vals = this.getGlobalFVals(0);
            int length = this.globalsLength(0);
            write(key, indices[key % length] );
        }
        public int getOutputPairsPerInput() {
            return 1;
        }
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }
        public Device.TYPE[] validDevices() {
            return null;
        }
    }

    public static class TestReducer extends IntIntIntIntHadoopCLReducerKernel {
        protected void reduce(int key, HadoopCLIntValueIterator valIter) {
            valIter.seekTo(0);
            write(key, valIter.get());
        }
        public int getOutputPairsPerInput() {
            return 1;
        }
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }
        public Device.TYPE[] validDevices() {
            return new Device.TYPE[] { Device.TYPE.JAVA };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       FileSystem fs = FileSystem.get(conf);
       FileSystem localFs = FileSystem.getLocal(conf);

       int[] globalIndices = { 1, 2, 3 };
       double[] globalVals = { 4.0, 5.0, 6.0 };
       conf.addHadoopCLGlobal(globalIndices, globalVals);

       Job job = new Job(conf, "gpu-globals");
       job.setJarByClass(TestFGlobalsOnGPU.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(IntWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(IntWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(TestMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(TestReducer.class);

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
