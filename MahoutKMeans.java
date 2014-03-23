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
                str.add(Device.TYPE.JAVA, 10);
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
       // Path path = new Path("/scratch/jmg3/wiki-sparse/random-seed-sparse");
       Path path = new Path("/home/yiskylee/hadoopcl-input/for-yiskylee/random-seed-sparse");
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
