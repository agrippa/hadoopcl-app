include ../Makefile.common

all: ReadTextLong.class ReadTextInt.class ReadTextVector.class ReadIntCluster.class \
		ReadIntFloat.class ReadDoubleDouble.class ReadIntInt.class ReadIntLong.class \
		ReadIntSparse.class ReadTextCluster.class

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
ReadTextCluster.class: ReadTextCluster.java MyReader.class
	javac -cp ${CLASSPATH} ReadTextCluster.java
MyReader.class: MyReader.java
	javac -cp ${CLASSPATH} MyReader.java

clean:
	rm *.class
