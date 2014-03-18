if [ $# -ne 4 ] ; then
    echo Incorrect num command line arguments $#
else

    BENCHMARK=${1}
    FORMAT=${2}
    MAP_OUTPUTFORMAT=${3}
    java_heap=${4}

    echo run_opencl_test.sh: Running ${BENCHMARK} with input compression \
        ${FORMAT}, intermediate compression ${MAP_OUTPUTFORMAT}
    echo     Java heap size ${java_heap}G

    if [ ${BENCHMARK} == sort ]; then
        EXE_NAME=SortOpenCLVersion
    fi
    if [ ${BENCHMARK} == pi ]; then
        EXE_NAME=PiOpenCLVersion
    fi
    if [ ${BENCHMARK} == kmeans ]; then
        EXE_NAME=KMeansOpenCLVersion
    fi
    if [ ${BENCHMARK} == kmeansdyn ]; then
        EXE_NAME=KMeansDynamicOpenCLVersion
    fi
    if [ ${BENCHMARK} == blackscholes ]; then
        EXE_NAME=BlackScholesOpenCLVersion
    fi
    if [ ${BENCHMARK} == svec-map-input ]; then
        EXE_NAME=TestMapInputSvec
    fi
    if [ ${BENCHMARK} == svec-map-output ]; then
        EXE_NAME=TestMapOutputSvec
    fi
    if [ ${BENCHMARK} == svec-reduce-output ]; then
        EXE_NAME=TestReduceOutputSvec
    fi
    if [ ${BENCHMARK} == svec-just-reduce-output ]; then
        EXE_NAME=TestJustReduceOutputSvec
    fi
    if [ ${BENCHMARK} == mahout-kmeans ]; then
        EXE_NAME=MahoutKMeans
    fi
    if [ ${BENCHMARK} == helloworld ]; then
        EXE_NAME=HelloWorld
    fi
    if [ ${BENCHMARK} == ivec-map-input ]; then
        EXE_NAME=TestMapInputIvec
    fi
    if [ ${BENCHMARK} == globals-on-gpu ]; then
        EXE_NAME=TestGlobalsOnGPU
    fi
    if [ ${BENCHMARK} == fglobals-on-gpu ]; then
        EXE_NAME=TestFGlobalsOnGPU
    fi
    if [ ${BENCHMARK} == test-strided ]; then
        EXE_NAME=TestStridedPerf
    fi
    if [ ${BENCHMARK} == pairwise ]; then
        EXE_NAME=PairwiseSimilarity
    fi
    if [ ${BENCHMARK} == pairwise64 ]; then
        EXE_NAME=PairwiseSimilarity64
    fi
    if [ ${BENCHMARK} == pairwise64_xiangyu ]; then
        EXE_NAME=PairwiseSimilarity64_xiangyu
    fi
    if [ ${BENCHMARK} == bsparse-strided ]; then
        EXE_NAME=TestBSparseStrided
    fi

    CPU_THREAD=64
    GPU_THREAD=256

    hdfs_chunk_size=268435456
    mapper=12
    if [ ${BENCHMARK} == sort ]; then
        reducer=4
    else
        # reducer=5
        # reducer=4
        reducer=1
    fi

    # NODES=`cat $PBS_NODEFILE | sort | uniq`

    echo Killing
    ./KILL.sh
    ./CLEAN.sh
    echo Done
    sleep 3
    ./KILL.sh
    ./CLEAN.sh

    ./startup.sh ${mapper} ${reducer} ${GPU_THREAD} \
        ${CPU_THREAD} \
        ${GPU_THREAD} ${CPU_THREAD} ${hdfs_chunk_size} \
        ${java_heap}
    sleep 20
    echo Putting inputs from \
        ${HADOOP_INPUT_DIR}/${BENCHMARK}.input/block.${FORMAT}
    ${HADOOP_HOME}/bin/hadoop fs -put \
        ${HADOOP_INPUT_DIR}/${BENCHMARK}.input/block.${FORMAT} \
        ${BENCHMARK}.input
    echo Done
    sleep 10
    echo Running Application with ${EXE_NAME}.jar
    time ${HADOOP_HOME}/bin/hadoop jar ${EXE_NAME}.jar ${EXE_NAME} \
        ${BENCHMARK}.input ${BENCHMARK}.output ${MAP_OUTPUTFORMAT} 

#      sleep 10
#      echo Retrieving Outputs into ${HADOOP_OUTPUT_DIR}/${BENCHMARK}.output
#      ${HADOOP_HOME}/bin/hadoop fs -get ${BENCHMARK}.output \
#          ${HADOOP_OUTPUT_DIR}/${BENCHMARK}.output
# #     
# #     echo Grepping Logs
# #     NODES=`cat ${HADOOP_HOME}/conf/slaves ${HADOOP_HOME}/conf/masters`
# #     for n in ${NODES}; do
# #         ssh -o ConnectTimeout=2 ${n} "grep -n -R 'DIAGNOSTICS' /tmp/${PBS_JOBID}/* > ${HADOOP_LOG_DIR}/diagnostics.${BENCHMARK}.${FORMAT}.${MAP_OUTPUTFORMAT}.opencl.${PBS_JOBID}.${mapper}.${n}"
# #         ssh -o ConnectTimeout=2 ${n} "grep -n -R 'Exception' /tmp/${PBS_JOBID}/* > ${HADOOP_LOG_DIR}/diagnostics.${BENCHMARK}.${FORMAT}.${MAP_OUTPUTFORMAT}.opencl.${PBS_JOBID}.${mapper}.${n}.exceptions"
# #     done
#     echo Done, Killing
#     ./KILL.sh

fi
