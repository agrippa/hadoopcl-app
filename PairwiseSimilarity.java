import java.util.*;
import java.lang.*;

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
import org.apache.hadoop.mapreduce.IntFsvecIntFsvecHadoopCLMapperKernel;
import org.apache.hadoop.mapreduce.HadoopCLFsvecValueIterator;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import com.amd.aparapi.device.Device;
import org.apache.mahout.math.Vector;
import org.apache.mahout.clustering.iterator.ClusterWritable;

public class PairwiseSimilarity {

    public static final int GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX = 0;
    public static final int GLOBAL_NORMS_INDEX = 1;

    public static class PairwiseMapper extends
            IntFsvecIntFsvecHadoopCLMapperKernel {

        private final double threshold = Double.MIN_VALUE;
        /*
        private OpenIntIntHashMap numNonZeroEntries;

        @Override
        protected void setup(Context ctx) throws IOException, InterruptedException {
        // to be deleted
        similarity = ClassUtils.instantiateAs(ctx.getConfiguration().get(SIMILARITY_CLASSNAME),
        VectorSimilarityMeasure.class);
        // store as hadoopcl global, need to find this file for our current data set though
        numNonZeroEntries = Vectors.readAsIntMap(new Path(ctx.getConfiguration().get(NUM_NON_ZERO_ENTRIES_PATH)),
        ctx.getConfiguration());
        maxValues = Vectors.read(new Path(ctx.getConfiguration().get(MAXVALUES_PATH)), ctx.getConfiguration());
        // hard code for simplicity
        threshold = Double.parseDouble(ctx.getConfiguration().get(THRESHOLD));
        }
        */

        private int findInSortedArr(int searchFor, int[] arr, int arrLength) {
            int i = 0;
            while (i < arrLength && searchFor < arr[i]) {
                i++;
            }
            if (arr[i] == searchFor) {
                return i;
            } else {
                return -1;
            }
        }

        private double getFromSparseVector(int index, int[] indices,
                double[] vals, int len) {
            int offset = findInSortedArr(index, indices, len);
            if (offset == -1) {
                return 0.0;
            } else {
                return vals[offset];
            }
        }

        private boolean similarity_consider(int numNonZeroEntriesA,
                int numNonZeroEntriesB) {
            return numNonZeroEntriesA >= threshold &&
                numNonZeroEntriesB >= threshold;
        }

        private float similarity_aggregate(float valA, float valB) {
            return 1.0f;
        }

        private boolean consider(int indexA, float valA,
                int indexB, float valB) {
            int i;
            int numNonZeroEntriesLength = this.globalsLength(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX);
            int[] numNonZeroEntriesIndices = this.getGlobalIndices(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX);
            double[] numNonZeroEntriesVals = this.getGlobalVals(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX);

            double numNonZeroEntriesA = getFromSparseVector(indexA,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);
            double numNonZeroEntriesB = getFromSparseVector(indexB,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);

            return similarity_consider((int)numNonZeroEntriesA, (int)numNonZeroEntriesB);
        }

        private void swapHelper(float[] arr, int index1, int index2) {
            if (index1 != index2) {
                float tmp = arr[index1];
                arr[index1] = arr[index2];
                arr[index2] = tmp;
            }
        }

        private void swapHelper(int[] arr, int index1, int index2) {
            if (index1 != index2) {
                arr[index1] += arr[index2];
                arr[index2] = arr[index1] - arr[index2];
                arr[index1] -= arr[index2];
            }
        }

        private void swap(int[] arr, float[] coarr, int index1, int index2) {
            swapHelper(arr, index1, index2);
            swapHelper(coarr, index1, index2);
        }

        private int partition(int[] arr, float[] coarr, int left, int right,
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

        private void quicksort(int[] arr, float[] coarr, int low, int high) {
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

        protected void map(int column, int[] occurrenceIndices,
                float[] occurrenceVals, int occurrenceLen) {
            int cooccurrences = 0;
            int prunedCooccurrences = 0;

            quicksort(occurrenceIndices, occurrenceVals, 0, occurrenceLen-1);

            for (int n = 0; n < occurrenceLen; n++) {
                int occurrenceAIndex = occurrenceIndices[n];
                float occurrenceAVal = occurrenceVals[n];

                int dotsSoFar = 0;
                int[] dotsIndices = allocInt(occurrenceLen);
                float[] dotsVals = allocFloat(occurrenceLen);

                for (int m = n; m < occurrenceLen; m++) {
                    int occurrenceBIndex = occurrenceIndices[m];
                    float occurrenceBVal = occurrenceVals[m];
                    // if (threshold == NO_THRESHOLD ||
                    //         consider(occurrenceAIndex, occurrenceAVal,
                    //             occurrenceBIndex, occurrenceBVal))
                    {
                        dotsIndices[dotsSoFar] = occurrenceBIndex;
                        dotsVals[dotsSoFar] = similarity_aggregate(
                                occurrenceAVal, occurrenceBVal);
                        dotsSoFar++;
                        cooccurrences++; // unused
                    }
                    // else {
                    //     prunedCooccurrences++; // unused
                    // }
                }

                if (dotsSoFar > 0) {
                    write(column, dotsIndices, dotsVals, dotsSoFar);
                }
            }
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

    public static class PairwiseReducer extends
        IntFsvecIntFsvecHadoopCLReducerKernel {

        private final double threshold = Double.MIN_VALUE;
        private final boolean excludeSelfSimilarity = false;
        /*
           private Vector norms;

           @Override
           protected void setup(Context ctx) throws IOException, InterruptedException {
           similarity = ClassUtils.instantiateAs(ctx.getConfiguration().get(SIMILARITY_CLASSNAME),
           VectorSimilarityMeasure.class);
           numberOfColumns = ctx.getConfiguration().getInt(NUMBER_OF_COLUMNS, -1);
           Preconditions.checkArgument(numberOfColumns > 0, "Incorrect number of columns!");
           excludeSelfSimilarity = ctx.getConfiguration().getBoolean(EXCLUDE_SELF_SIMILARITY, false);
           norms = Vectors.read(new Path(ctx.getConfiguration().get(NORMS_PATH)), ctx.getConfiguration());
           treshold = Double.parseDouble(ctx.getConfiguration().get(THRESHOLD));
           }
           */

        private int findInSortedArr(int searchFor, int[] arr, int arrLength) {
            int i = 0;
            while (i < arrLength && searchFor < arr[i]) {
                i++;
            }
            if (arr[i] == searchFor) {
                return i;
            } else {
                return -1;
            }
        }

        private double getFromSparseVector(int index, int[] indices,
                double[] vals, int len) {
            int offset = findInSortedArr(index, indices, len);
            if (offset == -1) {
                return 0.0;
            } else {
                return vals[offset];
            }
        }

        protected int contains(int searchFor, int[] arr, int arrLength) {
            int i;
            for (i = 0; i < arrLength; i++) {
                if (arr[i] == searchFor) return i;
            }
            return -1;
        }

        protected void reduce(int row, HadoopCLFsvecValueIterator valsIter) {
            int totalNValues = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNValues += valsIter.vectorLength(i);
            }

            int[] dotsIndices = allocInt(totalNValues);
            float[] dotsVals = allocFloat(totalNValues);
            int soFar = 0;

            /*
             * Aggregate all input value vectors into the dotsIndices and dotsVals,
             * probably not filling the whole arrays.
             */
            for (int i = 0; i < valsIter.nValues(); i++) {
                valsIter.seekTo(i);
                int[] indices = valsIter.getValIndices();
                float[] vals = valsIter.getValVals();
                int len = valsIter.currentVectorLength();

                for (int j = 0; j < len; j++) {
                    int offset = contains(indices[j], dotsIndices, soFar);
                    if (offset >= 0) {
                        dotsVals[offset] += vals[j];
                    } else {
                        dotsIndices[soFar] = indices[j];
                        dotsVals[soFar++] = vals[j];
                    }
                }
            }

            int[] normsIndices = this.getGlobalIndices(GLOBAL_NORMS_INDEX);
            double[] normsVals = this.getGlobalVals(GLOBAL_NORMS_INDEX);
            int normsLen = this.globalsLength(GLOBAL_NORMS_INDEX);
            double normA = getFromSparseVector(row, normsIndices,
                    normsVals, normsLen);

            int similaritySoFar = 0;
            for (int i = 0; i < soFar; i++) {
                // similarity_similarity collapses to 'return arg0;'
                double similarityValue = dotsVals[i];
                if (similarityValue >= threshold) {
                    dotsIndices[similaritySoFar] = dotsIndices[i];
                    dotsVals[similaritySoFar] = dotsVals[i];
                    similaritySoFar++;
                }
            }

            if (excludeSelfSimilarity) {
                int i;
                for (i = 0; i < similaritySoFar; i++) {
                    if (dotsIndices[i] == row) {
                        dotsVals[i] = 0.0f;
                        break;
                    }
                }
            }

            write(row, dotsIndices, dotsVals, similaritySoFar);
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

    public static class PairwiseCombiner extends
            IntFsvecIntFsvecHadoopCLReducerKernel {

        protected int contains(int searchFor, int[] arr, int arrLength) {
            int i;
            for (i = 0; i < arrLength; i++) {
                if (arr[i] == searchFor) return i;
            }
            return -1;
        }

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int totalNValues = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNValues += valsIter.vectorLength(i);
            }

            int[] combinedIndices = allocInt(totalNValues);
            float[] combinedVals = allocFloat(totalNValues);
            int soFar = 0;

            for (int i = 0; i < valsIter.nValues(); i++) {
                valsIter.seekTo(i);
                int[] indices = valsIter.getValIndices();
                float[] vals = valsIter.getValVals();
                int len = valsIter.currentVectorLength();

                for (int j = 0; j < len; j++) {
                    int offset = contains(indices[j], combinedIndices, soFar);
                    if (offset >= 0) {
                        combinedVals[offset] += vals[j];
                    } else {
                        combinedIndices[soFar] = indices[j];
                        combinedVals[soFar++] = vals[j];
                    }
                }
            }

            write(key, combinedIndices, combinedVals, soFar);
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

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

       String numNonZeroEntriesPath = args[3];
       String normsPath = args[4];
       OpenIntIntHashMap numNonZeroEntries = Vectors.readAsIntMap(new Path(numNonZeroEntriesPath), conf);
       Vector norms = Vectors.read(new Path(normsPath), conf);

       int normSoFar = 0;
       int[] normIndices = new int[norms.getNumNonZeroElements()];
       double[] normVals = new double[norms.getNumNonZeroElements()];
       Iterator<Vector.Element> normsIter = norms.nonZeroes().iterator();
       while (normsIter.hasNext()) {
           Vector.Element ele = normsIter.next();
           normIndices[normSoFar] = ele.index();
           normVals[normSoFar] = ele.get();
           normSoFar++;
       }

       int numNonZeroEntriesSoFar = 0;
       int[] numNonZeroEntriesIndices = new int[numNonZeroEntries.size()];
       double[] numNonZeroEntriesVals = new double[numNonZeroEntries.size()];
       Iterator<OpenIntIntHashMap.MapElement> nonZeroEntriesIter = numNonZeroEntries.iterator();
       while (nonZeroEntriesIter.hasNext()) {
           OpenIntIntHashMap.MapElement ele = nonZeroEntriesIter.next();
           numNonZeroEntriesIndices[numNonZeroEntriesSoFar] = ele.index();
           numNonZeroEntriesVals[numNonZeroEntriesSoFar] = ele.get();
           numNonZeroEntriesSoFar++;
       }

       conf.addHadoopCLGlobal(numNonZeroEntriesIndices, numNonZeroEntriesVals);
       conf.addHadoopCLGlobal(normIndices, normVals);

       Job job = new Job(conf, "mahout-pairwise");
       job.setJarByClass(PairwiseSimilarity.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(FSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(FSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(PairwiseMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(PairwiseReducer.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(PairwiseCombiner.class);

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
