CPU_GROUP="8"
GPU_GROUP="36"
CPU_THREAD="196"
GPU_THREAD="256"

buffer=1048576
mapper=8
hdfs_chunk_size=268435456

NODES=`cat $PBS_NODEFILE | sort | uniq`

./KILL.sh
sleep 30

./startup.sh ${mapper} 1 ${GPU_GROUP} ${CPU_GROUP} ${GPU_THREAD} ${CPU_THREAD} 3 3 ${buffer} ${buffer} ${GPU_GROUP} ${CPU_GROUP} ${GPU_THREAD} ${CPU_THREAD} 3 3 ${buffer} ${buffer} ${hdfs_chunk_size}
sleep 60 
bin/hadoop fs -put ${HADOOP_INPUT_DIR}/sort.input sort.input
./profile-scripts/profile.sh sort.opencl &
PROF_PID=$!
time bin/hadoop jar SortOpenCLVersion.jar SortOpenCLVersion sort.input sort.output
kill -9 ${PROF_PID}
