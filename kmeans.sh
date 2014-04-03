#!/bin/bash

ITERS=10

hadoop fs -put clusters.guess ./clusters

for i in $(seq 1 ${ITERS}); do
    echo ${i}
    hadoop jar KMeans.jar KMeans
    hadoop fs -get output/part-r-00000 ./kmeans.${i}
    hadoop fs -rm clusters
    hadoop fs -rmr output
    hadoop fs -put kmeans.${i} clusters
done

hadoop fs -rm clusters
