import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntPsvecIntBsvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.IntPsvecIntPsvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntBsvecIntPsvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLPsvecValueIterator;
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

public class FuzzyKMeans {

    public static class FuzzyKMeansReducer 
        extends IntPsvecIntBsvecHadoopCLReducerKernel {
            public FuzzyKMeansReducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

            /*
             * This reducer first counts the number of unique indices present
             * in the input vectors, allocates sufficiently large output buffers
             * to store the final merge of all input vectors, and then iterates
             * through the input vectors in parallel, merging them into the final
             * output in order to maintain total ordering
             */
            protected void reduce(int cluster, HadoopCLPsvecValueIterator valsIter) {
                int totalNElements = 0;
                for (int i = 0; i < valsIter.nValues(); i++) {
                    valsIter.seekTo(i);
                    totalNElements += valsIter.currentVectorLength();
                }
                int[] outputIndices = allocInt(totalNElements);
                double[] outputVals = allocDouble(totalNElements);
                int[] preallocInt = allocInt(valsIter.nValues() * 2);
                double[] preallocDouble = allocDouble(valsIter.nValues() * 3);
                final int nOutput = merge(valsIter, outputIndices, outputVals,
                        totalNElements, preallocDouble, preallocInt);
                write(cluster, outputIndices, outputVals, nOutput);
            }

            private static final Map<Device.TYPE, String> fileMapping =
                new HashMap<Device.TYPE, String>();
            static {
                fileMapping.put(Device.TYPE.CPU, "fuzzy.reducer.cpu");
                fileMapping.put(Device.TYPE.GPU, "fuzzy.reducer.gpu");
            }
            @Override
                public Map<Device.TYPE, String> getKernelFile() { return fileMapping; }

            public void deviceStrength(DeviceStrength str) {
                str.add(Device.TYPE.JAVA, 10);
            }
            public Device.TYPE[] validDevices() {
                // return new Device.TYPE[] { Device.TYPE.JAVA };
                return null;
            }
        }
    public static class FuzzyKMeansCombiner extends IntPsvecIntPsvecHadoopCLReducerKernel {
        public FuzzyKMeansCombiner(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void reduce(int cluster, HadoopCLPsvecValueIterator valsIter) {
            int totalNElements = 0;
            double prob = 0.0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                valsIter.seekTo(i);
                totalNElements += valsIter.currentVectorLength();
                prob += valsIter.getProb();
            }
            prob /= valsIter.nValues();

            int[] outputIndices = allocInt(totalNElements);
            double[] outputVals = allocDouble(totalNElements);
            int[] preallocInt = allocInt(valsIter.nValues() * 2);
            double[] preallocDouble = allocDouble(valsIter.nValues() * 3);
            final int nOutput = merge(valsIter, outputIndices, outputVals,
                    totalNElements, preallocDouble, preallocInt);
            write(cluster, prob, outputIndices, outputVals, nOutput);
        }

        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "fuzzy.combiner.cpu");
            fileMapping.put(Device.TYPE.GPU, "fuzzy.combiner.gpu");
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


    public static class FuzzyKMeansMapper
            extends IntBsvecIntPsvecHadoopCLMapperKernel {
            public FuzzyKMeansMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

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
            final double m = 2.0;
            int[] outputIndices = allocInt(len);
            double[] outputVals = allocDouble(len);
            // double[] distances = allocDouble(this.nGlobals());

            int minDistIndex = -1;
            double minDist = -1.0;

            for (int i = 0; i < this.nGlobals(); i++) {
                int[] centroidIndices = this.getGlobalIndices(i);
                double[] centroidVals = this.getGlobalVals(i);
                int centroidVectorLength = this.globalsLength(i);

                double dist = distance(indices, vals, len, centroidIndices,
                        centroidVals, centroidVectorLength);
                // distances[i] = distance(indices, vals, len, centroidIndices,
                //         centroidVals, centroidVectorLength);
                if (i == 0 || minDist > dist) {
                    minDistIndex = i;
                    minDist = dist;
                }
            }

            double dist = minDist;
            if (dist == 0.0) dist = 0.0000000001;
            double denom = 0.0;
            for (int j = 0; j < this.nGlobals(); j++) {
                int[] centroidIndices = this.getGlobalIndices(j);
                double[] centroidVals = this.getGlobalVals(j);
                int centroidVectorLength = this.globalsLength(j);
                double eachCDist = distance(indices, vals, len, centroidIndices, centroidVals, centroidVectorLength);
                if (eachCDist == 0.0) eachCDist = 0.0000000001;
                denom += Math.pow(dist / eachCDist, m);
            }
            double probWeight = 1.0 / denom;

            for(int i = 0; i < len; i++) {
                outputIndices[i] = indices[i];
                outputVals[i] = vals[i];
            }

            write(minDistIndex, probWeight, outputIndices, outputVals, len);
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
            fileMapping.put(Device.TYPE.CPU, "fuzzy.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "fuzzy.mapper.gpu");
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
       Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed-sparse.64");
       // Path path = new Path("/home/yiskylee/hadoopcl-input/for-yiskylee/random-seed-sparse");
       SequenceFile.Reader reader = new SequenceFile.Reader(localFs, path, conf);
       IntWritable tmpKey = new IntWritable();
       BSparseVectorWritable tmpVal = new BSparseVectorWritable();

       int count = 0;
       while(reader.next(tmpKey, tmpVal)) { 
           conf.addHadoopCLGlobal(tmpVal.indices(), tmpVal.vals());
           count++;
       }

       Job job = new Job(conf, "fuzzy-kmeans");
       job.setJarByClass(FuzzyKMeans.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(BSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(PSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(FuzzyKMeansMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(FuzzyKMeansReducer.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(FuzzyKMeansCombiner.class);

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
