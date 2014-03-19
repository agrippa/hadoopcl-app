#!/bin/bash

if [ $# -ne 2 ]; then
    echo usage: mahout-kmeans.sh pruning clusters
    exit 1
fi

PRUNING=32
CLUSTERS=1024

MAP_SLOTS=12
REDUCE_SLOTS=2
JAVA_HEAP=16
PWD=$(pwd)
cd ${HADOOP_APP_DIR}
./KILL.sh
sleep 2
./CLEAN.sh
sleep 2
./KILL.sh
sleep 2
./CLEAN.sh
sleep 2

./startup.sh ${MAP_SLOTS} ${REDUCE_SLOTS} 0 0 0 0 67108864 ${JAVA_HEAP}
sleep 60

${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged.1024/ input
${HADOOP_HOME}/bin/hadoop fs -mkdir clusters
${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/wiki-sparse/random-seed/cluster-randomSeed.pruned1024.1024clusters clusters/

sleep 30
time ${MAHOUT_HOME}/bin/mahout kmeans -i input -c clusters -o output -dm org.apache.mahout.common.distance.CosineDistanceMeasure -x 1 -ow

cd ${PWD}
