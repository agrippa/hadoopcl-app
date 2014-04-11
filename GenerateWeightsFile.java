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

public class GenerateWeightsFile {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("java GenerateWeightsFile output-path nfeatures nlabels");
            return;
        }
        final Configuration conf = new Configuration();
        final FileSystem fs = FileSystem.get(conf);

        int nfeatures = Integer.parseInt(args[1]);
        int nlabels = Integer.parseInt(args[2]);

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, new Path(args[0]),
                org.apache.hadoop.io.Text.class,
                VectorWritable.class);
        RandomAccessSparseVector labels = new RandomAccessSparseVector(nlabels);
        RandomAccessSparseVector features = new RandomAccessSparseVector(nfeatures);
        for (int i = 0; i < nlabels; i++) {
            labels.set(i, 0.0);
        }
        for (int i = 0; i < nfeatures; i++) {
            features.set(i, 1.0);
        }
        writer.append(new Text(TrainNaiveBayesJob.WEIGHTS_PER_FEATURE),
                new VectorWritable(features));
        writer.append(new Text(TrainNaiveBayesJob.WEIGHTS_PER_LABEL),
                new VectorWritable(labels));
        writer.close();
    }
}
