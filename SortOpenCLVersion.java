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

public class SortOpenCLVersion {

    public static class SortCLReducer extends IntLongIntLongHadoopCLReducerKernel {

        protected void reduce(int key, HadoopCLLongValueIterator valIter) {
            //bubbleSort(values, startValueOffset, stopValueOffset);
            for(int i = 0; i < valIter.nValues(); i++) {
                write(key, valIter.get());
                valIter.next();
            }
        }

        @Override
        public int getOutputPairsPerInput() {
            return -1;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return new Device.TYPE[] { Device.TYPE.JAVA };
        }
    }

    public static class SortCLMapper extends LongLongIntLongHadoopCLMapperKernel {
        protected void map(long val1, long val2) {
            long radixL = val1 >> 32;
            int radix = (int)radixL;
            write(radix, val1);

            radixL = val2 >> 32;
            radix = (int)radixL;
            write(radix, val2);
        }

        @Override
        public int getOutputPairsPerInput() {
            return 2;
        }

        @Override
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
            //str.add(TYPE.CPU, 1);
        }

        @Override
        public Device.TYPE[] validDevices() {
            return null;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
        Job job = new Job(conf, "sort");
        job.setJarByClass(SortOpenCLVersion.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setMapperClass(OpenCLMapper.class);
        job.setOCLMapperClass(SortCLMapper.class);

        job.setReducerClass(OpenCLReducer.class);
        job.setOCLReducerClass(SortCLReducer.class);

        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

//        job.setNumReduceTasks(0);

        long start = System.currentTimeMillis();
        job.waitForCompletion(true);
        long stop = System.currentTimeMillis();
        System.err.println("Execution Time "+(stop-start)+" ms");

    }

}
