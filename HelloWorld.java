import java.util.*;
import java.lang.*;

import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;

public class HelloWorld {
    public static class HelloWorldMapper extends IntIntIntIntHadoopCLMapperKernel {
        @Override
        protected void map(int key, int val) {
            write(key, val * 2);
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

    public static class HelloWorldReducer extends IntIntIntIntHadoopCLReducerKernel {
        @Override
        protected void reduce(int key, HadoopCLIntValueIterator valIter) {
            write(key, valIter.get());
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

    public static void main(String[] args) throws IOException, InterruptedException,
           ClassNotFoundException {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);
       Job job = new Job(conf, "hello-world");
       job.setJarByClass(HelloWorld.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(IntWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(IntWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(HelloWorldMapper.class);

       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(HelloWorldReducer.class);

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
