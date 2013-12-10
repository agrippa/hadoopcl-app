for threshold in 200 500 1000 3000 5000; do
  mkdir -p ${HOME}/job.threshold.${threshold}
  ./mahout-pairwise.sh ${threshold} &> ${HOME}/job.threshold.${threshold}/log
  scp -r compute-0-7:/state/partition1/hadoop-yiskylee/logs/userlogs/* ${HOME}/job.threshold.${threshold}
done
