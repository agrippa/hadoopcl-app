include ../Makefile.common

wiki-parser: ParseWiki.java
	javac -cp ${CLASSPATH} ParseWiki.java
wiki-txt-to-seq: WikiTxtToSeq.java
	javac -cp ${CLASSPATH} WikiTxtToSeq.java
wiki-txt-to-seq-run:
	java -cp ${CLASSPATH} WikiTxtToSeq /scratch/jmg3/wiki-txt/ /scratch/jmg3/wiki-seq/ 13638329

asf-txt-to-seq: ASFTxtToSeq.java
	javac -cp ${CLASSPATH} ASFTxtToSeq.java
asf-txt-to-seq-run:
	java -cp ${CLASSPATH} ASFTxtToSeq /scratch/jmg3/asf-mail-archive-tar-files-flattened/ /scratch/jmg3/asf-seq 47868

txt-to-tokens:
	javac -cp ${CLASSPATH} TextToTokens.java 
wiki-txt-to-tokens:
	java -cp ${CLASSPATH} TextToTokens /scratch/jmg3/wiki-seq-merged/ /scratch/jmg3/wiki-sparse/tokenized-documents
asf-txt-to-tokens:
	java -cp ${CLASSPATH} TextToTokens /scratch/jmg3/asf-seq-merged/ /scratch/jmg3/asf-sparse/tokenized-documents

word-count: WordCountFromTokenized.java
	javac -cp ${CLASSPATH} WordCountFromTokenized.java
merge-word-count: MergeWordCount.java
	javac -cp ${CLASSPATH} MergeWordCount.java
word-id: GenerateUniqueWordIDs.java
	javac -cp ${CLASSPATH} GenerateUniqueWordIDs.java
tf-vectors: GenerateDFVectors.java
	javac -cp ${CLASSPATH} GenerateDFVectors.java
tf-to-df: TFtoDF.java
	javac -cp ${CLASSPATH} TFtoDF.java
prune-tf: PruneTF.java
	javac -cp ${CLASSPATH} PruneTF.java
tfidf: TFPrunedtoTFIDF.java
	javac -cp ${CLASSPATH} TFPrunedtoTFIDF.java

wiki-word-count:
	java -Xmx48G -cp ${CLASSPATH} WordCountFromTokenized /scratch/jmg3/wiki-sparse/tokenized-documents/ /scratch/jmg3/wiki-sparse/wordcount/
asf-word-count:
	java -Xmx48G -cp ${CLASSPATH} WordCountFromTokenized /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/wordcount/
wiki-merge-word-count:
	java -Xmx48G -cp ${CLASSPATH} MergeWordCount /scratch/jmg3/wiki-sparse/wordcount/
asf-merge-word-count:
	java -Xmx48G -cp ${CLASSPATH} MergeWordCount /scratch/jmg3/asf-sparse/wordcount/
wiki-word-id:
	java -cp ${CLASSPATH} GenerateUniqueWordIDs /scratch/jmg3/wiki-sparse/wordcount/final-counts /scratch/jmg3/wiki-sparse/wordcount/dictionary-file
asf-word-id:
	java -cp ${CLASSPATH} GenerateUniqueWordIDs /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} GenerateDFVectors /scratch/jmg3/wiki-sparse/tokenized-documents/ /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/wordcount/dictionary-file
asf-tf-vector:
	java -Xmx48G -cp ${CLASSPATH} GenerateDFVectors /scratch/jmg3/asf-sparse/tokenized-documents/ /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/wordcount/dictionary-file
wiki-df-count:
	java -Xmx48G -cp ${CLASSPATH} TFtoDF /scratch/jmg3/wiki-sparse/wordcount/final-counts /scratch/jmg3/wiki-sparse/wordcount/dictionary-file /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/df-count/term-counts
asf-df-count:
	java -Xmx48G -cp ${CLASSPATH} TFtoDF /scratch/jmg3/asf-sparse/wordcount/final-counts /scratch/jmg3/asf-sparse/wordcount/dictionary-file /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts
wiki-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} PruneTF /scratch/jmg3/wiki-sparse/tf-vectors/ /scratch/jmg3/wiki-sparse/df-count/term-counts /scratch/jmg3/wiki-sparse/tf-vectors-pruned/ 13637681
asf-prune-tf:
	java -Xmx48G -cp ${CLASSPATH} PruneTF /scratch/jmg3/asf-sparse/tf-vectors/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tf-vectors-pruned/ 36909
wiki-tfidf:
	java -Xmx48G -cp ${CLASSPATH} TFPrunedtoTFIDF /scratch/jmg3/wiki-sparse/tf-vectors-pruned/ /scratch/jmg3/wiki-sparse/df-count/term-counts /scratch/jmg3/wiki-sparse/tfidf-vectors/ 149053452 13637681
asf-tfidf:
	java -Xmx48G -cp ${CLASSPATH} TFPrunedtoTFIDF /scratch/jmg3/asf-sparse/tf-vectors-pruned/ /scratch/jmg3/asf-sparse/df-count/term-counts /scratch/jmg3/asf-sparse/tfidf-vectors/ 124993853 36909


