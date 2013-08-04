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

public class PiJavaVersion {

    // maybe doubles or booleans aren't working well? try changing double to float and boolean to int
    public static class PiJavaMapper extends Mapper<LongWritable, LongWritable, IntWritable, IntWritable> {

        public void map(LongWritable key, LongWritable value, Context context) 
                throws IOException, InterruptedException {

            long m_z = key.get();
            long m_w = value.get();

            int nInside = 0;
            int nOutside = 0;
            for(int i = 0; i < 5000; i++) {
                m_z = 36969 * (m_z & 65535) + (m_z >> 16);
                m_w = 18000 * (m_w & 65535) + (m_w >> 16);
                long tmp = (m_z << 16);
                tmp = (tmp & 4294967295L);
                tmp = tmp + m_w;
                double x = (tmp + 1.0) * 2.328306435454494e-10;

                m_z = 36969 * (m_z & 65535) + (m_z >> 16);
                m_w = 18000 * (m_w & 65535) + (m_w >> 16);
                tmp = (m_z << 16);
                tmp = (tmp & 4294967295L);
                tmp = tmp + m_w;
                double y = (tmp + 1.0) * 2.328306435454494e-10;

                x = x - 0.5;
                y = y - 0.5;
                if(x * x + y * y > 0.25) {
                    nOutside = nOutside + 1;
                } else {
                    nInside = nInside + 1;
                }
            }
            context.write(new IntWritable(0), new IntWritable(nOutside));
            context.write(new IntWritable(1), new IntWritable(nInside));
        }
    }

    public static class PiJavaReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
        public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) 
                throws IOException, InterruptedException {
            int count = 0;
            for(IntWritable v : values) {
                count += v.get();
            }
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);
		Job job = new Job(conf, "pi");
        job.setJarByClass(PiJavaVersion.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setMapperClass(PiJavaMapper.class);
        job.setReducerClass(PiJavaReducer.class);

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
