SIZES="64 128 256"

for s in ${SIZES}; do
    mkdir -p /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-${s}/
    java -Xmx48G -cp ${CLASSPATH} pipeline.PruneWithTargetLength \
        /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed/ \
        /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-lengthpruned-${s}/ \
        /scratch/jmg3/wiki-sparse/token-counts ${s}
done
