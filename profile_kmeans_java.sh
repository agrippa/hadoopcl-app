SLOTS="12"


NODES=`cat $PBS_NODEFILE | sort | uniq`

./KILL.sh

for s in ${SLOTS}; do 
    ./startup.sh ${s} 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864
    sleep 30
    bin/hadoop fs -put ${HADOOP_INPUT_DIR}/kmeans.input kmeans.input
    echo DOING ${s}
    ./profile-scripts/profile.sh kmeans.java &
    PROF_PID=$!
    time bin/hadoop jar KMeansJavaVersion.jar KMeansJavaVersion kmeans.input kmeans.output
    kill -9 ${PROF_PID}
done
