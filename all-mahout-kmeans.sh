#! /bin/bash

if [ $# -ne 1 ]; then
    echo usage all-mahout-kmeans.sh pruning
    exit 1
fi

PRUNING=$1
echo Using pruning ${PRUNING}

for i in 64 128 512 1024; do
    echo ${i} Clusters
    ./mahout-kmeans.sh ${PRUNING} ${i}
done
