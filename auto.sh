if [ $# != 2 ]; then
    echo usage: auto.sh file log-folder
    exit
fi

FOLDER=$2

for i in {6..10}; do
    echo $i;
    source ${1} &> log
    cp log ${FOLDER}/${i}/
    cp /scratch/jmg3/hadoopcl-recordings/* ${FOLDER}/${i}/
    python aggregateRecordings.py /scratch/jmg3/hadoopcl-recordings/
    scp -r $(tail -n 1 ~/hadoop-1.0.3/hosts):/tmp/hadoop/logs ${FOLDER}/${i}/
done
