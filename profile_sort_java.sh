#SLOTS="1 2 4 8 12 16"
SLOTS="12"


NODES=`cat $PBS_NODEFILE | sort | uniq`

./KILL.sh

for s in ${SLOTS}; do 
    ./startup.sh ${s} 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864
    sleep 60
    bin/hadoop fs -put ${HADOOP_INPUT_DIR}/sort.input sort.input
    echo DOING ${s}
    ./profile-scripts/profile.sh sort.java &
    PROF_PID=$!
    time bin/hadoop jar SortJavaVersion.jar SortJavaVersion sort.input sort.output
    kill -9 ${PROF_PID}
    ./KILL.sh
done
