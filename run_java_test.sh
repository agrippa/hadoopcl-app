if [ $# -ne 4 ] ; then
    echo Incorrect num command line arguments
else
    SLOTS="12"
    BENCHMARK=${1}
    FORMAT=${2}
    MAP_OUTPUTFORMAT=${3}
    java_heap=${4}

    if [ ${BENCHMARK} == sort ]; then
        EXE_NAME=SortJavaVersion
    fi
    if [ ${BENCHMARK} == pi ]; then
        EXE_NAME=PiJavaVersion
    fi
    if [ ${BENCHMARK} == kmeans ]; then
        EXE_NAME=KMeansJavaVersion
    fi
    if [ ${BENCHMARK} == blackscholes ]; then
        EXE_NAME=BlackScholesJavaVersion
    fi

    NODES=`cat $PBS_NODEFILE | sort | uniq`

    ./CLEAN.sh
    ./KILL.sh
    sleep 10
    ./CLEAN.sh
    ./KILL.sh
    ./startup.sh ${SLOTS} 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 ${java_heap}
#    #./startup.sh ${SLOTS} 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 268435456 1 1 1 1
#    sleep 60
#    ${HADOOP_HOME}/bin/hadoop fs -put ${HADOOP_INPUT_DIR}/${BENCHMARK}.input/block.${FORMAT} ${BENCHMARK}.input
#    sleep 60
#    time ${HADOOP_HOME}/bin/hadoop jar ${EXE_NAME}.jar ${EXE_NAME} ${BENCHMARK}.input ${BENCHMARK}.output ${MAP_OUTPUTFORMAT} 
#
##    sleep 10
##    echo Retrieving Outputs
##    ${HADOOP_HOME}/bin/hadoop fs -get ${BENCHMARK}.output /scratch/jmg3/${BENCHMARK}.output
#
#    for n in ${NODES}; do
#        ssh -o ConnectTimeout=2 ${n} "grep -n -R 'DIAGNOSTICS' /tmp/${PBS_JOBID}/hadoop-jmg3/mapred/local/userlogs/* > ${HADOOP_LOG_DIR}/diagnostics.${BENCHMARK}.${FORMAT}.${MAP_OUTPUTFORMAT}.java.${PBS_JOBID}.${s}.${n}"
#    done
#
#    ./KILL.sh
fi
