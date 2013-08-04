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

        protected void swap(int[] indices, double[] vals, int a, int b) {
            int tmpInd = indices[a];
            double tmpVal = vals[a];
            indices[a] = indices[b];
            vals[a] = vals[b];
            indices[b] = tmpInd;
            vals[b] = tmpVal;
        }

        protected double searchAndAvg(int startingI, int endingI,
                int startingJ, int index, int[] valLookAsideBuffer,
                int[] valIndices, double[] valVals) {
            double sum = 0.0;
            int count = 0;
            for(int i = startingI; i < endingI; i++) {
                final int startOffset = valLookAsideBuffer[i];
                final int len = inputVectorLength(i);
                for(int j = (i == startingI ? startingJ : startOffset); j < startOffset + len; j++) {
                    if(valIndices[j] == index) {
                        sum = sum + valVals[j];
                        valIndices[j] = -1;
                        count++;
                    }
                }
            }
            return sum / count;
        }

        protected void reduce(int key, HadoopCLSvecValueIterator valIter) {

            int countUniqueIndices = 0;
            for(int v = 0; v < valIter.nValues(); v++) {
                valIter.seekTo(v);
                int[] indices = valIter.getValIndices();
                double[] vals = valIter.getValVals();
                int currentLength = valIter.currentVectorLength();

                for(int i = 0; i < currentLength; i++) {
                    if(indices[i] == -1) continue;
                    int currentIndex = indices[i];
                    countUniqueIndices++;

                    for(int vv = v + 1; vv < valIter.nValues(); vv++) {
                        valIter.seekTo(vv);
                        int[] tmpIndices = valIter.getValIndices();
                        double[] tmpVals = valIter.getValVals();
                        int tmpLength = valIter.currentVectorLength();

                        for(int j = 0; j < tmpLength; j++) {
                            if(tmpIndices[j] == currentIndex) {
                                vals[i] += tmpVals[j];
                                tmpIndices[j] = -1;
                            }
                        }
                    }
                }
            }

            int[] outputIndices = allocInt(countUniqueIndices);
            double[] outputVals = allocDouble(countUniqueIndices);

            for(int u = 0; u < countUniqueIndices; u++) {
                int minIndex = -1;
                double minVal = -1.0;
                int indexOfMinIndex = -1;
                int vectorOfMinIndex = -1;

                for(int v = 0; v < valIter.nValues(); v++) {
                    valIter.seekTo(v);
                    int[] indices = valIter.getValIndices();
                    int currentLength = valIter.currentVectorLength();

                    for(int i = 0; i < currentLength; i++) {
                        if(indices[i] == -1) continue;
                        if(indexOfMinIndex == -1 || indices[i] < minIndex) {
                            indexOfMinIndex = i;
                            vectorOfMinIndex = v;
                            minIndex = indices[i];
                        }
                    }
                }
                valIter.seekTo(vectorOfMinIndex);
                double[] vals = valIter.getValVals();
                outputIndices[u] = minIndex;
                outputVals[u] = vals[indexOfMinIndex];
            }
            write(key, outputIndices, outputVals, countUniqueIndices);
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

            double lengthSquaredv1 = vectorLengthSquared(thisVals, thisLen);
            double lengthSquaredv2 = vectorLengthSquared(otherVals, otherLength);

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
