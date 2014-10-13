#!/bin/bash

for app in $(ls); do
    if [[ -d $app ]]; then
        TOTAL_SPEC=0
        TOTAL_EXEC=0

        echo $app
        for machine in davinci amd; do
            for task in mapper reducer; do
                FOLDER=$app/$machine/auto.$task
                if [[ -d $FOLDER ]]; then
                    for run in $(ls $FOLDER/); do
                        RUN_FOLDER=$FOLDER/$run
                        TASKTRACKER_FILE=$RUN_FOLDER/logs/hadoop-*-tasktracker-*.out
                        SPECULATIVE_TIME=$(cat $TASKTRACKER_FILE | grep " speculative," | awk '{ print $13 }' | python ~/sum.py)
                        EXEC_TIME=$(cat $RUN_FOLDER/log | grep Execution | awk '{ print $3 }')

                        TOTAL_SPEC=$(echo $TOTAL_SPEC + $SPECULATIVE_TIME | bc -l)
                        TOTAL_EXEC=$(echo $TOTAL_EXEC + $EXEC_TIME | bc -l)
                    done
                fi
            done
        done

        # echo Total $TOTAL_EXEC
        # echo Spec $TOTAL_SPEC
        echo Perc $(echo $TOTAL_SPEC / $TOTAL_EXEC \* 100.0 | bc -l)
    fi
done
