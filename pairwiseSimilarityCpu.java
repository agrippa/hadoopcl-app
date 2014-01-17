import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
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

public class pairwiseSimilarityCpu {

	/**
	 * @param args
	 */
	public static final double NO_THRESHOLD = Double.MIN_VALUE;
	private static final int DEFAULT_MAX_SIMILARITIES_PER_ROW = 100;
	private static final int NORM_VECTOR_MARKER = Integer.MIN_VALUE;
	// private static final int MAXVALUE_VECTOR_MARKER = Integer.MIN_VALUE + 1;
	private static final int NUM_NON_ZERO_ENTRIES_VECTOR_MARKER = Integer.MIN_VALUE + 2;

	private static final String SIMILARITY_CLASSNAME = RowSimilarityJob.class
			+ ".distributedSimilarityClassname";
	// private static final String NUMBER_OF_COLUMNS = RowSimilarityJob.class
	// 		+ ".numberOfColumns";
	private static final String MAX_SIMILARITIES_PER_ROW = RowSimilarityJob.class
			+ ".maxSimilaritiesPerRow";
	private static final String EXCLUDE_SELF_SIMILARITY = RowSimilarityJob.class
			+ ".excludeSelfSimilarity";

	private static final String THRESHOLD = RowSimilarityJob.class
			+ ".threshold";
	private static final String NORMS_PATH = RowSimilarityJob.class
			+ ".normsPath";
	// private static final String MAXVALUES_PATH = RowSimilarityJob.class
	// 		+ ".maxWeightsPath";

	private static final String NUM_NON_ZERO_ENTRIES_PATH = RowSimilarityJob.class
			+ ".nonZeroEntriesPath";

	enum Counters {
		ROWS, COOCCURRENCES, PRUNED_COOCCURRENCES
	}

	protected Path tempPath;

	public static class CooccurrencesMapper extends
			Mapper<IntWritable, VectorWritable, IntWritable, VectorWritable> {

		private VectorSimilarityMeasure similarity;

		private OpenIntIntHashMap numNonZeroEntries;
		// private Vector maxValues;
		private double threshold;

		private static final Comparator<Vector.Element> BY_INDEX = new Comparator<Vector.Element>() {
			@Override
			public int compare(Vector.Element one, Vector.Element two) {
				return Ints.compare(one.index(), two.index());
			}
		};

		@Override
		protected void setup(Context ctx) throws IOException,
				InterruptedException {
			similarity = ClassUtils.instantiateAs(
					ctx.getConfiguration().get(SIMILARITY_CLASSNAME),
					VectorSimilarityMeasure.class);
			numNonZeroEntries = Vectors.readAsIntMap(new Path(ctx
					.getConfiguration().get(NUM_NON_ZERO_ENTRIES_PATH)), ctx
					.getConfiguration());
			// maxValues = Vectors.read(
			// 		new Path(ctx.getConfiguration().get(MAXVALUES_PATH)),
			// 		ctx.getConfiguration());
			threshold = Double.parseDouble(ctx.getConfiguration()
					.get(THRESHOLD));
		}

		private boolean consider(Vector.Element occurrenceA,
				Vector.Element occurrenceB) {
			int numNonZeroEntriesA = numNonZeroEntries.get(occurrenceA.index());
			int numNonZeroEntriesB = numNonZeroEntries.get(occurrenceB.index());

			// double maxValueA = maxValues.get(occurrenceA.index());
			// double maxValueB = maxValues.get(occurrenceB.index());

			return similarity.consider(numNonZeroEntriesA, numNonZeroEntriesB,
					0.0, 0.0 /*maxValueA, maxValueB*/ , threshold);
		}

		@Override
		protected void map(IntWritable column, VectorWritable occurrenceVector,
				Context ctx) throws IOException, InterruptedException {
			Vector.Element[] occurrences = Vectors.toArray(occurrenceVector);

			Arrays.sort(occurrences, BY_INDEX);
			int cooccurrences = 0;
			int prunedCooccurrences = 0;
			for (int n = 0; n < occurrences.length; n++) {
				Vector.Element occurrenceA = occurrences[n];
				Vector dots = new RandomAccessSparseVector(Integer.MAX_VALUE);
				for (int m = n; m < occurrences.length; m++) {
					Vector.Element occurrenceB = occurrences[m];
					if (threshold == NO_THRESHOLD
							|| consider(occurrenceA, occurrenceB)) {
						dots.setQuick(occurrenceB.index(),
								similarity.aggregate(occurrenceA.get(),
										occurrenceB.get()));
						cooccurrences++;
					} else {
						prunedCooccurrences++;
					}
				}
                                if (dots.getNumNonZeroElements() > 0) {
                                  ctx.write(new IntWritable(occurrenceA.index()),
                                                  new VectorWritable(dots));
                                }
			}
			ctx.getCounter(Counters.COOCCURRENCES).increment(cooccurrences);
			ctx.getCounter(Counters.PRUNED_COOCCURRENCES).increment(
					prunedCooccurrences);
		}
	}

	public static class SimilarityReducer extends
			Reducer<IntWritable, VectorWritable, IntWritable, VectorWritable> {

		private VectorSimilarityMeasure similarity;
		// private int numberOfColumns;
		private boolean excludeSelfSimilarity;
		private Vector norms;
		private double threshold;

		@Override
		protected void setup(Context ctx) throws IOException,
				InterruptedException {
			similarity = ClassUtils.instantiateAs(
					ctx.getConfiguration().get(SIMILARITY_CLASSNAME),
					VectorSimilarityMeasure.class);
			// numberOfColumns = ctx.getConfiguration().getInt(NUMBER_OF_COLUMNS,
			// 		-1);
			// Preconditions.checkArgument(numberOfColumns > 0,
			// 		"Incorrect number of columns!");
			excludeSelfSimilarity = ctx.getConfiguration().getBoolean(
					EXCLUDE_SELF_SIMILARITY, false);
			norms = Vectors.read(new Path(ctx.getConfiguration()
					.get(NORMS_PATH)), ctx.getConfiguration());
			threshold = Double
					.parseDouble(ctx.getConfiguration().get(THRESHOLD));
		}

		@Override
		protected void reduce(IntWritable row,
				Iterable<VectorWritable> partialDots, Context ctx)
				throws IOException, InterruptedException {

			Iterator<VectorWritable> partialDotsIterator = partialDots
					.iterator();
			Vector dots = partialDotsIterator.next().get();

			while (partialDotsIterator.hasNext()) {
				Vector toAdd = partialDotsIterator.next().get();
				for (Element nonZeroElement : toAdd.nonZeroes()) {
					dots.setQuick(nonZeroElement.index(),
							dots.getQuick(nonZeroElement.index())
									+ nonZeroElement.get());
				}
			}

			Vector similarities = dots.like();
			double normA = norms.getQuick(row.get());
			for (Element b : dots.nonZeroes()) {
				double similarityValue = similarity.similarity(b.get(), normA,
						norms.getQuick(b.index()), 0 /*numberOfColumns*/ );
				if (similarityValue >= threshold) {
					similarities.set(b.index(), similarityValue);
				}
			}

			if (excludeSelfSimilarity) {
				similarities.setQuick(row.get(), 0);
			}
                        if (similarities.getNumNonZeroElements() > 0) {
                          ctx.write(row, new VectorWritable(similarities));
                        }
		}
	}

	public static class VectorSumReducer
			extends
			Reducer<WritableComparable<?>, VectorWritable, WritableComparable<?>, VectorWritable> {

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
		// TODO Auto-generated method stub
		Configuration pairwiseConf = new Configuration();

		pairwiseConf.set(THRESHOLD, String.valueOf(Double.parseDouble(args[0])));
		pairwiseConf.set(NORMS_PATH, "norms-file");
		pairwiseConf.set(NUM_NON_ZERO_ENTRIES_PATH,
				"non-zero-entries-file");
		// pairwiseConf.set(MAXVALUES_PATH, maxValuesPath.toString());
		pairwiseConf.set(SIMILARITY_CLASSNAME,
                    "org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.CooccurrenceCountSimilarity");
		// pairwiseConf.setInt(NUMBER_OF_COLUMNS, numberOfColumns);
		pairwiseConf.setBoolean(EXCLUDE_SELF_SIMILARITY, false);

		Job pairwiseSimilarity = new Job(pairwiseConf, "mahout-pairwise-cpu");
                ((JobConf)pairwiseSimilarity.getConfiguration()).setJar("/home/yiskylee/hadoopcl-app/pairwiseSimilarityCpu.jar");

		pairwiseSimilarity.setMapperClass(CooccurrencesMapper.class);
		pairwiseSimilarity.setMapOutputKeyClass(IntWritable.class);
		pairwiseSimilarity.setMapOutputValueClass(VectorWritable.class);

		pairwiseSimilarity.setReducerClass(SimilarityReducer.class);
		pairwiseSimilarity.setOutputKeyClass(IntWritable.class);
		pairwiseSimilarity.setOutputValueClass(VectorWritable.class);

		pairwiseSimilarity.setCombinerClass(VectorSumReducer.class);

                pairwiseSimilarity.setInputFormatClass(SequenceFileInputFormat.class);
                pairwiseSimilarity.setOutputFormatClass(SequenceFileOutputFormat.class);
                FileInputFormat.addInputPath(pairwiseSimilarity, new Path("input"));
                FileOutputFormat.setOutputPath(pairwiseSimilarity, new Path("output"));

		long start = System.currentTimeMillis();
		boolean succeeded = pairwiseSimilarity.waitForCompletion(true);
		long stop = System.currentTimeMillis();
	}
}

