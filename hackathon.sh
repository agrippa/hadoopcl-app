#!/bin/bash

# MAP_SLOTS=12
# REDUCE_SLOTS=4
MAP_SLOTS=12
REDUCE_SLOTS=1
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

./startup.sh ${MAP_SLOTS} ${REDUCE_SLOTS} 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 ${JAVA_HEAP}
sleep 20

# ${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/wiki-sparse/tfidf-vectors-transformed-pruned-merged.1024/ input
# ${HADOOP_HOME}/bin/hadoop fs -mkdir clusters

sleep 20
# time ${MAHOUT_HOME}/bin/mahout kmeans -i input -c clusters -o output -dm org.apache.mahout.common.distance.CosineDistanceMeasure -x 1 -k 20 -ow
# hadoop jar KMeans.jar KMeans

#${HADOOP_HOME}/bin/hadoop fs -put /scratch/jmg3/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/ reuters-out-seqdir-sparse-kmeans
#
#${MAHOUT_HOME}/bin/mahout kmeans -i reuters-out-seqdir-sparse-kmeans/tfidf-vectors -c reuters-kmeans-clusters -o reuters-kmeans -dm org.apache.mahout.common.distance.CosineDistanceMeasure -x 10 -k 20 -ow --clustering
#
#${HADOOP_HOME}/bin/hadoop fs -get reuters-kmeans-clusters /scratch/jmg3/mahout-kmeans-clusters
#sleep 3
#${HADOOP_HOME}/bin/hadoop fs -get reuters-kmeans /scratch/jmg3/mahout-kmeans-output
#sleep 3

#./KILL.sh
#sleep 3
#./CLEAN.sh

cd ${PWD}