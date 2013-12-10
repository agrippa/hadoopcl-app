#!/bin/bash

HADOOP=${HADOOP_HOME}/bin/hadoop

if [ $# != 1 ]; then
  echo usage: mahout-pairwise.sh threshold
  exit 1
fi

MAP_SLOTS=1
REDUCE_SLOTS=1
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

${HADOOP} fs -put ${HOME}/mahout/allTempFiles/weights-small/ input
${HADOOP} fs -put ./threshold.1/norms.bin norms-file
${HADOOP} fs -put ./threshold.1/numNonZeroEntries.bin non-zero-entries-file

# ${HADOOP} fs -put ${HOME}/mahout/allTempFiles/norms.bin norms-file
# ${HADOOP} fs -put ${HOME}/mahout/allTempFiles/numNonZeroEntries.bin non-zero-entries-file

sleep 30
time ${HADOOP} jar pairwiseSimilarityCpu.jar pairwiseSimilarityCpu ${1}

cd ${PWD}
