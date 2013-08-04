#!/bin/bash

# ARGS:
# 1: Profile Output Directory
# 2: 
PROF_DIR=`pwd`
OUT_DIR=$1

cd $HADOOP_HOME

# Args: #Mappers, Blocksize (MB), Data size (GB)
#$PROF_DIR/runSetup.sh 2 256 13

cp Grep-HJ/GrepMap-seq.hj Grep-HJ/GrepMap.hj

./testCompileGrep.sh
bin/hadoop fs -rmr out-seq

$PROF_DIR/profile.sh $OUT_DIR 0 &
PROF_PID=$!

time bin/hadoop jar Grep-HJ.jar Grep input out-seq

kill -9 $PROF_PID

#$PROF_DIR/runFinish.sh

