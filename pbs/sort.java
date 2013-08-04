java: no process killed
java: no process killed
jmg3     18251  0.0  0.0 106084  1400 ?        Ss   13:17   0:00 bash -c ps aux | grep java
jmg3     18267  0.0  0.0 103232   840 ?        S    13:17   0:00 grep java
jmg3     30442  0.0  0.0  59072  3528 pts/0    S+   13:17   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     30448  0.0  0.0 106084  1400 ?        Ss   13:17   0:00 bash -c ps aux | grep java
jmg3     30464  0.0  0.0 103232   840 ?        S    13:17   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1300891.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-015.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>4</value>
  <name>mapred.map.tasks</name><value>12</value>
  <name>opencl.mapper.gpumult</name><value>1</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>1</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>12</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>4</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>67108864</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1300891.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-015.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-012
-----------------------------------------------------
gpu-015
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/17 13:17:39 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 13:17:39 INFO util.GSet: VM type       = 64-bit
13/05/17 13:17:39 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 13:17:39 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 13:17:39 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 13:17:39 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 13:17:39 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 13:17:39 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 13:17:39 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 13:17:39 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 13:17:39 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 13:17:39 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 13:17:39 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 13:17:39 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-015.davinci.rice.edu/192.168.110.215
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-012: no tasktracker to stop
no namenode to stop
gpu-012: no datanode to stop
gpu-015: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/17 13:19:48 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 13:19:48 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 13:19:48 INFO mapred.JobClient: Running job: job_201305171317_0001
13/05/17 13:19:49 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 13:20:06 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 13:20:09 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 13:20:12 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 13:20:15 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 13:20:18 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 13:20:21 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 13:20:39 INFO mapred.JobClient:  map 45% reduce 0%
13/05/17 13:20:42 INFO mapred.JobClient:  map 52% reduce 1%
13/05/17 13:20:45 INFO mapred.JobClient:  map 56% reduce 2%
13/05/17 13:20:48 INFO mapred.JobClient:  map 61% reduce 3%
13/05/17 13:20:51 INFO mapred.JobClient:  map 65% reduce 5%
13/05/17 13:20:54 INFO mapred.JobClient:  map 69% reduce 6%
13/05/17 13:20:57 INFO mapred.JobClient:  map 73% reduce 8%
13/05/17 13:21:00 INFO mapred.JobClient:  map 77% reduce 9%
13/05/17 13:21:03 INFO mapred.JobClient:  map 79% reduce 10%
13/05/17 13:21:09 INFO mapred.JobClient:  map 80% reduce 11%
13/05/17 13:21:12 INFO mapred.JobClient:  map 80% reduce 12%
13/05/17 13:21:15 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 13:21:21 INFO mapred.JobClient:  map 80% reduce 14%
13/05/17 13:21:24 INFO mapred.JobClient:  map 81% reduce 15%
13/05/17 13:21:27 INFO mapred.JobClient:  map 84% reduce 16%
13/05/17 13:21:30 INFO mapred.JobClient:  map 86% reduce 18%
13/05/17 13:21:33 INFO mapred.JobClient:  map 90% reduce 20%
13/05/17 13:21:36 INFO mapred.JobClient:  map 92% reduce 21%
13/05/17 13:21:39 INFO mapred.JobClient:  map 95% reduce 23%
13/05/17 13:21:42 INFO mapred.JobClient:  map 98% reduce 24%
13/05/17 13:21:45 INFO mapred.JobClient:  map 99% reduce 25%
13/05/17 13:21:48 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 13:22:00 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 13:22:06 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 13:22:09 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 13:22:12 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 13:22:18 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 13:22:48 INFO mapred.JobClient:  map 100% reduce 50%
13/05/17 13:22:51 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 13:22:54 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 13:22:57 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 13:23:00 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 13:23:03 INFO mapred.JobClient:  map 100% reduce 76%
13/05/17 13:23:06 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 13:23:09 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 13:23:12 INFO mapred.JobClient:  map 100% reduce 81%
13/05/17 13:23:15 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 13:23:18 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 13:23:24 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 13:23:27 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 13:23:30 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 13:23:33 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 13:23:36 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 13:23:39 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 13:23:42 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 13:23:51 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 13:24:01 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 13:24:06 INFO mapred.JobClient: Job complete: job_201305171317_0001
13/05/17 13:24:06 INFO mapred.JobClient: Counters: 29
13/05/17 13:24:06 INFO mapred.JobClient:   Job Counters 
13/05/17 13:24:06 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 13:24:06 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1212853
13/05/17 13:24:06 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 13:24:06 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 13:24:06 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 13:24:06 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 13:24:06 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=784283
13/05/17 13:24:06 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 13:24:06 INFO mapred.JobClient:     Bytes Written=4848000352
13/05/17 13:24:06 INFO mapred.JobClient:   FileSystemCounters
13/05/17 13:24:06 INFO mapred.JobClient:     FILE_BYTES_READ=8530471560
13/05/17 13:24:06 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 13:24:06 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11693596501
13/05/17 13:24:06 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000352
13/05/17 13:24:06 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 13:24:06 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 13:24:06 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 13:24:06 INFO mapred.JobClient:     Map output materialized bytes=3173231761
13/05/17 13:24:06 INFO mapred.JobClient:     Map input records=120000000
13/05/17 13:24:06 INFO mapred.JobClient:     Reduce shuffle bytes=3067459432
13/05/17 13:24:06 INFO mapred.JobClient:     Spilled Records=904741654
13/05/17 13:24:06 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 13:24:06 INFO mapred.JobClient:     CPU time spent (ms)=1564450
13/05/17 13:24:06 INFO mapred.JobClient:     Total committed heap usage (bytes)=38215942144
13/05/17 13:24:06 INFO mapred.JobClient:     Combine input records=0
13/05/17 13:24:06 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 13:24:06 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 13:24:06 INFO mapred.JobClient:     Reduce input groups=227076445
13/05/17 13:24:06 INFO mapred.JobClient:     Combine output records=0
13/05/17 13:24:06 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27674488832
13/05/17 13:24:06 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 13:24:06 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=365430530048
13/05/17 13:24:06 INFO mapred.JobClient:     Map output records=240000000
Execution Time 258048 ms

real	4m18.824s
user	0m1.967s
sys	0m0.189s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     21506  0.0  0.0 106084  1396 ?        Ss   13:24   0:00 bash -c ps aux | grep java
jmg3     21522  0.0  0.0 103232   840 ?        S    13:24   0:00 grep java
jmg3     31603  0.0  0.0  59072  3528 pts/0    S+   13:24   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     31609  0.0  0.0 106084  1396 ?        Ss   13:24   0:00 bash -c ps aux | grep java
jmg3     31625  0.0  0.0 103232   840 ?        S    13:24   0:00 grep java
java: no process killed
java: no process killed
jmg3     21568  0.0  0.0 106084  1400 ?        Ss   13:24   0:00 bash -c ps aux | grep java
jmg3     21584  0.0  0.0 103232   844 ?        S    13:24   0:00 grep java
jmg3     31691  0.0  0.0  59072  3532 pts/0    S+   13:24   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     31697  0.0  0.0 106084  1396 ?        Ss   13:24   0:00 bash -c ps aux | grep java
jmg3     31713  0.0  0.0 103232   840 ?        S    13:24   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1300891.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-015.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>4</value>
  <name>mapred.map.tasks</name><value>12</value>
  <name>opencl.mapper.gpumult</name><value>1</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>1</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>12</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>4</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>67108864</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1300891.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-015.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-012
-----------------------------------------------------
gpu-015
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/17 13:24:28 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 13:24:28 INFO util.GSet: VM type       = 64-bit
13/05/17 13:24:28 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 13:24:28 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 13:24:28 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 13:24:28 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 13:24:28 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 13:24:28 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 13:24:28 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 13:24:28 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 13:24:28 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 13:24:28 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 13:24:29 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 13:24:29 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-015.davinci.rice.edu/192.168.110.215
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-012: no tasktracker to stop
no namenode to stop
gpu-012: no datanode to stop
gpu-015: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/17 13:26:45 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 13:26:46 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 13:26:46 INFO mapred.JobClient: Running job: job_201305171324_0001
13/05/17 13:26:47 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 13:27:09 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 13:27:12 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 13:27:15 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 13:27:18 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 13:27:21 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 13:27:24 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 13:27:27 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 13:27:45 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 13:27:48 INFO mapred.JobClient:  map 52% reduce 1%
13/05/17 13:27:51 INFO mapred.JobClient:  map 57% reduce 2%
13/05/17 13:27:54 INFO mapred.JobClient:  map 61% reduce 3%
13/05/17 13:27:57 INFO mapred.JobClient:  map 64% reduce 4%
13/05/17 13:28:00 INFO mapred.JobClient:  map 68% reduce 6%
13/05/17 13:28:03 INFO mapred.JobClient:  map 70% reduce 7%
13/05/17 13:28:06 INFO mapred.JobClient:  map 73% reduce 8%
13/05/17 13:28:09 INFO mapred.JobClient:  map 75% reduce 9%
13/05/17 13:28:12 INFO mapred.JobClient:  map 77% reduce 10%
13/05/17 13:28:15 INFO mapred.JobClient:  map 79% reduce 11%
13/05/17 13:28:18 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 13:28:37 INFO mapred.JobClient:  map 80% reduce 14%
13/05/17 13:28:40 INFO mapred.JobClient:  map 81% reduce 14%
13/05/17 13:28:43 INFO mapred.JobClient:  map 85% reduce 14%
13/05/17 13:28:46 INFO mapred.JobClient:  map 89% reduce 15%
13/05/17 13:28:49 INFO mapred.JobClient:  map 92% reduce 17%
13/05/17 13:28:52 INFO mapred.JobClient:  map 93% reduce 18%
13/05/17 13:28:55 INFO mapred.JobClient:  map 95% reduce 20%
13/05/17 13:28:58 INFO mapred.JobClient:  map 98% reduce 21%
13/05/17 13:29:01 INFO mapred.JobClient:  map 99% reduce 23%
13/05/17 13:29:04 INFO mapred.JobClient:  map 99% reduce 24%
13/05/17 13:29:07 INFO mapred.JobClient:  map 100% reduce 25%
13/05/17 13:29:10 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 13:29:28 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 13:29:31 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 13:29:34 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 13:29:37 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 13:30:10 INFO mapred.JobClient:  map 100% reduce 58%
13/05/17 13:30:13 INFO mapred.JobClient:  map 100% reduce 59%
13/05/17 13:30:16 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 13:30:19 INFO mapred.JobClient:  map 100% reduce 71%
13/05/17 13:30:22 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 13:30:25 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 13:30:28 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 13:30:31 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 13:30:34 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 13:30:37 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 13:30:40 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 13:30:43 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 13:30:46 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 13:30:49 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 13:30:52 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 13:30:55 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 13:30:58 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 13:31:01 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 13:31:04 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 13:31:42 INFO mapred.JobClient: Job complete: job_201305171324_0001
13/05/17 13:31:42 INFO mapred.JobClient: Counters: 29
13/05/17 13:31:42 INFO mapred.JobClient:   Job Counters 
13/05/17 13:31:42 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 13:31:42 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1357630
13/05/17 13:31:42 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 13:31:42 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 13:31:42 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 13:31:42 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 13:31:42 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=858659
13/05/17 13:31:42 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 13:31:42 INFO mapred.JobClient:     Bytes Written=4848000352
13/05/17 13:31:42 INFO mapred.JobClient:   FileSystemCounters
13/05/17 13:31:42 INFO mapred.JobClient:     FILE_BYTES_READ=8530471987
13/05/17 13:31:42 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 13:31:42 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11693596928
13/05/17 13:31:42 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000352
13/05/17 13:31:42 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 13:31:42 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 13:31:42 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 13:31:42 INFO mapred.JobClient:     Map output materialized bytes=3173231761
13/05/17 13:31:42 INFO mapred.JobClient:     Map input records=120000000
13/05/17 13:31:42 INFO mapred.JobClient:     Reduce shuffle bytes=3067454676
13/05/17 13:31:42 INFO mapred.JobClient:     Spilled Records=904741654
13/05/17 13:31:42 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 13:31:42 INFO mapred.JobClient:     CPU time spent (ms)=1604430
13/05/17 13:31:42 INFO mapred.JobClient:     Total committed heap usage (bytes)=39464665088
13/05/17 13:31:42 INFO mapred.JobClient:     Combine input records=0
13/05/17 13:31:42 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 13:31:42 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 13:31:42 INFO mapred.JobClient:     Reduce input groups=227076445
13/05/17 13:31:42 INFO mapred.JobClient:     Combine output records=0
13/05/17 13:31:42 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27492548608
13/05/17 13:31:42 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 13:31:42 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=365432635392
13/05/17 13:31:42 INFO mapred.JobClient:     Map output records=240000000
Execution Time 297029 ms

real	4m57.804s
user	0m1.996s
sys	0m0.201s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     24803  0.0  0.0 106084  1396 ?        Ss   13:31   0:00 bash -c ps aux | grep java
jmg3     24819  0.0  0.0 103232   840 ?        S    13:31   0:00 grep java
jmg3       384  0.0  0.0  59072  3524 pts/0    S+   13:31   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3       390  0.0  0.0 106084  1400 ?        Ss   13:31   0:00 bash -c ps aux | grep java
jmg3       406  0.0  0.0 103232   836 ?        S    13:31   0:00 grep java
java: no process killed
java: no process killed
jmg3     24865  0.0  0.0 106084  1396 ?        Ss   13:32   0:00 bash -c ps aux | grep java
jmg3     24881  0.0  0.0 103232   844 ?        S    13:32   0:00 grep java
jmg3       476  0.0  0.0  59072  3528 pts/0    S+   13:32   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3       482  0.0  0.0 106084  1400 ?        Ss   13:32   0:00 bash -c ps aux | grep java
jmg3       499  0.0  0.0 103232   832 ?        S    13:32   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1300891.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-015.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>4</value>
  <name>mapred.map.tasks</name><value>12</value>
  <name>opencl.mapper.gpumult</name><value>1</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>1</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>12</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>4</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>67108864</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1300891.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-015.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-012
-----------------------------------------------------
gpu-015
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/17 13:32:07 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 13:32:07 INFO util.GSet: VM type       = 64-bit
13/05/17 13:32:07 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 13:32:07 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 13:32:07 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 13:32:07 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 13:32:07 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 13:32:07 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 13:32:07 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 13:32:07 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 13:32:07 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 13:32:08 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 13:32:08 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 13:32:08 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-015.davinci.rice.edu/192.168.110.215
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-012: no tasktracker to stop
no namenode to stop
gpu-012: no datanode to stop
gpu-015: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/17 13:35:03 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 13:35:03 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 13:35:03 INFO mapred.JobClient: Running job: job_201305171332_0001
13/05/17 13:35:04 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 13:35:22 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 13:35:25 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 13:35:28 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 13:35:31 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 13:35:34 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 13:35:37 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 13:35:56 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 13:35:59 INFO mapred.JobClient:  map 52% reduce 1%
13/05/17 13:36:02 INFO mapred.JobClient:  map 57% reduce 2%
13/05/17 13:36:05 INFO mapred.JobClient:  map 61% reduce 3%
13/05/17 13:36:08 INFO mapred.JobClient:  map 65% reduce 5%
13/05/17 13:36:11 INFO mapred.JobClient:  map 69% reduce 6%
13/05/17 13:36:14 INFO mapred.JobClient:  map 73% reduce 7%
13/05/17 13:36:17 INFO mapred.JobClient:  map 76% reduce 8%
13/05/17 13:36:20 INFO mapred.JobClient:  map 79% reduce 8%
13/05/17 13:36:23 INFO mapred.JobClient:  map 80% reduce 10%
13/05/17 13:36:26 INFO mapred.JobClient:  map 80% reduce 11%
13/05/17 13:36:33 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 13:36:49 INFO mapred.JobClient:  map 80% reduce 14%
13/05/17 13:36:52 INFO mapred.JobClient:  map 81% reduce 15%
13/05/17 13:36:54 INFO mapred.JobClient:  map 86% reduce 16%
13/05/17 13:36:57 INFO mapred.JobClient:  map 89% reduce 17%
13/05/17 13:37:00 INFO mapred.JobClient:  map 92% reduce 19%
13/05/17 13:37:03 INFO mapred.JobClient:  map 94% reduce 20%
13/05/17 13:37:06 INFO mapred.JobClient:  map 97% reduce 22%
13/05/17 13:37:09 INFO mapred.JobClient:  map 99% reduce 23%
13/05/17 13:37:12 INFO mapred.JobClient:  map 99% reduce 25%
13/05/17 13:37:16 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 13:37:28 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 13:37:33 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 13:37:40 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 13:37:43 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 13:37:46 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 13:37:49 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 13:38:19 INFO mapred.JobClient:  map 100% reduce 50%
13/05/17 13:38:22 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 13:38:25 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 13:38:28 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 13:38:31 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 13:38:34 INFO mapred.JobClient:  map 100% reduce 76%
13/05/17 13:38:37 INFO mapred.JobClient:  map 100% reduce 78%
13/05/17 13:38:40 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 13:38:43 INFO mapred.JobClient:  map 100% reduce 82%
13/05/17 13:38:46 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 13:38:49 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 13:38:52 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 13:38:55 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 13:38:58 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 13:39:01 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 13:39:04 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 13:39:07 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 13:39:13 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 13:39:18 INFO mapred.JobClient: Job complete: job_201305171332_0001
13/05/17 13:39:18 INFO mapred.JobClient: Counters: 29
13/05/17 13:39:18 INFO mapred.JobClient:   Job Counters 
13/05/17 13:39:18 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 13:39:18 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1319738
13/05/17 13:39:18 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 13:39:18 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 13:39:18 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 13:39:18 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 13:39:18 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=807831
13/05/17 13:39:18 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 13:39:18 INFO mapred.JobClient:     Bytes Written=4848000352
13/05/17 13:39:18 INFO mapred.JobClient:   FileSystemCounters
13/05/17 13:39:18 INFO mapred.JobClient:     FILE_BYTES_READ=8530473766
13/05/17 13:39:18 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 13:39:18 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11693598707
13/05/17 13:39:18 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000352
13/05/17 13:39:18 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 13:39:18 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 13:39:18 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 13:39:18 INFO mapred.JobClient:     Map output materialized bytes=3173231761
13/05/17 13:39:18 INFO mapred.JobClient:     Map input records=120000000
13/05/17 13:39:18 INFO mapred.JobClient:     Reduce shuffle bytes=3067459432
13/05/17 13:39:18 INFO mapred.JobClient:     Spilled Records=904741654
13/05/17 13:39:18 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 13:39:18 INFO mapred.JobClient:     CPU time spent (ms)=1587620
13/05/17 13:39:18 INFO mapred.JobClient:     Total committed heap usage (bytes)=40461795328
13/05/17 13:39:18 INFO mapred.JobClient:     Combine input records=0
13/05/17 13:39:18 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 13:39:18 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 13:39:18 INFO mapred.JobClient:     Reduce input groups=227076445
13/05/17 13:39:18 INFO mapred.JobClient:     Combine output records=0
13/05/17 13:39:18 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27651624960
13/05/17 13:39:18 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 13:39:18 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=365567905792
13/05/17 13:39:18 INFO mapred.JobClient:     Map output records=240000000
Execution Time 255411 ms

real	4m16.194s
user	0m1.948s
sys	0m0.201s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     28094  0.0  0.0 106084  1400 ?        Ss   13:39   0:00 bash -c ps aux | grep java
jmg3     28110  0.0  0.0 103232   840 ?        S    13:39   0:00 grep java
jmg3      1690  0.0  0.0  59072  3528 pts/0    S+   13:39   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      1696  0.0  0.0 106084  1396 ?        Ss   13:39   0:00 bash -c ps aux | grep java
jmg3      1712  0.0  0.0 103232   840 ?        S    13:39   0:00 grep java
java: no process killed
java: no process killed
jmg3     28156  0.0  0.0 106084  1396 ?        Ss   13:39   0:00 bash -c ps aux | grep java
jmg3     28172  0.0  0.0 103232   844 ?        S    13:39   0:00 grep java
jmg3      1779  0.0  0.0  59072  3528 pts/0    S+   13:39   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      1786  0.0  0.0 106084  1396 ?        Ss   13:39   0:00 bash -c ps aux | grep java
jmg3      1803  0.0  0.0 103232   836 ?        S    13:39   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1300891.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-015.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>4</value>
  <name>mapred.map.tasks</name><value>12</value>
  <name>opencl.mapper.gpumult</name><value>1</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>1</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>12</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>4</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>67108864</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1300891.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-015.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-012
-----------------------------------------------------
gpu-015
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/17 13:39:45 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 13:39:45 INFO util.GSet: VM type       = 64-bit
13/05/17 13:39:45 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 13:39:45 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 13:39:45 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 13:39:45 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 13:39:45 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 13:39:45 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 13:39:45 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 13:39:45 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 13:39:46 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 13:39:46 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 13:39:46 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 13:39:46 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-015.davinci.rice.edu/192.168.110.215
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-012: no tasktracker to stop
no namenode to stop
gpu-012: no datanode to stop
gpu-015: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/17 13:42:04 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 13:42:04 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 13:42:04 INFO mapred.JobClient: Running job: job_201305171339_0001
13/05/17 13:42:05 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 13:42:24 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 13:42:27 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 13:42:30 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 13:42:33 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 13:42:36 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 13:42:39 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 13:42:57 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 13:43:00 INFO mapred.JobClient:  map 50% reduce 1%
13/05/17 13:43:03 INFO mapred.JobClient:  map 55% reduce 2%
13/05/17 13:43:06 INFO mapred.JobClient:  map 59% reduce 3%
13/05/17 13:43:09 INFO mapred.JobClient:  map 64% reduce 5%
13/05/17 13:43:12 INFO mapred.JobClient:  map 68% reduce 6%
13/05/17 13:43:15 INFO mapred.JobClient:  map 70% reduce 8%
13/05/17 13:43:18 INFO mapred.JobClient:  map 74% reduce 8%
13/05/17 13:43:21 INFO mapred.JobClient:  map 76% reduce 9%
13/05/17 13:43:24 INFO mapred.JobClient:  map 77% reduce 10%
13/05/17 13:43:27 INFO mapred.JobClient:  map 78% reduce 11%
13/05/17 13:43:30 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 13:43:33 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 13:43:42 INFO mapred.JobClient:  map 81% reduce 14%
13/05/17 13:43:48 INFO mapred.JobClient:  map 85% reduce 16%
13/05/17 13:43:51 INFO mapred.JobClient:  map 89% reduce 18%
13/05/17 13:43:54 INFO mapred.JobClient:  map 92% reduce 18%
13/05/17 13:43:57 INFO mapred.JobClient:  map 95% reduce 20%
13/05/17 13:44:01 INFO mapred.JobClient:  map 96% reduce 21%
13/05/17 13:44:04 INFO mapred.JobClient:  map 99% reduce 23%
13/05/17 13:44:07 INFO mapred.JobClient:  map 99% reduce 25%
13/05/17 13:44:13 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 13:44:22 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 13:44:25 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 13:44:34 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 13:44:37 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 13:44:40 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 13:45:04 INFO mapred.JobClient:  map 100% reduce 41%
13/05/17 13:45:07 INFO mapred.JobClient:  map 100% reduce 42%
13/05/17 13:45:13 INFO mapred.JobClient:  map 100% reduce 59%
13/05/17 13:45:28 INFO mapred.JobClient:  map 100% reduce 71%
13/05/17 13:45:31 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 13:45:34 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 13:45:37 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 13:45:40 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 13:45:43 INFO mapred.JobClient:  map 100% reduce 81%
13/05/17 13:45:46 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 13:45:49 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 13:45:52 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 13:45:55 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 13:45:58 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 13:46:01 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 13:46:04 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 13:46:07 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 13:46:10 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 13:46:16 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 13:46:37 INFO mapred.JobClient: Job complete: job_201305171339_0001
13/05/17 13:46:37 INFO mapred.JobClient: Counters: 29
13/05/17 13:46:37 INFO mapred.JobClient:   Job Counters 
13/05/17 13:46:37 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 13:46:37 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1271161
13/05/17 13:46:37 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 13:46:37 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 13:46:37 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 13:46:37 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 13:46:37 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=825092
13/05/17 13:46:37 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 13:46:37 INFO mapred.JobClient:     Bytes Written=4848000352
13/05/17 13:46:37 INFO mapred.JobClient:   FileSystemCounters
13/05/17 13:46:37 INFO mapred.JobClient:     FILE_BYTES_READ=8530473332
13/05/17 13:46:37 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 13:46:37 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11693598273
13/05/17 13:46:37 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000352
13/05/17 13:46:37 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 13:46:37 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 13:46:37 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 13:46:37 INFO mapred.JobClient:     Map output materialized bytes=3173231761
13/05/17 13:46:37 INFO mapred.JobClient:     Map input records=120000000
13/05/17 13:46:37 INFO mapred.JobClient:     Reduce shuffle bytes=3067459432
13/05/17 13:46:37 INFO mapred.JobClient:     Spilled Records=904741654
13/05/17 13:46:37 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 13:46:37 INFO mapred.JobClient:     CPU time spent (ms)=1580030
13/05/17 13:46:37 INFO mapred.JobClient:     Total committed heap usage (bytes)=39951794176
13/05/17 13:46:37 INFO mapred.JobClient:     Combine input records=0
13/05/17 13:46:37 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 13:46:37 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 13:46:37 INFO mapred.JobClient:     Reduce input groups=227076445
13/05/17 13:46:37 INFO mapred.JobClient:     Combine output records=0
13/05/17 13:46:37 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27974160384
13/05/17 13:46:37 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 13:46:37 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=365430530048
13/05/17 13:46:37 INFO mapred.JobClient:     Map output records=240000000
Execution Time 273013 ms

real	4m33.771s
user	0m1.971s
sys	0m0.205s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     31404  0.0  0.0 106084  1404 ?        Ss   13:46   0:00 bash -c ps aux | grep java
jmg3     31420  0.0  0.0 103232   844 ?        S    13:46   0:00 grep java
jmg3      2983  0.0  0.0  59072  3532 pts/0    S+   13:46   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      2989  0.0  0.0 106084  1396 ?        Ss   13:46   0:00 bash -c ps aux | grep java
jmg3      3005  0.0  0.0 103232   844 ?        S    13:46   0:00 grep java
java: no process killed
java: no process killed
jmg3     31466  0.0  0.0 106084  1396 ?        Ss   13:46   0:00 bash -c ps aux | grep java
jmg3     31482  0.0  0.0 103232   844 ?        S    13:46   0:00 grep java
jmg3      3072  0.0  0.0  59072  3528 pts/0    S+   13:46   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      3078  0.0  0.0 106084  1400 ?        Ss   13:46   0:00 bash -c ps aux | grep java
jmg3      3094  0.0  0.0 103232   844 ?        S    13:46   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1300891.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-015.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>4</value>
  <name>mapred.map.tasks</name><value>12</value>
  <name>opencl.mapper.gpumult</name><value>1</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>1</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>12</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>4</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>67108864</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1300891.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-015.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-012
-----------------------------------------------------
gpu-015
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/17 13:46:58 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 13:46:58 INFO util.GSet: VM type       = 64-bit
13/05/17 13:46:58 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 13:46:58 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 13:46:58 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 13:46:58 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 13:46:58 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 13:46:58 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 13:46:58 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 13:46:58 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 13:46:58 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 13:46:58 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 13:46:59 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 13:46:59 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-015.davinci.rice.edu/192.168.110.215
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-012: no tasktracker to stop
no namenode to stop
gpu-012: no datanode to stop
gpu-015: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1300891.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/17 13:49:47 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 13:49:47 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 13:49:48 INFO mapred.JobClient: Running job: job_201305171347_0001
13/05/17 13:49:49 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 13:50:07 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 13:50:10 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 13:50:13 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 13:50:16 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 13:50:19 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 13:50:22 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 13:50:25 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 13:50:40 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 13:50:43 INFO mapred.JobClient:  map 50% reduce 1%
13/05/17 13:50:46 INFO mapred.JobClient:  map 55% reduce 2%
13/05/17 13:50:49 INFO mapred.JobClient:  map 59% reduce 3%
13/05/17 13:50:52 INFO mapred.JobClient:  map 63% reduce 5%
13/05/17 13:50:55 INFO mapred.JobClient:  map 66% reduce 6%
13/05/17 13:50:58 INFO mapred.JobClient:  map 69% reduce 8%
13/05/17 13:51:01 INFO mapred.JobClient:  map 73% reduce 8%
13/05/17 13:51:04 INFO mapred.JobClient:  map 76% reduce 10%
13/05/17 13:51:07 INFO mapred.JobClient:  map 78% reduce 11%
13/05/17 13:51:10 INFO mapred.JobClient:  map 79% reduce 12%
13/05/17 13:51:13 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 13:51:28 INFO mapred.JobClient:  map 80% reduce 14%
13/05/17 13:51:31 INFO mapred.JobClient:  map 84% reduce 15%
13/05/17 13:51:34 INFO mapred.JobClient:  map 87% reduce 16%
13/05/17 13:51:37 INFO mapred.JobClient:  map 89% reduce 18%
13/05/17 13:51:40 INFO mapred.JobClient:  map 92% reduce 20%
13/05/17 13:51:43 INFO mapred.JobClient:  map 95% reduce 21%
13/05/17 13:51:46 INFO mapred.JobClient:  map 98% reduce 23%
13/05/17 13:51:49 INFO mapred.JobClient:  map 99% reduce 25%
13/05/17 13:51:52 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 13:51:55 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 13:52:10 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 13:52:13 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 13:52:16 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 13:52:19 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 13:52:22 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 13:52:58 INFO mapred.JobClient:  map 100% reduce 67%
13/05/17 13:53:01 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 13:53:04 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 13:53:07 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 13:53:10 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 13:53:13 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 13:53:16 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 13:53:19 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 13:53:22 INFO mapred.JobClient:  map 100% reduce 82%
13/05/17 13:53:25 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 13:53:28 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 13:53:31 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 13:53:34 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 13:53:37 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 13:53:40 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 13:53:43 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 13:53:49 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 13:53:52 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 13:53:57 INFO mapred.JobClient: Job complete: job_201305171347_0001
13/05/17 13:53:57 INFO mapred.JobClient: Counters: 29
13/05/17 13:53:57 INFO mapred.JobClient:   Job Counters 
13/05/17 13:53:57 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 13:53:57 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1277251
13/05/17 13:53:57 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 13:53:57 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 13:53:57 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 13:53:57 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 13:53:57 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=771879
13/05/17 13:53:57 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 13:53:57 INFO mapred.JobClient:     Bytes Written=4848000352
13/05/17 13:53:57 INFO mapred.JobClient:   FileSystemCounters
13/05/17 13:53:57 INFO mapred.JobClient:     FILE_BYTES_READ=8530472666
13/05/17 13:53:57 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 13:53:57 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11693597607
13/05/17 13:53:57 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000352
13/05/17 13:53:57 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 13:53:57 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 13:53:57 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 13:53:57 INFO mapred.JobClient:     Map output materialized bytes=3173231761
13/05/17 13:53:57 INFO mapred.JobClient:     Map input records=120000000
13/05/17 13:53:57 INFO mapred.JobClient:     Reduce shuffle bytes=3067459432
13/05/17 13:53:57 INFO mapred.JobClient:     Spilled Records=904741654
13/05/17 13:53:57 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 13:53:57 INFO mapred.JobClient:     CPU time spent (ms)=1631900
13/05/17 13:53:57 INFO mapred.JobClient:     Total committed heap usage (bytes)=38388105216
13/05/17 13:53:57 INFO mapred.JobClient:     Combine input records=0
13/05/17 13:53:57 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 13:53:57 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 13:53:57 INFO mapred.JobClient:     Reduce input groups=227076445
13/05/17 13:53:57 INFO mapred.JobClient:     Combine output records=0
13/05/17 13:53:57 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27535826944
13/05/17 13:53:57 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 13:53:57 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=365429477376
13/05/17 13:53:57 INFO mapred.JobClient:     Map output records=240000000
Execution Time 249986 ms

real	4m10.754s
user	0m1.929s
sys	0m0.220s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3      2343  0.0  0.0 106084  1396 ?        Ss   13:54   0:00 bash -c ps aux | grep java
jmg3      2360  0.0  0.0 103232   840 ?        S    13:54   0:00 grep java
jmg3      4329  0.0  0.0  59072  3528 pts/0    S+   13:54   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      4335  0.0  0.0 106084  1400 ?        Ss   13:54   0:00 bash -c ps aux | grep java
jmg3      4351  0.0  0.0 103232   844 ?        S    13:54   0:00 grep java
