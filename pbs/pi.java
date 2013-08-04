java: no process killed
java: no process killed
jmg3     20057  0.0  0.0 106084  1396 ?        Ss   15:49   0:00 bash -c ps aux | grep java
jmg3     20073  0.0  0.0 103232   840 ?        S    15:49   0:00 grep java
jmg3     10981  0.0  0.0  59204  3528 pts/0    S+   15:49   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     10987  0.0  0.0 106084  1400 ?        Ss   15:49   0:00 bash -c ps aux | grep java
jmg3     11003  0.0  0.0 103232   844 ?        S    15:49   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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

13/05/17 15:49:29 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:49:29 INFO util.GSet: VM type       = 64-bit
13/05/17 15:49:29 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:49:29 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:49:29 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:49:29 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:49:29 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:49:29 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:49:29 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:49:29 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:49:29 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:49:29 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:49:30 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:49:30 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 15:51:31 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:51:32 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:51:32 INFO mapred.JobClient: Running job: job_201305171549_0001
13/05/17 15:51:33 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:51:53 INFO mapred.JobClient:  map 1% reduce 0%
13/05/17 15:52:05 INFO mapred.JobClient:  map 2% reduce 0%
13/05/17 15:52:08 INFO mapred.JobClient:  map 3% reduce 0%
13/05/17 15:52:20 INFO mapred.JobClient:  map 4% reduce 0%
13/05/17 15:52:29 INFO mapred.JobClient:  map 5% reduce 0%
13/05/17 15:52:38 INFO mapred.JobClient:  map 6% reduce 0%
13/05/17 15:52:47 INFO mapred.JobClient:  map 7% reduce 0%
13/05/17 15:52:53 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 15:53:02 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 15:53:11 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 15:53:17 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 15:53:26 INFO mapred.JobClient:  map 12% reduce 0%
13/05/17 15:53:32 INFO mapred.JobClient:  map 13% reduce 0%
13/05/17 15:53:41 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 15:53:50 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 15:53:56 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 15:54:05 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 15:54:11 INFO mapred.JobClient:  map 18% reduce 0%
13/05/17 15:54:20 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 15:54:32 INFO mapred.JobClient:  map 20% reduce 0%
13/05/17 15:54:35 INFO mapred.JobClient:  map 21% reduce 0%
13/05/17 15:54:47 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 15:54:53 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 15:55:02 INFO mapred.JobClient:  map 24% reduce 0%
13/05/17 15:55:08 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 15:55:17 INFO mapred.JobClient:  map 26% reduce 0%
13/05/17 15:55:26 INFO mapred.JobClient:  map 27% reduce 0%
13/05/17 15:55:32 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 15:55:41 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 15:55:50 INFO mapred.JobClient:  map 30% reduce 0%
13/05/17 15:55:56 INFO mapred.JobClient:  map 31% reduce 0%
13/05/17 15:56:05 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 15:56:11 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 15:56:20 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 15:56:29 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 15:56:35 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 15:56:44 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 15:56:50 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 15:56:59 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 15:57:08 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 15:57:29 INFO mapred.JobClient:  map 41% reduce 2%
13/05/17 15:57:32 INFO mapred.JobClient:  map 41% reduce 5%
13/05/17 15:57:35 INFO mapred.JobClient:  map 41% reduce 13%
13/05/17 15:57:38 INFO mapred.JobClient:  map 42% reduce 13%
13/05/17 15:57:47 INFO mapred.JobClient:  map 43% reduce 13%
13/05/17 15:57:57 INFO mapred.JobClient:  map 44% reduce 13%
13/05/17 15:58:06 INFO mapred.JobClient:  map 45% reduce 13%
13/05/17 15:58:12 INFO mapred.JobClient:  map 46% reduce 13%
13/05/17 15:58:21 INFO mapred.JobClient:  map 47% reduce 13%
13/05/17 15:58:27 INFO mapred.JobClient:  map 48% reduce 13%
13/05/17 15:58:36 INFO mapred.JobClient:  map 49% reduce 13%
13/05/17 15:58:45 INFO mapred.JobClient:  map 50% reduce 13%
13/05/17 15:58:54 INFO mapred.JobClient:  map 51% reduce 13%
13/05/17 15:59:03 INFO mapred.JobClient:  map 52% reduce 13%
13/05/17 15:59:09 INFO mapred.JobClient:  map 53% reduce 13%
13/05/17 15:59:18 INFO mapred.JobClient:  map 54% reduce 13%
13/05/17 15:59:27 INFO mapred.JobClient:  map 55% reduce 13%
13/05/17 15:59:33 INFO mapred.JobClient:  map 56% reduce 13%
13/05/17 15:59:42 INFO mapred.JobClient:  map 57% reduce 13%
13/05/17 15:59:51 INFO mapred.JobClient:  map 58% reduce 13%
13/05/17 15:59:57 INFO mapred.JobClient:  map 59% reduce 13%
13/05/17 16:00:06 INFO mapred.JobClient:  map 60% reduce 13%
13/05/17 16:00:15 INFO mapred.JobClient:  map 61% reduce 13%
13/05/17 16:00:24 INFO mapred.JobClient:  map 62% reduce 13%
13/05/17 16:00:30 INFO mapred.JobClient:  map 63% reduce 13%
13/05/17 16:00:39 INFO mapred.JobClient:  map 64% reduce 13%
13/05/17 16:00:48 INFO mapred.JobClient:  map 65% reduce 13%
13/05/17 16:00:54 INFO mapred.JobClient:  map 66% reduce 13%
13/05/17 16:01:03 INFO mapred.JobClient:  map 67% reduce 13%
13/05/17 16:01:09 INFO mapred.JobClient:  map 68% reduce 13%
13/05/17 16:01:18 INFO mapred.JobClient:  map 69% reduce 13%
13/05/17 16:01:27 INFO mapred.JobClient:  map 70% reduce 13%
13/05/17 16:01:33 INFO mapred.JobClient:  map 71% reduce 13%
13/05/17 16:01:42 INFO mapred.JobClient:  map 72% reduce 13%
13/05/17 16:01:48 INFO mapred.JobClient:  map 73% reduce 13%
13/05/17 16:01:57 INFO mapred.JobClient:  map 74% reduce 13%
13/05/17 16:02:06 INFO mapred.JobClient:  map 75% reduce 13%
13/05/17 16:02:12 INFO mapred.JobClient:  map 76% reduce 13%
13/05/17 16:02:21 INFO mapred.JobClient:  map 77% reduce 13%
13/05/17 16:02:30 INFO mapred.JobClient:  map 78% reduce 13%
13/05/17 16:02:36 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 16:03:03 INFO mapred.JobClient:  map 80% reduce 15%
13/05/17 16:03:06 INFO mapred.JobClient:  map 80% reduce 23%
13/05/17 16:03:09 INFO mapred.JobClient:  map 80% reduce 26%
13/05/17 16:03:15 INFO mapred.JobClient:  map 81% reduce 26%
13/05/17 16:03:27 INFO mapred.JobClient:  map 82% reduce 26%
13/05/17 16:03:45 INFO mapred.JobClient:  map 83% reduce 26%
13/05/17 16:04:00 INFO mapred.JobClient:  map 84% reduce 26%
13/05/17 16:04:15 INFO mapred.JobClient:  map 85% reduce 26%
13/05/17 16:04:33 INFO mapred.JobClient:  map 86% reduce 26%
13/05/17 16:04:48 INFO mapred.JobClient:  map 87% reduce 26%
13/05/17 16:05:03 INFO mapred.JobClient:  map 88% reduce 26%
13/05/17 16:05:18 INFO mapred.JobClient:  map 89% reduce 26%
13/05/17 16:05:33 INFO mapred.JobClient:  map 90% reduce 26%
13/05/17 16:05:51 INFO mapred.JobClient:  map 91% reduce 26%
13/05/17 16:06:06 INFO mapred.JobClient:  map 92% reduce 26%
13/05/17 16:06:21 INFO mapred.JobClient:  map 93% reduce 26%
13/05/17 16:06:36 INFO mapred.JobClient:  map 94% reduce 26%
13/05/17 16:06:51 INFO mapred.JobClient:  map 95% reduce 26%
13/05/17 16:07:10 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 16:07:25 INFO mapred.JobClient:  map 97% reduce 26%
13/05/17 16:07:40 INFO mapred.JobClient:  map 98% reduce 26%
13/05/17 16:07:58 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 16:08:13 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 16:08:27 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 16:08:33 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 16:08:36 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 16:09:18 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 16:09:21 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 16:09:24 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 16:09:27 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 16:09:30 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 16:09:33 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 16:09:36 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 16:09:39 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 16:09:48 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 16:09:53 INFO mapred.JobClient: Job complete: job_201305171549_0001
13/05/17 16:09:53 INFO mapred.JobClient: Counters: 29
13/05/17 16:09:53 INFO mapred.JobClient:   Job Counters 
13/05/17 16:09:53 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 16:09:53 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=10004839
13/05/17 16:09:53 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 16:09:53 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 16:09:53 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 16:09:53 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 16:09:53 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=2831941
13/05/17 16:09:53 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 16:09:53 INFO mapred.JobClient:     Bytes Written=400
13/05/17 16:09:53 INFO mapred.JobClient:   FileSystemCounters
13/05/17 16:09:53 INFO mapred.JobClient:     FILE_BYTES_READ=2040543661
13/05/17 16:09:53 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 16:09:53 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2775940819
13/05/17 16:09:53 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=400
13/05/17 16:09:53 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 16:09:53 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 16:09:53 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 16:09:53 INFO mapred.JobClient:     Map output materialized bytes=735957967
13/05/17 16:09:53 INFO mapred.JobClient:     Map input records=120000000
13/05/17 16:09:53 INFO mapred.JobClient:     Reduce shuffle bytes=711425446
13/05/17 16:09:53 INFO mapred.JobClient:     Spilled Records=904949930
13/05/17 16:09:53 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 16:09:53 INFO mapred.JobClient:     CPU time spent (ms)=10076670
13/05/17 16:09:53 INFO mapred.JobClient:     Total committed heap usage (bytes)=32246464512
13/05/17 16:09:53 INFO mapred.JobClient:     Combine input records=0
13/05/17 16:09:53 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 16:09:53 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 16:09:53 INFO mapred.JobClient:     Reduce input groups=2
13/05/17 16:09:53 INFO mapred.JobClient:     Combine output records=0
13/05/17 16:09:53 INFO mapred.JobClient:     Physical memory (bytes) snapshot=21289402368
13/05/17 16:09:53 INFO mapred.JobClient:     Reduce output records=2
13/05/17 16:09:53 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658442379264
13/05/17 16:09:53 INFO mapred.JobClient:     Map output records=240000000
Execution Time 1101516 ms

real	18m22.277s
user	0m3.041s
sys	0m0.467s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     23078  0.0  0.0 106084  1400 ?        Ss   16:10   0:00 bash -c ps aux | grep java
jmg3     23094  0.0  0.0 103232   844 ?        S    16:10   0:00 grep java
jmg3     12202  0.0  0.0  59072  3528 pts/0    S+   16:10   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     12208  0.0  0.0 106084  1400 ?        Ss   16:10   0:00 bash -c ps aux | grep java
jmg3     12224  0.0  0.0 103232   844 ?        S    16:10   0:00 grep java
java: no process killed
java: no process killed
jmg3     23140  0.0  0.0 106084  1396 ?        Ss   16:10   0:00 bash -c ps aux | grep java
jmg3     23156  0.0  0.0 103232   840 ?        S    16:10   0:00 grep java
jmg3     12290  0.0  0.0  59072  3528 pts/0    S+   16:10   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     12296  0.0  0.0 106084  1400 ?        Ss   16:10   0:00 bash -c ps aux | grep java
jmg3     12312  0.0  0.0 103232   844 ?        S    16:10   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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

13/05/17 16:10:06 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 16:10:06 INFO util.GSet: VM type       = 64-bit
13/05/17 16:10:06 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 16:10:06 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 16:10:06 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 16:10:06 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 16:10:06 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 16:10:06 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 16:10:06 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 16:10:06 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 16:10:06 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 16:10:06 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 16:10:07 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 16:10:07 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 16:12:50 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 16:12:50 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 16:12:50 INFO mapred.JobClient: Running job: job_201305171610_0001
13/05/17 16:12:51 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 16:13:07 INFO mapred.JobClient:  map 1% reduce 0%
13/05/17 16:13:19 INFO mapred.JobClient:  map 2% reduce 0%
13/05/17 16:13:25 INFO mapred.JobClient:  map 3% reduce 0%
13/05/17 16:13:37 INFO mapred.JobClient:  map 4% reduce 0%
13/05/17 16:13:43 INFO mapred.JobClient:  map 5% reduce 0%
13/05/17 16:13:52 INFO mapred.JobClient:  map 6% reduce 0%
13/05/17 16:14:01 INFO mapred.JobClient:  map 7% reduce 0%
13/05/17 16:14:07 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 16:14:16 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 16:14:25 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 16:14:31 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 16:14:40 INFO mapred.JobClient:  map 12% reduce 0%
13/05/17 16:14:46 INFO mapred.JobClient:  map 13% reduce 0%
13/05/17 16:14:55 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 16:15:04 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 16:15:10 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 16:15:20 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 16:15:26 INFO mapred.JobClient:  map 18% reduce 0%
13/05/17 16:15:35 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 16:15:47 INFO mapred.JobClient:  map 20% reduce 0%
13/05/17 16:15:50 INFO mapred.JobClient:  map 21% reduce 0%
13/05/17 16:16:02 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 16:16:08 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 16:16:17 INFO mapred.JobClient:  map 24% reduce 0%
13/05/17 16:16:26 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 16:16:32 INFO mapred.JobClient:  map 26% reduce 0%
13/05/17 16:16:41 INFO mapred.JobClient:  map 27% reduce 0%
13/05/17 16:16:50 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 16:16:56 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 16:17:05 INFO mapred.JobClient:  map 30% reduce 0%
13/05/17 16:17:11 INFO mapred.JobClient:  map 31% reduce 0%
13/05/17 16:17:20 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 16:17:29 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 16:17:35 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 16:17:44 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 16:17:50 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 16:17:59 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 16:18:08 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 16:18:17 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 16:18:41 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 16:18:44 INFO mapred.JobClient:  map 40% reduce 2%
13/05/17 16:18:47 INFO mapred.JobClient:  map 41% reduce 8%
13/05/17 16:18:50 INFO mapred.JobClient:  map 41% reduce 12%
13/05/17 16:18:56 INFO mapred.JobClient:  map 42% reduce 12%
13/05/17 16:19:05 INFO mapred.JobClient:  map 43% reduce 13%
13/05/17 16:19:11 INFO mapred.JobClient:  map 44% reduce 13%
13/05/17 16:19:23 INFO mapred.JobClient:  map 45% reduce 13%
13/05/17 16:19:29 INFO mapred.JobClient:  map 46% reduce 13%
13/05/17 16:19:41 INFO mapred.JobClient:  map 47% reduce 13%
13/05/17 16:19:47 INFO mapred.JobClient:  map 48% reduce 13%
13/05/17 16:19:56 INFO mapred.JobClient:  map 49% reduce 13%
13/05/17 16:20:02 INFO mapred.JobClient:  map 50% reduce 13%
13/05/17 16:20:11 INFO mapred.JobClient:  map 51% reduce 13%
13/05/17 16:20:20 INFO mapred.JobClient:  map 52% reduce 13%
13/05/17 16:20:26 INFO mapred.JobClient:  map 53% reduce 13%
13/05/17 16:20:35 INFO mapred.JobClient:  map 54% reduce 13%
13/05/17 16:20:44 INFO mapred.JobClient:  map 55% reduce 13%
13/05/17 16:20:50 INFO mapred.JobClient:  map 56% reduce 13%
13/05/17 16:20:59 INFO mapred.JobClient:  map 57% reduce 13%
13/05/17 16:21:08 INFO mapred.JobClient:  map 58% reduce 13%
13/05/17 16:21:14 INFO mapred.JobClient:  map 59% reduce 13%
13/05/17 16:21:23 INFO mapred.JobClient:  map 60% reduce 13%
13/05/17 16:21:32 INFO mapred.JobClient:  map 61% reduce 13%
13/05/17 16:21:41 INFO mapred.JobClient:  map 62% reduce 13%
13/05/17 16:21:47 INFO mapred.JobClient:  map 63% reduce 13%
13/05/17 16:21:56 INFO mapred.JobClient:  map 64% reduce 13%
13/05/17 16:22:02 INFO mapred.JobClient:  map 65% reduce 13%
13/05/17 16:22:11 INFO mapred.JobClient:  map 66% reduce 13%
13/05/17 16:22:20 INFO mapred.JobClient:  map 67% reduce 13%
13/05/17 16:22:26 INFO mapred.JobClient:  map 68% reduce 13%
13/05/17 16:22:35 INFO mapred.JobClient:  map 69% reduce 13%
13/05/17 16:22:41 INFO mapred.JobClient:  map 70% reduce 13%
13/05/17 16:22:50 INFO mapred.JobClient:  map 71% reduce 13%
13/05/17 16:22:59 INFO mapred.JobClient:  map 72% reduce 13%
13/05/17 16:23:05 INFO mapred.JobClient:  map 73% reduce 13%
13/05/17 16:23:14 INFO mapred.JobClient:  map 74% reduce 13%
13/05/17 16:23:23 INFO mapred.JobClient:  map 75% reduce 13%
13/05/17 16:23:29 INFO mapred.JobClient:  map 76% reduce 13%
13/05/17 16:23:38 INFO mapred.JobClient:  map 77% reduce 13%
13/05/17 16:23:47 INFO mapred.JobClient:  map 78% reduce 13%
13/05/17 16:23:56 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 16:24:14 INFO mapred.JobClient:  map 80% reduce 14%
13/05/17 16:24:17 INFO mapred.JobClient:  map 80% reduce 17%
13/05/17 16:24:20 INFO mapred.JobClient:  map 80% reduce 21%
13/05/17 16:24:23 INFO mapred.JobClient:  map 80% reduce 23%
13/05/17 16:24:26 INFO mapred.JobClient:  map 80% reduce 25%
13/05/17 16:24:29 INFO mapred.JobClient:  map 81% reduce 25%
13/05/17 16:24:38 INFO mapred.JobClient:  map 81% reduce 26%
13/05/17 16:24:41 INFO mapred.JobClient:  map 82% reduce 26%
13/05/17 16:24:57 INFO mapred.JobClient:  map 83% reduce 26%
13/05/17 16:25:15 INFO mapred.JobClient:  map 84% reduce 26%
13/05/17 16:25:30 INFO mapred.JobClient:  map 85% reduce 26%
13/05/17 16:25:48 INFO mapred.JobClient:  map 86% reduce 26%
13/05/17 16:26:03 INFO mapred.JobClient:  map 87% reduce 26%
13/05/17 16:26:18 INFO mapred.JobClient:  map 88% reduce 26%
13/05/17 16:26:33 INFO mapred.JobClient:  map 89% reduce 26%
13/05/17 16:26:48 INFO mapred.JobClient:  map 90% reduce 26%
13/05/17 16:27:06 INFO mapred.JobClient:  map 91% reduce 26%
13/05/17 16:27:21 INFO mapred.JobClient:  map 92% reduce 26%
13/05/17 16:27:36 INFO mapred.JobClient:  map 93% reduce 26%
13/05/17 16:27:51 INFO mapred.JobClient:  map 94% reduce 26%
13/05/17 16:28:09 INFO mapred.JobClient:  map 95% reduce 26%
13/05/17 16:28:24 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 16:28:39 INFO mapred.JobClient:  map 97% reduce 26%
13/05/17 16:28:54 INFO mapred.JobClient:  map 98% reduce 26%
13/05/17 16:29:12 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 16:29:27 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 16:29:42 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 16:29:48 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 16:30:21 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 16:30:24 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 16:30:27 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 16:30:30 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 16:30:33 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 16:30:36 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 16:30:39 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 16:30:42 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 16:30:45 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 16:30:51 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 16:30:56 INFO mapred.JobClient: Job complete: job_201305171610_0001
13/05/17 16:30:56 INFO mapred.JobClient: Counters: 29
13/05/17 16:30:56 INFO mapred.JobClient:   Job Counters 
13/05/17 16:30:56 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 16:30:56 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=10024424
13/05/17 16:30:56 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 16:30:56 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 16:30:56 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 16:30:56 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 16:30:56 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=2805227
13/05/17 16:30:56 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 16:30:56 INFO mapred.JobClient:     Bytes Written=400
13/05/17 16:30:56 INFO mapred.JobClient:   FileSystemCounters
13/05/17 16:30:56 INFO mapred.JobClient:     FILE_BYTES_READ=2040547100
13/05/17 16:30:56 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 16:30:56 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2775944258
13/05/17 16:30:56 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=400
13/05/17 16:30:56 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 16:30:56 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 16:30:56 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 16:30:56 INFO mapred.JobClient:     Map output materialized bytes=735957967
13/05/17 16:30:56 INFO mapred.JobClient:     Map input records=120000000
13/05/17 16:30:56 INFO mapred.JobClient:     Reduce shuffle bytes=711425446
13/05/17 16:30:56 INFO mapred.JobClient:     Spilled Records=904949930
13/05/17 16:30:56 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 16:30:56 INFO mapred.JobClient:     CPU time spent (ms)=10086270
13/05/17 16:30:56 INFO mapred.JobClient:     Total committed heap usage (bytes)=32111984640
13/05/17 16:30:56 INFO mapred.JobClient:     Combine input records=0
13/05/17 16:30:56 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 16:30:56 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 16:30:56 INFO mapred.JobClient:     Reduce input groups=2
13/05/17 16:30:56 INFO mapred.JobClient:     Combine output records=0
13/05/17 16:30:56 INFO mapred.JobClient:     Physical memory (bytes) snapshot=20256997376
13/05/17 16:30:56 INFO mapred.JobClient:     Reduce output records=2
13/05/17 16:30:56 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658579763200
13/05/17 16:30:56 INFO mapred.JobClient:     Map output records=240000000
Execution Time 1086480 ms

real	18m7.246s
user	0m3.131s
sys	0m0.393s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     26155  0.0  0.0 106084  1400 ?        Ss   16:31   0:00 bash -c ps aux | grep java
jmg3     26171  0.0  0.0 103232   844 ?        S    16:31   0:00 grep java
jmg3     13498  0.0  0.0  59072  3528 pts/0    S+   16:31   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     13504  0.0  0.0 106084  1400 ?        Ss   16:31   0:00 bash -c ps aux | grep java
jmg3     13520  0.0  0.0 103232   844 ?        S    16:31   0:00 grep java
java: no process killed
java: no process killed
jmg3     26217  0.0  0.0 106084  1400 ?        Ss   16:31   0:00 bash -c ps aux | grep java
jmg3     26233  0.0  0.0 103232   840 ?        S    16:31   0:00 grep java
jmg3     13586  0.0  0.0  59072  3532 pts/0    S+   16:31   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     13592  0.0  0.0 106084  1400 ?        Ss   16:31   0:00 bash -c ps aux | grep java
jmg3     13608  0.0  0.0 103232   844 ?        S    16:31   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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

13/05/17 16:31:09 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 16:31:09 INFO util.GSet: VM type       = 64-bit
13/05/17 16:31:09 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 16:31:09 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 16:31:09 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 16:31:09 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 16:31:09 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 16:31:09 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 16:31:09 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 16:31:09 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 16:31:09 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 16:31:10 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 16:31:10 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 16:31:10 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 16:33:15 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 16:33:16 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 16:33:16 INFO mapred.JobClient: Running job: job_201305171631_0001
13/05/17 16:33:17 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 16:33:33 INFO mapred.JobClient:  map 1% reduce 0%
13/05/17 16:33:45 INFO mapred.JobClient:  map 2% reduce 0%
13/05/17 16:33:48 INFO mapred.JobClient:  map 3% reduce 0%
13/05/17 16:34:03 INFO mapred.JobClient:  map 4% reduce 0%
13/05/17 16:34:12 INFO mapred.JobClient:  map 5% reduce 0%
13/05/17 16:34:18 INFO mapred.JobClient:  map 6% reduce 0%
13/05/17 16:34:27 INFO mapred.JobClient:  map 7% reduce 0%
13/05/17 16:34:33 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 16:34:42 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 16:34:51 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 16:34:57 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 16:35:06 INFO mapred.JobClient:  map 12% reduce 0%
13/05/17 16:35:12 INFO mapred.JobClient:  map 13% reduce 0%
13/05/17 16:35:21 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 16:35:30 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 16:35:36 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 16:35:45 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 16:35:51 INFO mapred.JobClient:  map 18% reduce 0%
13/05/17 16:36:00 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 16:36:12 INFO mapred.JobClient:  map 20% reduce 0%
13/05/17 16:36:15 INFO mapred.JobClient:  map 21% reduce 0%
13/05/17 16:36:27 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 16:36:30 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 16:36:42 INFO mapred.JobClient:  map 24% reduce 0%
13/05/17 16:36:48 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 16:36:57 INFO mapred.JobClient:  map 26% reduce 0%
13/05/17 16:37:06 INFO mapred.JobClient:  map 27% reduce 0%
13/05/17 16:37:12 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 16:37:21 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 16:37:30 INFO mapred.JobClient:  map 30% reduce 0%
13/05/17 16:37:36 INFO mapred.JobClient:  map 31% reduce 0%
13/05/17 16:37:45 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 16:37:51 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 16:38:00 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 16:38:09 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 16:38:15 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 16:38:24 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 16:38:30 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 16:38:39 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 16:38:48 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 16:39:09 INFO mapred.JobClient:  map 41% reduce 2%
13/05/17 16:39:12 INFO mapred.JobClient:  map 41% reduce 5%
13/05/17 16:39:15 INFO mapred.JobClient:  map 41% reduce 13%
13/05/17 16:39:18 INFO mapred.JobClient:  map 42% reduce 13%
13/05/17 16:39:31 INFO mapred.JobClient:  map 43% reduce 13%
13/05/17 16:39:37 INFO mapred.JobClient:  map 44% reduce 13%
13/05/17 16:39:46 INFO mapred.JobClient:  map 45% reduce 13%
13/05/17 16:39:52 INFO mapred.JobClient:  map 46% reduce 13%
13/05/17 16:40:01 INFO mapred.JobClient:  map 47% reduce 13%
13/05/17 16:40:11 INFO mapred.JobClient:  map 48% reduce 13%
13/05/17 16:40:17 INFO mapred.JobClient:  map 49% reduce 13%
13/05/17 16:40:29 INFO mapred.JobClient:  map 50% reduce 13%
13/05/17 16:40:32 INFO mapred.JobClient:  map 51% reduce 13%
13/05/17 16:40:44 INFO mapred.JobClient:  map 52% reduce 13%
13/05/17 16:40:53 INFO mapred.JobClient:  map 53% reduce 13%
13/05/17 16:40:59 INFO mapred.JobClient:  map 54% reduce 13%
13/05/17 16:41:08 INFO mapred.JobClient:  map 55% reduce 13%
13/05/17 16:41:14 INFO mapred.JobClient:  map 56% reduce 13%
13/05/17 16:41:23 INFO mapred.JobClient:  map 57% reduce 13%
13/05/17 16:41:32 INFO mapred.JobClient:  map 58% reduce 13%
13/05/17 16:41:38 INFO mapred.JobClient:  map 59% reduce 13%
13/05/17 16:41:47 INFO mapred.JobClient:  map 60% reduce 13%
13/05/17 16:41:53 INFO mapred.JobClient:  map 61% reduce 13%
13/05/17 16:42:02 INFO mapred.JobClient:  map 62% reduce 13%
13/05/17 16:42:11 INFO mapred.JobClient:  map 63% reduce 13%
13/05/17 16:42:17 INFO mapred.JobClient:  map 64% reduce 13%
13/05/17 16:42:26 INFO mapred.JobClient:  map 65% reduce 13%
13/05/17 16:42:32 INFO mapred.JobClient:  map 66% reduce 13%
13/05/17 16:42:41 INFO mapred.JobClient:  map 67% reduce 13%
13/05/17 16:42:50 INFO mapred.JobClient:  map 68% reduce 13%
13/05/17 16:42:56 INFO mapred.JobClient:  map 69% reduce 13%
13/05/17 16:43:05 INFO mapred.JobClient:  map 70% reduce 13%
13/05/17 16:43:14 INFO mapred.JobClient:  map 71% reduce 13%
13/05/17 16:43:23 INFO mapred.JobClient:  map 72% reduce 13%
13/05/17 16:43:29 INFO mapred.JobClient:  map 73% reduce 13%
13/05/17 16:43:38 INFO mapred.JobClient:  map 74% reduce 13%
13/05/17 16:43:47 INFO mapred.JobClient:  map 75% reduce 13%
13/05/17 16:43:56 INFO mapred.JobClient:  map 76% reduce 13%
13/05/17 16:44:05 INFO mapred.JobClient:  map 77% reduce 13%
13/05/17 16:44:11 INFO mapred.JobClient:  map 78% reduce 13%
13/05/17 16:44:20 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 16:44:41 INFO mapred.JobClient:  map 79% reduce 14%
13/05/17 16:44:44 INFO mapred.JobClient:  map 80% reduce 21%
13/05/17 16:44:47 INFO mapred.JobClient:  map 80% reduce 24%
13/05/17 16:44:50 INFO mapred.JobClient:  map 80% reduce 26%
13/05/17 16:44:56 INFO mapred.JobClient:  map 81% reduce 26%
13/05/17 16:45:08 INFO mapred.JobClient:  map 82% reduce 26%
13/05/17 16:45:25 INFO mapred.JobClient:  map 83% reduce 26%
13/05/17 16:45:41 INFO mapred.JobClient:  map 84% reduce 26%
13/05/17 16:45:58 INFO mapred.JobClient:  map 85% reduce 26%
13/05/17 16:46:13 INFO mapred.JobClient:  map 86% reduce 26%
13/05/17 16:46:28 INFO mapred.JobClient:  map 87% reduce 26%
13/05/17 16:46:43 INFO mapred.JobClient:  map 88% reduce 26%
13/05/17 16:47:01 INFO mapred.JobClient:  map 89% reduce 26%
13/05/17 16:47:16 INFO mapred.JobClient:  map 90% reduce 26%
13/05/17 16:47:31 INFO mapred.JobClient:  map 91% reduce 26%
13/05/17 16:47:49 INFO mapred.JobClient:  map 92% reduce 26%
13/05/17 16:48:04 INFO mapred.JobClient:  map 93% reduce 26%
13/05/17 16:48:19 INFO mapred.JobClient:  map 94% reduce 26%
13/05/17 16:48:34 INFO mapred.JobClient:  map 95% reduce 26%
13/05/17 16:48:52 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 16:49:07 INFO mapred.JobClient:  map 97% reduce 26%
13/05/17 16:49:25 INFO mapred.JobClient:  map 98% reduce 26%
13/05/17 16:49:40 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 16:50:11 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 16:50:14 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 16:50:17 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 16:50:38 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 16:50:50 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 16:50:53 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 16:50:56 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 16:50:59 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 16:51:02 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 16:51:05 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 16:51:08 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 16:51:11 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 16:51:17 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 16:51:22 INFO mapred.JobClient: Job complete: job_201305171631_0001
13/05/17 16:51:22 INFO mapred.JobClient: Counters: 29
13/05/17 16:51:22 INFO mapred.JobClient:   Job Counters 
13/05/17 16:51:22 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 16:51:22 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=10029312
13/05/17 16:51:22 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 16:51:22 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 16:51:22 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 16:51:22 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 16:51:22 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=2853249
13/05/17 16:51:22 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 16:51:22 INFO mapred.JobClient:     Bytes Written=400
13/05/17 16:51:22 INFO mapred.JobClient:   FileSystemCounters
13/05/17 16:51:22 INFO mapred.JobClient:     FILE_BYTES_READ=2040546198
13/05/17 16:51:22 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 16:51:22 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2775943356
13/05/17 16:51:22 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=400
13/05/17 16:51:22 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 16:51:22 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 16:51:22 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 16:51:22 INFO mapred.JobClient:     Map output materialized bytes=735957967
13/05/17 16:51:22 INFO mapred.JobClient:     Map input records=120000000
13/05/17 16:51:22 INFO mapred.JobClient:     Reduce shuffle bytes=711426708
13/05/17 16:51:22 INFO mapred.JobClient:     Spilled Records=904949930
13/05/17 16:51:22 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 16:51:22 INFO mapred.JobClient:     CPU time spent (ms)=10095670
13/05/17 16:51:22 INFO mapred.JobClient:     Total committed heap usage (bytes)=34266152960
13/05/17 16:51:22 INFO mapred.JobClient:     Combine input records=0
13/05/17 16:51:22 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 16:51:22 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 16:51:22 INFO mapred.JobClient:     Reduce input groups=2
13/05/17 16:51:22 INFO mapred.JobClient:     Combine output records=0
13/05/17 16:51:22 INFO mapred.JobClient:     Physical memory (bytes) snapshot=23110275072
13/05/17 16:51:22 INFO mapred.JobClient:     Reduce output records=2
13/05/17 16:51:22 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658711707648
13/05/17 16:51:22 INFO mapred.JobClient:     Map output records=240000000
Execution Time 1086376 ms

real	18m7.140s
user	0m3.090s
sys	0m0.404s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     29227  0.0  0.0 106084  1396 ?        Ss   16:51   0:00 bash -c ps aux | grep java
jmg3     29243  0.0  0.0 103232   844 ?        S    16:51   0:00 grep java
jmg3     14787  0.0  0.0  59072  3528 pts/0    S+   16:51   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     14793  0.0  0.0 106084  1400 ?        Ss   16:51   0:00 bash -c ps aux | grep java
jmg3     14809  0.0  0.0 103232   844 ?        S    16:51   0:00 grep java
java: no process killed
java: no process killed
jmg3     29289  0.0  0.0 106084  1404 ?        Ss   16:51   0:00 bash -c ps aux | grep java
jmg3     29305  0.0  0.0 103232   844 ?        S    16:51   0:00 grep java
jmg3     14875  0.0  0.0  59072  3524 pts/0    S+   16:51   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     14881  0.0  0.0 106084  1396 ?        Ss   16:51   0:00 bash -c ps aux | grep java
jmg3     14897  0.0  0.0 103232   840 ?        S    16:51   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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

13/05/17 16:51:35 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 16:51:35 INFO util.GSet: VM type       = 64-bit
13/05/17 16:51:35 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 16:51:35 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 16:51:35 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 16:51:35 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 16:51:35 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 16:51:35 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 16:51:35 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 16:51:35 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 16:51:35 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 16:51:35 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 16:51:35 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 16:51:35 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 16:53:53 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 16:53:53 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 16:53:54 INFO mapred.JobClient: Running job: job_201305171651_0001
13/05/17 16:53:55 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 16:54:14 INFO mapred.JobClient:  map 1% reduce 0%
13/05/17 16:54:26 INFO mapred.JobClient:  map 2% reduce 0%
13/05/17 16:54:29 INFO mapred.JobClient:  map 3% reduce 0%
13/05/17 16:54:41 INFO mapred.JobClient:  map 4% reduce 0%
13/05/17 16:54:50 INFO mapred.JobClient:  map 5% reduce 0%
13/05/17 16:54:56 INFO mapred.JobClient:  map 6% reduce 0%
13/05/17 16:55:05 INFO mapred.JobClient:  map 7% reduce 0%
13/05/17 16:55:11 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 16:55:20 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 16:55:29 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 16:55:35 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 16:55:44 INFO mapred.JobClient:  map 12% reduce 0%
13/05/17 16:55:50 INFO mapred.JobClient:  map 13% reduce 0%
13/05/17 16:55:59 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 16:56:08 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 16:56:14 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 16:56:23 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 16:56:29 INFO mapred.JobClient:  map 18% reduce 0%
13/05/17 16:56:38 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 16:56:50 INFO mapred.JobClient:  map 20% reduce 0%
13/05/17 16:56:56 INFO mapred.JobClient:  map 21% reduce 0%
13/05/17 16:57:05 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 16:57:11 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 16:57:20 INFO mapred.JobClient:  map 24% reduce 0%
13/05/17 16:57:26 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 16:57:35 INFO mapred.JobClient:  map 26% reduce 0%
13/05/17 16:57:44 INFO mapred.JobClient:  map 27% reduce 0%
13/05/17 16:57:53 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 16:58:02 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 16:58:08 INFO mapred.JobClient:  map 30% reduce 0%
13/05/17 16:58:17 INFO mapred.JobClient:  map 31% reduce 0%
13/05/17 16:58:26 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 16:58:32 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 16:58:41 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 16:58:50 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 16:58:56 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 16:59:05 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 16:59:11 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 16:59:20 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 16:59:29 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 16:59:50 INFO mapred.JobClient:  map 41% reduce 2%
13/05/17 16:59:53 INFO mapred.JobClient:  map 41% reduce 8%
13/05/17 16:59:56 INFO mapred.JobClient:  map 41% reduce 13%
13/05/17 16:59:59 INFO mapred.JobClient:  map 42% reduce 13%
13/05/17 17:00:05 INFO mapred.JobClient:  map 43% reduce 13%
13/05/17 17:00:17 INFO mapred.JobClient:  map 44% reduce 13%
13/05/17 17:00:26 INFO mapred.JobClient:  map 45% reduce 13%
13/05/17 17:00:35 INFO mapred.JobClient:  map 46% reduce 13%
13/05/17 17:00:44 INFO mapred.JobClient:  map 47% reduce 13%
13/05/17 17:00:50 INFO mapred.JobClient:  map 48% reduce 13%
13/05/17 17:00:59 INFO mapred.JobClient:  map 49% reduce 13%
13/05/17 17:01:08 INFO mapred.JobClient:  map 50% reduce 13%
13/05/17 17:01:14 INFO mapred.JobClient:  map 51% reduce 13%
13/05/17 17:01:23 INFO mapred.JobClient:  map 52% reduce 13%
13/05/17 17:01:29 INFO mapred.JobClient:  map 53% reduce 13%
13/05/17 17:01:38 INFO mapred.JobClient:  map 54% reduce 13%
13/05/17 17:01:47 INFO mapred.JobClient:  map 55% reduce 13%
13/05/17 17:01:53 INFO mapred.JobClient:  map 56% reduce 13%
13/05/17 17:02:02 INFO mapred.JobClient:  map 57% reduce 13%
13/05/17 17:02:08 INFO mapred.JobClient:  map 58% reduce 13%
13/05/17 17:02:17 INFO mapred.JobClient:  map 59% reduce 13%
13/05/17 17:02:26 INFO mapred.JobClient:  map 60% reduce 13%
13/05/17 17:02:35 INFO mapred.JobClient:  map 61% reduce 13%
13/05/17 17:02:41 INFO mapred.JobClient:  map 62% reduce 13%
13/05/17 17:02:50 INFO mapred.JobClient:  map 63% reduce 13%
13/05/17 17:03:00 INFO mapred.JobClient:  map 64% reduce 13%
13/05/17 17:03:06 INFO mapred.JobClient:  map 65% reduce 13%
13/05/17 17:03:15 INFO mapred.JobClient:  map 66% reduce 13%
13/05/17 17:03:21 INFO mapred.JobClient:  map 67% reduce 13%
13/05/17 17:03:30 INFO mapred.JobClient:  map 68% reduce 13%
13/05/17 17:03:39 INFO mapred.JobClient:  map 69% reduce 13%
13/05/17 17:03:48 INFO mapred.JobClient:  map 70% reduce 13%
13/05/17 17:03:54 INFO mapred.JobClient:  map 71% reduce 13%
13/05/17 17:04:03 INFO mapred.JobClient:  map 72% reduce 13%
13/05/17 17:04:09 INFO mapred.JobClient:  map 73% reduce 13%
13/05/17 17:04:18 INFO mapred.JobClient:  map 74% reduce 13%
13/05/17 17:04:26 INFO mapred.JobClient:  map 75% reduce 13%
13/05/17 17:04:32 INFO mapred.JobClient:  map 76% reduce 13%
13/05/17 17:04:41 INFO mapred.JobClient:  map 77% reduce 13%
13/05/17 17:04:50 INFO mapred.JobClient:  map 78% reduce 13%
13/05/17 17:04:59 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 17:05:23 INFO mapred.JobClient:  map 80% reduce 13%
13/05/17 17:05:26 INFO mapred.JobClient:  map 80% reduce 21%
13/05/17 17:05:29 INFO mapred.JobClient:  map 80% reduce 25%
13/05/17 17:05:32 INFO mapred.JobClient:  map 80% reduce 26%
13/05/17 17:05:35 INFO mapred.JobClient:  map 81% reduce 26%
13/05/17 17:05:50 INFO mapred.JobClient:  map 82% reduce 26%
13/05/17 17:06:05 INFO mapred.JobClient:  map 83% reduce 26%
13/05/17 17:06:20 INFO mapred.JobClient:  map 84% reduce 26%
13/05/17 17:06:35 INFO mapred.JobClient:  map 85% reduce 26%
13/05/17 17:06:53 INFO mapred.JobClient:  map 86% reduce 26%
13/05/17 17:07:08 INFO mapred.JobClient:  map 87% reduce 26%
13/05/17 17:07:23 INFO mapred.JobClient:  map 88% reduce 26%
13/05/17 17:07:38 INFO mapred.JobClient:  map 89% reduce 26%
13/05/17 17:07:53 INFO mapred.JobClient:  map 90% reduce 26%
13/05/17 17:08:11 INFO mapred.JobClient:  map 91% reduce 26%
13/05/17 17:08:29 INFO mapred.JobClient:  map 92% reduce 26%
13/05/17 17:08:44 INFO mapred.JobClient:  map 93% reduce 26%
13/05/17 17:08:59 INFO mapred.JobClient:  map 94% reduce 26%
13/05/17 17:09:14 INFO mapred.JobClient:  map 95% reduce 26%
13/05/17 17:09:29 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 17:09:47 INFO mapred.JobClient:  map 97% reduce 26%
13/05/17 17:10:02 INFO mapred.JobClient:  map 98% reduce 26%
13/05/17 17:10:17 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 17:10:32 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 17:10:47 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 17:10:50 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 17:10:53 INFO mapred.JobClient:  map 100% reduce 48%
13/05/17 17:10:56 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 17:11:26 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 17:11:29 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 17:11:32 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 17:11:35 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 17:11:38 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 17:11:41 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 17:11:44 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 17:11:47 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 17:11:50 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 17:11:59 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 17:12:04 INFO mapred.JobClient: Job complete: job_201305171651_0001
13/05/17 17:12:04 INFO mapred.JobClient: Counters: 29
13/05/17 17:12:04 INFO mapred.JobClient:   Job Counters 
13/05/17 17:12:04 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 17:12:04 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=10026057
13/05/17 17:12:04 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 17:12:04 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 17:12:04 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 17:12:04 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 17:12:04 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=2808312
13/05/17 17:12:04 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 17:12:04 INFO mapred.JobClient:     Bytes Written=400
13/05/17 17:12:04 INFO mapred.JobClient:   FileSystemCounters
13/05/17 17:12:04 INFO mapred.JobClient:     FILE_BYTES_READ=2040543540
13/05/17 17:12:04 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 17:12:04 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2775940698
13/05/17 17:12:04 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=400
13/05/17 17:12:04 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 17:12:04 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 17:12:04 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 17:12:04 INFO mapred.JobClient:     Map output materialized bytes=735957967
13/05/17 17:12:04 INFO mapred.JobClient:     Map input records=120000000
13/05/17 17:12:04 INFO mapred.JobClient:     Reduce shuffle bytes=711425446
13/05/17 17:12:04 INFO mapred.JobClient:     Spilled Records=904949930
13/05/17 17:12:04 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 17:12:04 INFO mapred.JobClient:     CPU time spent (ms)=10073450
13/05/17 17:12:04 INFO mapred.JobClient:     Total committed heap usage (bytes)=31330140160
13/05/17 17:12:04 INFO mapred.JobClient:     Combine input records=0
13/05/17 17:12:04 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 17:12:04 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 17:12:04 INFO mapred.JobClient:     Reduce input groups=2
13/05/17 17:12:04 INFO mapred.JobClient:     Combine output records=0
13/05/17 17:12:04 INFO mapred.JobClient:     Physical memory (bytes) snapshot=20335472640
13/05/17 17:12:04 INFO mapred.JobClient:     Reduce output records=2
13/05/17 17:12:04 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658369847296
13/05/17 17:12:04 INFO mapred.JobClient:     Map output records=240000000
Execution Time 1091393 ms

real	18m12.153s
user	0m3.087s
sys	0m0.425s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     32312  0.0  0.0 106084  1396 ?        Ss   17:12   0:00 bash -c ps aux | grep java
jmg3     32328  0.0  0.0 103232   840 ?        S    17:12   0:00 grep java
jmg3     16096  0.0  0.0  59204  3532 pts/0    S+   17:12   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     16102  0.0  0.0 106084  1400 ?        Ss   17:12   0:00 bash -c ps aux | grep java
jmg3     16118  0.0  0.0 103232   840 ?        S    17:12   0:00 grep java
java: no process killed
java: no process killed
jmg3     32374  0.0  0.0 106084  1396 ?        Ss   17:12   0:00 bash -c ps aux | grep java
jmg3     32390  0.0  0.0 103232   844 ?        S    17:12   0:00 grep java
jmg3     16184  0.0  0.0  59072  3528 pts/0    S+   17:12   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     16190  0.0  0.0 106084  1396 ?        Ss   17:12   0:00 bash -c ps aux | grep java
jmg3     16206  0.0  0.0 103232   844 ?        S    17:12   0:00 grep java
Setting path to /tmp/1300891.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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

13/05/17 17:12:18 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 17:12:18 INFO util.GSet: VM type       = 64-bit
13/05/17 17:12:18 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 17:12:18 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 17:12:18 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 17:12:18 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 17:12:18 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 17:12:18 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 17:12:18 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 17:12:18 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 17:12:18 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 17:12:18 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 17:12:18 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 17:12:18 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 17:15:06 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 17:15:06 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 17:15:07 INFO mapred.JobClient: Running job: job_201305171712_0001
13/05/17 17:15:08 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 17:15:24 INFO mapred.JobClient:  map 1% reduce 0%
13/05/17 17:15:36 INFO mapred.JobClient:  map 2% reduce 0%
13/05/17 17:15:39 INFO mapred.JobClient:  map 3% reduce 0%
13/05/17 17:15:54 INFO mapred.JobClient:  map 4% reduce 0%
13/05/17 17:16:00 INFO mapred.JobClient:  map 5% reduce 0%
13/05/17 17:16:09 INFO mapred.JobClient:  map 6% reduce 0%
13/05/17 17:16:18 INFO mapred.JobClient:  map 7% reduce 0%
13/05/17 17:16:24 INFO mapred.JobClient:  map 8% reduce 0%
13/05/17 17:16:33 INFO mapred.JobClient:  map 9% reduce 0%
13/05/17 17:16:42 INFO mapred.JobClient:  map 10% reduce 0%
13/05/17 17:16:48 INFO mapred.JobClient:  map 11% reduce 0%
13/05/17 17:16:57 INFO mapred.JobClient:  map 12% reduce 0%
13/05/17 17:17:03 INFO mapred.JobClient:  map 13% reduce 0%
13/05/17 17:17:12 INFO mapred.JobClient:  map 14% reduce 0%
13/05/17 17:17:21 INFO mapred.JobClient:  map 15% reduce 0%
13/05/17 17:17:27 INFO mapred.JobClient:  map 16% reduce 0%
13/05/17 17:17:36 INFO mapred.JobClient:  map 17% reduce 0%
13/05/17 17:17:42 INFO mapred.JobClient:  map 18% reduce 0%
13/05/17 17:17:51 INFO mapred.JobClient:  map 19% reduce 0%
13/05/17 17:18:00 INFO mapred.JobClient:  map 20% reduce 0%
13/05/17 17:18:06 INFO mapred.JobClient:  map 21% reduce 0%
13/05/17 17:18:18 INFO mapred.JobClient:  map 22% reduce 0%
13/05/17 17:18:21 INFO mapred.JobClient:  map 23% reduce 0%
13/05/17 17:18:33 INFO mapred.JobClient:  map 24% reduce 0%
13/05/17 17:18:39 INFO mapred.JobClient:  map 25% reduce 0%
13/05/17 17:18:48 INFO mapred.JobClient:  map 26% reduce 0%
13/05/17 17:18:57 INFO mapred.JobClient:  map 27% reduce 0%
13/05/17 17:19:03 INFO mapred.JobClient:  map 28% reduce 0%
13/05/17 17:19:12 INFO mapred.JobClient:  map 29% reduce 0%
13/05/17 17:19:21 INFO mapred.JobClient:  map 30% reduce 0%
13/05/17 17:19:27 INFO mapred.JobClient:  map 31% reduce 0%
13/05/17 17:19:36 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 17:19:42 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 17:19:51 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 17:20:00 INFO mapred.JobClient:  map 35% reduce 0%
13/05/17 17:20:06 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 17:20:15 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 17:20:21 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 17:20:30 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 17:20:42 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 17:21:00 INFO mapred.JobClient:  map 40% reduce 2%
13/05/17 17:21:03 INFO mapred.JobClient:  map 41% reduce 8%
13/05/17 17:21:06 INFO mapred.JobClient:  map 41% reduce 13%
13/05/17 17:21:12 INFO mapred.JobClient:  map 42% reduce 13%
13/05/17 17:21:21 INFO mapred.JobClient:  map 43% reduce 13%
13/05/17 17:21:30 INFO mapred.JobClient:  map 44% reduce 13%
13/05/17 17:21:36 INFO mapred.JobClient:  map 45% reduce 13%
13/05/17 17:21:45 INFO mapred.JobClient:  map 46% reduce 13%
13/05/17 17:21:54 INFO mapred.JobClient:  map 47% reduce 13%
13/05/17 17:22:00 INFO mapred.JobClient:  map 48% reduce 13%
13/05/17 17:22:09 INFO mapred.JobClient:  map 49% reduce 13%
13/05/17 17:22:18 INFO mapred.JobClient:  map 50% reduce 13%
13/05/17 17:22:27 INFO mapred.JobClient:  map 51% reduce 13%
13/05/17 17:22:33 INFO mapred.JobClient:  map 52% reduce 13%
13/05/17 17:22:42 INFO mapred.JobClient:  map 53% reduce 13%
13/05/17 17:22:51 INFO mapred.JobClient:  map 54% reduce 13%
13/05/17 17:22:57 INFO mapred.JobClient:  map 55% reduce 13%
13/05/17 17:23:06 INFO mapred.JobClient:  map 56% reduce 13%
13/05/17 17:23:12 INFO mapred.JobClient:  map 57% reduce 13%
13/05/17 17:23:21 INFO mapred.JobClient:  map 58% reduce 13%
13/05/17 17:23:31 INFO mapred.JobClient:  map 59% reduce 13%
13/05/17 17:23:37 INFO mapred.JobClient:  map 60% reduce 13%
13/05/17 17:23:46 INFO mapred.JobClient:  map 61% reduce 13%
13/05/17 17:23:55 INFO mapred.JobClient:  map 62% reduce 13%
13/05/17 17:24:01 INFO mapred.JobClient:  map 63% reduce 13%
13/05/17 17:24:10 INFO mapred.JobClient:  map 64% reduce 13%
13/05/17 17:24:19 INFO mapred.JobClient:  map 65% reduce 13%
13/05/17 17:24:25 INFO mapred.JobClient:  map 66% reduce 13%
13/05/17 17:24:34 INFO mapred.JobClient:  map 67% reduce 13%
13/05/17 17:24:43 INFO mapred.JobClient:  map 68% reduce 13%
13/05/17 17:24:49 INFO mapred.JobClient:  map 69% reduce 13%
13/05/17 17:24:58 INFO mapred.JobClient:  map 70% reduce 13%
13/05/17 17:25:07 INFO mapred.JobClient:  map 71% reduce 13%
13/05/17 17:25:16 INFO mapred.JobClient:  map 72% reduce 13%
13/05/17 17:25:25 INFO mapred.JobClient:  map 73% reduce 13%
13/05/17 17:25:31 INFO mapred.JobClient:  map 74% reduce 13%
13/05/17 17:25:40 INFO mapred.JobClient:  map 75% reduce 13%
13/05/17 17:25:49 INFO mapred.JobClient:  map 76% reduce 13%
13/05/17 17:25:55 INFO mapred.JobClient:  map 77% reduce 13%
13/05/17 17:26:04 INFO mapred.JobClient:  map 78% reduce 13%
13/05/17 17:26:13 INFO mapred.JobClient:  map 79% reduce 13%
13/05/17 17:26:34 INFO mapred.JobClient:  map 79% reduce 16%
13/05/17 17:26:37 INFO mapred.JobClient:  map 80% reduce 18%
13/05/17 17:26:40 INFO mapred.JobClient:  map 80% reduce 21%
13/05/17 17:26:43 INFO mapred.JobClient:  map 80% reduce 25%
13/05/17 17:26:49 INFO mapred.JobClient:  map 81% reduce 25%
13/05/17 17:26:58 INFO mapred.JobClient:  map 81% reduce 26%
13/05/17 17:27:01 INFO mapred.JobClient:  map 82% reduce 26%
13/05/17 17:27:16 INFO mapred.JobClient:  map 83% reduce 26%
13/05/17 17:27:31 INFO mapred.JobClient:  map 84% reduce 26%
13/05/17 17:27:49 INFO mapred.JobClient:  map 85% reduce 26%
13/05/17 17:28:07 INFO mapred.JobClient:  map 86% reduce 26%
13/05/17 17:28:22 INFO mapred.JobClient:  map 87% reduce 26%
13/05/17 17:28:37 INFO mapred.JobClient:  map 88% reduce 26%
13/05/17 17:28:48 INFO mapred.JobClient:  map 89% reduce 26%
13/05/17 17:29:06 INFO mapred.JobClient:  map 90% reduce 26%
13/05/17 17:29:24 INFO mapred.JobClient:  map 91% reduce 26%
13/05/17 17:29:39 INFO mapred.JobClient:  map 92% reduce 26%
13/05/17 17:29:54 INFO mapred.JobClient:  map 93% reduce 26%
13/05/17 17:30:09 INFO mapred.JobClient:  map 94% reduce 26%
13/05/17 17:30:24 INFO mapred.JobClient:  map 95% reduce 26%
13/05/17 17:30:42 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 17:30:57 INFO mapred.JobClient:  map 97% reduce 26%
13/05/17 17:31:12 INFO mapred.JobClient:  map 98% reduce 26%
13/05/17 17:31:30 INFO mapred.JobClient:  map 99% reduce 26%
13/05/17 17:31:45 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 17:32:06 INFO mapred.JobClient:  map 100% reduce 47%
13/05/17 17:32:09 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 17:32:42 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 17:32:45 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 17:32:48 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 17:32:51 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 17:32:54 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 17:32:57 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 17:33:00 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 17:33:04 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 17:33:10 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 17:33:13 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 17:33:18 INFO mapred.JobClient: Job complete: job_201305171712_0001
13/05/17 17:33:18 INFO mapred.JobClient: Counters: 29
13/05/17 17:33:18 INFO mapred.JobClient:   Job Counters 
13/05/17 17:33:18 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/17 17:33:18 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=10034733
13/05/17 17:33:18 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 17:33:18 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 17:33:18 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 17:33:18 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 17:33:18 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=2819382
13/05/17 17:33:18 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 17:33:18 INFO mapred.JobClient:     Bytes Written=400
13/05/17 17:33:18 INFO mapred.JobClient:   FileSystemCounters
13/05/17 17:33:18 INFO mapred.JobClient:     FILE_BYTES_READ=2040552857
13/05/17 17:33:18 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 17:33:18 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2775950015
13/05/17 17:33:18 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=400
13/05/17 17:33:18 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 17:33:18 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 17:33:18 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 17:33:18 INFO mapred.JobClient:     Map output materialized bytes=735957967
13/05/17 17:33:18 INFO mapred.JobClient:     Map input records=120000000
13/05/17 17:33:18 INFO mapred.JobClient:     Reduce shuffle bytes=711425446
13/05/17 17:33:18 INFO mapred.JobClient:     Spilled Records=904949930
13/05/17 17:33:18 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 17:33:18 INFO mapred.JobClient:     CPU time spent (ms)=10093990
13/05/17 17:33:18 INFO mapred.JobClient:     Total committed heap usage (bytes)=33526841344
13/05/17 17:33:18 INFO mapred.JobClient:     Combine input records=0
13/05/17 17:33:18 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 17:33:18 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 17:33:18 INFO mapred.JobClient:     Reduce input groups=2
13/05/17 17:33:18 INFO mapred.JobClient:     Combine output records=0
13/05/17 17:33:18 INFO mapred.JobClient:     Physical memory (bytes) snapshot=22297976832
13/05/17 17:33:18 INFO mapred.JobClient:     Reduce output records=2
13/05/17 17:33:18 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658507223040
13/05/17 17:33:18 INFO mapred.JobClient:     Map output records=240000000
Execution Time 1091412 ms

real	18m12.171s
user	0m3.082s
sys	0m0.432s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3      3022  0.0  0.0 106084  1396 ?        Ss   17:33   0:00 bash -c ps aux | grep java
jmg3      3038  0.0  0.0 103232   844 ?        S    17:33   0:00 grep java
jmg3     17384  0.0  0.0  59072  3532 pts/0    S+   17:33   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     17390  0.0  0.0 106084  1400 ?        Ss   17:33   0:00 bash -c ps aux | grep java
jmg3     17406  0.0  0.0 103232   844 ?        S    17:33   0:00 grep java
