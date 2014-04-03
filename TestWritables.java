import java.util.*;
import java.lang.*;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntFsvecIntFsvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntIntIntFsvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLFsvecValueIterator;
import org.apache.hadoop.mapreduce.HadoopOpenCLContext;
import com.amd.aparapi.Kernel.TaskType;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;
import org.apache.mahout.math.Vector;
import org.apache.mahout.clustering.iterator.ClusterWritable;

public class TestWritables {

    public static class TestWritableMapper extends IntIntIntFsvecHadoopCLMapperKernel {
        public TestWritableMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void map(int key, int val) {
            incrementWritable(0, key, 1.0f);
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
            fileMapping.put(Device.TYPE.CPU, "writable.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "writable.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class TestWritableReducer extends IntFsvecIntFsvecHadoopCLReducerKernel {
        public TestWritableReducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int[] indices = allocInt(valsIter.currentVectorLength());
            float[] vals = allocFloat(valsIter.currentVectorLength());

            int[] inIndices = valsIter.getValIndices();
            float[] inVals = valsIter.getValVals();

            for (int i = 0; i < valsIter.currentVectorLength(); i++) {
                indices[i] = inIndices[i];
                vals[i] = inVals[i];
            }
            write(key, indices, vals, valsIter.currentVectorLength());
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
            fileMapping.put(Device.TYPE.CPU, "writable.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "writable.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       int[] indices = new int[100];
       float[] vals = new float[indices.length];
       for (int i = 0; i < indices.length; i++) {
           indices[i] = i;
           vals[i] = 0.0f;
       }
       conf.addWritableHadoopCLGlobal(indices, vals, TaskType.MAPPER);

       Job job = new Job(conf, "test-writable");
       ((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/TestWritables.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(FSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(FSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(TestWritableMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(TestWritableReducer.class);

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
