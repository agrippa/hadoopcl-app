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

        boolean search(int[] list, int start, int end, int val) {
            if (start == end) {
                // Empty list
                return false;
            } else if (start + 1 == end) {
                // Single element
                return list[start] == val;
            } else {
                // List length > 1
                int mid = start + ((end-start)/2);
                if (val == list[mid]) {
                    return true;
                } else if (val < list[mid]) {
                    return search(list, start, mid, val);
                } else {
                    return search(list, mid+1, end, val);
                }
            }
        }

        /*
         * Count the number of unique indices in the input sparse vectors
         * and return that count
         */
        int countUniqueIndices(HadoopCLSvecValueIterator valIter) {
            int countUniques = 0;
            // For each input sparse vector
            for (int v = 0; v < valIter.nValues(); v++) {
                valIter.seekTo(v);
                int[] currentIndices = valIter.getValIndices();
                int currentLength = valIter.currentVectorLength();

                for (int i = 0; i < currentLength; i++) {
                    int currentIndex = currentIndices[i];
                    boolean found = false;
                    for (int vv = 0; vv < v && !found; vv++) {
                        valIter.seekTo(vv);
                        found = search(valIter.getValIndices(), 0, valIter.currentVectorLength(), currentIndex);
                    }
                    if (!found) {
                        countUniques++;
                    }
                }
            }
            return countUniques;
        }

        /*
         * From the list of offsets in vectorIndices,
         * find the input sparse vector with the minimum
         * index value at the corresponding offset
         */
        protected int findMinIndexVector(int[] vectorIndices,
                HadoopCLSvecValueIterator valIter) {
            int vectorWithMinIndex = -1;
            int minIndex = -1;
            for (int i = 0; i < valIter.nValues(); i++) {
                valIter.seekTo(i);
                if (vectorIndices[i] < valIter.currentVectorLength() &&
                        (minIndex == -1 || 
                             valIter.getValIndices()[vectorIndices[i]] < minIndex)) {
                    vectorWithMinIndex = i;
                    minIndex = valIter.getValIndices()[vectorIndices[i]];
                }
            }
            return vectorWithMinIndex;
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
                totalElements += valIter.vectorLength(i);
            }
            int[] outputIndices = allocInt(totalElements);
            double[] outputVals = allocDouble(totalElements);
            int[] vectorIndices = allocInt(valIter.nValues());

            for(int i = 0; i < valIter.nValues(); i++) {
                vectorIndices[i] = 0;
            }

            int currentCount = 0;
            int nProcessed = 0;
            int nOutput = 0;
            while (nProcessed < totalElements) {
                int minVector = findMinIndexVector(vectorIndices, valIter);
                valIter.seekTo(minVector);
                int minIndex = valIter.getValIndices()[vectorIndices[minVector]];
                double minValue = valIter.getValVals()[vectorIndices[minVector]];
                if (nOutput > 0 && outputIndices[nOutput-1] == minIndex) {
                    outputVals[nOutput-1] += minValue;
                } else {
                    if (nOutput > 0) outputVals[nOutput-1] /= (double)currentCount;
                    outputIndices[nOutput] = minIndex;
                    outputVals[nOutput] = minValue;
                    nOutput++;
                    currentCount = 0;
                }

                vectorIndices[minVector]++;
                nProcessed++;
                currentCount++;
            }
            outputVals[nOutput-1] /= (double)currentCount;
            System.err.println("DIAGNOSTICS: Reducer writing vector of length "+nOutput+" for key "+key);
            write(key, outputIndices, outputVals, nOutput);

/*
            long startUniques = System.currentTimeMillis();
            int uniqueIndices = countUniqueIndices(valIter);
            long stopUniques = System.currentTimeMillis();

            int[] vectorIndices = allocInt(valIter.nValues());
            int[] outputIndices = allocInt(uniqueIndices);
            double[] outputVals = allocDouble(uniqueIndices);

            for(int i = 0; i < valIter.nValues(); i++) {
                vectorIndices[i] = 0;
            }

            long startMerging = System.currentTimeMillis();
            int indicesDone = 0;
            int vectorsDone = 0;
            while(vectorsDone < valIter.nValues()) {
                int minIndexVector = findMinIndexVector(vectorIndices, valIter);
                valIter.seekTo(minIndexVector);
                int minIndex = valIter.getValIndices()[vectorIndices[minIndexVector]];
                double correspondingVal = 
                    valIter.getValVals()[vectorIndices[minIndexVector]];
                if (indicesDone > 0 && outputIndices[indicesDone-1] == minIndex) {
                    outputVals[indicesDone-1] += correspondingVal; 
                } else {
                    outputIndices[indicesDone] = minIndex;
                    outputVals[indicesDone] = correspondingVal;
                    indicesDone++;
                }
                vectorIndices[minIndexVector]++;
                if (vectorIndices[minIndexVector] >=
                        valIter.vectorLength(minIndexVector)) {
                    vectorsDone++;
                }
            }
            long stopMerging = System.currentTimeMillis();
            System.out.println("DIAGNOSTICS: Unique calc took "+(stopUniques-startUniques)+" ms, merging took "+(stopMerging-startMerging)+" ms");

            write(key, outputIndices, outputVals, uniqueIndices);
            */
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
            for(int i = 0; i < this.nGlobals(); i++) {
                int[] centroidIndices = this.getGlobalIndices(i);
                double[] centroidVals = this.getGlobalVals(i);
                int centroidVectorLength = this.globalsLength(i);

                double dist = distance(indices, vals, len,
                        centroidIndices, centroidVals, centroidVectorLength);
                if(minDist == -1.0 || minDist > dist ){
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
            str.add(Device.TYPE.JAVA, 10);
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
       Path path = new Path("/scratch/jmg3/mahout-work-jmg3/reuters-kmeans-clusters/part-randomSeed");
       SequenceFile.Reader reader = new SequenceFile.Reader(localFs, path, conf);
       Text tmpKey = new Text();
       ClusterWritable tmpVal = new ClusterWritable();

       int count = 0;
       while(reader.next(tmpKey, tmpVal)) { 
           SparseVectorWritable translated = clusterToSparseVector(tmpVal);
           conf.addHadoopCLGlobal(translated.indices(), translated.vals());
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
