#!/bin/bash

# ARGS:
# 1: Number of map tasks
# 2: Block Size in MB
# 3: Data Size in GB

maps=$1
blocksize=$2
datasize=$3

KB=1024
MB=`expr $KB \* 1024`
GB=`expr $MB \* 1024`

cd $HADOOP_HOME

# Data Size in GB
datasizeBytes=`expr $datasize \* $GB`
echo "Data Size: $datasize GB ($datasizeBytes B)"

# Block Size in MB
blocksizeBytes=`expr $blocksize \* $MB`
echo "Block Size: $blocksize MB ($blocksizeBytes B)"

#./reconfigure-hadoop-bm $maps $blocksizeBytes
		
#bin/hadoop namenode -format

#bin/start-all.sh

#./delete-datanode-contents

#./live-hadoop-processes-check

bin/hadoop fs -rmr input

bin/hadoop jar hadoop-examples-1.0.3.jar randomtextwriter -D test.randomtextwrite.bytes_per_map=$blocksizeBytes -D test.randomtextwrite.mapsperhost=1 -D test.randomtextwrite.total_bytes=$datasizeBytes input

