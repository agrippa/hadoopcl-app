#!/bin/bash

if [ $# != 1 ]; then
    echo usage: mahout-fuzzy.davinci.sh nruns
    exit 1
fi

MAP_SLOTS=4
REDUCE_SLOTS=2
JAVA_HEAP=3
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

    ${HADOOP_HOME}/bin/hadoop fs -put ~/hadoopcl-input/wiki-sparse/9.tfidf-vectors-transformed-lengthpruned-32-combined-merged.20.split/ input
    ${HADOOP_HOME}/bin/hadoop fs -mkdir clusters
    ${HADOOP_HOME}/bin/hadoop fs -put ~/hadoopcl-input/wiki-sparse/random-seed-cluster.128 clusters/

    sleep 30
    time ${MAHOUT_HOME}/bin/mahout fkmeans -i input -c clusters -o output -dm org.apache.mahout.common.distance.CosineDistanceMeasure -x 1 -ow -m 2.0
done
cd ${PWD}
