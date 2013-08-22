CLASSPATH = /home/jmg3/commons-io-2.4-src/target/commons-io-2.2-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-io-2.1.jar:${MAHOUT_HOME}/integration/target/dependency/mahout-math-0.9-SNAPSHOT.jar:${MAHOUT_HOME}/integration/target/dependency/guava-14.0.1.jar:${MAHOUT_HOME}/core/target/mahout-core-0.9-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-lang-2.4.jar:${HADOOP_HOME}/lib/commons-configuration-1.6.jar:${HADOOP_HOME}/lib/commons-logging-1.1.1.jar:${HADOOP_HOME}/build/hadoop-core-1.0.4-SNAPSHOT.jar:${HADOOP_GPL_COMPRESSION_HOME}/build/hadoop-gpl-compression-0.2.0-dev.jar:build/hadoop-core-1.0.4-SNAPSHOT.jar:${APARAPI_HOME}/com.amd.aparapi/dist/aparapi.jar::${MAHOUT_HOME}/integration/target/mahout-integration-0.9-SNAPSHOT.jar:${MAHOUT_HOME}/integration/target/dependency/lucene-core-4.3.0.jar:.
RUN_FLAGS=-Djava.library.path=${HADOOP_HOME}/lib/native/Linux-amd64-64:${HADOOP_HOME}/build/native/Linux-amd64-64/lib -classpath ${CLASSPATH}:${HADOOP_HOME}/lib/commons-logging-1.1.1.jar:${HADOOP_HOME}/lib/commons-logging-api-1.0.4.jar:${HADOOP_HOME}/lib/commons-configuration-1.6.jar:${HADOOP_HOME}/lib/commons-lang-2.4.jar:.

all: SetupInputCompression.class
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d openclsortclasses/ SortOpenCLVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d javasortclasses/ SortJavaVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d openclkmeansclasses/ KMeansOpenCLVersion.java -Xlint:deprecation
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d opencldynamickmeansclasses/ KMeansDynamicOpenCLVersion.java -Xlint:deprecation
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d javakmeansclasses/ KMeansJavaVersion.java -Xlint:deprecation
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d openclpiclasses/ PiOpenCLVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d javapiclasses/ PiJavaVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d javablackscholesclasses/ BlackScholesJavaVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d openclblackscholesclasses/ BlackScholesOpenCLVersion.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d testmapinputsvecclasses/ TestMapInputSvec.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d testmapoutputsvecclasses/ TestMapOutputSvec.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d testreduceoutputsvecclasses/ TestReduceOutputSvec.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d mahoutkmeansclasses/ MahoutKMeans.java
	javac -g:lines -g:vars -classpath ${CLASSPATH} -d helloworldclasses/ HelloWorld.java
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

transforms: transform/TransformMahoutInput.java
	cd transform && javac -cp ${CLASSPATH} TransformMahoutInput.java

compression-gen-build:
	javac -cp ${CLASSPATH} CompressedInputGenerator.java
	javac -cp ${CLASSPATH} SortCompressedInputGenerator.java
	javac -cp ${CLASSPATH} BlacksholesCompressedInputGenerator.java
	javac -cp ${CLASSPATH} PiCompressedInputGenerator.java
	javac -cp ${CLASSPATH} KMeansCompressedInputGenerator.java
	javac -cp ${CLASSPATH} TestMapInputSvecCompressedInputGenerator.java
	javac -cp ${CLASSPATH} HelloWorldCompressedInputGenerator.java

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
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-map-input.input 10 1000000
svec-map-output-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-map-output.input 10 1000000
svec-reduce-output-generate:
	java ${RUN_FLAGS} TestMapInputSvecCompressedInputGenerator ${HADOOP_INPUT_DIR}/svec-reduce-output.input 10 1000000
helloworld-generate:
	java ${RUN_FLAGS} HelloWorldCompressedInputGenerator ${HADOOP_INPUT_DIR}/helloworld.input 10 10000

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
	javac -cp ${CLASSPATH} SetupInputCompression.java

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
