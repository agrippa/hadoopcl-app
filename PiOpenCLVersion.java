import java.util.*;
import java.lang.*;

import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;

public class PiOpenCLVersion {

    public static class PiCLMapper extends LongLongIntIntHadoopCLMapperKernel {

        @Override
        protected void map(long key, long val) {

            long m_z = key;
            long m_w = val;
            int nInside = 0;
            int nOutside = 0;

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

        @Override
        public int getOutputPairsPerInput() {
            return 2;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return null;
        }

        //@Override
        //public boolean doIntermediateReduction() {
        //    return true;
        //}
    }

    public static class PiCLReducer extends IntIntIntLongHadoopCLReducerKernel {
        @Override
        protected void reduce(int key, HadoopCLIntValueIterator valIter) {
            long count = 0;
            for(int i = 0; i < valIter.nValues(); i++) {
                count = count + valIter.get();
                valIter.next();
            }
            write(key, count);
        }

        @Override
        public int getOutputPairsPerInput() {
            return 1;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            //str.add(TYPE.CPU, 10);
            str.add(Device.TYPE.JAVA, 10);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return new Device.TYPE[] { Device.TYPE.JAVA };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
        Job job = new Job(conf, "pi");
        job.setJarByClass(PiOpenCLVersion.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setMapperClass(OpenCLMapper.class);
        job.setOCLMapperClass(PiCLMapper.class);

        //job.setReducerClass(BlackScholesCLReducer.class);
        job.setReducerClass(OpenCLReducer.class);
        job.setOCLReducerClass(PiCLReducer.class);

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
