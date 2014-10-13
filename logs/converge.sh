#!/bin/bash

PERC=0.10

for app in $(ls); do
    if [[ -d $app ]]; then
        for MACHINE in davinci amd; do
            ACC=0
            TOTAL=0
            for task in mapper reducer; do
                if [[ -d $app/$MACHINE/auto.$task ]]; then
                    # echo $app $MACHINE $task $(ls $app/$MACHINE/auto.$task/ | wc -w)
                    COUNT=$(ls $app/$MACHINE/auto.$task/ | wc -w)
                    TOTAL=$(echo $TOTAL + $COUNT | bc)

                    SUM=$(grep -n "Execution" $app/$MACHINE/hand/* | awk '{ print $3 }' | python ~/sum.py)
                    MIN=$(echo $SUM / $(ls $app/$MACHINE/hand/ | wc -w) | bc -l)
                    # MIN=$(grep -n "Execution" $app/$MACHINE/hand/* | awk '{ print $3 }' | python ~/min.py)
                    # MIN=$(grep -n "Execution" $app/$MACHINE/auto.$task/**/log | awk '{ print $3 }' | python ~/min.py)
                    PERC_VAL=$(echo $MIN \* $PERC | bc -l)
                    MAX_VAL=$(echo $MIN + $PERC_VAL | bc -l)

                    NFOUND=0
                    for f in $app/$MACHINE/auto.$task/**/log; do
                        EXEC_TIME=$(cat $f | grep "Execution" | awk '{ print $3 }')
                        if [[ $(echo $EXEC_TIME'<='$MAX_VAL | bc -l) -eq "1" ]]; then
                            NFOUND=$(echo $NFOUND + 1 | bc)
                        fi
                    done
                    ACC=$(echo $ACC + $NFOUND | bc)
                fi
            done
            echo $app $MACHINE
            echo $ACC / $TOTAL
        done
    fi
done
