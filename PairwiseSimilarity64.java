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

public class PairwiseSimilarity64 {

    public static final int GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX = 0;
    public static final int GLOBAL_NORMS_INDEX = 1;
    public static final int GLOBAL_MAX_VALUES_INDEX = 2;
    public static final double NO_THRESHOLD = Double.MIN_VALUE;

    public static class PairwiseMapper extends
      IntSvecIntSvecHadoopCLMapperKernel {

        private final double threshold = 5000.0f;
        // private final double threshold = NO_THRESHOLD;

        /* CooccurrenceCountSimilarity */
        private double similarity_aggregate(double valA, double valB) {
          return 1.0;
        }

        /* CosineSimilarity */
        // private double similarity_aggregate(double valA, double valB) {
        //   return valA * valB;
        // }

        /* EuclideanDistanceSimilarity */
        // private double similarity_aggregate(double valA, double valB) {
        //   return valA * valB;
        // }

        /* CooccurrenceCountSimilarity */
        private boolean similarity_consider(double numNonZeroEntriesA,
            double numNonZeroEntriesB) {
          return numNonZeroEntriesA >= threshold &&
                    numNonZeroEntriesB >= threshold;
        }

        /* CosineSimilarity */
        private boolean similarity_consider(double numNonZeroEntriesA,
            double numNonZeroEntriesB, double maxValueA, double maxValueB) {
          return numNonZeroEntriesB >= threshold / maxValueA &&
            numNonZeroEntriesA >= threshold / maxValueB;
        }

        private void stupidSort(int[] arr, double[] coarr, int len) {
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

            double tmp = coarr[i];
            coarr[i] = coarr[minIndex];
            coarr[minIndex] = tmp;
          }
        }

        protected void map(int column, int[] occurrenceIndices,
            double[] occurrenceVals, int occurrenceLen) {

          int[] dotsIndices = null;
          double[] dotsVals = null;

          stupidSort(occurrenceIndices, occurrenceVals, occurrenceLen);

          for (int n = 0; n < occurrenceLen; n++) {
            int occurrenceAIndex = occurrenceIndices[n];
            double occurrenceAVal = occurrenceVals[n];

            double numNonZeroEntriesA = referenceGlobalVal(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX,
                occurrenceAIndex);

            if (threshold != NO_THRESHOLD && numNonZeroEntriesA < threshold) continue;

            int dotsSoFar = 0;
            if (dotsIndices == null) {
              dotsIndices = allocInt(occurrenceLen - n);
              dotsVals = allocDouble(occurrenceLen - n);
            }

            for (int m = n; m < occurrenceLen; m++) {
              int occurrenceBIndex = occurrenceIndices[m];

              double numNonZeroEntriesB = referenceGlobalVal(GLOBAL_NUM_NON_ZERO_ENTRIES_INDEX,
                  occurrenceBIndex);

              if (threshold == NO_THRESHOLD ||
                  similarity_consider(numNonZeroEntriesA, numNonZeroEntriesB)) {

                dotsIndices[dotsSoFar] = occurrenceBIndex;
                dotsVals[dotsSoFar] = similarity_aggregate(
                    occurrenceAVal, occurrenceVals[m]);
                dotsSoFar++;
              }
            }

            if (dotsSoFar > 0) {
              write(occurrenceAIndex, dotsIndices, dotsVals, dotsSoFar);
              dotsIndices = null;
              dotsVals = null;
            }
          }
        }

        public int getOutputPairsPerInput() {
          return 1;
        }

        public void deviceStrength(DeviceStrength str) {
          str.add(Device.TYPE.CPU, 10);
        }

        public Device.TYPE[] validDevices() {
          return null;
        }
      }

    public static class PairwiseReducer extends
      IntSvecIntSvecHadoopCLReducerKernel {

        private final double threshold = 5000.0f;
        // private final double threshold = Double.MIN_VALUE;
        private final boolean excludeSelfSimilarity = false;

        protected void reduce(int row, HadoopCLSvecValueIterator valsIter) {
          int[] dotsIndices = null;
          double[] dotsVals = null;
          int nOutput = -1;

          if (valsIter.nValues() == 1) {
            valsIter.seekTo(0);
            int length = valsIter.currentVectorLength();
            dotsIndices = allocInt(length);
            dotsVals = allocDouble(length);

            int[] inputIndices = valsIter.getValIndices();
            double[] inputVals = valsIter.getValVals();

            for (int i = 0; i < length; i++) {
              dotsIndices[i] = inputIndices[i];
              dotsVals[i] = inputVals[i];
            }
            nOutput = length;
          } else {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
              valsIter.seekTo(i);
              totalNElements += valsIter.currentVectorLength();
            }

            // Arrays to merge input values into
            dotsIndices = allocInt(totalNElements);
            dotsVals = allocDouble(totalNElements);

            // Stores an index indicating how far we've incremented into each
            // input vector so far.
            int[] vectorIndices = allocInt(valsIter.nValues());
            // Stores actual index values from the vectors, the current
            // minimum for that vector that hasn't been merged into the
            // output vector.
            int[] queueOfOffsets = allocInt(valsIter.nValues());
            int[] queueOfOffsetsLinks = allocInt(valsIter.nValues());
            int[] queueOfVectors = allocInt(valsIter.nValues());

            nOutput = merge(valsIter, dotsIndices, dotsVals, totalNElements,
                vectorIndices, queueOfOffsets, queueOfOffsetsLinks, queueOfVectors);
          }

          // double normA = referenceGlobalFval(GLOBAL_NORMS_INDEX, row);

          int similaritySoFar = 0;
          for (int i = 0; i < nOutput; i++) {
            // similarity_similarity collapses to 'return arg0;'
            double similarityValue = dotsVals[i];
            if (similarityValue >= threshold) {
              if (similaritySoFar != i) {
                dotsIndices[similaritySoFar] = dotsIndices[i];
                dotsVals[similaritySoFar] = similarityValue;
              }
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
            IntSvecIntSvecHadoopCLReducerKernel {

        protected void reduce(int key, HadoopCLSvecValueIterator valsIter) {
          int[] combinedIndices = null;
          double[] combinedVals = null;
          int nOutput = -1;
          if (valsIter.nValues() == 1) {
            int length = valsIter.currentVectorLength();
            combinedIndices = allocInt(length);
            combinedVals = allocDouble(length);

            int[] inputIndices = valsIter.getValIndices();
            double[] inputVals = valsIter.getValVals();

            for (int i = 0; i < length; i++) {
              combinedIndices[i] = inputIndices[i];
              combinedVals[i] = inputVals[i];
            }
            
            nOutput = length;
          } else {
            int totalNElements = 0;
            for (int i = 0; i < valsIter.nValues(); i++) {
                valsIter.seekTo(i);
                totalNElements += valsIter.currentVectorLength();
            }
            // Arrays to merge input values into
            combinedIndices = allocInt(totalNElements);
            combinedVals = allocDouble(totalNElements);
            // Stores an index indicating how far we've incremented into each
            // input vector so far.
            int[] vectorIndices = allocInt(valsIter.nValues());
            // Stores actual index values from the vectors, the current
            // minimum for that vector that hasn't been merged into the
            // output vector.
            int[] queueOfOffsets = allocInt(valsIter.nValues());
            int[] queueOfOffsetsLinks = allocInt(valsIter.nValues());
            int[] queueOfVectors = allocInt(valsIter.nValues());

            nOutput = merge(valsIter, combinedIndices, combinedVals, totalNElements,
                vectorIndices, queueOfOffsets, queueOfOffsetsLinks, queueOfVectors);
          }

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

        /*
        protected void reduce(int key, HadoopCLSvecValueIterator valsIter) {
          HashMap<Integer, MutableDouble> merged = new HashMap<Integer, MutableDouble>();
          TreeSet<Integer> sorted = new TreeSet<Integer>();

          for (int i = 0; i < valsIter.nValues(); i++) {
            valsIter.seekTo(i);
            int[] indices = valsIter.getValIndices();
            double[] vals = valsIter.getValVals();
            for (int j = 0; j < valsIter.currentVectorLength(); j++) {
              if (!merged.containsKey(indices[j])) {
                sorted.add(indices[j]);
                merged.put(indices[j], new MutableDouble(vals[j]));
              } else {
                merged.get(indices[j]).incr(vals[j]);
              }
            }
          }

          int[] outputIndices = new int[sorted.size()];
          double[] outputVals = new double[sorted.size()];
          int index = 0;
          for (Integer i : sorted) {
            outputIndices[index] = i.intValue();
            outputVals[index] = merged.get(i).val;
            index++;
          }
          write(key, outputIndices, outputVals, outputIndices.length);
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

        class MutableDouble {
          public double val;
          public MutableDouble(double v) {
            this.val = v;
          }
          public void incr(double v) { this.val += v; }
        }
        */
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
       ((JobConf)job.getConfiguration()).setJar("/home/yiskylee/hadoopcl-app/PairwiseSimilarity64.jar");
       // job.setJarByClass(PairwiseSimilarity.class);
       // job.setJar("/home/yiskylee/hadoopcl-app/PairwiseSimilarity.jar");

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(SparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(SparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(PairwiseMapper.class);
       
       job.setReducerClass(OpenCLReducer.class);
       job.setOCLReducerClass(PairwiseReducer.class);

       job.setCombinerClass(OpenCLReducer.class);
       job.setOCLCombinerClass(PairwiseCombiner.class);

       job.setOCLCombinerDeviceType(Device.TYPE.JAVA);

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
