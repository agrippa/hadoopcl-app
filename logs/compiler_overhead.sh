#!/bin/bash

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

        echo Avg $(echo $TOTAL_EXEC / $COUNT | bc -l)
        echo $N_MAPPERS mappers, $N_REDUCERS reducers
    fi
done
