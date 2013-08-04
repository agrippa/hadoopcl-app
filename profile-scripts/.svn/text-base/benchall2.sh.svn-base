file="out-"`date +%s`

cd $HADOOP_HOME

KB=1024
MB=`expr $KB \* 1024`
GB=`expr $MB \* 1024`

datasize=13
datasizeBytes=`expr $datasize \* $GB`
echo "Data Size: $datasize GB, $datasizeBytes B"

for blocksize in 128 256 512; do
	blocksizeBytes=`expr $blocksize \* $MB`
	echo "Block Size $blocksize MB, $blocksizeBytes B"

	echo Stopping All
	bin/stop-all.sh
	echo Deleting Data Node Contents
	./delete-datanode-contents
	echo Hard Stop
	./s-kill-hadoop

	sleep 5

	echo Reconfiguring Hadoop
	./reconfigure-hadoop-bm 2 $blocksizeBytes

	echo Formatting Namenode
	echo "Y" | bin/hadoop namenode -format
	
	echo Starting All
	bin/start-all.sh

	sleep 5
	./live-hadoop-processes-check

	echo Deleting Input
	bin/hadoop fs -rmr input

	echo Writing Input
	bin/hadoop jar hadoop-examples-1.0.3.jar randomtextwriter -D test.randomtextwrite.bytes_per_map=$blocksizeBytes -D test.randomtextwrite.mapsperhost=1 -D test.randomtextwrite.total_bytes=$datasizeBytes input

	echo Stopping All
	bin/stop-all.sh
	./s-kill-hadoop
	sleep 1

	./live-hadoop-processes-check

	for mappers in 2 4 8 12; do
		echo Mappers $mappers

		echo Reconfiguring
		./reconfigure-hadoop-bm $mappers $blocksizeBytes

		echo Starting All
		bin/start-all.sh
		sleep 5
		./live-hadoop-processes-check

		echo "Data Size $datasize GB, Block Size $blocksize MB, Mappers $mappers" >> $file

		for type in seq par; do
			echo $type

			echo Copying $type Grep
			cp Grep-HJ/GrepMap-$type.hj Grep-HJ/GrepMap.hj

			echo Compiling
			./testCompileGrep.sh

			./live-hadoop-processes-check
			sleep 5


			for try in 1 2 3; do
				echo Running $type $try...
				d=`date +%s`
				(time bin/hadoop jar Grep-HJ.jar Grep input out-$type-$mappers) 2>./.tmp-out-$d

				echo $type, Try $try >> $file
				cat ./.tmp-out-$d | grep ^real >> $file

				sleep 5

				./live-hadoop-processes-check
			done

		done

		# echo "Par:" >> $file

		# echo Copying Par Grep
		# cp Grep-HJ/GrepMap-par.hj Grep-HJ/GrepMap.hj

		# echo Compiling
		# ./testCompileGrep.sh
		# sleep 5

		# echo Running...
		# d=`date +%s`
		# (time bin/hadoop jar Grep-HJ.jar Grep input out-par-$mappers) 2>./.tmp-out-$d
		# cat ./.tmp-out-$d | grep ^real >> $file

		echo Stopping Hadoop
		bin/stop-all.sh
		sleep 5
	done
done
