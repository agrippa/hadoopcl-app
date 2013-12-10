#!/bin/bash

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

hadoop fs -put /home/yiskylee/mahout/input/input.txt input.txt

mahout recommenditembased --input input.txt --output output --booleanData true --similarityClassname SIMILARITY_COOCCURRENCE --threshold 10.0

cd ${PWD}
