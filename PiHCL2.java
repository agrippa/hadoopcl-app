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

public class PiHCL2 {
    public static class PiHCL2Mapper extends LongLongIntLongHadoopCLMapperKernel {
        public PiHCL2Mapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void map(long key, long val) {
            long m_z = key;
            long m_w = val;
            long nInside = 0;
            long nOutside = 0;

            for(int i = 0; i < 5000; i++) {
                m_z = 36969 * (m_z & 65535) + (m_z >> 16);
                m_w = 18000 * (m_w & 65535) + (m_w >> 16);
                long tmp = (m_z << 16);
                tmp = (tmp & 4294967295L);
                tmp = tmp + m_w;
                double x = (tmp + 1.0) * 2.328306435454494e-10;

                m_z = 36969 * (m_z & 65535) + (m_z >> 16);
                m_w = 18000 * (m_w & 65535) + (m_w >> 16);
                tmp = (m_z << 16);
                tmp = (tmp & 4294967295L);
                tmp = tmp + m_w;
                double y = (tmp + 1.0) * 2.328306435454494e-10;

                x = x - 0.5;
                y = y - 0.5;
                if(x * x + y * y > 0.25) {
                    nOutside = nOutside + 1;
                } else {
                    nInside = nInside + 1;
                }
            }

            write(0, nOutside);
            write(1, nInside);

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
            fileMapping.put(Device.TYPE.CPU, "pi.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "pi.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class PiHCL2Reducer extends
            IntLongIntLongHadoopCLReducerKernel {
        public PiHCL2Reducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLLongValueIterator valsIter) {
            long count = 0;
            do {
                count += valsIter.get();
            } while (valsIter.next());
            write(key, count);
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
            fileMapping.put(Device.TYPE.CPU, "pi.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "pi.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       Job job = new Job(conf, "pi");
       // Davinci
       ((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/PiHCL2.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(LongWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(LongWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(PiHCL2Mapper.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(PiHCL2Reducer.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(PiHCL2Reducer.class);

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
