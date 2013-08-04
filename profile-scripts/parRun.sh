#!/bin/bash

# ARGS:
# 1: Time Out File 
PROF_DIR=`pwd`
OUT_FILE=$PROF_DIR/$1
OUT_NAME=$2
cd $HADOOP_HOME

# Args: #Mappers, Blocksize (MB), Data size (GB)
#$PROF_DIR/runSetup.sh 2 256 13

cp Grep-HJ/GrepMap-par.hj Grep-HJ/GrepMap.hj

./testCompileGrep.sh
#bin/hadoop fs -rmr out-par
sleep 1
#$PROF_DIR/profile.sh $OUT_DIR 0 &
#PROF_PID=$!

(time bin/hadoop jar Grep-HJ.jar Grep input out-par-$OUT_NAME) 2>&1 | grep ^real >> $OUT_FILE

#kill -9 $PROF_PID

#$PROF_DIR/runFinish.sh

