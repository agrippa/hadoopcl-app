CLASSPATH = /home/jmg3/commons-io-2.4-src/target/commons-io-2.2-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-io-2.1.jar:${MAHOUT_HOME}/integration/target/dependency/mahout-math-0.9-SNAPSHOT.jar:${MAHOUT_HOME}/integration/target/dependency/guava-14.0.1.jar:${MAHOUT_HOME}/core/target/mahout-core-0.9-SNAPSHOT.jar:${HADOOP_HOME}/lib/commons-lang-2.4.jar:${HADOOP_HOME}/lib/commons-configuration-1.6.jar:${HADOOP_HOME}/lib/commons-logging-1.1.1.jar:${HADOOP_HOME}/build/hadoop-core-1.0.4-SNAPSHOT.jar:/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:build/hadoop-core-1.0.4-SNAPSHOT.jar:${APARAPI_HOME}/com.amd.aparapi/dist/aparapi.jar::${MAHOUT_HOME}/integration/target/mahout-integration-0.9-SNAPSHOT.jar:.

all: ReadTextLong.class ReadTextInt.class ReadTextVector.class ReadIntCluster.class \
		ReadIntFloat.class ReadDoubleDouble.class ReadIntInt.class ReadIntLong.class \
		ReadIntSparse.class

ReadIntSparse.class: ReadIntSparse.java MyReader.class
	javac -cp ${CLASSPATH} ReadIntSparse.java
ReadIntLong.class: ReadIntLong.java MyReader.class
	javac -cp ${CLASSPATH} ReadIntLong.java
ReadIntInt.class: ReadIntInt.java MyReader.class
	javac -cp ${CLASSPATH} ReadIntInt.java
ReadDoubleDouble.class: ReadDoubleDouble.java MyReader.class
	javac -cp ${CLASSPATH} ReadDoubleDouble.java
ReadIntFloat.class: ReadIntFloat.java MyReader.class
	javac -cp ${CLASSPATH} ReadIntFloat.java
ReadTextLong.class: ReadTextLong.java MyReader.class
	javac -cp ${CLASSPATH} ReadTextLong.java
ReadTextInt.class: ReadTextInt.java MyReader.class
	javac -cp ${CLASSPATH} ReadTextInt.java
ReadTextVector.class: ReadTextVector.java MyReader.class
	javac -cp ${CLASSPATH} ReadTextVector.java
ReadIntCluster.class: ReadIntCluster.java MyReader.class
	javac -cp ${CLASSPATH} ReadIntCluster.java
MyReader.class: MyReader.java
	javac -cp ${CLASSPATH} MyReader.java

clean:
	rm *.class
