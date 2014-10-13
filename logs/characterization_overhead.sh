#!/bin/bash

for app in $(ls); do
    if [[ -d $app ]]; then
        export TOTAL_OVERHEAD=0
        export TOTAL_EXEC=0

        echo $app
        for machine in davinci amd; do
            for task in mapper reducer; do
                FOLDER=$app/$machine/auto.$task
                if [[ -d $FOLDER ]]; then
                    JOBCOUNT=$(ls $FOLDER | wc -w)
                    JOBSOFAR=0
                    for run in $(ls $FOLDER/); do
                        RUN_FOLDER=$FOLDER/$run
                        TASKTRACKER_FILE=$RUN_FOLDER/logs/hadoop-*-tasktracker-*.out
                        cat  $TASKTRACKER_FILE | grep python > python.lines

                        THIS_SUM=$(cat python.lines | while read line; do
                            CMD=$(echo $line | awk '{$1=$2=""; print $0}')
                            CMD_TRIMMED=$(echo $CMD | cut -c 2-)
                            LEN=$(echo ${#CMD_TRIMMED} - 1 | bc)
                            CMD=$(echo $CMD_TRIMMED | cut -c -$LEN)

                            { time $CMD &> /dev/null ; } &> timing
                            TIME=$(cat timing | awk '{ print $2 }' | tail -n 3  | head -n 1)
                            LEN=$(echo ${#TIME} - 3 | bc)
                            TIME=${TIME:2:$LEN}

                            echo $TIME
                        done | python ~/sum.py)
                        TOTAL_OVERHEAD=$(echo $TOTAL_OVERHEAD + $THIS_SUM | bc -l)

                        EXEC_TIME=$(cat $RUN_FOLDER/log | grep Execution | awk '{ print $3 }')
                        TOTAL_EXEC=$(echo $TOTAL_EXEC + $EXEC_TIME | bc -l)

                        JOBSOFAR=$(echo $JOBSOFAR + 1 | bc)
                        echo Finished job $JOBSOFAR / $JOBCOUNT
                    done
                fi
            done
        done

        echo $TOTAL_OVERHEAD
        echo $TOTAL_EXEC
        echo Perc $(echo $TOTAL_OVERHEAD / $TOTAL_EXEC \* 100.0 | bc -l)
    fi
done
