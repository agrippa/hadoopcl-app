import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntBsvecIntBsvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntBsvecIntBsvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLSvecValueIterator;
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

public class MahoutKMeans {

    public static class MahoutKMeansReducer 
        extends IntBsvecIntBsvecHadoopCLReducerKernel {
            public MahoutKMeansReducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

            /*
             * This reducer first counts the number of unique indices present
             * in the input vectors, allocates sufficiently large output buffers
             * to store the final merge of all input vectors, and then iterates
             * through the input vectors in parallel, merging them into the final
             * output in order to maintain total ordering
             */
            protected void reduce(int key, HadoopCLSvecValueIterator valsIter) {
                int totalNElements = 0;
                for (int i = 0; i < valsIter.nValues(); i++) {
                  valsIter.seekTo(i);
                  totalNElements += valsIter.currentVectorLength();
                }
                int[] outputIndices = allocInt(totalNElements);
                double[] outputVals = allocDouble(totalNElements);
                int[] preallocInt = allocInt(valsIter.nValues() * 2);
                double[] preallocDouble = allocDouble(valsIter.nValues() * 2);
                final int nOutput = merge(valsIter, outputIndices, outputVals,
                        totalNElements, preallocDouble, preallocInt);
                write(key, outputIndices, outputVals, nOutput);
            }

        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "kmeans.reducer.cpu");
            fileMapping.put(Device.TYPE.GPU, "kmeans.reducer.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }


            public void deviceStrength(DeviceStrength str) {
                str.add(Device.TYPE.CPU, 10);
            }
            public Device.TYPE[] validDevices() {
                // return new Device.TYPE[] { Device.TYPE.JAVA };
                return null;
            }
        }

    public static class MahoutKMeansMapper
            extends IntBsvecIntBsvecHadoopCLMapperKernel {
            public MahoutKMeansMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected double vectorLengthSquared(double[] vals, int length) {
            double agg = 0.0;
            for(int i = 0; i < length; i++) {
                agg += (vals[i] * vals[i]);
            }
            return agg;
        }

        protected double dot(int[] index1, double[] val1, int length1,
                int[] index2, double[] val2, int length2) {

            int next = 0;
            double agg = 0.0;
            for(int i = 0; next < length2 && i < length1; i++) {
                int currentIndex = index1[i];

                while (next < length2 && index2[next] < currentIndex) {
                  next++;
                }
                if (next < length2 && index2[next] == currentIndex) {
                    agg += val1[i] * val2[next];
                }
            }
            return agg;
        }

        protected double distance(int[] thisIndices, double[] thisVals,
                int thisLen, int[] otherIndices,
                double[] otherVals, int otherLength) {

            double lengthSquaredv1 = 0.0;
            for (int i = 0; i < thisLen; i++) {
                lengthSquaredv1 += (thisVals[i] * thisVals[i]);
            }
            double lengthSquaredv2 = 0.0;
            for (int i = 0; i < otherLength; i++) {
                lengthSquaredv2 += (otherVals[i] * otherVals[i]);
            }

            double dotProduct = dot(thisIndices, thisVals, thisLen,
                    otherIndices, otherVals, otherLength);
            double denominator = Math.sqrt(lengthSquaredv1) * Math.sqrt(lengthSquaredv2);

            if(denominator < dotProduct) {
                denominator = dotProduct;
            }

            if(denominator == 0.0 && dotProduct == 0.0) {
                return 0.0;
            } 
            return 1.0 - dotProduct / denominator;
        }

        protected void map(int key, int[] indices, double[] vals, int len) {

            int[] outputIndices = allocInt(len);
            double[] outputVals = allocDouble(len);

            double minDist = -1.0;
            int closestCluster = -1;
            for (int i = 0; i < this.nGlobals(); i++) {
                int[] centroidIndices = this.getGlobalIndices(i);
                double[] centroidVals = this.getGlobalVals(i);
                int centroidVectorLength = this.globalsLength(i);

                double dist = distance(indices, vals, len,
                        centroidIndices, centroidVals, centroidVectorLength);
                if(closestCluster == -1 || minDist > dist ){
                    minDist = dist;
                    closestCluster = i;
                }
            }

            for(int i = 0; i < len; i++) {
                outputIndices[i] = indices[i];
                outputVals[i] = vals[i];
            }
            write(closestCluster, outputIndices, outputVals, len);
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
            fileMapping.put(Device.TYPE.CPU, "kmeans.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "kmeans.mapper.gpu");
        }
        @Override
        public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }
    }

    public static void main(String[] args) throws IOException, InterruptedException,
           ClassNotFoundException {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       FileSystem fs = FileSystem.get(conf);
       FileSystem localFs = FileSystem.getLocal(conf);
       Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed-sparse");
       // Path path = new Path("/home/yiskylee/hadoopcl-input/for-yiskylee/random-seed-sparse");
       SequenceFile.Reader reader = new SequenceFile.Reader(localFs, path, conf);
       IntWritable tmpKey = new IntWritable();
       BSparseVectorWritable tmpVal = new BSparseVectorWritable();

       int count = 0;
       while(reader.next(tmpKey, tmpVal)) { 
           conf.addHadoopCLGlobal(tmpVal.indices(), tmpVal.vals());
           count++;
       }

       Job job = new Job(conf, "mahout-kmeans");
       job.setJarByClass(MahoutKMeans.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(BSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(BSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(MahoutKMeansMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(MahoutKMeansReducer.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(MahoutKMeansReducer.class);

       job.setInputFormatClass(SequenceFileInputFormat.class);
       job.setOutputFormatClass(SequenceFileOutputFormat.class);

       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));

       long start = System.currentTimeMillis();
       job.waitForCompletion(true);
       long stop = System.currentTimeMillis();
       System.err.println("Execution Time "+(stop-start)+" ms");
       System.err.println("# clusters = "+count);
   }
}
