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
import org.apache.hadoop.mapreduce.IntFloatIntFloatHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntFloatIntFloatHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLFloatValueIterator;
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

public class BlackscholesHCL2 {
    public static class BlackscholesHCL2Mapper extends IntFloatIntFloatHadoopCLMapperKernel {
        public BlackscholesHCL2Mapper(HadoopOpenCLContext c, Integer i) { super(c, i); }


        float phi(float X) {
            final float c1 = 0.319381530f;
            final float c2 = -0.356563782f;
            final float c3 = 1.781477937f;
            final float c4 = -1.821255978f;
            final float c5 = 1.330274429f;

            final float zero = 0.0f;
            final float one = 1.0f;
            final float two = 2.0f;
            final float temp4 = 0.2316419f;

            final float oneBySqrt2pi = 0.398942280f;

            float absX = Math.abs(X);
            float t = one / (one + temp4 * absX);

            float y = one - oneBySqrt2pi * (float)Math.exp(-X * X / two) * t * (c1 + t * (c2 + t * (c3 + t * (c4 + t * c5))));

            float result = (X < zero) ? (one - y) : y;

            return result;
        }

        @Override
        protected void map(int key, float val) {
            final float S_LOWER_LIMIT = 10.0f;

            final float S_UPPER_LIMIT = 100.0f;

            final float K_LOWER_LIMIT = 10.0f;

            final float K_UPPER_LIMIT = 100.0f;

            final float T_LOWER_LIMIT = 1.0f;

            final float T_UPPER_LIMIT = 10.0f;

            final float R_LOWER_LIMIT = 0.01f;

            final float R_UPPER_LIMIT = 0.05f;

            final float SIGMA_LOWER_LIMIT = 0.01f;

            final float SIGMA_UPPER_LIMIT = 0.10f;

            float put = 0.0f;
            float call = 0.0f;

            for(int incr = 100; incr >= 0; incr--) {
                float d1, d2;
                float phiD1 = 0.0f, phiD2 = 0.0f;
                float sigmaSqrtT;
                float KexpMinusRT = 0.0f;
                float two = 2.0f;
                float inRand = val + (incr * 0.01f);
                //float inRand = val;
                float S = S_LOWER_LIMIT * inRand + S_UPPER_LIMIT * (1.0f - inRand);
                float K = K_LOWER_LIMIT * inRand + K_UPPER_LIMIT * (1.0f - inRand);
                float T = T_LOWER_LIMIT * inRand + T_UPPER_LIMIT * (1.0f - inRand);
                float R = R_LOWER_LIMIT * inRand + R_UPPER_LIMIT * (1.0f - inRand);
                float sigmaVal = SIGMA_LOWER_LIMIT * inRand + SIGMA_UPPER_LIMIT * (1.0f - inRand);

                sigmaSqrtT = sigmaVal * (float)Math.sqrt(T);

                d1 = ((float)Math.log(S / K) + (R + sigmaVal * sigmaVal / two) * T) / sigmaSqrtT;
                d2 = d1 - sigmaSqrtT;

                KexpMinusRT = K * (float)Math.exp(-R * T);

                phiD1 = phi(d1);
                phiD2 = phi(d2);

                call = S * phiD1 - KexpMinusRT * phiD2;

                phiD1 = phi(-d1);
                phiD2 = phi(-d2);

                put= KexpMinusRT * phiD2 - S * phiD1;
            }

            write(key, put - call);
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
            fileMapping.put(Device.TYPE.CPU, "blackscholes.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "blackscholes.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class BlackscholesHCL2Reducer extends
            IntFloatIntFloatHadoopCLReducerKernel {
        public BlackscholesHCL2Reducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLFloatValueIterator valsIter) {
            write(key, valsIter.get());
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
            fileMapping.put(Device.TYPE.CPU, "blackscholes.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "blackscholes.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       Job job = new Job(conf, "blackscholes");
       // Davinci
       ((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/BlackscholesHCL2.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(FloatWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(FloatWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(BlackscholesHCL2Mapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(BlackscholesHCL2Reducer.class);

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
