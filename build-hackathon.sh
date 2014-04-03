# export CLASSPATH=/home/jmg3/commons-io-2.4-src/target/commons-io-2.2-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-io-2.1.jar:${MAHOUT_HOME}/integration/target/dependency/mahout-math-0.9-SNAPSHOT.jar:${MAHOUT_HOME}/integration/target/dependency/guava-14.0.1.jar:${MAHOUT_HOME}/core/target/mahout-core-0.9-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-lang-2.4.jar:${HADOOP_HOME}/lib/commons-configuration-1.6.jar:${HADOOP_HOME}/lib/commons-logging-1.1.1.jar:${HADOOP_HOME}/build/hadoop-core-1.0.4-SNAPSHOT.jar:${HADOOP_GPL_COMPRESSION_HOME}/build/hadoop-gpl-compression-0.2.0-dev.jar:build/hadoop-core-1.0.4-SNAPSHOT.jar:${APARAPI_HOME}/com.amd.aparapi/dist/aparapi.jar::${MAHOUT_HOME}/integration/target/mahout-integration-0.9-SNAPSHOT.jar:${MAHOUT_HOME}/integration/target/dependency/lucene-core-4.3.0.jar:${MAHOUT_HOME}/examples/target/dependency/slf4j-jcl-1.7.5.jar:${MAHOUT_HOME}/examples/target/dependency/slf4j-api-1.7.5.jar:${MAHOUT_HOME}/integration/target/dependency/commons-math3-3.2.jar:.

rm KMeans.jar
rm hackathon/*
javac -g -classpath ${CLASSPATH} -d hackathon/ KMeans.java
javac -g -classpath ${CLASSPATH} -d hackathon/ KMeansUtil.java
jar cvf KMeans.jar -C hackathon .
