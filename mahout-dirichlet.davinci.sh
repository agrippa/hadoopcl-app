#!/bin/bash

if [ $# != 1 ]; then
    echo usage: mahout-dirichlet.davinci.sh nruns
    exit 1
fi

MAP_SLOTS=12
REDUCE_SLOTS=2
JAVA_HEAP=4
PWD=$(pwd)

cd ${HADOOP_APP_DIR}

for i in $(seq $1); do

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

    ${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/wiki-sparse/9.tfidf-vectors-transformed-lengthpruned-32-combined-merged/ input
    ${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/wiki-sparse/random-seed-cluster random-seed-cluster

    sleep 30
    time ${MAHOUT_HOME}/bin/mahout dirichlet -i input -o output \
        -dm org.apache.mahout.common.distance.CosineDistanceMeasure -ow -x 1 -a0 2 --numClusters 1024
done
cd ${PWD}
