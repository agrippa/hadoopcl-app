import java.io.IOException;
import java.util.*;
import java.lang.*;

import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;

//import org.apache.hadoop.mapreduce.*;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
/*
import com.amd.aparapi.Kernel;
import com.amd.aparapi.Range;
import com.amd.aparapi.Device;
import com.amd.aparapi.OpenCLPlatform;
import com.amd.aparapi.OpenCLDevice;
*/

public class BlackScholesJavaVersion  {

    public static class BlackScholesMapper extends Mapper<IntWritable, FloatWritable, 
           IntWritable, FloatWritable> {
               private static float[] inputs = null;

               float phi(float X) {
                   final float c1 = 0.319381530f;
                   final float c2 = -0.356563782f;
                   final float c3 = 1.781477937f;
                   final float c4 = -1.821255978f;
                   final float c5 = 1.330274429f;

                   final float zero = 0.0f;
                   final float one = 1.0f;
                   final float two = 2.0f;
                   final float temp4 = 0.2316419f;

                   final float oneBySqrt2pi = 0.398942280f;

                   float absX = Math.abs(X);
                   float t = one / (one + temp4 * absX);

                   float y = one - oneBySqrt2pi * (float)Math.exp(-X * X / two) * t * (c1 + t * (c2 + t * (c3 + t * (c4 + t * c5))));

                   float result = (X < zero) ? (one - y) : y;

                   return result;
               }

               public void map(IntWritable key, FloatWritable value, Context context) 
                   throws IOException, InterruptedException {


                       final float S_LOWER_LIMIT = 10.0f;

                       final float S_UPPER_LIMIT = 100.0f;

                       final float K_LOWER_LIMIT = 10.0f;

                       final float K_UPPER_LIMIT = 100.0f;

                       final float T_LOWER_LIMIT = 1.0f;

                       final float T_UPPER_LIMIT = 10.0f;

                       final float R_LOWER_LIMIT = 0.01f;

                       final float R_UPPER_LIMIT = 0.05f;

                       final float SIGMA_LOWER_LIMIT = 0.01f;

                       final float SIGMA_UPPER_LIMIT = 0.10f;

                       float put = 0.0f;
                       float call = 0.0f;

                       for(int incr = 100; incr >= 0; incr--) 
                       {
                           
                           float d1, d2;
                           float phiD1 = 0.0f, phiD2 = 0.0f;
                           float sigmaSqrtT;
                           float KexpMinusRT = 0.0f;
                           float two = 2.0f;
                           float inRand = value.get() + (incr * 0.01f);
                           //float inRand = value.get();
                           float S = S_LOWER_LIMIT * inRand + S_UPPER_LIMIT * (1.0f - inRand);
                           float K = K_LOWER_LIMIT * inRand + K_UPPER_LIMIT * (1.0f - inRand);
                           float T = T_LOWER_LIMIT * inRand + T_UPPER_LIMIT * (1.0f - inRand);
                           float R = R_LOWER_LIMIT * inRand + R_UPPER_LIMIT * (1.0f - inRand);
                           float sigmaVal = SIGMA_LOWER_LIMIT * inRand + SIGMA_UPPER_LIMIT * (1.0f - inRand);

                           sigmaSqrtT = sigmaVal * (float)Math.sqrt(T);

                           d1 = ((float)Math.log(S / K) + (R + sigmaVal * sigmaVal / two) * T) / sigmaSqrtT;
                           d2 = d1 - sigmaSqrtT;

                           KexpMinusRT = K * (float)Math.exp(-R * T);

                           phiD1 = phi(d1);
                           phiD2 = phi(d2);

                           call = S * phiD1 - KexpMinusRT * phiD2;

                           phiD1 = phi(-d1);
                           phiD2 = phi(-d2);

                           put= KexpMinusRT * phiD2 - S * phiD1;
                       }
                       context.write(new IntWritable((int)key.get()), new FloatWritable(put-call));
                   }
           }

    public static class BlackScholesReducer extends Reducer<IntWritable, FloatWritable, IntWritable, FloatWritable> {
        public void reduce(IntWritable key, Iterable<FloatWritable> values, Context context)
            throws IOException, InterruptedException {
                for(FloatWritable val : values ){
                    context.write(key, val);
                }
            }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
        //// Compress Map output
        //conf.set("mapred.compress.map.output","true");
        //conf.set("mapred.map.output.compression.codec","org.apache.hadoop.io.compress.SnappyCodec");
        //
        //// Compress MapReduce output
        //conf.set("mapred.output.compress","true");
        //conf.set("mapred.output.compression","org.apache.hadoop.io.compress.SnappyCodec");

        Job job = new Job(conf, "blackscholes");
        job.setJarByClass(BlackScholesMapper.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(FloatWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(FloatWritable.class);

        job.setMapperClass(BlackScholesMapper.class);
        job.setReducerClass(BlackScholesReducer.class);

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
