cp ~/hadoopcl/conf/mapred-site.toset.pairwise.mapper ~/hadoopcl/conf/mapred-site.toset
rm -rf ~/hadoopcl-recordings/*
./auto.sh pbs/pairwise64-opencl_uncompressed-amd.pbs ./logs/pairwise/amd/auto.mapper/
python aggregateRecordings.py ~/hadoopcl-recordings/
cp ~/hadoopcl/conf/mapred-site.toset.pairwise.reducer ~/hadoopcl/conf/mapred-site.toset
./auto.sh pbs/pairwise64-opencl_uncompressed-amd.pbs ./logs/pairwise/amd/auto.reducer/


cp ~/hadoopcl/conf/mapred-site.toset.bayes.mapper ~/hadoopcl/conf/mapred-site.toset
rm -rf ~/hadoopcl-recordings/*
./auto.sh pbs/bayes-opencl_uncompressed-amd.pbs ./logs/bayes/amd/auto.mapper/
python aggregateRecordings.py ~/hadoopcl-recordings/
cp ~/hadoopcl/conf/mapred-site.toset.bayes.reducer ~/hadoopcl/conf/mapred-site.toset
./auto.sh pbs/bayes-opencl_uncompressed-amd.pbs ./logs/bayes/amd/auto.reducer/
