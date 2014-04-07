include Makefile.common

all: SetupInputCompression.class
	javac -g -classpath ${CLASSPATH} -d testbsparsestridedclasses/ TestBSparseStrided.java 
	javac -g -classpath ${CLASSPATH} -d pairwise64classes/ PairwiseSimilarity64.java
	javac -g -classpath ${CLASSPATH} -d pairwise64classes_xiangyu/ PairwiseSimilarity64_xiangyu.java
	javac -g -classpath ${CLASSPATH} -d testglobalsongpuclasses/ TestGlobalsOnGPU.java
	javac -g -classpath ${CLASSPATH} -d testfglobalsongpuclasses/ TestFGlobalsOnGPU.java
	javac -g -classpath ${CLASSPATH} -d openclsortclasses/ SortOpenCLVersion.java
	javac -g -classpath ${CLASSPATH} -d javasortclasses/ SortJavaVersion.java
	javac -g -classpath ${CLASSPATH} -d openclkmeansclasses/ KMeansOpenCLVersion.java -Xlint:deprecation
	javac -g -classpath ${CLASSPATH} -d opencldynamickmeansclasses/ KMeansDynamicOpenCLVersion.java -Xlint:deprecation
	javac -g -classpath ${CLASSPATH} -d javakmeansclasses/ KMeansJavaVersion.java -Xlint:deprecation
	javac -g -classpath ${CLASSPATH} -d openclpiclasses/ PiOpenCLVersion.java
	javac -g -classpath ${CLASSPATH} -d javapiclasses/ PiJavaVersion.java
	javac -g -classpath ${CLASSPATH} -d javablackscholesclasses/ BlackScholesJavaVersion.java
	javac -g -classpath ${CLASSPATH} -d openclblackscholesclasses/ BlackScholesOpenCLVersion.java
	javac -g -classpath ${CLASSPATH} -d testmapinputsvecclasses/ TestMapInputSvec.java
	javac -g -classpath ${CLASSPATH} -d testmapoutputsvecclasses/ TestMapOutputSvec.java
	javac -g -classpath ${CLASSPATH} -d testreduceoutputsvecclasses/ TestReduceOutputSvec.java
	javac -g -classpath ${CLASSPATH} -d mahoutkmeansclasses/ MahoutKMeans.java
	javac -g -classpath ${CLASSPATH} -d helloworldclasses/ HelloWorld.java
	javac -g -classpath ${CLASSPATH} -d testmapinputivecclasses/ TestMapInputIvec.java
	javac -g -classpath ${CLASSPATH} -d testjustreduceoutputsvecclasses/ TestJustReduceOutputSvec.java
	javac -g -classpath ${CLASSPATH} -d teststridedclasses/ TestStridedPerf.java
	jar cvf TestBSparseStrided.jar -C testbsparsestridedclasses/ . SetupInputCompression.class
	jar cvf PairwiseSimilarity64.jar -C pairwise64classes/ .
	jar cvf PairwiseSimilarity64_xiangyu.jar -C pairwise64classes_xiangyu/ . 
	jar cvf SortOpenCLVersion.jar -C openclsortclasses/ . SetupInputCompression.class
	jar cvf SortJavaVersion.jar -C javasortclasses/ . SetupInputCompression.class
	jar cvf KMeansOpenCLVersion.jar -C openclkmeansclasses/ . SetupInputCompression.class
	jar cvf KMeansDynamicOpenCLVersion.jar -C opencldynamickmeansclasses/ . SetupInputCompression.class
	jar cvf KMeansJavaVersion.jar -C javakmeansclasses/ . SetupInputCompression.class
	jar cvf PiOpenCLVersion.jar -C openclpiclasses/ . SetupInputCompression.class
	jar cvf PiJavaVersion.jar -C javapiclasses/ . SetupInputCompression.class
	jar cvf BlackScholesOpenCLVersion.jar -C openclblackscholesclasses/ . SetupInputCompression.class
	jar cvf BlackScholesJavaVersion.jar -C javablackscholesclasses/ . SetupInputCompression.class
	jar cvf TestMapInputSvec.jar -C testmapinputsvecclasses/ . SetupInputCompression.class
	jar cvf TestMapOutputSvec.jar -C testmapoutputsvecclasses/ . SetupInputCompression.class
	jar cvf TestReduceOutputSvec.jar -C testreduceoutputsvecclasses/ . SetupInputCompression.class
	jar cvf MahoutKMeans.jar -C mahoutkmeansclasses/ . SetupInputCompression.class
	jar cvf HelloWorld.jar -C helloworldclasses/ . SetupInputCompression.class
	jar cvf TestMapInputIvec.jar -C testmapinputivecclasses/ . SetupInputCompression.class
	jar cvf TestJustReduceOutputSvec.jar -C testjustreduceoutputsvecclasses/ . SetupInputCompression.class
	jar cvf TestGlobalsOnGPU.jar -C testglobalsongpuclasses/ . SetupInputCompression.class
	jar cvf TestFGlobalsOnGPU.jar -C testfglobalsongpuclasses/ . SetupInputCompression.class
	jar cvf TestStridedPerf.jar -C teststridedclasses/ . SetupInputCompression.class

opencl-pairwise: SetupInputCompression.class
	javac -g -classpath ${CLASSPATH} -d pairwise64classes/ PairwiseSimilarity64.java
	jar cvf PairwiseSimilarity64.jar -C pairwise64classes/ .
opencl-kmeans: SetupInputCompression.class
	rm -rf mahoutkmeansclasses/*
	javac -g -classpath ${CLASSPATH} -d mahoutkmeansclasses/ MahoutKMeans.java
	jar cvf MahoutKMeans.jar -C mahoutkmeansclasses/ .
opencl-fuzzy: SetupInputCompression.class
	rm -rf fuzzyclasses/*
	javac -g -classpath ${CLASSPATH} -d fuzzyclasses/ FuzzyKMeans.java
	jar cvf FuzzyKMeans.jar -C fuzzyclasses/ .
opencl-bayes:
	rm -rf bayesclasses/*
	javac -g -classpath ${CLASSPATH} -d bayesclasses/ NaiveBayes.java
	jar cvf NaiveBayes.jar -C bayesclasses/ .
opencl-dirichlet:
	rm -rf dirichletclasses/*
	javac -g -classpath ${CLASSPATH} -d dirichletclasses/ Dirichlet.java
	jar cvf Dirichlet.jar -C dirichletclasses/ .
opencl-writable: SetupInputCompression.class
	rm -rf writableclasses/*
	javac -g -classpath ${CLASSPATH} -d writableclasses/ TestWritables.java
	jar cvf TestWritables.jar -C writableclasses/ .

opencl-pairwise_xiangyu: SetupInputCompression.class
	javac -g -classpath ${CLASSPATH} -d pairwise64classes_xiangyu/ PairwiseSimilarity64_xiangyu.java
	jar cvf PairwiseSimilarity64_xiangyu.jar -C pairwise64classes_xiangyu/ . 

cpu-pairwise: SetupInputCompression.class
	javac -g -classpath ${CLASSPATH} -d cpu-pairwise-classes/ pairwiseSimilarityCpu.java
	jar cvf pairwiseSimilarityCpu.jar -C cpu-pairwise-classes/ . SetupInputCompression.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/Vector.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/Vector$$Element.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/VectorWritable.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/AbstractVector.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/LengthCachingVector.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/SequentialAccessSparseVector.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/RandomAccessSparseVector.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/hadoop/similarity/cooccurrence/RowSimilarityJob.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/common/AbstractJob.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/hadoop/similarity/cooccurrence/measures/VectorSimilarityMeasure.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/hadoop/similarity/cooccurrence/measures/CooccurrenceCountSimilarity.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/common/ClassUtils.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/hadoop/similarity/cooccurrence/measures/CountbasedMeasure.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/hadoop/similarity/cooccurrence/Vectors.class \
	  -C ${MAHOUT_HOME}/core/target/classes org/apache/mahout/math/Varint.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/map/OpenIntIntHashMap.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/map/AbstractIntIntMap.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/set/AbstractSet.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/PersistentObject.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/function/IntIntProcedure.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/function/IntProcedure.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/Swapper.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/function/IntComparator.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/map/PrimeFinder.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/IndexException.class \
	  -C ${MAHOUT_HOME}/math/target/classes org/apache/mahout/math/CardinalityException.class

clean:
	rm *.class *.jar openclsortclasses/* javasortclasses/* openclkmeansclasses/* javakmeansclasses/* openclpiclasses/* javapiclasses/* openclblackscholesclasses/* javablackscholesclasses/* testmapinputsvecclasses/*

output-readers:
	cd readers && javac -cp ${CLASSPATH} BlackscholesReader.java
	cd readers && javac -cp ${CLASSPATH} SortReader.java
	cd readers && javac -cp ${CLASSPATH} PiReader.java
	cd readers && javac -cp ${CLASSPATH} MahoutSequenceDirReader.java
	cd readers && javac -cp ${CLASSPATH} MahoutReader.java
	cd readers && javac -cp ${CLASSPATH} SvecMapInputReader.java
	cd readers && javac -cp ${CLASSPATH} SvecReduceOutputReader.java
	cd readers && javac -cp ${CLASSPATH} HelloWorldReader.java
	cd readers && javac -cp ${CLASSPATH} PairsReader.java

transforms: transform/TransformMahoutInput.java transform/MergeIntSparseFiles.java
	cd transform && javac -cp ${CLASSPATH} FileMerger.java
	cd transform && javac -cp ${CLASSPATH} TransformMahoutInput.java
	cd transform && javac -cp ${CLASSPATH} MergeIntSparseFiles.java
	cd transform && javac -cp ${CLASSPATH} MergeTextVector.java

compression-gen-build:
	javac -cp ${CLASSPATH} CompressedInputGenerator.java
	javac -cp ${CLASSPATH} SortCompressedInputGenerator.java
	javac -cp ${CLASSPATH} BlacksholesCompressedInputGenerator.java
	javac -cp ${CLASSPATH} PiCompressedInputGenerator.java
	javac -cp ${CLASSPATH} KMeansCompressedInputGenerator.java
	javac -cp ${CLASSPATH} TestMapInputSvecCompressedInputGenerator.java
	javac -cp ${CLASSPATH} HelloWorldCompressedInputGenerator.java
	javac -cp ${CLASSPATH} MapInputIvecInputGenerator.java
	javac -cp ${CLASSPATH} TestJustReduceOutputSvecInputGenerator.java
	javac -cp ${CLASSPATH} GenerateRandomSvec.java
	javac -cp ${CLASSPATH} GlobalsOnGpuGenerator.java
	javac -cp ${CLASSPATH} GenerateSimpleBSparse.java
	javac -cp ${CLASSPATH} GenerateTestWritables.java

bs-generate:
	java ${RUN_FLAGS} BlacksholesCompressedInputGenerator ${HADOOP_INPUT_DIR}/blackscholes.input 50 038400
sort-generate:
	java ${RUN_FLAGS} SortCompressedInputGenerator ${HADOOP_INPUT_DIR}/sort.input 30 4000000
pi-generate:
	java ${RUN_FLAGS} PiCompressedInputGenerator ${HADOOP_INPUT_DIR}/pi.input 50 4000000
kmeans-generate:
	java ${RUN_FLAGS} KMeansCompressedInputGenerator ${HADOOP_INPUT_DIR}/kmeans.input 30 4000000
kmeans-dyn-generate:
	java ${RUN_FLAGS} KMeansCompressedInputGenerator ${HADOOP_INPUT_DIR}/kmeansdyn.input 30 4000000
svec-map-input-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-map-input.input 10 1000
svec-map-output-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-map-output.input 10 1000
svec-reduce-output-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-reduce-output.input 10 1000000
helloworld-generate:
	java ${RUN_FLAGS} HelloWorldCompressedInputGenerator ${HADOOP_INPUT_DIR}/helloworld.input 10 10000
ivec-map-input-generate:
	java ${RUN_FLAGS} MapInputIvecInputGenerator ${HADOOP_INPUT_DIR}/ivec-map-input.input 10 1000
svec-just-reduce-output-generate:
	java ${RUN_FLAGS} TestJustReduceOutputSvecInputGenerator ${HADOOP_INPUT_DIR}/svec-just-reduce-output.input 10 1000
random-svec-generate:
	java ${RUN_FLAGS} GenerateRandomSvec ${HADOOP_INPUT_DIR}/random-svec/ 100 1000000
globals-on-gpu-generate:
	java ${RUN_FLAGS} GlobalsOnGpuGenerator ${HADOOP_INPUT_DIR}/globals-on-gpu.input/ 10 1000
strided-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/test-strided.input 10 1000
bstrided-generate:
	java ${RUN_FLAGS} GenerateSimpleBSparse ${HADOOP_INPUT_DIR}/bsparse-strided.input 10 1000
writables-generate:
	java ${RUN_FLAGS} GenerateTestWritables ${HADOOP_INPUT_DIR}/test-writables.input 10 100

bs-read:
	cd readers && java -cp ${CLASSPATH} BlackscholesReader -1 /scratch/jmg3/blackscholes.output/part-r-*
sort-read:
	cd readers && java -cp ${CLASSPATH} SortReader -1 /scratch/jmg3/sort.output/part-r-*
pi-read:
	cd readers && java -cp ${CLASSPATH} PiReader -1 /scratch/jmg3/pi.output/part-r-*
seqdir-read:
	cd readers && java -cp ${CLASSPATH} MahoutSequenceDirReader /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir/part-m-00000
sparse-read:
	cd readers && java -cp ${CLASSPATH} MahoutReader /scratch/jmg3/mahout-kmeans-output/clusters-2-final/ cluster
	# cd readers && java -cp ${CLASSPATH} MahoutReader /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/ tf
svec-map-input-read:
	cd readers && java -cp ${CLASSPATH} SvecMapInputReader -1 /scratch/jmg3/svec-map-input.output/part-r-*
svec-map-output-read:
	cd readers && java -cp ${CLASSPATH} SvecMapInputReader -1 /scratch/jmg3/svec-map-output.output/part-r-*
svec-reduce-output-read:
	cd readers && java -cp ${CLASSPATH} SvecReduceOutputReader -1 /scratch/jmg3/svec-reduce-output.output/part-r-*
mahout-kmeans-read:
	cd readers && java -cp ${CLASSPATH} SvecReduceOutputReader -1 /scratch/jmg3/mahout-kmeans.output/part-r-*
mahout-kmeans-input-read:
	cd readers && java -cp ${CLASSPATH} SvecReduceOutputReader -1 /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/hadoopcl-input/part-r-*
mahout-kmeans-seed-read:
	cd readers && java -cp ${CLASSPATH} MahoutReader /scratch/jmg3/mahout-work-jmg3/reuters-kmeans-clusters/part-randomSeed seed
mahout-compare-ele:
	cd readers && java -cp ${CLASSPATH} MahoutReader /scratch/jmg3 compare

reuters-transform:
	cd transform && java -cp ${CLASSPATH} TransformMahoutInput /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/tfidf-vectors/ /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/hadoopcl-input/
asf-transform:
	cd transform && java -cp ${CLASSPATH} TransformMahoutInput /scratch/jmg3/asf-sparse/tfidf-vectors/ /scratch/jmg3/asf-sparse/hadoopcl-input/
wiki-transform:
	cd transform && java -cp ${CLASSPATH} TransformMahoutInput /scratch/jmg3/wiki-sparse/tfidf-vectors/ /scratch/jmg3/wiki-sparse/hadoopcl-input/

CompressedInputGenerator.class: CompressedInputGenerator.java
	javac CompressedInputGenerator.java

SetupInputCompression.class: SetupInputCompression.java
	javac -g -cp ${CLASSPATH} SetupInputCompression.java

blackscholesinput: BlackScholesInputGenerator.java
	javac -classpath ${CLASSPATH} BlackScholesInputGenerator.java
piinput: PiInputGenerator.java
	javac -classpath ${CLASSPATH} PiInputGenerator.java
kmeansinput: KMeansInputGenerator.java
	javac -classpath ${CLASSPATH} KMeansInputGenerator.java
sortinput: SortInputGenerator.java
	javac -classpath ${CLASSPATH} SortInputGenerator.java

blackscholesgenerate: BlackScholesInputGenerator.java
	java ${RUN_FLAGS} BlackScholesInputGenerator ${HADOOP_INPUT_DIR}/blackscholes.input/input 1 401920000 1004800 seq #200960000
blackscholesgeneratesmall: BlackScholesInputGenerator.java
	java ${RUN_FLAGS} BlackScholesInputGenerator ${HADOOP_INPUT_DIR}/blackscholes.input.small/input 1 20096000 20096000 seq
pigenerate: 
	java ${RUN_FLAGS} PiInputGenerator ${HADOOP_INPUT_DIR}/pi.input/input 1 400000000
kmeansgenerate: 
	java ${RUN_FLAGS} KMeansInputGenerator ${HADOOP_INPUT_DIR}/kmeans.input/input 1 400000000 #100000000
sortgenerate: 
	java ${RUN_FLAGS} SortInputGenerator ${HADOOP_INPUT_DIR}/sort.input/input 1 200000000
sortcompressgenerate: 
	java ${RUN_FLAGS} SortCompressedInputGenerator ${HADOOP_INPUT_DIR}/sort.input 50 17000000
