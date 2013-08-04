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
bin/hadoop fs -put ${HADOOP_INPUT_DIR}/kmeans.input kmeans.input
./profile-scripts/profile.sh kmeans.opencl &
PROF_PID=$!
time bin/hadoop jar KMeansOpenCLVersion.jar KMeansOpenCLVersion kmeans.input kmeans.output
kill -9 ${PROF_PID}
./KILL.sh
sleep 10
