include ../Makefile.common

transform-mahout-input:
	javac -cp ${CLASSPATH} TransformMahoutInput.java

merge-int-sparse: file-merger merge/MergeIntSparseFiles.java
	javac -cp ${CLASSPATH} merge/MergeIntSparseFiles.java
merge-text-vector: file-merger merge/MergeTextVector.java
	javac -cp ${CLASSPATH} merge/MergeTextVector.java
file-merger:
	javac -cp ${CLASSPATH} merge/FileMerger.java

parallel-file-iterator: ParallelFileIterator.java
	javac -cp ${CLASSPATH} ParallelFileIterator.java
