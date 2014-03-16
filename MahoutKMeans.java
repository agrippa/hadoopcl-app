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
            public MahoutKMeansReducer(HadoopOpenCLContext c, Integer i) { super(c, i); }

            private void swapHelper(int[] arr, int index1, int index2) {
                if (index1 != index2) {
                    arr[index1] += arr[index2];
                    arr[index2] = arr[index1] - arr[index2];
                    arr[index1] -= arr[index2];
                }
            }

            private void swap(int[] arr, int[] coarr, int index1, int index2) {
                swapHelper(arr, index1, index2);
                swapHelper(coarr, index1, index2);
            }

            private int partition(int[] arr, int[] coarr, int left, int right,
                    int pivotIndex) {
                int pivotValue = arr[pivotIndex];
                swap(arr, coarr, pivotIndex, right);
                int storeIndex = left;
                int topIndex = right-1;
                int nPivots = 1;
                for (int i = left; i <= topIndex; ) {
                    int curr = arr[i];
                    if (curr < pivotValue) {
                        swap(arr, coarr, i, storeIndex);
                        storeIndex++;
                        i++;
                    } else if(curr == pivotValue) {
                        swap(arr, coarr, i, topIndex);
                        topIndex--;
                        nPivots++;
                    } else {
                        i++;
                    }
                }
                for (int i = 0; i < nPivots; i++) {
                    swap(arr, coarr, storeIndex + i, topIndex + 1 + i);
                }
                return storeIndex;
            }

            private void quicksort(int[] arr, int[] coarr, int low, int high) {
                if (high - low <= 1) {
                    return;
                }
                int baseOfPivot = partition(arr, coarr, low, high, low);
                int pivot = arr[baseOfPivot];
                int topOfPivot = baseOfPivot;
                while (topOfPivot < arr.length && arr[topOfPivot] == pivot) {
                    topOfPivot++;
                }
                quicksort(arr, coarr, low, baseOfPivot-1);
                quicksort(arr, coarr, topOfPivot, high);
            }

            protected int reverseIterateHelper(int queueHead, int queueLength) {
                int tmp = queueHead  -1;
                return tmp < 0 ? queueLength-1 : tmp;
            }

            protected int forwardIterateHelper(int queueHead, int queueLength) {
                int tmp = queueHead + 1;
                return tmp >= queueLength ? 0 : tmp;
            }

            protected int reverseIterate(int queueHead, int[] q, int queueLength) {
                return reverseIterateHelper(queueHead, queueLength);
            }

            protected int forwardIterate(int queueHead, int[] q, int queueLength) {
                return forwardIterateHelper(queueHead, queueLength);
            }

            /*
             * Already know the first element has been used and is no longer needed
             */
            protected void insert(int newIndex, int newVector, int[] queueOfOffsets,
                    int[] queueOfVectors, int queueLength, int queueHead) {
                int emptySlot = queueHead;
                int checkingSlot = reverseIterate(emptySlot, queueOfOffsets, queueLength);
                // System.err.println("Starting at slot "+queueHead);

                // System.err.println("Checking "+newIndex+" against "+queueOfOffsets[checkingSlot]+" at slot "+checkingSlot);
                while (queueOfOffsets[checkingSlot] > newIndex) {
                    queueOfOffsets[emptySlot] = queueOfOffsets[checkingSlot];
                    queueOfVectors[emptySlot] = queueOfVectors[checkingSlot];
                    emptySlot = checkingSlot;
                    checkingSlot = reverseIterate(checkingSlot, queueOfOffsets, queueLength);
                    // System.err.println("Checking "+newIndex+" against "+queueOfOffsets[checkingSlot]+" at slot "+checkingSlot);
                }

                queueOfOffsets[emptySlot] = newIndex;
                queueOfVectors[emptySlot] = newVector;
            }

            class MutableDouble {
                private double val;
                public MutableDouble(double v) { this.val = v; }
                public double get() { return this.val; }
                public void incr(double i) { this.val = this.val + i; }
            }

            /*
             * This reducer first counts the number of unique indices present
             * in the input vectors, allocates sufficiently large output buffers
             * to store the final merge of all input vectors, and then iterates
             * through the input vectors in parallel, merging them into the final
             * output in order to maintain total ordering
             */
            protected void reduce(int key, HadoopCLSvecValueIterator valIter) {
                HashMap<Integer, MutableDouble> merged = new HashMap<Integer, MutableDouble>();

                for (int i = 0; i < valIter.nValues(); i++) {
                    valIter.seekTo(i);
                    int[] indices = valIter.getValIndices();
                    double[] vals = valIter.getValVals();
                    int length = valIter.currentVectorLength();
                    for (int j = 0; j < length; j++) {
                        if (merged.containsKey(indices[j])) {
                            merged.get(indices[j]).incr(vals[j]);
                        } else {
                            merged.put(indices[j], new MutableDouble(vals[j]));
                        }
                    }
                }
                
                List<Integer> indices = new ArrayList<Integer>(merged.size());
                indices.addAll(merged.keySet());
                Collections.sort(indices);
                int[] outputIndices = allocInt(merged.size());
                double[] outputVals = allocDouble(merged.size());
                for (int i = 0; i < indices.size(); i++) {
                    outputIndices[i] = indices.get(i);
                    outputVals[i] = merged.get(outputIndices[i]).get();
                }
                write(key, outputIndices, outputVals, merged.size());

                /*
                // System.err.println("DIAGNOSTICS: Entering reduce with key "+key+" and "+valIter.nValues()+" values");
                long initStart = System.currentTimeMillis();
                int totalElements = 0;
                for (int i = 0; i < valIter.nValues(); i++) {
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

                quicksort(queueOfOffsets, queueOfVectors, 0, valIter.nValues()-1);
                // System.err.println("After quicksort: ");
                // for (int i = 0; i < valIter.nValues(); i++) {
                //     System.err.print(queueOfOffsets[i]+" ");
                // }
                // System.err.println();
                long initStop = System.currentTimeMillis();

                int queueHead = 0;

                int currentCount = 0;
                int nProcessed = 0;
                int nOutput = 0;
                int queueLength = valIter.nValues();

                // long mainStart = System.currentTimeMillis();
                while (nProcessed < totalElements) {
                    // if ((nProcessed+1) % 1000 == 0) {
                    //     System.err.println("DIAGNOSTICS: nProcessed="+(nProcessed+1)+"/"+totalElements);
                    // }

                    int minVector = queueOfVectors[queueHead];
                    boolean dontIncr = false;

                    valIter.seekTo(minVector);
                    int newIndex = ++vectorIndices[minVector];
                    int minIndex = valIter.getValIndices()[newIndex-1];
                    double minValue = valIter.getValVals()[newIndex-1];

                    if (newIndex < valIter.currentVectorLength()) {
                        int tmp = valIter.getValIndices()[newIndex];
                        if (tmp <= queueOfOffsets[forwardIterate(queueHead, queueOfOffsets, queueLength)]) {
                            queueOfOffsets[queueHead] = tmp;
                            queueOfVectors[queueHead] = minVector;
                            // because we're going to advance it below
                            dontIncr = true;
                        } else {
                            insert(tmp, minVector,
                                    queueOfOffsets, queueOfVectors,
                                    queueLength, queueHead);
                        }
                    } else {
                        for (int i = queueHead + 1; i < queueLength; i++) {
                            queueOfOffsets[i-1] = queueOfOffsets[i];
                            queueOfVectors[i-1] = queueOfVectors[i];
                        }
                        queueLength--;
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
                    if (!dontIncr) {
                        queueHead = forwardIterate(queueHead, queueOfOffsets, queueLength);
                    }
                }
                long runStop = System.currentTimeMillis();
                // System.err.println("Reducer init time: "+(initStop-initStart)+" ms, run time "+(runStop-initStop)+" ms");
                // long mainStop = System.currentTimeMillis();
                // System.err.println("Main loop took "+(mainStop-mainStart)+" ms, find time = "+findTime+" ms");
                outputVals[nOutput-1] /= (double)currentCount;
                // System.err.println("DIAGNOSTICS: Reducer writing vector of length "+nOutput+" for key "+key);
                write(key, outputIndices, outputVals, nOutput);
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
            public MahoutKMeansMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected double vectorLengthSquared(double[] vals, int length) {
            double agg = 0.0;
            for(int i = 0; i < length; i++) {
                agg += (vals[i] * vals[i]);
            }
            return agg;
        }

        /*
        protected double dot(int[] index1, double[] val1, int length1,
                int[] index2, double[] val2, int length2) {

            double agg = 0.0;
            int i = 0;
            int j = 0;
            int iIndex = index1[i];
            int jIndex = index2[i];
            while (i < length1 && j < length2) {
                if (iIndex == jIndex) {
                    agg += (val1[i] * val2[j]);
                    i++;
                    j++;
                    if (i < length1) iIndex = index1[i];
                    if (j < length2) jIndex = index2[j];
                } else if (iIndex < jIndex) {
                    i++;
                    if (i < length1) iIndex = index1[i];
                } else {
                    j++;
                    if (j < length2) jIndex = index2[j];
                }
            }
            return agg;
        }
        */
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
       // Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed/sparse-randomSeed.pruned128.512clusters");
       Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed/sparse-randomSeed.length32.512clusters");
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

       job.setOCLCombinerDeviceType(Device.TYPE.JAVA);

       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));

       long start = System.currentTimeMillis();
       job.waitForCompletion(true);
       long stop = System.currentTimeMillis();
       System.err.println("Execution Time "+(stop-start)+" ms");
       System.err.println("# clusters = "+count);
   }
}
