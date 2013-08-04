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

public class TestMapInputSvec {
    public static class TestReducer extends IntIntIntIntHadoopCLReducerKernel {
        protected void reduce(int key, HadoopCLIntValueIterator valIter) {
            for(int i = 0; i < valIter.nValues(); i++) {
                write(key, valIter.get());
                valIter.next();
            }
        }
        public int getOutputPairsPerInput() {
            return -1;
        }
        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.JAVA, 10);
        }
        public Device.TYPE[] validDevices() {
            return new Device.TYPE[] { Device.TYPE.JAVA };
        }
    }

    public static class TestMapper extends IntSvecIntIntHadoopCLMapperKernel {
        protected void map(int key, int[] indices, double[] vals, int len) {
            write(key, (int)vals[1]);
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

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
        Job job = new Job(conf, "test-map-svec-input");
        job.setJarByClass(TestMapInputSvec.class);

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
