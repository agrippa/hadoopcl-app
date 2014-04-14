#!/bin/bash

HADOOP=${HADOOP_HOME}/bin/hadoop

if [ $# != 1 ]; then
  echo usage: mahout-pairwise.sh nruns
  exit 1
fi

MAP_SLOTS=12
REDUCE_SLOTS=2
JAVA_HEAP=4
PWD=$(pwd)

for i in $(seq $1); do

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
sleep 30

${HADOOP} fs -put /scratch/jmg3/bayes-dataset/classified-points.splitless.vector/ input
${HADOOP} fs -put /scratch/jmg3/bayes-dataset/weights.bayes weights

sleep 30

${HADOOP} jar MahoutNaiveBayes.jar MahoutNaiveBayes
done

cd ${PWD}
