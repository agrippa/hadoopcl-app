#!/bin/bash

if [[ $# != 1 ]]; then
    echo usage: compiler_overhead.sh filename
    exit 1
fi

file=$1

for app in $(ls); do
    if [[ -d $app ]]; then
        TOTAL_EXEC=0

        echo $app
        machine=davinci
        FOLDER=$app/$machine/hand
        COUNT=0
        for run in $(ls $FOLDER/); do
            EXEC_TIME=$(cat $FOLDER/$run | grep Execution | awk '{ print $3 }')
            TOTAL_EXEC=$(echo $TOTAL_EXEC + $EXEC_TIME | bc -l)
            COUNT=$(echo $COUNT + 1 | bc)
        done

        if [[ -d $app/$machine/auto.mapper ]]; then
            N_MAPPERS=$(cat $app/$machine/auto.mapper/1/tracker_gpu-*.recordings | grep Mapper | wc -l)
        else
            N_MAPPERS=0
        fi
        if [[ -d $app/$machine/auto.reducer ]]; then
            N_REDUCERS=$(cat $app/$machine/auto.reducer/1/tracker_gpu-*.recordings | grep Reducer | wc -l)
        else
            N_REDUCERS=0
        fi

        if [[ $app == "mahout-kmeans" ]]; then
            app=kmeans
        elif [[ $app == "fuzzy-kmeans" ]]; then
            app=fuzzy
        fi

        AVG_EXEC=$(echo $TOTAL_EXEC / $COUNT | bc -l)
        MAPPER_TRANSLATE_TIME=$(cat $file | grep $app | grep mapper | grep translation | awk '{ print $4 }')
        MAPPER_TRANSLATE_TIME_MS=$(echo $MAPPER_TRANSLATE_TIME \* 1000 | bc -l)
        MAPPER_COMPILE_TIME=$(cat $file | grep $app | grep mapper | grep compilation | awk '{ print $4 }')
        MAPPER_TOTAL=$(echo $MAPPER_TRANSLATE_TIME_MS + $MAPPER_COMPILE_TIME | bc -l)
        # TOTAL=$(echo $MAPPER_TOTAL \* $N_MAPPERS | bc -l)
        TOTAL=$(echo $MAPPER_TOTAL | bc -l)

        EXISTS=$(cat $file | grep $app | grep reduce)
        EXISTS_LEN=$(echo $EXISTS | wc -c)
        if [[ $EXISTS_LEN -ne "1" ]]; then
            REDUCER_TRANSLATE_TIME=$(cat $file | grep $app | grep reducer | grep translation | awk '{ print $4 }')
            REDUCER_TRANSLATE_TIME_MS=$(echo $REDUCER_TRANSLATE_TIME \* 1000 | bc -l)
            REDUCER_COMPILE_TIME=$(cat $file | grep $app | grep reducer | grep compilation | awk '{ print $4 }')
            REDUCER_TOTAL=$(echo $REDUCER_TRANSLATE_TIME_MS + $REDUCER_COMPILE_TIME)
            # TOTAL=$(echo $TOTAL + $REDUCER_TOTAL \* $N_REDUCERS | bc -l)
            TOTAL=$(echo $TOTAL + $REDUCER_TOTAL | bc -l)
        fi
        TOTAL_CPU=$(echo $TOTAL | bc -l)
        echo $TOTAL_CPU $AVG_EXEC
        # echo 100 \* $TOTAL_CPU / $AVG_EXEC | bc -l
    fi
done
