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
import org.apache.hadoop.mapreduce.IntLongIntLongHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.LongLongIntLongHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLLongValueIterator;
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

public class SortHCL2 {
    public static class SortHCL2Mapper extends LongLongIntLongHadoopCLMapperKernel {
        public SortHCL2Mapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void map(long val1, long val2) {
            long radixL = val1 >> 32;
            int radix = (int)radixL;
            write(radix, val1);

            radixL = val2 >> 32;
            radix = (int)radixL;
            write(radix, val2);
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
            fileMapping.put(Device.TYPE.CPU, "sort.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "sort.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class SortHCL2Reducer extends
            IntLongIntLongHadoopCLReducerKernel {
        public SortHCL2Reducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLLongValueIterator valIter) {
            for(int i = 0; i < valIter.nValues(); i++) {
                write(key, valIter.get());
                valIter.next();
            }
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
            fileMapping.put(Device.TYPE.CPU, "sort.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "sort.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       Job job = new Job(conf, "sort");
       // Davinci
       ((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/SortHCL2.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(LongWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(LongWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(SortHCL2Mapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(SortHCL2Reducer.class);

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
