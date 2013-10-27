include ../Makefile.common

transform-mahout-input:
	javac -cp ${CLASSPATH} common/TransformMahoutInput.java
transform-wiki:
	java common.TransformMahoutInput /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged.1024/ /scratch/jmg3/wiki-sparse/hadoopcl-input.1024/
transform-wiki-length:
	java common.TransformMahoutInput /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-32-merged/ /scratch/jmg3/wiki-sparse/hadoopcl-input-length.32/
random-seed:
	javac -cp ${CLASSPATH} common/CreateRandomSeed.java
# create-wiki-seed:
# 	java -cp ${CLASSPATH} common.CreateRandomSeed /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged.128/ /scratch/jmg3/wiki-sparse/random-seed/ 128
# create-asf-seed:
# 	java -cp ${CLASSPATH} common.CreateRandomSeed /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-pruned-merged/ /scratch/jmg3/asf-sparse/random-seed/ 20
filter-zero-length: parallel-file-iterator
	javac -cp ${CLASSPATH} pipeline/FilterZeroLengthSparseVectors.java
filter-zero-length-wiki:
	java -cp ${CLASSPATH} pipeline.FilterZeroLengthSparseVectors /scratch/jmg3/wiki-sparse/hadoopcl-input-length/
select-random-seeds:
	javac -cp ${CLASSPATH} pipeline/SelectRandomSeeds.java
select-random-seeds-wiki:
	java -cp ${CLASSPATH} pipeline.SelectRandomSeeds /scratch/jmg3/wiki-sparse/hadoopcl-input-length.32/ /scratch/jmg3/wiki-sparse/random-seed/ 512


###########################################################
################### Checkers ##############################
###########################################################
sorted-checker:
	javac -cp ${CLASSPATH} checks/InputsSortedChecker.java
check-sorted:
	java -cp ${CLASSPATH} checks.InputsSortedChecker /scratch/jmg3/mahout-kmeans.input/block.none/

duplicate-checker:
	javac -cp ${CLASSPATH} checks/CheckDuplicates.java
check-duplicates:
	java -cp ${CLASSPATH} checks.CheckDuplicates /scratch/jmg3/mahout-kmeans.input/block.none/

quick-duplicate-checker:
	javac -cp ${CLASSPATH} checks/QuickCheckDuplicates.java
quick-check-duplicates:
	java -cp ${CLASSPATH} checks.QuickCheckDuplicates /scratch/jmg3/mahout-kmeans.input/block.none/

###########################################################
################### Mergers ###############################
###########################################################
merges: merge-int-sparse merge-text-vector

merge-int-sparse: file-merger merge/MergeIntSparseFiles.java
	javac -cp ${CLASSPATH} merge/MergeIntSparseFiles.java
merge-text-vector: file-merger merge/MergeTextVector.java
	javac -cp ${CLASSPATH} merge/MergeTextVector.java
file-merger:
	javac -cp ${CLASSPATH} merge/FileMerger.java

###########################################################
################### Mahout Pipeline++ #####################
###########################################################

full-pipeline: wiki-parser wiki-txt-to-seq asf-txt-to-seq word-count \
		merge-word-count word-id tf-vectors tf-to-df prune-tf tfidf \
		mypruner

wiki-parser: pipeline/ParseWiki.java
	javac -cp ${CLASSPATH} pipeline/ParseWiki.java
wiki-txt-to-seq: pipeline/WikiTxtToSeq.java
	javac -cp ${CLASSPATH} pipeline/WikiTxtToSeq.java
wiki-txt-to-seq-run:
	java -cp ${CLASSPATH} pipeline.WikiTxtToSeq /scratch/jmg3/wiki-txt/ /scratch/jmg3/wiki-seq/ 13638329

asf-txt-to-seq: pipeline/ASFTxtToSeq.java
	javac -cp ${CLASSPATH} pipeline/ASFTxtToSeq.java
asf-txt-to-seq-run:
	java -cp ${CLASSPATH} pipeline.ASFTxtToSeq /scratch/jmg3/asf-mail-archive-tar-files-flattened/ /scratch/jmg3/asf-seq 47868

txt-to-tokens:
	javac -cp ${CLASSPATH} pipeline/TextToTokens.java 
wiki-txt-to-tokens:
	java -cp ${CLASSPATH} pipeline.TextToTokens /scratch/jmg3/wiki-seq-merged/ /scratch/jmg3/wiki-sparse/tokenized-documents
asf-txt-to-tokens:
	java -cp ${CLASSPATH} pipeline.TextToTokens /scratch/jmg3/asf-seq-merged/ /scratch/jmg3/asf-sparse/tokenized-documents

word-count: pipeline/WordCountFromTokenized.java
	javac -cp ${CLASSPATH} pipeline/WordCountFromTokenized.java
merge-word-count: pipeline/MergeWordCount.java
	javac -cp ${CLASSPATH} pipeline/MergeWordCount.java
word-id: pipeline/GenerateUniqueWordIDs.java
	javac -cp ${CLASSPATH} pipeline/GenerateUniqueWordIDs.java
tf-vectors: pipeline/GenerateDFVectors.java
	javac -cp ${CLASSPATH} pipeline/GenerateDFVectors.java
tf-to-df: pipeline/TFtoDF.java
	javac -cp ${CLASSPATH} pipeline/TFtoDF.java
prune-tf: pipeline/PruneTF.java
	javac -cp ${CLASSPATH} pipeline/PruneTF.java
tfidf: pipeline/TFPrunedtoTFIDF.java
	javac -cp ${CLASSPATH} pipeline/TFPrunedtoTFIDF.java
mypruner: pipeline/MyPruner.java
	javac -cp ${CLASSPATH} pipeline/MyPruner.java
prune-with-target-length: pipeline/PruneWithTargetLength.java
	javac -cp ${CLASSPATH} pipeline/PruneWithTargetLength.java

wiki-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.WordCountFromTokenized /scratch/jmg3/wiki-sparse/tokenized-documents/ /scratch/jmg3/wiki-sparse/wordcount/
asf-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.WordCountFromTokenized /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/wordcount/
wiki-merge-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/wiki-sparse/wordcount/
asf-merge-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/asf-sparse/wordcount/
wiki-word-id:
	java -cp ${CLASSPATH} pipeline.GenerateUniqueWordIDs /scratch/jmg3/wiki-sparse/wordcount/final-counts /scratch/jmg3/wiki-sparse/wordcount/dictionary-file
asf-word-id:
	java -cp ${CLASSPATH} pipeline.GenerateUniqueWordIDs /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} pipeline.GenerateDFVectors /scratch/jmg3/wiki-sparse/tokenized-documents/ /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/wordcount/dictionary-file
asf-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} pipeline.GenerateDFVectors /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-df-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFtoDF /scratch/jmg3/wiki-sparse/wordcount/final-counts /scratch/jmg3/wiki-sparse/wordcount/dictionary-file /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/df-count/term-counts
asf-df-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFtoDF /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts
wiki-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneTF /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/df-count/term-counts /scratch/jmg3/wiki-sparse/tf-vectors-pruned/ 13637681
asf-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneTF /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tf-vectors-pruned/ 36909
wiki-tfidf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFPrunedtoTFIDF /scratch/jmg3/wiki-sparse/tf-vectors-pruned/ /scratch/jmg3/wiki-sparse/df-count/term-counts /scratch/jmg3/wiki-sparse/tfidf-vectors/ 149053452 13637681
asf-tfidf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFPrunedtoTFIDF /scratch/jmg3/asf-sparse/tf-vectors-pruned/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tfidf-vectors/ 124993853 36909

asf-myprune:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MyPruner /scratch/jmg3/asf-sparse/tfidf-vectors-transformed/ /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-pruned/ /scratch/jmg3/asf-sparse/token-counts 50
wiki-myprune:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MyPruner /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned/ /scratch/jmg3/wiki-sparse/token-counts c 1024
asf-prune-for-length:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneWithTargetLength /scratch/jmg3/asf-sparse/tfidf-vectors-transformed/ /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-lengthpruned/ /scratch/jmg3/asf-sparse/token-counts 32
wiki-prune-for-length:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneWithTargetLength /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-32/ /scratch/jmg3/wiki-sparse/token-counts 32

wiki-merge:
	java merge.MergeTextVector /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged/ 230 part-r-
wiki-length-merge:
	java merge.MergeTextVector /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-32/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-32-merged/ 130 part-r-

###########################################################
################### Utilities #############################
###########################################################
parallel-file-iterator: common/ParallelFileIterator.java
	javac -cp ${CLASSPATH} common/ParallelFileIterator.java
take-partial-count: pipeline/TakePartialCounts.java
	javac -cp ${CLASSPATH} pipeline/TakePartialCounts.java
mutable-long: common/MutableLong.java
	javac -cp ${CLASSPATH} common/MutableLong.java
token-count: common/TokenCount.java
	javac -cp ${CLASSPATH} common/TokenCount.java
clean:
	rm *.class
	rm merge/*.class
	rm pipeline/*.class
