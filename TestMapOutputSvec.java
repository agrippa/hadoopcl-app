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

public class TestMapOutputSvec {
    public static class TestReducer extends IntSvecIntIntHadoopCLReducerKernel {
        protected void reduce(int key, HadoopCLSvecValueIterator valIter) {
            write(key, valIter.getValIndices()[5]);
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

    public static class TestMapper extends IntSvecIntSvecHadoopCLMapperKernel {
        protected void map(int key, int[] indices, double[] vals, int len) {
            int[] outputIndices = allocInt(len);
            double[] outputVals = allocDouble(len);
            // for (int i =0 ; i < len; i++) {
            //     outputIndices[i] = indices[i];
            //     outputVals[i] = vals[i];
            // }
            for (int i = 0; i < len; i++) {
                outputIndices[i] = indices[i];
                outputVals[i] = 5.0 + (double)i;
            }
            write(key, outputIndices, outputVals, len);
            /*
            int[] outputIndices = allocInt(6);
            double[] outputVals = allocDouble(6);
            outputIndices[0] = key+0;
            outputIndices[1] = key+1;
            outputIndices[2] = key+2;
            outputIndices[3] = key+3;
            outputIndices[4] = key+4;
            outputIndices[5] = key+5;
            outputVals[0] = 1.0;
            outputVals[0] = 2.0;
            outputVals[0] = 3.0;
            outputVals[0] = 4.0;
            outputVals[0] = 5.0;
            outputVals[0] = 6.0;
            write(key, outputIndices, outputVals, 6);
            */
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
        Job job = new Job(conf, "test-map-svec-output");
        job.setJarByClass(TestMapOutputSvec.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(SparseVectorWritable.class);

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
