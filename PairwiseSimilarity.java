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
import org.apache.hadoop.mapreduce.HadoopCLUtils;

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
    public static final float NO_THRESHOLD = Float.MIN_VALUE;

    public static class PairwiseMapper extends
      IntFsvecIntFsvecHadoopCLMapperKernel {

        private final float threshold = 5000.0f;
        // private final float threshold = NO_THRESHOLD;

        private float similarity_aggregate(float valA, float valB) {
          return 1.0f;
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

        protected void map(int column, int[] occurrenceIndices,
            float[] occurrenceVals, int occurrenceLen) {

          System.out.println("map got column="+column);

          int cooccurrences = 0;
          int prunedCooccurrences = 0;

          int[] dotsIndices = null;
          float[] dotsVals = null;

          stupidSort(occurrenceIndices, occurrenceVals, occurrenceLen);

          for (int n = 0; n < occurrenceLen; n++) {
            int occurrenceAIndex = occurrenceIndices[n];
            float occurrenceAVal = occurrenceVals[n];

            float numNonZeroEntriesA = referenceGlobalFval(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX,
                occurrenceAIndex);

            if (threshold != NO_THRESHOLD && numNonZeroEntriesA < threshold) continue;

            int dotsSoFar = 0;
            if (dotsIndices == null) {
              dotsIndices = allocInt(occurrenceLen - n);
              dotsVals = allocFloat(occurrenceLen - n);
            }

            for (int m = n; m < occurrenceLen; m++) {
              int occurrenceBIndex = occurrenceIndices[m];
              // float occurrenceBVal = occurrenceVals[m];

              float numNonZeroEntriesB = referenceGlobalFval(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX,
                  occurrenceBIndex);

              if (threshold == NO_THRESHOLD || (numNonZeroEntriesA >= threshold &&
                    numNonZeroEntriesB >= threshold)) {
                dotsIndices[dotsSoFar] = occurrenceBIndex;
                dotsVals[dotsSoFar] = similarity_aggregate(
                    occurrenceAVal, occurrenceVals[m]);
                dotsSoFar++;
                cooccurrences++; // unused
                    }
            }

            System.out.println("Completed iter for "+column+" with length "+dotsSoFar);

            if (dotsSoFar > 0) {
              write(occurrenceAIndex, dotsIndices, dotsVals, dotsSoFar);
              dotsIndices = null;
              dotsVals = null;
            }
          }

          return;
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

    public static class PairwiseReducer extends
      IntFsvecIntFsvecHadoopCLReducerKernel {

        private final float threshold = 5000.0f;
        // private final float threshold = Float.MIN_VALUE;
        private final boolean excludeSelfSimilarity = false;

        protected void reduce(int row, HadoopCLFsvecValueIterator valsIter) {
          int totalNElements = 0;
          for (int i = 0; i < valsIter.nValues(); i++) {
            totalNElements += valsIter.vectorLength(i);
          }

          System.out.println("Reducer getting row="+row+" with "+valsIter.nValues()+" values");

          int[] dotsIndices = null;
          float[] dotsVals = null;
          int nOutput = -1;

          if (valsIter.nValues() == 1) {
            dotsIndices = allocInt(totalNElements);
            dotsVals = allocFloat(totalNElements);

            int[] inputIndices = valsIter.getValIndices();
            float[] inputVals = valsIter.getValVals();
            int length = valsIter.currentVectorLength();

            for (int i = 0; i < length; i++) {
              dotsIndices[i] = inputIndices[i];
              dotsVals[i] = inputVals[i];
            }
            nOutput = length;
          } else {

            // Arrays to merge input values into
            dotsIndices = allocInt(totalNElements);
            dotsVals = allocFloat(totalNElements);

            // Stores an index indicating how far we've incremented into each
            // input vector so far.
            int[] vectorIndices = allocInt(valsIter.nValues());
            // Stores actual index values from the vectors, the current
            // minimum for that vector that hasn't been merged into the
            // output vector.
            int[] queueOfOffsets = allocInt(valsIter.nValues());
            // Stores ID for vector associated with index in queueOfOffsets.
            int[] queueOfVectors = allocInt(valsIter.nValues());

            nOutput = HadoopCLUtils.merge(valsIter, dotsIndices, dotsVals, totalNElements,
                vectorIndices, queueOfOffsets, queueOfVectors);
          }

          System.out.println("  Reducer merged inputs to length of "+nOutput);

          // float normA = referenceGlobalFval(GLOBAL_NORMS_INDEX, row);

          int similaritySoFar = 0;
          for (int i = 0; i < nOutput; i++) {
            // similarity_similarity collapses to 'return arg0;'
            float similarityValue = dotsVals[i];
            System.out.println("  Checking "+similarityValue+" against "+threshold);
            if (similarityValue >= threshold) {
              System.out.println("    Setting "+dotsIndices[i]+" to "+similarityValue);
              dotsIndices[similaritySoFar] = dotsIndices[i];
              dotsVals[similaritySoFar] = similarityValue;
              similaritySoFar++;
            }
          }

          System.out.println("  Filtered to length "+similaritySoFar+" exclude="+excludeSelfSimilarity);

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

        protected void reduce(int key, HadoopCLFsvecValueIterator valsIter) {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                totalNElements += valsIter.vectorLength(i);
            }

            System.out.println("Combiner getting key="+key+" with "+valsIter.nValues()+" values");

            // Arrays to merge input values into
            int[] combinedIndices = allocInt(totalNElements);
            float[] combinedVals = allocFloat(totalNElements);

            // Stores an index indicating how far we've incremented into each
            // input vector so far.
            int[] vectorIndices = allocInt(valsIter.nValues());
            // Stores actual index values from the vectors, the current
            // minimum for that vector that hasn't been merged into the
            // output vector.
            int[] queueOfOffsets = allocInt(valsIter.nValues());
            // Stores ID for vector associated with index in queueOfOffsets.
            int[] queueOfVectors = allocInt(valsIter.nValues());

            int nOutput = HadoopCLUtils.merge(valsIter, combinedIndices, combinedVals, totalNElements,
                vectorIndices, queueOfOffsets, queueOfVectors);

            System.out.println("Merged into "+nOutput);

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

       String numNonZeroEntriesPath = "file:///home/yiskylee/hadoopcl-app/threshold.1/numNonZeroEntries.bin";
       String normsPath = "file:///home/yiskylee/hadoopcl-app/threshold.1/norms.bin";
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
