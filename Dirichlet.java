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
import org.apache.mahout.clustering.dirichlet.UncommonDistributions;

public class Dirichlet {

    public static class DirichletMapper extends IntBsvecIntBsvecHadoopCLMapperKernel {
        public DirichletMapper(HadoopOpenCLContext c, Integer i) { super(c, i); }

        protected void map(int key, int[] indices, double[] vals, int length) {
            int nClusters = nGlobals() / 3;

            double maxPdf = -1.0;
            int maxPdfIndex = -1;

            int[] outputIndices = allocInt(length);
            double[] outputVals = allocDouble(length);

            for (int i = 0; i < nClusters; i++) {
                int[] centroidIndices = this.getGlobalIndices(3 * i);
                double[] centroidVals = this.getGlobalVals(3 * i);
                int centroidLength = this.globalsLength(3 * i);

                int[] radiusIndices = this.getGlobalIndices(3 * i + 1);
                double[] radiusVals = this.getGlobalVals(3 * i + 1);
                int radiusLength = this.globalsLength(3 * i + 1);

                double zProd2piR = getGlobalVals(3 * i + 2)[0];

                int inputOffset = 0;
                int centroidOffset = 0;

                double result = 0;
                for (int j = 0; j < radiusLength; j++) {
                    int index = radiusIndices[j];

                    while (inputOffset < length && indices[inputOffset] < index) {
                        inputOffset++;
                    }
                    while (centroidOffset < centroidLength && centroidIndices[centroidOffset] < index) {
                        centroidOffset++;
                    }
                    double inputVal = 0.0;
                    if (inputOffset < length && indices[inputOffset] == index) {
                        inputVal = vals[inputOffset];
                    }
                    double centroidVal = 0.0;
                    if (centroidOffset < centroidLength && centroidIndices[centroidOffset] == index) {
                        centroidVal = centroidVals[centroidOffset];
                    }
                    double quotient = (inputVal - centroidVal) / radiusVals[j];
                    result += (quotient * quotient);
                }

                double pdf = Math.exp(-(result / 2)) / zProd2piR;
                if (maxPdfIndex == -1 || maxPdf < pdf) {
                    maxPdfIndex = i;
                    maxPdf = pdf;
                }
            }

            for (int i = 0; i < length; i++) {
                outputIndices[i] = indices[i];
                outputVals[i] = vals[i];
            }
            write(maxPdfIndex, outputIndices, outputVals, length);
        }

        public void deviceStrength(DeviceStrength str) {
            str.add(Device.TYPE.GPU, 10);
        }

        public Device.TYPE[] validDevices() { return null; }

        private static final Map<Device.TYPE, String> fileMapping =
            new HashMap<Device.TYPE, String>();
        static {
            fileMapping.put(Device.TYPE.CPU, "dirichlet.mapper.cpu");
            fileMapping.put(Device.TYPE.GPU, "dirichlet.mapper.gpu");
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
       // Davinci
       // Path clusterPath = new Path("/scratch/jmg3/wiki-sparse/random-seed-sparse");
       // Path radiusPath = new Path("/scratch/jmg3/wiki-sparse/random-seed-sparse-radius");
       // AMD
       Path clusterPath = new Path("/home-nis/mgrossman/hadoopcl-input/wiki-sparse/random-seed-sparse");
       Path radiusPath = new Path("/home-nis/mgrossman/hadoopcl-input/wiki-sparse/random-seed-sparse-radius");

       SequenceFile.Reader clusterReader = new SequenceFile.Reader(localFs, clusterPath, conf);
       SequenceFile.Reader radiusReader = new SequenceFile.Reader(localFs, radiusPath, conf);

       IntWritable clusterKey = new IntWritable();
       BSparseVectorWritable clusterCenter = new BSparseVectorWritable();

       IntWritable radiusKey = new IntWritable();
       BSparseVectorWritable clusterRadius = new BSparseVectorWritable();

       int count = 0;
       while(clusterReader.next(clusterKey, clusterCenter)) {
           radiusReader.next(radiusKey, clusterRadius);
           conf.addHadoopCLGlobal(clusterCenter.indices(), clusterCenter.vals());
           conf.addHadoopCLGlobal(clusterRadius.indices(), clusterRadius.vals());
           double zProd2piR = 1.0;
           for (int i = 0; i < clusterRadius.size(); i++) {
               zProd2piR *= clusterRadius.vals()[i] * UncommonDistributions.SQRT2PI;
           }
           int[] zprodIndices = new int[1];
           double[] zprodVals = new double[1];
           zprodVals[0] = zProd2piR;
           conf.addHadoopCLGlobal(zprodIndices, zprodVals);
           count++;
       }

       clusterReader.close();
       radiusReader.close();

       Job job = new Job(conf, "dirichlet");
       job.setJarByClass(Dirichlet.class);

       job.setOutputKeyClass(IntWritable.class);
       job.setOutputValueClass(BSparseVectorWritable.class);

       job.setMapOutputKeyClass(IntWritable.class);
       job.setMapOutputValueClass(BSparseVectorWritable.class);

       job.setMapperClass(OpenCLMapper.class);
       job.setOCLMapperClass(DirichletMapper.class);

       job.setNumReduceTasks(0);
       
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
