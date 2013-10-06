import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import java.io.IOException;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.OpenCLReducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.IntSvecIntSvecHadoopCLReducerKernel;
import org.apache.hadoop.mapreduce.DeviceStrength;
import org.apache.hadoop.mapreduce.IntSvecIntSvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLSvecValueIterator;

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
            extends IntSvecIntSvecHadoopCLReducerKernel {

        /*
         * From the list of offsets in vectorIndices,
         * find the input sparse vector with the minimum
         * index value at the corresponding offset
         */
        protected int findMinIndexVector(int[] currentVectorMins,
                int nValues) {
            int minIndex = currentVectorMins[0];
            int vectorWithMinIndex = 0;
            for (int i = 1; i < nValues; i++) {
                int cvm = currentVectorMins[i];
                if (cvm < minIndex) {
                    minIndex = cvm;
                    vectorWithMinIndex = i;
                }
            }
            return vectorWithMinIndex;
        }

        /*
         * Already know the first element has been used and is no longer needed
         */
        protected void insert(int newIndex, int newVector, int[] queueOfOffsets,
                int[] queueOfVectors, int queueLength) {
            int i = 1;
            while (i < queueLength && queueOfOffsets[i] < newIndex) {
                queueOfOffsets[i-1] = queueOfOffsets[i];
                queueOfVectors[i-1] = queueOfVectors[i];
                i++;
            }
            queueOfOffsets[i-1] = newIndex;
            queueOfVectors[i-1] = newVector;
        }

        /*
         * This reducer first counts the number of unique indices present
         * in the input vectors, allocates sufficiently large output buffers
         * to store the final merge of all input vectors, and then iterates
         * through the input vectors in parallel, merging them into the final
         * output in order to maintain total ordering
         */
        protected void reduce(int key, HadoopCLSvecValueIterator valIter) {

            System.err.println("DIAGNOSTICS: Entering reduce with key "+key+" and "+valIter.nValues()+" values");
            int totalElements = 0;
            for (int i = 0; i < valIter.nValues(); i++) {
                // System.err.println("DIAGNOSTICS:   value "+i+" has length "+valIter.vectorLength(i));
                totalElements += valIter.vectorLength(i);
            }
            // System.err.println("DIAGNOSTICS: totalElements="+totalElements);

            int[] outputIndices = allocInt(totalElements);
            double[] outputVals = allocDouble(totalElements);
            // Offset into outputIndices for each vector
            int[] vectorIndices = allocInt(valIter.nValues());
            int[] queueOfOffsets = allocInt(valIter.nValues());
            int[] queueOfVectors = allocInt(valIter.nValues());

            for(int i = 0; i < valIter.nValues(); i++) {
                valIter.seekTo(i);
                vectorIndices[i] = 0;
                queueOfOffsets[i] = valIter.getValIndices()[0];
                queueOfVectors[i] = i;
            }

            for(int i = 0; i < valIter.nValues(); i++) {
                int minVal = queueOfOffsets[i];
                int minIndex = i;
                for (int j = i+1; j < valIter.nValues(); j++) {
                    int current = queueOfOffsets[j];
                    if (current < minVal) {
                        minVal = current;
                        minIndex = j;
                    }
                }
                int tmpVal = queueOfOffsets[i]; int tmpVector = queueOfVectors[i];
                queueOfOffsets[i] = queueOfOffsets[minIndex];
                queueOfVectors[i] = queueOfVectors[minIndex];
                queueOfOffsets[minIndex] = tmpVal;
                queueOfVectors[minIndex] = tmpVector;
            }

            int currentCount = 0;
            int nProcessed = 0;
            int nOutput = 0;

            // long mainStart = System.currentTimeMillis();
            while (nProcessed < totalElements) {
                // if ((nProcessed+1) % 1000 == 0) {
                //     System.err.println("DIAGNOSTICS: nProcessed="+(nProcessed+1)+"/"+totalElements);
                // }

                int minVector = queueOfVectors[0];
                valIter.seekTo(minVector);
                int newIndex = ++vectorIndices[minVector];
                int minIndex = valIter.getValIndices()[newIndex-1];
                double minValue = valIter.getValVals()[newIndex-1];
                if (newIndex < valIter.currentVectorLength()) {
                    insert(valIter.getValIndices()[newIndex], minVector, queueOfOffsets, queueOfVectors, valIter.nValues());
                } else {
                    insert(Integer.MAX_VALUE, minVector, queueOfOffsets, queueOfVectors, valIter.nValues());
                }
                nProcessed++;

                if (nOutput > 0 && outputIndices[nOutput-1] == minIndex) {
                    outputVals[nOutput-1] += minValue;
                } else {
                    if (nOutput > 0) outputVals[nOutput-1] /= (double)currentCount;
                    outputIndices[nOutput] = minIndex;
                    outputVals[nOutput] = minValue;
                    nOutput++;
                    currentCount = 0;
                }

                currentCount++;
            }
            // long mainStop = System.currentTimeMillis();
            // System.err.println("Main loop took "+(mainStop-mainStart)+" ms, find time = "+findTime+" ms");
            outputVals[nOutput-1] /= (double)currentCount;
            // System.err.println("DIAGNOSTICS: Reducer writing vector of length "+nOutput+" for key "+key);
            write(key, outputIndices, outputVals, nOutput);
            // System.err.println("DIAGNOSTICS: Done!");
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

    public static class MahoutKMeansMapper
            extends IntSvecIntSvecHadoopCLMapperKernel {

        protected double vectorLengthSquared(double[] vals, int length) {
            double agg = 0.0;
            for(int i = 0; i < length; i++) {
                agg += (vals[i] * vals[i]);
            }
            return agg;
        }

        protected double dot(int[] index1, double[] val1, int length1,
                int[] index2, double[] val2, int length2) {

            double agg = 0.0;
            for(int i = 0; i < length1; i++) {
                int currentIndex = index1[i];
                int j = 0;
                while(j < length2 && currentIndex != index2[j]) {
                    j++;
                }
                if(j != length2) {
                    agg += val1[i] * val2[j];
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

    private static SparseVectorWritable clusterToSparseVector(
            org.apache.mahout.clustering.iterator.ClusterWritable cluster) {
        Iterator<Vector.Element> iter = cluster.getValue().getCenter().nonZeroes().iterator();
        int count = 0;
        while(iter.hasNext()) {
            iter.next();
            count++;
        }
        iter = cluster.getValue().getCenter().nonZeroes().iterator();
        int[] indices = new int[count];
        double[] vals = new double[count];
        count = 0;
        while(iter.hasNext()) {
            Vector.Element ele = iter.next();
            indices[count] = ele.index();
            vals[count] = ele.get();
            count++;
        }
        return new SparseVectorWritable(indices, vals);
    }

    public static void main(String[] args) throws IOException, InterruptedException,
           ClassNotFoundException {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       FileSystem fs = FileSystem.get(conf);
       FileSystem localFs = FileSystem.getLocal(conf);
       Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed/sparse-randomSeed");
       SequenceFile.Reader reader = new SequenceFile.Reader(localFs, path, conf);
       IntWritable tmpKey = new IntWritable();
       SparseVectorWritable tmpVal = new SparseVectorWritable();

       int count = 0;
       while(reader.next(tmpKey, tmpVal)) { 
           conf.addHadoopCLGlobal(tmpVal.indices(), tmpVal.vals());
           count++;
       }

       Job job = new Job(conf, "mahout-kmeans");
       job.setJarByClass(MahoutKMeans.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(SparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(SparseVectorWritable.class);

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
