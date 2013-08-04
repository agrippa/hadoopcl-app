#!/bin/bash

# ARGS:
# 1: Output file
# 2: Seconds between profiles

OUT_DIR=$HADOOP_HOME/profile/${PBS_JOBID}.${1}
DELAY=1

NODES=`cat $PBS_NODEFILE | sort | uniq`
CMD="ps aux | awk '/hadoop/ {print \"\t\t\" \$3 \"\t\" \$4}'"
GPU_CMD="~/gpu.sh"

mkdir $OUT_DIR -p
cd $OUT_DIR

for node in $NODES; do
	echo -e "ITER\tTIME\tCPU %\tMEM%" >$node
done

START=`date +%s.%N`
iter="0"
while true
do
	NOW=`date +%s.%N`
	TIME=`echo "$NOW - $START" | bc`
	iter=`echo "$iter + 1" | bc`
	#echo -e "$iter\t$TIME sec:" >>$OUT_FILE
	for node in $NODES; do
		# echo -e "\t$node: " >>$OUT_FILE
		echo -e "$iter\t$TIME sec:" >>$node
		ssh -o ConnectTimeout=2 $node $CMD >>$node;
        echo -e "GPU" >> $node
		ssh -o ConnectTimeout=2 $node $GPU_CMD >>$node;
	done
	#echo "" >>$OUT_FILE
	echo "Logged $TIME sec ($iter iters)"
	sleep $DELAY
done


