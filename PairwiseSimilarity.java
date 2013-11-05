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

public class PairwiseSimilarity {

    public static class PairwiseMapper extends
            HadoopCLIntFsvecIntFsvecMapper {
        /*
        // TODO: replace with hard-coded functions, CooccurenceCountSimilarity
        private VectorSimilarityMeasure similarity;

        //class org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob.nonZeroEntriesPath
        private OpenIntIntHashMap numNonZeroEntries;
        //class org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob.maxWeightsPath
        private Vector maxValues;
        //class org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob.threshold
        private double threshold;

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
            int i;
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
            return 1.0;
        }

        private boolean consider(int indexA, float valA,
                int indexB, float valB) {
            int i;
            int numNonZeroEntriesLength = this.globalsLength(0);
            int[] numNonZeroEntriesIndices = this.getGlobalIndices(0);
            double[] numNonZeroEntriesVals = this.getGlobalVals(0);

            double numNonZeroEntriesA = getFromSparseVector(indexA,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);
            double numNonZeroEntriesB = getFromSparseVector(indexB,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);

            return similarity_consider(numNonZeroEntriesA, numNonZeroEntriesB);
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

        protected void map(int column, int[] occurenceIndices,
                float[] occurenceVals, int occurenceLen) {
            int cooccurrences = 0;
            int prunedCooccurrences = 0;

            quicksort(occurenceIndices, occurenceVals, 0, occurenceLen-1);

            for (int n = 0; n < occurenceLen; n++) {
                int occurenceAIndex = occurenceIndices[n];
                float occurenceAVal = occurenceVals[n];

                int dotsSoFar = 0;
                int[] dotsIndices = allocInt(occurenceLen);
                float[] dotsVals = allocFloat(occurenceLen);

                for (int m = n; m < occurencesLen; m++) {
                    int occurrenceBIndex = occurenceIndices[m];
                    float occurrenceBVal = occurenceVals[m];
                    if (threshold == NO_THRESHOLD ||
                            consider(occurenceAIndex, occurenceAVal,
                                occurenceBIndex, occurenceBVal)) {
                        dotsIndices[dotsSoFar] = occurenceBIndex;
                        dotsVals[dotsSoFar] = similarity_aggregate(
                                occurenceAVal, occurenceBVal);
                        dotsSoFar++;
                        cooccurrences++; // unused
                    } else {
                        prunedCooccurrences++; // unused
                    }
                }

                if (dotsSoFar > 0) {
                    write(dotsIndices, dotsVals, dotsSoFar);
                }
            }
        }
    }

    public static class SimilarityReducer extends HadoopCLIntFsvecIntFsvecReducer {

        /*
           private VectorSimilarityMeasure similarity;
           private int numberOfColumns;
           private boolean excludeSelfSimilarity;
           private Vector norms;
           private double treshold;

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
            int i;
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

        protected void reduce(int row, HadoopCLFsvecIterator valsIter) {
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
                        dotsVals[soFar] = indices[j];
                        dotsVals[soFar++] = vals[j];
                    }
                }
            }

            int[] normsIndices = this.getGlobalIndices(0);
            double[] normsVals = this.getGlobalVals(0);
            int normsLen = this.globalsLength(0);
            double normA = getFromSparseVector(row, normsIndices,
                    normsVals, normsLen);

            int similaritySoFar = 0;
            for (int i = 0; i < soFar; i++) {
                double similarityValue = similarity_similarity(dotsVals[i], normA,
                        getFromSparseVector(dotsIndices[i], normsIndices, normsVals, normsLen),
                        numberOfColumns);
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
                        dotsVals[i] = 0.0;
                        break;
                    }
                }
            }

            write(row, dotsIndices, dotsVals, similaritySoFar);
        }
    }

    public static void main(String[] args) throws Exception {
       Configuration conf = new Configuration();
       SetupInputCompression.setupCompression(conf, args);

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
