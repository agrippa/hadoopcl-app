import java.util.*;
import java.lang.*;

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

    public static class Util {

        private void stupidSort(int[] arr, int[] coarr, int len) {
          for (int i = 0; i < len; i++) {
            int minIndex = i;
            int minVal = arr[i];
            for (int j = i + 1; j < len; j++) {
              if (arr[j] < minVal) {
                minIndex = j;
                minVal = arr[j];
              }
            }
            arr[minIndex] = arr[i];
            arr[i] = minVal;

            int tmp = coarr[i];
            coarr[i] = coarr[minIndex];
            coarr[minIndex] = tmp;
          }
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

          while (queueOfOffsets[checkingSlot] > newIndex) {
            queueOfOffsets[emptySlot] = queueOfOffsets[checkingSlot];
            queueOfVectors[emptySlot] = queueOfVectors[checkingSlot];
            emptySlot = checkingSlot;
            checkingSlot = reverseIterate(checkingSlot, queueOfOffsets, queueLength);
          }

          queueOfOffsets[emptySlot] = newIndex;
          queueOfVectors[emptySlot] = newVector;
        }

        public static int merge(HadoopCLFsvecValueIterator valsIter,
                int[] outputIndices, float[] outputVals, int totalNElements) {

            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNElements += valsIter.vectorLength(i);
            }

            // Arrays to merge input values into
            int[] dotsIndices = allocInt(totalNElements);
            float[] dotsVals = allocFloat(totalNElements);

            // Stores an index indicating how far we've incremented into each
            // input vector so far.
            int[] vectorIndices = allocInt(valsIter.nValues());
            // Stores actual index values from the vectors, the current
            // minimum for that vector that hasn't been merged into the
            // output vector.
            int[] queueOfOffsets = allocInt(valsIter.nValues());
            // Stores ID for vector associated with index in queueOfOffsets.
            int[] queueOfVectors = allocInt(valsIter.nValues());

            for (int i = 0; i < valsIter.nValues(); i++) {
              valsIter.seekTo(i);
              vectorIndices[i] = 0;
              queueOfOffsets[i] = valsIter.getValIndices()[0];
              queueOfVectors[i] = i;
            }

            // Sort queueOfOffsets so that the vectors with the smallest minimum
            // index is at the front of the queue (i.e. index 0)
            stupidSort(queueOfOffsets, queueOfVectors, valsIter.nValues());

            // Current queue head, incremented as we pass through the queue
            int queueHead = 0;

            // The number of individual input elements we've passed over so far.
            int nProcessed = 0;
            // The number of individual output elements we've written so far.
            // This may be less than nProcessed if there are duplicated indices
            // in different input vectors.
            int nOutput = 0;
            // Current length of the queue
            int queueLength = valsIter.nValues();

            // While we haven't processed all input elements.
            while (nProcessed < totalNElements) {

              // Retrieve the vector ID in the input vals which has the
              // smallest minimum index that hasn't been processed so far.
              int minVector = queueOfVectors[queueHead];
              boolean dontIncr = false;

              valsIter.seekTo(minVector);
              int newIndex = ++vectorIndices[minVector];
              int minIndex = valsIter.getValIndices()[newIndex-1];
              float minValue = valsIter.getValVals()[newIndex-1];

              if (newIndex < valsIter.currentVectorLength()) {
                // If there are still elements to be processed in the current
                // vector, start by grabbing the value of the next smallest
                // index.
                int tmp = valsIter.getValIndices()[newIndex];
                if (tmp <= queueOfOffsets[forwardIterate(queueHead,
                            queueOfOffsets, queueLength)]) {
                  // If the next element in the current vector is also smaller
                  // than any of the current elements in the queue, just place
                  // it back at our current location in the circular queue and
                  // don't increment the queueHead below.
                  queueOfOffsets[queueHead] = tmp;
                  dontIncr = true;
                } else {
                  // Otherwise, we need to insert our newly discovered min for
                  // the current vector back into the appropriate place in the
                  // queue.
                  insert(tmp, minVector,
                      queueOfOffsets, queueOfVectors,
                      queueLength, queueHead);
                }
              } else {
                // We've finished all of the elements in the current vector, so
                // the queue can be resized down.
                for (int i = queueHead + 1; i < queueLength; i++) {
                  queueOfOffsets[i-1] = queueOfOffsets[i];
                  queueOfVectors[i-1] = queueOfVectors[i];
                }
                queueLength--;
              }
              nProcessed++;

              // Write the values we just extracted to the output combined
              // values.
              if (nOutput > 0 && dotIndices[nOutput-1] == minIndex) {
                dotVals[nOutput-1] += minValue;
              } else {
                dotIndices[nOutput] = minIndex;
                dotVals[nOutput] = minValue;
                nOutput++;
              }

              // If we didn't find the next smallest index in the same vector,
              // need to iterate the queueHead to the next location.
              if (!dontIncr) {
                queueHead = forwardIterate(queueHead, queueOfOffsets, queueLength);
              }
            }
            return nOutput;
        }
    }

    public static class PairwiseMapper extends
            IntFsvecIntFsvecHadoopCLMapperKernel {

        private final float threshold = Float.MIN_VALUE;
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

        private float getFromSparseVector(int index, int[] indices,
                float[] vals, int len) {
            int offset = findInSortedArr(index, indices, len);
            if (offset == -1) {
                return 0.0f;
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
            float[] numNonZeroEntriesVals = this.getGlobalFVals(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX);

            float numNonZeroEntriesA = getFromSparseVector(indexA,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);
            float numNonZeroEntriesB = getFromSparseVector(indexB,
                    numNonZeroEntriesIndices, numNonZeroEntriesVals,
                    numNonZeroEntriesLength);

            return similarity_consider((int)numNonZeroEntriesA, (int)numNonZeroEntriesB);
        }

        private void swapHelperFloat(float[] arr, int index1, int index2) {
            if (index1 != index2) {
                float tmp = arr[index1];
                arr[index1] = arr[index2];
                arr[index2] = tmp;
            }
        }

        private void swapHelperInt(int[] arr, int index1, int index2) {
            if (index1 != index2) {
                arr[index1] += arr[index2];
                arr[index2] = arr[index1] - arr[index2];
                arr[index1] -= arr[index2];
            }
        }

        private void swap(int[] arr, float[] coarr, int index1, int index2) {
            swapHelperInt(arr, index1, index2);
            swapHelperFloat(coarr, index1, index2);
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

        private void bubbleSort(int[] arr, float[] coarr, int len) {
            boolean change = false;
            do {
                change = false;
                for (int i = 1; i < len; i++) {
                    if (arr[i] < arr[i-1]) {
                        int tmp = arr[i];
                        arr[i] = arr[i-1];
                        arr[i-1] = tmp;
                        float tmpf = coarr[i];
                        coarr[i] = coarr[i-1];
                        coarr[i-1] = tmpf;
                        change = true;
                    }
                }
            } while (change);
        }

        private void stupidSort(int[] arr, float[] coarr, int len) {
          for (int i = 0; i < len; i++) {
            int minIndex = i;
            int minVal = arr[i];
            for (int j = i + 1; j < len; j++) {
              if (arr[j] < minVal) {
                minIndex = j;
                minVal = arr[j];
            }
          }
          arr[minIndex] = arr[i];
          arr[i] = minVal;

          float tmp = coarr[i];
          coarr[i] = coarr[minIndex];
          coarr[minIndex] = tmp;
        }
      }

  private void quicksort(int[] arr, float[] coarr, int len, int[] partitions1,
          int[] partitions2) {

      int[] currentPartitions = partitions1;
      int[] otherPartitions = partitions2;
      int currentNpartitions = 1;
      int otherNpartitions;
      currentPartitions[0] = 0;
      currentPartitions[1] = len-1;

      while (currentNpartitions > 0) {
          otherNpartitions = 0;

          for (int i = 0; i < currentNpartitions; i++) {
              int low = currentPartitions[2*i];
              int high = currentPartitions[2*i+1];
              int baseOfPivot = partition(arr, coarr, low, high, low);
              int pivot = arr[baseOfPivot];
              int topOfPivot = baseOfPivot;
              while (topOfPivot <= high && arr[topOfPivot] == pivot) {
                  topOfPivot++;
              }

              if (baseOfPivot > low) {
                  otherPartitions[2*otherNpartitions] = low;
                  otherPartitions[2*otherNpartitions + 1] = baseOfPivot-1;
                  otherNpartitions++;
              }
              if (topOfPivot <= high) {
                  otherPartitions[2 * otherNpartitions] = topOfPivot;
                  otherPartitions[2 * otherNpartitions + 1] = high;
                  otherNpartitions++;
              }
          }

          int tmpNPartitions = currentNpartitions;
          currentNpartitions = otherNpartitions;
          otherNpartitions = tmpNPartitions;

          int[] tmpPartitions = currentPartitions;
          currentPartitions = otherPartitions;
          otherPartitions = tmpPartitions;
      }

      /*
      int baseOfPivot = partition(arr, coarr, low, high, low);
      int pivot = arr[baseOfPivot];
      int topOfPivot = baseOfPivot;
      while (topOfPivot < arr.length && arr[topOfPivot] == pivot) {
          topOfPivot++;
      }
      quicksort(arr, coarr, low, baseOfPivot-1);
      quicksort(arr, coarr, topOfPivot, high);
      */
  }

        protected void map(int column, int[] occurrenceIndices,
                float[] occurrenceVals, int occurrenceLen) {

            int cooccurrences = 0;
            int prunedCooccurrences = 0;

            stupidSort(occurrenceIndices, occurrenceVals, occurrenceLen);
            for (int n = 0; n < occurrenceLen; n++) {
                int occurrenceAIndex = occurrenceIndices[n];
                float occurrenceAVal = occurrenceVals[n];

                int dotsSoFar = 0;
                int[] dotsIndices = allocInt(occurrenceLen - n);
                float[] dotsVals = allocFloat(occurrenceLen - n);

                for (int m = n; m < occurrenceLen; m++) {
                    int occurrenceBIndex = occurrenceIndices[m];
                    float occurrenceBVal = occurrenceVals[m];
                    dotsIndices[dotsSoFar] = occurrenceBIndex;
                    dotsVals[dotsSoFar] = similarity_aggregate(
                            occurrenceAVal, occurrenceBVal);
                    dotsSoFar++;
                    cooccurrences++; // unused
                }

                if (dotsSoFar > 0) {
                    write(column, dotsIndices, dotsVals, dotsSoFar);
                }
            }

            // int[] indices = allocInt(3);
            // float[] vals = allocFloat(3);
            // indices[0] = 0; indices[1] = 1; indices[2] = 2;
            // vals[0] = 0; vals[1] = 1; vals[2] = 2;
            // write(column, indices, vals, 3);

            // int[] partitions1 = allocInt(occurrenceLen * 2);
            // int[] partitions2 = allocInt(occurrenceLen * 2);

            // quicksort(occurrenceIndices, occurrenceVals, occurrenceLen,
            //         partitions1, partitions2);

            /*
            bubbleSort(occurrenceIndices, occurrenceVals, occurrenceLen);
            
            */
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

        private final float threshold = Float.MIN_VALUE;
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

        private float getFromSparseVector(int index, int[] indices,
                float[] vals, int len) {
            int offset = findInSortedArr(index, indices, len);
            if (offset == -1) {
                return 0.0f;
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

          while (queueOfOffsets[checkingSlot] > newIndex) {
            queueOfOffsets[emptySlot] = queueOfOffsets[checkingSlot];
            queueOfVectors[emptySlot] = queueOfVectors[checkingSlot];
            emptySlot = checkingSlot;
            checkingSlot = reverseIterate(checkingSlot, queueOfOffsets, queueLength);
          }

          queueOfOffsets[emptySlot] = newIndex;
          queueOfVectors[emptySlot] = newVector;
        }

        protected void reduce(int row, HadoopCLFsvecValueIterator valsIter) {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNElements += valsIter.vectorLength(i);
            }

            // Arrays to merge input values into
            int[] dotsIndices = allocInt(totalNElements);
            float[] dotsVals = allocFloat(totalNElements);

            int nOutput = merge(valsIter, dotsIndices, dotsVals, totalNElements);

            int[] normsIndices = this.getGlobalIndices(GLOBAL_NORMS_INDEX);
            float[] normsVals = this.getGlobalFVals(GLOBAL_NORMS_INDEX);
            int normsLen = this.globalsLength(GLOBAL_NORMS_INDEX);
            float normA = getFromSparseVector(row, normsIndices,
                    normsVals, normsLen);

            int similaritySoFar = 0;
            for (int i = 0; i < nOutput; i++) {
                // similarity_similarity collapses to 'return arg0;'
                float similarityValue = dotsVals[i];
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

        private void stupidSort(int[] arr, int[] coarr, int len) {
          for (int i = 0; i < len; i++) {
            int minIndex = i;
            int minVal = arr[i];
            for (int j = i + 1; j < len; j++) {
              if (arr[j] < minVal) {
                minIndex = j;
                minVal = arr[j];
              }
            }
            arr[minIndex] = arr[i];
            arr[i] = minVal;

            int tmp = coarr[i];
            coarr[i] = coarr[minIndex];
            coarr[minIndex] = tmp;
          }
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

          while (queueOfOffsets[checkingSlot] > newIndex) {
            queueOfOffsets[emptySlot] = queueOfOffsets[checkingSlot];
            queueOfVectors[emptySlot] = queueOfVectors[checkingSlot];
            emptySlot = checkingSlot;
            checkingSlot = reverseIterate(checkingSlot, queueOfOffsets, queueLength);
          }

          queueOfOffsets[emptySlot] = newIndex;
          queueOfVectors[emptySlot] = newVector;
        }

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNElements += valsIter.vectorLength(i);
            }

            // Arrays to merge input values into
            int[] combinedIndices = allocInt(totalNElements);
            float[] combinedVals = allocFloat(totalNElements);

            int nOutput = Utils.merge(valsIter, combinedIndices, combinedVals,
                    totalNElements);

            write(key, combinedIndices, combinedVals, nOutput);
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

       String numNonZeroEntriesPath = "file:///home/yiskylee/mahout/allTempFiles/numNonZeroEntries.bin";
       String normsPath = "file:///home/yiskylee/mahout/allTempFiles/norms.bin";
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

       System.out.println("Norms length = "+normSoFar);
       System.out.println("numNonZeroEntriesIndices length = "+numNonZeroEntriesSoFar);

       conf.addHadoopCLGlobal(numNonZeroEntriesIndices, numNonZeroEntriesVals);
       conf.addHadoopCLGlobal(normIndices, normVals);

       Job job = new Job(conf, "mahout-pairwise");
       ((JobConf)job.getConfiguration()).setJar("/home/yiskylee/hadoopcl-app/PairwiseSimilarity.jar");
       // job.setJarByClass(PairwiseSimilarity.class);
       // job.setJar("/home/yiskylee/hadoopcl-app/PairwiseSimilarity.jar");

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
