include ../Makefile.common

transform-mahout-input:
	javac -cp ${CLASSPATH} common/TransformMahoutInput.java
transform-wiki:
	java common.TransformMahoutInput /scratch/jmg3/wiki-sparse/9.tfidf-vectors-transformed-lengthpruned-96/ /scratch/jmg3/wiki-sparse/10.hadoopcl-input/
transform-wiki-length:
	java common.TransformMahoutInput /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-64-merged/ /scratch/jmg3/wiki-sparse/hadoopcl-input-length.64/
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
select-random-seeds-asf:
	java -cp ${CLASSPATH} pipeline.SelectRandomSeeds /scratch/jmg3/asf-sparse/hadoopcl-input.lengthpruned-32/ /scratch/jmg3/asf-sparse/random-seed/ 64
build-dump-features:
	javac -cp ${CLASSPATH} pipeline/DumpFeatures.java
dump-features:
	java -cp ${CLASSPATH} pipeline.DumpFeatures /scratch/jmg3/mahout-kmeans.input/block.none /scratch/jmg3/features.dump
build-classify-points:
	javac -cp ${CLASSPATH} pipeline/ClassifyPoints.java
classify-points:
	java -cp ${CLASSPATH} pipeline.ClassifyPoints \
		/scratch/jmg3/wiki-sparse/9.tfidf-vectors-transformed-lengthpruned-32-combined-merged/ \
		/scratch/jmg3/first-iter-clusters \
		/scratch/jmg3/classified-points
build-subset-cluster:
	javac -cp ${CLASSPATH} pipeline/SubsetClusters.java
subset-cluster:
	java -cp ${CLASSPATH} pipeline.SubsetClusters /scratch/jmg3/wiki-sparse/random-seed-cluster /scratch/jmg3/wiki-sparse/random-seed-cluster.64 64
build-subset-sparse:
	javac -cp ${CLASSPATH} pipeline/SubsetSparse.java
subset-sparse:
	java -cp ${CLASSPATH} pipeline.SubsetSparse /scratch/jmg3/wiki-sparse/random-seed-sparse /scratch/jmg3/wiki-sparse/random-seed-sparse.128 128
bsparse-to-vector-build:
	javac -cp ${CLASSPATH} pipeline/BSparseToVector.java
bsparse-to-vector:
	java -cp ${CLASSPATH} pipeline.BSparseToVector ~/hadoopcl-input/bayes-dataset/classified-points.splitless/ ~/hadoopcl-input/bayes-dataset/classified-points.splitless.vector/ 126007518
cluster-to-bsparse-build:
	javac -cp ${CLASSPATH} pipeline/ClusterToBSparse.java
cluster-to-bsparse:
	java -cp ${CLASSPATH} pipeline.ClusterToBSparse ~/hadoopcl-input/wiki-sparse/random-seed-cluster ~/hadoopcl-input/wiki-sparse/random-seed-sparse
###########################################################
################### Checkers ##############################
###########################################################
all-checks: sorted-checker duplicate-checker quick-duplicate-checker num-unique-tokens

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

num-unique-tokens:
	javac -cp ${CLASSPATH} checks/NumUniqueTokens.java
get-num-unique-tokens-asf:
	java -cp ${CLASSPATH} checks.NumUniqueTokens /scratch/jmg3/asf-sparse/hadoopcl-input.lengthpruned-96/ true /scratch/jmg3/asf-sparse/token-counts-96

###########################################################
################### Mergers ###############################
###########################################################
merges: merge-int-sparse merge-text-vector merge-int-fsparse merge-int-bsparse

merge-int-sparse: file-merger merge/MergeIntSparseFiles.java
	javac -cp ${CLASSPATH} merge/MergeIntSparseFiles.java
merge-int-bsparse: file-merger merge/MergeIntBSparseFiles.java
	javac -cp ${CLASSPATH} merge/MergeIntBSparseFiles.java
merge-int-fsparse: file-merger merge/MergeIntFSparseFiles.java
	javac -cp ${CLASSPATH} merge/MergeIntFSparseFiles.java
merge-text-vector: file-merger merge/MergeTextVector.java
	javac -cp ${CLASSPATH} merge/MergeTextVector.java
merge-int-vector:
	javac -cp ${CLASSPATH} merge/MergeIntVector.java
file-merger:
	javac -cp ${CLASSPATH} merge/FileMerger.java


###########################################################
################### For Xiangyu ###########################
###########################################################

pairwise: pipeline/ConvertPairwiseSimilarity.java
	javac -cp ${CLASSPATH} pipeline/ConvertPairwiseSimilarity.java
pairwise64: pipeline/ConvertPairwiseSimilarity.java
	javac -cp ${CLASSPATH} pipeline/ConvertPairwiseSimilarity64.java
pairwise-run:
	java -cp ${CLASSPATH} pipeline.ConvertPairwiseSimilarity /home/yiskylee/mahout/allTempFiles/weights-small /home/yiskylee/mahout/allTempFiles/weights-hadoopcl-small
pairwise64-run:
	java -cp ${CLASSPATH} pipeline.ConvertPairwiseSimilarity64 /home/yiskylee/mahout/allTempFiles/weights-small /home/yiskylee/mahout/allTempFiles/weights-64-small

###########################################################
################### Compression ###########################
###########################################################
compression: compress/MyCompressor.java compress/CompressIntSparse.java compress/CompressTextCluster.java
	javac -cp ${CLASSPATH} compress/MyCompressor.java
	javac -cp ${CLASSPATH} compress/CompressIntSparse.java
	javac -cp ${CLASSPATH} compress/CompressIntBSparse.java
	javac -cp ${CLASSPATH} compress/CompressTextCluster.java


###########################################################
################### Mahout Pipeline++ #####################
###########################################################

full-pipeline: wiki-parser wiki-txt-to-seq asf-txt-to-seq word-count \
		merge-word-count word-id tf-vectors tf-to-df prune-tf tfidf \
		mypruner

sparse-to-new: pipeline/SparseToNewSparse.java
	javac -cp ${CLASSPATH} pipeline/SparseToNewSparse.java
wiki-parser: pipeline/ParseWiki.java
	javac -cp ${CLASSPATH} pipeline/ParseWiki.java
wiki-txt-to-seq: pipeline/WikiTxtToSeq.java
	javac -cp ${CLASSPATH} pipeline/WikiTxtToSeq.java
wiki-txt-to-seq-run:
	java -cp ${CLASSPATH} pipeline.WikiTxtToSeq /scratch/jmg3/wiki-sparse/0.wiki-files/ /scratch/jmg3/wiki-sparse/1.wiki-seq

asf-txt-to-seq: pipeline/ASFTxtToSeq.java
	javac -cp ${CLASSPATH} pipeline/ASFTxtToSeq.java
asf-txt-to-seq-run:
	java -cp ${CLASSPATH} pipeline.ASFTxtToSeq /scratch/jmg3/asf-mail-archive-tar-files-flattened/ /scratch/jmg3/asf-seq 47868

txt-to-tokens:
	javac -cp ${CLASSPATH} pipeline/TextToTokens.java 
wiki-txt-to-tokens:
	java -cp ${CLASSPATH} pipeline.TextToTokens /scratch/jmg3/wiki-sparse/1.wiki-seq/ /scratch/jmg3/wiki-sparse/2.tokenized-documents
asf-txt-to-tokens:
	java -cp ${CLASSPATH} pipeline.TextToTokens /scratch/jmg3/asf-seq-merged/ /scratch/jmg3/asf-sparse/tokenized-documents


word-count: pipeline/WordCountFromTokenized.java
	javac -cp ${CLASSPATH} pipeline/WordCountFromTokenized.java
sort-word-counts: pipeline/SortCounts.java
	javac -cp ${CLASSPATH} pipeline/SortCounts.java
merge-word-count: pipeline/MergeWordCount.java
	javac -cp ${CLASSPATH} pipeline/MergeWordCount.java
merge-sorted-word-count: pipeline/MergeSortedWordCount.java
	javac -cp ${CLASSPATH} pipeline/MergeSortedWordCount.java
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
split-seq-file: pipeline/SplitSequenceFile.java
	javac -cp ${CLASSPATH} pipeline/SplitSequenceFile.java

wiki-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.WordCountFromTokenized /scratch/jmg3/wiki-sparse/2.tokenized-documents/ /scratch/jmg3/wiki-sparse/3.wordcount/
asf-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.WordCountFromTokenized /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/wordcount/
wiki-merge-word-count-1:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/wiki-sparse/3.wordcount/interm-counts-2 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-6 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-7 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-8 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-9 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-10 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-11
wiki-merge-word-count-2:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/wiki-sparse/3.wordcount/interm-counts-1 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-0 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-1 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-2 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-3 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-4 \
		/scratch/jmg3/wiki-sparse/3.wordcount/counts-5
wiki-merge-word-count-final:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/wiki-sparse/3.wordcount/final-counts \
		/scratch/jmg3/wiki-sparse/3.wordcount/interm-counts-1 \
		/scratch/jmg3/wiki-sparse/3.wordcount/interm-counts-2


asf-merge-word-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MergeWordCount /scratch/jmg3/asf-sparse/wordcount/
wiki-word-id:
	java -cp ${CLASSPATH} pipeline.GenerateUniqueWordIDs /scratch/jmg3/wiki-sparse/3.wordcount/final-counts /scratch/jmg3/wiki-sparse/3.wordcount/dictionary-file
asf-word-id:
	java -cp ${CLASSPATH} pipeline.GenerateUniqueWordIDs /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} pipeline.GenerateDFVectors /scratch/jmg3/wiki-sparse/2.tokenized-documents/ /scratch/jmg3/wiki-sparse/4.tf-vectors/ /scratch/jmg3/wiki-sparse/3.wordcount/dictionary-file
asf-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} pipeline.GenerateDFVectors /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-df-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFtoDF /scratch/jmg3/wiki-sparse/3.wordcount/final-counts /scratch/jmg3/wiki-sparse/3.wordcount/dictionary-file /scratch/jmg3/wiki-sparse/4.tf-vectors/ /scratch/jmg3/wiki-sparse/5.df-count/term-counts
asf-df-count:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFtoDF /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts
wiki-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneTF /scratch/jmg3/wiki-sparse/4.tf-vectors/ /scratch/jmg3/wiki-sparse/5.df-count/term-counts /scratch/jmg3/wiki-sparse/6.tf-vectors-pruned/ 14303375
asf-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneTF /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tf-vectors-pruned/ 36909
wiki-tfidf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFPrunedtoTFIDF /scratch/jmg3/wiki-sparse/6.tf-vectors-pruned/ /scratch/jmg3/wiki-sparse/5.df-count/term-counts /scratch/jmg3/wiki-sparse/7.tfidf-vectors/ 126007518 14303375
asf-tfidf:
	java -Xmx48G -cp ${CLASSPATH} pipeline.TFPrunedtoTFIDF /scratch/jmg3/asf-sparse/tf-vectors-pruned/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tfidf-vectors/ 124993853 36909

wiki-tfidf-transform:
	python ../RenameTFIDF.py /scratch/jmg3/wiki-sparse/7.tfidf-vectors/ /scratch/jmg3/wiki-sparse/8.tfidf-vectors-transformed

asf-myprune:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MyPruner /scratch/jmg3/asf-sparse/tfidf-vectors-transformed/ /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-pruned.4096/ /scratch/jmg3/asf-sparse/token-counts c 4096
wiki-myprune:
	java -Xmx48G -cp ${CLASSPATH} pipeline.MyPruner /scratch/jmg3/wiki-sparse/8.tfidf-vectors-transformed/ /scratch/jmg3/wiki-sparse/9.tfidf-vectors-transformed-pruned.1024/ /scratch/jmg3/wiki-sparse/token-counts c 1024
asf-prune-for-length:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneWithTargetLength /scratch/jmg3/asf-sparse/tfidf-vectors-transformed/ /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-lengthpruned.96/ /scratch/jmg3/asf-sparse/token-counts 96
wiki-prune-for-length:
	java -Xmx48G -cp ${CLASSPATH} pipeline.PruneWithTargetLength \
		/scratch/jmg3/wiki-sparse/8.tfidf-vectors-transformed/ \
		/scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-32 \
		/scratch/jmg3/wiki-sparse/token-counts 32

asf-merge:
	java merge.MergeTextVector /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-lengthpruned.96/ /scratch/jmg3/asf-sparse/tfidf-vectors-transformed-lengthpruned.96-merged/ 13 part-r-
wiki-merge:
	java merge.MergeTextVector /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged/ 230 part-r-
wiki-length-merge:
	java merge.MergeTextVector /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-96/ /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-96-merged/ 250 part-r-


###########################################################
################### Generator #############################
###########################################################

generate-fake:
	javac -cp ${CLASSPATH} pipeline/GenerateFake.java
asf-generate-fake-32:
	java -cp ${CLASSPATH} pipeline.GenerateFake /scratch/jmg3/asf-sparse/token-counts-96 /scratch/jmg3/asf-sparse/fake-tfidf-vectors-32/ 32 10000000 200
asf-generate-fake-64:
	java -cp ${CLASSPATH} pipeline.GenerateFake /scratch/jmg3/asf-sparse/token-counts-96 /scratch/jmg3/asf-sparse/fake-tfidf-vectors-64/ 64 7000000 300
asf-generate-fake-128:
	java -cp ${CLASSPATH} pipeline.GenerateFake /scratch/jmg3/asf-sparse/token-counts-96 /scratch/jmg3/asf-sparse/fake-tfidf-vectors-128/ 128 4000000 300

###########################################################
################### Utilities #############################
###########################################################
all-utilities: parallel-file-iterator take-partial-count mutable-long token-count

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
