import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import org.apache.hadoop.io.*;
import org.apache.mahout.classifier.naivebayes.training.AbstractThetaTrainer;
import java.util.HashMap;
import org.apache.hadoop.fs.FileSystem;
import org.apache.mahout.classifier.naivebayes.training.StandardThetaTrainer;
import org.apache.mahout.classifier.naivebayes.training.TrainNaiveBayesJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.common.ClassUtils;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.commandline.DefaultOptionCreator;
import org.apache.mahout.common.mapreduce.VectorSumReducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.function.Functions;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.RowSimilarityJob;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.VectorSimilarityMeasures;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.VectorSimilarityMeasure;
import org.apache.mahout.math.map.OpenIntIntHashMap;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MahoutNaiveBayes {
    public static class ThetaMapper extends Mapper<IntWritable, VectorWritable,
           Text, VectorWritable> {

        private static final String weightsPath = "weights";
        private AbstractThetaTrainer trainer;

        @Override
        protected void setup(Context ctx) throws IOException, InterruptedException {
            super.setup(ctx);
            Configuration conf = ctx.getConfiguration();

            float alphaI = 5.0f;
            Map<String, Vector> scores = new HashMap<String, Vector>();
            SequenceFile.Reader reader = new SequenceFile.Reader(
                    FileSystem.get(conf), new Path(weightsPath), conf);
            Text key = new Text();
            VectorWritable val = new VectorWritable();
            while (reader.next(key, val)) {
                scores.put(key.toString(), val.get());
                key = new Text();
                val = new VectorWritable();
            }

            trainer = new StandardThetaTrainer(
                    scores.get(TrainNaiveBayesJob.WEIGHTS_PER_FEATURE),
                    scores.get(TrainNaiveBayesJob.WEIGHTS_PER_LABEL), alphaI);
        }

        @Override
        protected void map(IntWritable key, VectorWritable value, Context ctx)
                throws IOException, InterruptedException {
            trainer.train(key.get(), value.get());
        }

        @Override
        protected void cleanup(Context ctx) throws IOException, InterruptedException {
            ctx.write(new Text(TrainNaiveBayesJob.LABEL_THETA_NORMALIZER),
                    new VectorWritable(trainer.retrievePerLabelThetaNormalizer()));
            super.cleanup(ctx);
        }
    }

    public static class VectorSumReducer extends
            Reducer<WritableComparable<?>, VectorWritable,
            WritableComparable<?>, VectorWritable> {

        @Override
        protected void reduce(WritableComparable<?> key,
                Iterable<VectorWritable> values, Context ctx)
        throws IOException, InterruptedException {
        Vector vector = null;
        for (VectorWritable v : values) {

            if (vector == null) {
                vector = v.get();
            } else {
                vector.assign(v.get(), Functions.PLUS);
            }
        }

        ctx.write(key, new VectorWritable(vector));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "mahout-bayes");
		// ((JobConf)job.getConfiguration()).setJar("/home-nis/mgrossman/hadoopcl-app/MahoutNaiveBayes.jar");
		((JobConf)job.getConfiguration()).setJar("/home/jmg3/app/MahoutNaiveBayes.jar");

        job.setMapperClass(ThetaMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(VectorWritable.class);

        job.setReducerClass(VectorSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(VectorWritable.class);

        job.setCombinerClass(VectorSumReducer.class);

        job.setInputFormatClass(SequenceFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path("input"));
        FileOutputFormat.setOutputPath(job, new Path("output"));

        long start = System.currentTimeMillis();
        job.waitForCompletion(true);
        long stop = System.currentTimeMillis();
        System.err.println("Program Time = "+(stop-start)+" ms");
    }
}
