import java.io.IOException;
import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;

public class SortJavaVersion {

    public static class SortJavaReducer extends Reducer<IntWritable, LongWritable, IntWritable, LongWritable> {
    
        public void reduce(IntWritable radix, Iterable<LongWritable> inputs, Context context) throws IOException, InterruptedException {
           
            /*
            List<Long> values = new ArrayList<Long>();
            for(LongWritable l : inputs) {
                values.add(l.get());
            }
            

            bubbleSort(values);

            
            for(Long l : values) {
                context.write(radix, new LongWritable(l.longValue()));
            }
            */

            for(LongWritable l : inputs) {
                context.write(radix, l);
            }
        }
    }

    public static class SortJavaMapper extends Mapper<LongWritable, LongWritable, IntWritable, LongWritable> {
        public void map(LongWritable val1, LongWritable val2, Context context) throws IOException, InterruptedException  {
            /*
            context.write(new IntWritable((int)val1.get()), val1);
            context.write(new IntWritable((int)val2.get()), val2);
            */
            long radixL = val1.get() >> 32;
            int radix = (int)radixL;
            context.write(new IntWritable(radix), val1);

            radixL = val2.get() >> 32;
            radix = (int)radixL;
            context.write(new IntWritable(radix), val2);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        SetupInputCompression.setupCompression(conf, args);

        Job job = new Job(conf, "sort");
        job.setJarByClass(SortJavaVersion.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setMapperClass(SortJavaMapper.class);
        job.setReducerClass(SortJavaReducer.class);

        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

//        job.setNumReduceTasks(0);

        long start = System.currentTimeMillis();
        job.waitForCompletion(true);
        long stop = System.currentTimeMillis();
        System.err.println("Execution Time "+(stop-start)+" ms");
    }

}
