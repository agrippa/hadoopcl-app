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
import org.apache.hadoop.mapreduce.IntFsvecIntFsvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntBsvecIntFsvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLFsvecValueIterator;
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

public class NaiveBayes {
    public static class NaiveBayesMapper extends IntBsvecIntFsvecHadoopCLMapperKernel {
        public NaiveBayesMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void map(int label, int[] indices, double[] vals, int length) {
            // System.err.println("Processing mapper with key="+label+" and vector with length "+length);
            double labelWeight = referenceGlobalVal(0, label);
            double alphaI = 5.0;
            int numFeatures = globalsLength(1);
            double zSum = 0.0;
            for (int i = 0; i < length; i++) {
                zSum = zSum + vals[i];
            }
            double weight = Math.log((zSum + alphaI) / (labelWeight + alphaI * numFeatures));
            incrementWritable(0, label, (float)weight);
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
            fileMapping.put(Device.TYPE.CPU, "bayes.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "bayes.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class NaiveBayesCombiner extends
            IntFsvecIntFsvecHadoopCLReducerKernel {
        public NaiveBayesCombiner(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
              valsIter.seekTo(i);
              totalNElements += valsIter.currentVectorLength();
            }
            int[] outputIndices = allocInt(totalNElements);
            float[] outputVals = allocFloat(totalNElements);
            int[] preallocInt = allocInt(valsIter.nValues() * 2);
            float[] preallocFloat = allocFloat(valsIter.nValues() * 4);
            final int nOutput = merge(valsIter, outputIndices, outputVals,
                    totalNElements, preallocFloat, preallocInt);
            write(key, outputIndices, outputVals, nOutput);
        }

        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.CPU, 10);
        }
        public Device.TYPE[] validDevices() {
          return null;
        }
        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "bayes.combiner.cpu");
            fileMapping.put(Device.TYPE.GPU, "bayes.combiner.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static class NaiveBayesReducer extends
            IntFsvecIntFsvecHadoopCLReducerKernel {
        public NaiveBayesReducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
              valsIter.seekTo(i);
              totalNElements += valsIter.currentVectorLength();
            }
            int[] outputIndices = allocInt(totalNElements);
            float[] outputVals = allocFloat(totalNElements);
            int[] preallocInt = allocInt(valsIter.nValues() * 2);
            float[] preallocFloat = allocFloat(valsIter.nValues() * 4);
            final int nOutput = merge(valsIter, outputIndices, outputVals,
                    totalNElements, preallocFloat, preallocInt);
            write(key, outputIndices, outputVals, nOutput);
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
            fileMapping.put(Device.TYPE.CPU, "bayes.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "bayes.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);
       int nClusters = 1024;

       int[] labelWeightIndices = new int[nClusters];
       double[] labelWeightVals = new double[nClusters];
       for (int i = 0; i < labelWeightIndices.length; i++) {
           labelWeightIndices[i] = i;
           labelWeightVals[i] = 0.0;
       }

       int[] trainerIndices = new int[nClusters];
       float[] trainerVals = new float[nClusters];
       for (int i = 0; i < trainerIndices.length; i++) {
           trainerIndices[i] = i;
           trainerVals[i] = 0.0f;
       }

       byte[] bytes = Files.readAllBytes(Paths.get("/home-nis/mgrossman/hadoopcl-input/features.dump"));
       int nFeatures = bytes.length / 4;
       int[] featureWeightIndices = new int[nFeatures];
       double[] featureWeightVals = new double[nFeatures];

       for (int i = 0; i < featureWeightVals.length; i++) {
           featureWeightVals[i] = 1.0;
       }

       ByteBuffer.wrap(bytes).asIntBuffer().get(featureWeightIndices, 0, nFeatures);

       conf.addHadoopCLGlobal(labelWeightIndices, labelWeightVals);
       conf.addHadoopCLGlobal(featureWeightIndices, featureWeightVals);

       conf.addWritableHadoopCLGlobal(trainerIndices, trainerVals,
               TaskType.MAPPER);

       Job job = new Job(conf, "naive-bayes");
       ((JobConf)job.getConfiguration()).setJar("/home-nis/mgrossman/hadoopcl-app/NaiveBayes.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(FSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(FSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(NaiveBayesMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(NaiveBayesReducer.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(NaiveBayesReducer.class);

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
