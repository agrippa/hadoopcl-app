
PRUNING="32 64 128"
CLUSTERS="64 128 512 1024"
RAND_DIR=/scratch/jmg3/asf-sparse/random-seed
for p in ${PRUNING}; do
    for c in ${CLUSTERS}; do
        java -cp ${CLASSPATH} pipeline.SelectRandomSeeds /scratch/jmg3/asf-sparse/fake-hadoopcl-input-${p} ${RAND_DIR} ${c}
        mv ${RAND_DIR}/cluster-randomSeed ${RAND_DIR}/cluster-randomSeed.${p}pruning.${c}clusters
        mv ${RAND_DIR}/sparse-randomSeed ${RAND_DIR}/sparse-randomSeed.${p}pruning.${c}clusters
    done
done
