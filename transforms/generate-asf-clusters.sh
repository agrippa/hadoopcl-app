
PRUNING="32 64 128"
CLUSTERS="64 128 512 1024"
RAND_DIR=/scratch/jmg3/asf-sparse/random-seed
for p in ${PRUNING}; do
    for c in ${CLUSTERS}; do
        java -cp ${CLASSPATH} pipeline.SelectRandomSeeds /scratch/jmg3/asf-sparse/fake-hadoopcl-input-${p} ${RAND_DIR}/randomSeed.${p}pruning.${c}clusters ${c}
    done
done
