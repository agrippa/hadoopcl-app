#!/bin/bash

# ARGS:
# 1: Block Size
# 2: Data Size
# 3: Try

#BLOCK_SIZE=$1
#DATA_SIZE=$2
#TRY=$3

datasize=2
file="out-"`date +%s`

#for try in 1 2 3; do
	echo "Try $try"
	for blocksize in 128 256 512; do
		echo "Block Size $blocksize"
		cd $HADOOP_HOME
		#./s-kill-hadoop
		#sleep 1
		echo "Between"
		./delete-datanode-contents
		echo "After"
		#./s-kill-hadoop
		#sleep 1
		cd $HADOOP_HOME/profile/
		./firstSetup.sh 2 $blocksize $datasize
		sleep 5
		./writeText.sh 2 $blocksize $datasize
		./runFinish.sh
		echo "Init Finish"
		../live-hadoop-processes-check
		sleep 1
		for mappers in 2 4 8 12; do
			echo "Mappers $mappers"
			for try in 2 3; do
				echo "Try $try"
				./runSetup.sh $mappers $blocksize $datasize
				
			#file="out-"$mappers"Map-"$blocksize"MB-"$DATA_SIZE"GB"
			#fileSeq=$file"-seq-"$TRY
			#filePar=$file"-par-"$TRY
			#echo $file
			#for try in 2 3; do
				echo "Data Size: "$datasize"GB, Block Size: "$blocksize"MB, Mappers: "$mappers", Try: "$try >> $file
				echo "Seq:" >> $file
				./seqRun.sh $file $mappers
				echo "Par:" >> $file
				./parRun.sh $file $mappers
			#done
		
				./runFinish.sh
				echo "Finish Try"
				../live-hadoop-processes-check
				sleep 1
			done
		done
	done
#done

