import java.util.*;
import java.lang.*;

import java.io.IOException;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;


public class Pairs {
  public static class PairsMapper extends IntSvecIntIntHadoopCLMapperKernel{
    @Override
    protected void map(int key, int[] indices, double[] val, int len) {
      for(int i = 0; i < len; i++)
        for(int j = 0; j < i; j++)
          write((int)val[i], (int)val[j]);
    }
    
    @Override
    public int getOutputPairsPerInput() {
      return -2;
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
  public static class PairsReducer extends IntIntIntSvecHadoopCLReducerKernel{
    @Override
    protected void reduce(int key, HadoopCLIntValueIterator valIter) {
      int len = 0;
      int[] valIndices = allocInt(valIter.nValues());
      double[] valVals = allocDouble(valIter.nValues());
      int preIndex;
      int curIndex;
      double counts = 0;
      int j = 0;

      preIndex = valIter.get();
      counts++;
      while(valIter.next()) {
        curIndex = valIter.get();
        if(curIndex == preIndex) {
          counts ++;
        }
        else{
          valIndices[j] = preIndex;
          valVals[j] = counts;
          counts = 1;
          preIndex = curIndex;
          j++;
        }
        valIndices[j] = curIndex;
        valVals[j] = counts;
        write(key, valIndices, valVals, j+1);
      }
    }
  @Override
    public int getOutputPairsPerInput() {
      return -2;
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
       Job job = new Job(conf, "Pairs");
       job.setJarByClass(Pairs.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(SparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(IntWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(PairsMapper.class);

       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(PairsReducer.class);

//       job.setInputFormatClass(SequenceFileInputFormat.class);
//       job.setOutputFormatClass(SequenceFileOutputFormat.class);

       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));

       long start = System.currentTimeMillis();
       job.waitForCompletion(true);
       long stop = System.currentTimeMillis();
       System.err.println("Execution Time "+(stop-start)+" ms");
    }
}
