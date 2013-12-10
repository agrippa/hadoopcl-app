#!/bin/bash

if [ $# != 1 ]; then
  echo usage: rowsimilarityJob.sh threshold
  exit 1
fi

THRESHOLD=${1}

if [ -d threshold.${THRESHOLD} ]; then
  echo Already ran this
  exit 1
fi

HADOOP=${HADOOP_HOME}/bin/hadoop

MAP_SLOTS=4
REDUCE_SLOTS=2
JAVA_HEAP=3
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

./startup.sh ${MAP_SLOTS} ${REDUCE_SLOTS} 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 ${JAVA_HEAP}
sleep 30

hadoop fs -put /home/yiskylee/mahout/allTempFiles/preparePreferenceMatrix/ratingMatrix/ ratingMatrix

mahout rowsimilarity --input ratingMatrix/ --output similarityMatrix --similarityClassname SIMILARITY_COOCCURRENCE --threshold ${THRESHOLD} --numberOfColumns 4288742

# hadoop fs -get temp/ threshold.${THRESHOLD}

cd ${PWD}
