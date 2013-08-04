java: no process killed
java: no process killed
jmg3     22921  0.0  0.0 106084  1400 ?        Ss   18:44   0:00 bash -c ps aux | grep java
jmg3     22937  0.0  0.0 103232   840 ?        S    18:44   0:00 grep java
jmg3      6800  0.0  0.0  59072  3528 pts/0    S+   18:44   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      6806  0.0  0.0 106084  1400 ?        Ss   18:44   0:00 bash -c ps aux | grep java
jmg3      6822  0.0  0.0 103232   844 ?        S    18:44   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 24



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1298557.daman.davinci.rice.edu/logs
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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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
  <value>/tmp/1298557.daman.davinci.rice.edu/hadoop-${user.name}</value>
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

13/05/16 18:44:22 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 18:44:23 INFO util.GSet: VM type       = 64-bit
13/05/16 18:44:23 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 18:44:23 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 18:44:23 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 18:44:23 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 18:44:23 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 18:44:23 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 18:44:23 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 18:44:23 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 18:44:23 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 18:44:23 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 18:44:23 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 18:44:23 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

starting namenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/16 18:54:23 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 18:54:23 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 18:54:24 INFO mapred.JobClient: Running job: job_201305161844_0001
13/05/16 18:54:25 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 18:54:47 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 18:54:59 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 18:55:08 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 18:55:23 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 18:55:35 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 18:55:47 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 18:55:59 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 18:56:08 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 18:56:20 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 18:56:32 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 18:56:44 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 18:56:56 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 18:57:08 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 18:57:17 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 18:57:29 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 18:57:41 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 18:57:53 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 18:58:05 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 18:58:17 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 18:58:29 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 18:58:41 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 18:58:50 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 18:59:02 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 18:59:20 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 18:59:32 INFO mapred.JobClient:  map 24% reduce 2%
13/05/16 18:59:35 INFO mapred.JobClient:  map 24% reduce 3%
13/05/16 18:59:38 INFO mapred.JobClient:  map 24% reduce 5%
13/05/16 18:59:41 INFO mapred.JobClient:  map 25% reduce 6%
13/05/16 18:59:44 INFO mapred.JobClient:  map 25% reduce 7%
13/05/16 18:59:53 INFO mapred.JobClient:  map 26% reduce 7%
13/05/16 19:00:02 INFO mapred.JobClient:  map 27% reduce 7%
13/05/16 19:00:14 INFO mapred.JobClient:  map 28% reduce 7%
13/05/16 19:00:23 INFO mapred.JobClient:  map 29% reduce 7%
13/05/16 19:00:35 INFO mapred.JobClient:  map 30% reduce 7%
13/05/16 19:00:44 INFO mapred.JobClient:  map 31% reduce 7%
13/05/16 19:00:53 INFO mapred.JobClient:  map 32% reduce 7%
13/05/16 19:01:05 INFO mapred.JobClient:  map 33% reduce 7%
13/05/16 19:01:14 INFO mapred.JobClient:  map 34% reduce 7%
13/05/16 19:01:26 INFO mapred.JobClient:  map 35% reduce 7%
13/05/16 19:01:35 INFO mapred.JobClient:  map 36% reduce 7%
13/05/16 19:01:47 INFO mapred.JobClient:  map 37% reduce 7%
13/05/16 19:01:56 INFO mapred.JobClient:  map 38% reduce 7%
13/05/16 19:02:08 INFO mapred.JobClient:  map 39% reduce 7%
13/05/16 19:02:17 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 19:02:29 INFO mapred.JobClient:  map 41% reduce 7%
13/05/16 19:02:39 INFO mapred.JobClient:  map 42% reduce 7%
13/05/16 19:02:48 INFO mapred.JobClient:  map 43% reduce 7%
13/05/16 19:03:00 INFO mapred.JobClient:  map 44% reduce 7%
13/05/16 19:03:09 INFO mapred.JobClient:  map 45% reduce 7%
13/05/16 19:03:21 INFO mapred.JobClient:  map 46% reduce 7%
13/05/16 19:03:30 INFO mapred.JobClient:  map 47% reduce 7%
13/05/16 19:03:39 INFO mapred.JobClient:  map 47% reduce 8%
13/05/16 19:03:45 INFO mapred.JobClient:  map 48% reduce 8%
13/05/16 19:03:54 INFO mapred.JobClient:  map 48% reduce 9%
13/05/16 19:03:57 INFO mapred.JobClient:  map 48% reduce 10%
13/05/16 19:04:00 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 19:04:03 INFO mapred.JobClient:  map 49% reduce 15%
13/05/16 19:04:12 INFO mapred.JobClient:  map 50% reduce 15%
13/05/16 19:04:21 INFO mapred.JobClient:  map 51% reduce 15%
13/05/16 19:04:30 INFO mapred.JobClient:  map 52% reduce 15%
13/05/16 19:04:39 INFO mapred.JobClient:  map 53% reduce 15%
13/05/16 19:04:48 INFO mapred.JobClient:  map 54% reduce 15%
13/05/16 19:05:00 INFO mapred.JobClient:  map 55% reduce 15%
13/05/16 19:05:09 INFO mapred.JobClient:  map 56% reduce 15%
13/05/16 19:05:18 INFO mapred.JobClient:  map 57% reduce 15%
13/05/16 19:05:24 INFO mapred.JobClient:  map 58% reduce 15%
13/05/16 19:05:36 INFO mapred.JobClient:  map 59% reduce 15%
13/05/16 19:05:45 INFO mapred.JobClient:  map 60% reduce 15%
13/05/16 19:05:51 INFO mapred.JobClient:  map 61% reduce 15%
13/05/16 19:06:03 INFO mapred.JobClient:  map 62% reduce 15%
13/05/16 19:06:12 INFO mapred.JobClient:  map 63% reduce 15%
13/05/16 19:06:21 INFO mapred.JobClient:  map 64% reduce 15%
13/05/16 19:06:30 INFO mapred.JobClient:  map 65% reduce 15%
13/05/16 19:06:36 INFO mapred.JobClient:  map 66% reduce 15%
13/05/16 19:06:45 INFO mapred.JobClient:  map 67% reduce 15%
13/05/16 19:06:54 INFO mapred.JobClient:  map 68% reduce 15%
13/05/16 19:07:03 INFO mapred.JobClient:  map 69% reduce 15%
13/05/16 19:07:12 INFO mapred.JobClient:  map 70% reduce 15%
13/05/16 19:07:21 INFO mapred.JobClient:  map 71% reduce 15%
13/05/16 19:07:36 INFO mapred.JobClient:  map 71% reduce 16%
13/05/16 19:07:39 INFO mapred.JobClient:  map 72% reduce 16%
13/05/16 19:07:45 INFO mapred.JobClient:  map 72% reduce 20%
13/05/16 19:07:54 INFO mapred.JobClient:  map 73% reduce 21%
13/05/16 19:07:57 INFO mapred.JobClient:  map 73% reduce 22%
13/05/16 19:08:00 INFO mapred.JobClient:  map 74% reduce 23%
13/05/16 19:08:09 INFO mapred.JobClient:  map 75% reduce 23%
13/05/16 19:08:15 INFO mapred.JobClient:  map 76% reduce 23%
13/05/16 19:08:24 INFO mapred.JobClient:  map 77% reduce 23%
13/05/16 19:08:33 INFO mapred.JobClient:  map 78% reduce 23%
13/05/16 19:08:39 INFO mapred.JobClient:  map 79% reduce 23%
13/05/16 19:08:48 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 19:08:57 INFO mapred.JobClient:  map 81% reduce 23%
13/05/16 19:09:03 INFO mapred.JobClient:  map 82% reduce 23%
13/05/16 19:09:12 INFO mapred.JobClient:  map 83% reduce 23%
13/05/16 19:09:21 INFO mapred.JobClient:  map 84% reduce 23%
13/05/16 19:09:27 INFO mapred.JobClient:  map 85% reduce 23%
13/05/16 19:09:36 INFO mapred.JobClient:  map 86% reduce 23%
13/05/16 19:09:42 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 19:09:51 INFO mapred.JobClient:  map 88% reduce 23%
13/05/16 19:09:57 INFO mapred.JobClient:  map 89% reduce 23%
13/05/16 19:10:06 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 19:10:12 INFO mapred.JobClient:  map 91% reduce 23%
13/05/16 19:10:21 INFO mapred.JobClient:  map 92% reduce 23%
13/05/16 19:10:30 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 19:10:36 INFO mapred.JobClient:  map 94% reduce 23%
13/05/16 19:10:45 INFO mapred.JobClient:  map 95% reduce 23%
13/05/16 19:10:57 INFO mapred.JobClient:  map 95% reduce 24%
13/05/16 19:11:00 INFO mapred.JobClient:  map 96% reduce 25%
13/05/16 19:11:06 INFO mapred.JobClient:  map 96% reduce 28%
13/05/16 19:11:12 INFO mapred.JobClient:  map 96% reduce 29%
13/05/16 19:11:19 INFO mapred.JobClient:  map 96% reduce 30%
13/05/16 19:11:25 INFO mapred.JobClient:  map 96% reduce 31%
13/05/16 19:11:34 INFO mapred.JobClient:  map 97% reduce 31%
13/05/16 19:12:13 INFO mapred.JobClient:  map 98% reduce 31%
13/05/16 19:12:53 INFO mapred.JobClient:  map 99% reduce 31%
13/05/16 19:13:35 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 19:13:50 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 19:14:17 INFO mapred.JobClient:  map 100% reduce 41%
13/05/16 19:14:20 INFO mapred.JobClient:  map 100% reduce 59%
13/05/16 19:14:26 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 19:14:29 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 19:14:32 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 19:14:35 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 19:14:38 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 19:14:41 INFO mapred.JobClient:  map 100% reduce 75%
13/05/16 19:14:44 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 19:14:47 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 19:14:50 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 19:14:53 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 19:14:56 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 19:14:59 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 19:15:02 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 19:15:05 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 19:15:08 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 19:15:11 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 19:15:14 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 19:15:17 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 19:15:20 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 19:15:23 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 19:15:26 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 19:15:29 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 19:15:32 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 19:15:35 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 19:15:41 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 19:15:53 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 19:16:02 INFO mapred.JobClient: Job complete: job_201305161844_0001
13/05/16 19:16:02 INFO mapred.JobClient: Counters: 29
13/05/16 19:16:02 INFO mapred.JobClient:   Job Counters 
13/05/16 19:16:02 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 19:16:02 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=12158207
13/05/16 19:16:02 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 19:16:02 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 19:16:02 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 19:16:02 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 19:16:02 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=3939997
13/05/16 19:16:02 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 19:16:02 INFO mapred.JobClient:     Bytes Written=6495027576
13/05/16 19:16:02 INFO mapred.JobClient:   FileSystemCounters
13/05/16 19:16:02 INFO mapred.JobClient:     FILE_BYTES_READ=4785716282
13/05/16 19:16:02 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 19:16:02 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=6488431858
13/05/16 19:16:02 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027576
13/05/16 19:16:02 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 19:16:02 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 19:16:02 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 19:16:02 INFO mapred.JobClient:     Map output materialized bytes=1720113448
13/05/16 19:16:02 INFO mapred.JobClient:     Map input records=401920000
13/05/16 19:16:02 INFO mapred.JobClient:     Reduce shuffle bytes=1685794353
13/05/16 19:16:02 INFO mapred.JobClient:     Spilled Records=1515929852
13/05/16 19:16:02 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 19:16:02 INFO mapred.JobClient:     CPU time spent (ms)=12461980
13/05/16 19:16:02 INFO mapred.JobClient:     Total committed heap usage (bytes)=48880615424
13/05/16 19:16:02 INFO mapred.JobClient:     Combine input records=0
13/05/16 19:16:02 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 19:16:02 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 19:16:02 INFO mapred.JobClient:     Reduce input groups=401920000
13/05/16 19:16:02 INFO mapred.JobClient:     Combine output records=0
13/05/16 19:16:02 INFO mapred.JobClient:     Physical memory (bytes) snapshot=31040532480
13/05/16 19:16:02 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 19:16:02 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=1509696786432
13/05/16 19:16:02 INFO mapred.JobClient:     Map output records=401920000
Execution Time 1298767 ms

real	21m39.567s
user	0m3.399s
sys	0m0.448s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     27644  0.0  0.0 106084  1400 ?        Ss   19:16   0:00 bash -c ps aux | grep java
jmg3     27660  0.0  0.0 103232   840 ?        S    19:16   0:00 grep java
jmg3      8024  0.0  0.0  59072  3532 pts/0    S+   19:16   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      8030  0.0  0.0 106084  1400 ?        Ss   19:16   0:00 bash -c ps aux | grep java
jmg3      8046  0.0  0.0 103232   844 ?        S    19:16   0:00 grep java
java: no process killed
java: no process killed
jmg3     27706  0.0  0.0 106084  1396 ?        Ss   19:16   0:00 bash -c ps aux | grep java
jmg3     27722  0.0  0.0 103232   844 ?        S    19:16   0:00 grep java
jmg3      8112  0.0  0.0  59072  3528 pts/0    S+   19:16   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      8118  0.0  0.0 106084  1400 ?        Ss   19:16   0:00 bash -c ps aux | grep java
jmg3      8134  0.0  0.0 103232   844 ?        S    19:16   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 24



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1298557.daman.davinci.rice.edu/logs
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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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
  <value>/tmp/1298557.daman.davinci.rice.edu/hadoop-${user.name}</value>
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

13/05/16 19:16:22 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 19:16:22 INFO util.GSet: VM type       = 64-bit
13/05/16 19:16:22 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 19:16:22 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 19:16:22 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 19:16:22 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 19:16:22 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 19:16:22 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 19:16:22 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 19:16:22 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 19:16:22 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 19:16:22 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 19:16:22 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 19:16:22 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

starting namenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/16 19:24:25 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 19:24:25 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 19:24:25 INFO mapred.JobClient: Running job: job_201305161916_0001
13/05/16 19:24:26 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 19:24:50 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 19:25:02 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 19:25:11 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 19:25:26 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 19:25:38 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 19:25:50 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 19:26:02 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 19:26:11 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 19:26:23 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 19:26:35 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 19:26:47 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 19:26:59 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 19:27:11 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 19:27:20 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 19:27:32 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 19:27:44 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 19:27:56 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 19:28:08 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 19:28:20 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 19:28:32 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 19:28:44 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 19:28:53 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 19:29:08 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 19:29:23 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 19:29:32 INFO mapred.JobClient:  map 24% reduce 1%
13/05/16 19:29:35 INFO mapred.JobClient:  map 24% reduce 2%
13/05/16 19:29:38 INFO mapred.JobClient:  map 24% reduce 4%
13/05/16 19:29:41 INFO mapred.JobClient:  map 24% reduce 6%
13/05/16 19:29:44 INFO mapred.JobClient:  map 25% reduce 7%
13/05/16 19:29:56 INFO mapred.JobClient:  map 26% reduce 7%
13/05/16 19:30:05 INFO mapred.JobClient:  map 27% reduce 7%
13/05/16 19:30:17 INFO mapred.JobClient:  map 28% reduce 7%
13/05/16 19:30:26 INFO mapred.JobClient:  map 29% reduce 7%
13/05/16 19:30:38 INFO mapred.JobClient:  map 30% reduce 7%
13/05/16 19:30:46 INFO mapred.JobClient:  map 31% reduce 7%
13/05/16 19:30:58 INFO mapred.JobClient:  map 32% reduce 7%
13/05/16 19:31:07 INFO mapred.JobClient:  map 33% reduce 7%
13/05/16 19:31:16 INFO mapred.JobClient:  map 34% reduce 7%
13/05/16 19:31:28 INFO mapred.JobClient:  map 35% reduce 7%
13/05/16 19:31:40 INFO mapred.JobClient:  map 36% reduce 7%
13/05/16 19:31:49 INFO mapred.JobClient:  map 37% reduce 7%
13/05/16 19:31:58 INFO mapred.JobClient:  map 38% reduce 7%
13/05/16 19:32:07 INFO mapred.JobClient:  map 39% reduce 7%
13/05/16 19:32:19 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 19:32:28 INFO mapred.JobClient:  map 41% reduce 7%
13/05/16 19:32:40 INFO mapred.JobClient:  map 42% reduce 7%
13/05/16 19:32:49 INFO mapred.JobClient:  map 43% reduce 7%
13/05/16 19:32:58 INFO mapred.JobClient:  map 44% reduce 7%
13/05/16 19:33:10 INFO mapred.JobClient:  map 45% reduce 7%
13/05/16 19:33:19 INFO mapred.JobClient:  map 46% reduce 7%
13/05/16 19:33:28 INFO mapred.JobClient:  map 47% reduce 7%
13/05/16 19:33:46 INFO mapred.JobClient:  map 48% reduce 7%
13/05/16 19:33:49 INFO mapred.JobClient:  map 48% reduce 8%
13/05/16 19:33:52 INFO mapred.JobClient:  map 48% reduce 9%
13/05/16 19:33:58 INFO mapred.JobClient:  map 48% reduce 12%
13/05/16 19:34:04 INFO mapred.JobClient:  map 49% reduce 14%
13/05/16 19:34:08 INFO mapred.JobClient:  map 49% reduce 15%
13/05/16 19:34:14 INFO mapred.JobClient:  map 50% reduce 15%
13/05/16 19:34:23 INFO mapred.JobClient:  map 51% reduce 15%
13/05/16 19:34:32 INFO mapred.JobClient:  map 52% reduce 15%
13/05/16 19:34:41 INFO mapred.JobClient:  map 53% reduce 15%
13/05/16 19:34:50 INFO mapred.JobClient:  map 54% reduce 15%
13/05/16 19:35:02 INFO mapred.JobClient:  map 55% reduce 15%
13/05/16 19:35:11 INFO mapred.JobClient:  map 56% reduce 15%
13/05/16 19:35:20 INFO mapred.JobClient:  map 57% reduce 15%
13/05/16 19:35:26 INFO mapred.JobClient:  map 58% reduce 15%
13/05/16 19:35:38 INFO mapred.JobClient:  map 59% reduce 15%
13/05/16 19:35:44 INFO mapred.JobClient:  map 60% reduce 15%
13/05/16 19:35:53 INFO mapred.JobClient:  map 61% reduce 15%
13/05/16 19:36:02 INFO mapred.JobClient:  map 62% reduce 15%
13/05/16 19:36:11 INFO mapred.JobClient:  map 63% reduce 15%
13/05/16 19:36:20 INFO mapred.JobClient:  map 64% reduce 15%
13/05/16 19:36:29 INFO mapred.JobClient:  map 65% reduce 15%
13/05/16 19:36:38 INFO mapred.JobClient:  map 66% reduce 15%
13/05/16 19:36:47 INFO mapred.JobClient:  map 67% reduce 15%
13/05/16 19:36:56 INFO mapred.JobClient:  map 68% reduce 15%
13/05/16 19:37:05 INFO mapred.JobClient:  map 69% reduce 15%
13/05/16 19:37:14 INFO mapred.JobClient:  map 70% reduce 15%
13/05/16 19:37:23 INFO mapred.JobClient:  map 71% reduce 15%
13/05/16 19:37:38 INFO mapred.JobClient:  map 72% reduce 16%
13/05/16 19:37:41 INFO mapred.JobClient:  map 72% reduce 17%
13/05/16 19:37:47 INFO mapred.JobClient:  map 72% reduce 19%
13/05/16 19:37:50 INFO mapred.JobClient:  map 72% reduce 20%
13/05/16 19:37:53 INFO mapred.JobClient:  map 73% reduce 20%
13/05/16 19:37:56 INFO mapred.JobClient:  map 73% reduce 22%
13/05/16 19:38:02 INFO mapred.JobClient:  map 74% reduce 23%
13/05/16 19:38:08 INFO mapred.JobClient:  map 75% reduce 23%
13/05/16 19:38:14 INFO mapred.JobClient:  map 76% reduce 23%
13/05/16 19:38:23 INFO mapred.JobClient:  map 77% reduce 23%
13/05/16 19:38:32 INFO mapred.JobClient:  map 78% reduce 23%
13/05/16 19:38:38 INFO mapred.JobClient:  map 79% reduce 23%
13/05/16 19:38:47 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 19:38:53 INFO mapred.JobClient:  map 81% reduce 23%
13/05/16 19:39:02 INFO mapred.JobClient:  map 82% reduce 23%
13/05/16 19:39:11 INFO mapred.JobClient:  map 83% reduce 23%
13/05/16 19:39:20 INFO mapred.JobClient:  map 84% reduce 23%
13/05/16 19:39:26 INFO mapred.JobClient:  map 85% reduce 23%
13/05/16 19:39:35 INFO mapred.JobClient:  map 86% reduce 23%
13/05/16 19:39:44 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 19:39:50 INFO mapred.JobClient:  map 88% reduce 23%
13/05/16 19:39:59 INFO mapred.JobClient:  map 89% reduce 23%
13/05/16 19:40:05 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 19:40:14 INFO mapred.JobClient:  map 91% reduce 23%
13/05/16 19:40:20 INFO mapred.JobClient:  map 92% reduce 23%
13/05/16 19:40:29 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 19:40:35 INFO mapred.JobClient:  map 94% reduce 23%
13/05/16 19:40:44 INFO mapred.JobClient:  map 95% reduce 23%
13/05/16 19:40:56 INFO mapred.JobClient:  map 95% reduce 24%
13/05/16 19:40:59 INFO mapred.JobClient:  map 96% reduce 24%
13/05/16 19:41:05 INFO mapred.JobClient:  map 96% reduce 28%
13/05/16 19:41:08 INFO mapred.JobClient:  map 96% reduce 29%
13/05/16 19:41:20 INFO mapred.JobClient:  map 96% reduce 30%
13/05/16 19:41:23 INFO mapred.JobClient:  map 96% reduce 31%
13/05/16 19:41:35 INFO mapred.JobClient:  map 97% reduce 31%
13/05/16 19:42:14 INFO mapred.JobClient:  map 98% reduce 31%
13/05/16 19:42:53 INFO mapred.JobClient:  map 99% reduce 31%
13/05/16 19:43:32 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 19:43:48 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 19:44:18 INFO mapred.JobClient:  map 100% reduce 41%
13/05/16 19:44:21 INFO mapred.JobClient:  map 100% reduce 58%
13/05/16 19:44:24 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 19:44:27 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 19:44:30 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 19:44:33 INFO mapred.JobClient:  map 100% reduce 71%
13/05/16 19:44:36 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 19:44:39 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 19:44:42 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 19:44:45 INFO mapred.JobClient:  map 100% reduce 75%
13/05/16 19:44:48 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 19:44:51 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 19:44:54 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 19:44:57 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 19:45:03 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 19:45:06 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 19:45:09 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 19:45:12 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 19:45:15 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 19:45:21 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 19:45:24 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 19:45:27 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 19:45:33 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 19:45:36 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 19:45:39 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 19:45:42 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 19:45:45 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 19:45:51 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 19:46:00 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 19:46:09 INFO mapred.JobClient: Job complete: job_201305161916_0001
13/05/16 19:46:09 INFO mapred.JobClient: Counters: 29
13/05/16 19:46:09 INFO mapred.JobClient:   Job Counters 
13/05/16 19:46:09 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 19:46:09 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=12126648
13/05/16 19:46:09 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 19:46:09 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 19:46:09 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 19:46:09 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 19:46:09 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=3972325
13/05/16 19:46:09 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 19:46:09 INFO mapred.JobClient:     Bytes Written=6495027576
13/05/16 19:46:09 INFO mapred.JobClient:   FileSystemCounters
13/05/16 19:46:09 INFO mapred.JobClient:     FILE_BYTES_READ=4785716282
13/05/16 19:46:09 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 19:46:09 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=6488431858
13/05/16 19:46:09 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027576
13/05/16 19:46:09 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 19:46:09 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 19:46:09 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 19:46:09 INFO mapred.JobClient:     Map output materialized bytes=1720113448
13/05/16 19:46:09 INFO mapred.JobClient:     Map input records=401920000
13/05/16 19:46:09 INFO mapred.JobClient:     Reduce shuffle bytes=1685794353
13/05/16 19:46:09 INFO mapred.JobClient:     Spilled Records=1515929852
13/05/16 19:46:09 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 19:46:09 INFO mapred.JobClient:     CPU time spent (ms)=12437670
13/05/16 19:46:09 INFO mapred.JobClient:     Total committed heap usage (bytes)=49163337728
13/05/16 19:46:09 INFO mapred.JobClient:     Combine input records=0
13/05/16 19:46:09 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 19:46:09 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 19:46:09 INFO mapred.JobClient:     Reduce input groups=401920000
13/05/16 19:46:09 INFO mapred.JobClient:     Combine output records=0
13/05/16 19:46:09 INFO mapred.JobClient:     Physical memory (bytes) snapshot=31975923712
13/05/16 19:46:09 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 19:46:09 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=1509766000640
13/05/16 19:46:09 INFO mapred.JobClient:     Map output records=401920000
Execution Time 1303870 ms

real	21m44.638s
user	0m3.317s
sys	0m0.506s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     32399  0.0  0.0 106084  1400 ?        Ss   19:46   0:00 bash -c ps aux | grep java
jmg3     32415  0.0  0.0 103232   844 ?        S    19:46   0:00 grep java
jmg3      9311  0.0  0.0  59072  3528 pts/0    S+   19:46   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      9317  0.0  0.0 106084  1400 ?        Ss   19:46   0:00 bash -c ps aux | grep java
jmg3      9333  0.0  0.0 103232   840 ?        S    19:46   0:00 grep java
java: no process killed
java: no process killed
jmg3     32461  0.0  0.0 106084  1400 ?        Ss   19:46   0:00 bash -c ps aux | grep java
jmg3     32477  0.0  0.0 103232   840 ?        S    19:46   0:00 grep java
jmg3      9399  0.0  0.0  59072  3528 pts/0    S+   19:46   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      9405  0.0  0.0 106084  1396 ?        Ss   19:46   0:00 bash -c ps aux | grep java
jmg3      9421  0.0  0.0 103232   840 ?        S    19:46   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 24



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1298557.daman.davinci.rice.edu/logs
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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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
  <value>/tmp/1298557.daman.davinci.rice.edu/hadoop-${user.name}</value>
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

13/05/16 19:46:25 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 19:46:25 INFO util.GSet: VM type       = 64-bit
13/05/16 19:46:25 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 19:46:25 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 19:46:25 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 19:46:25 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 19:46:25 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 19:46:25 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 19:46:25 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 19:46:25 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 19:46:25 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 19:46:25 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 19:46:26 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 19:46:26 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

starting namenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/16 19:53:13 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 19:53:13 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 19:53:13 INFO mapred.JobClient: Running job: job_201305161946_0001
13/05/16 19:53:14 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 19:53:37 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 19:53:49 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 19:54:01 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 19:54:13 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 19:54:25 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 19:54:37 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 19:54:46 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 19:54:58 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 19:55:13 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 19:55:22 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 19:55:34 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 19:55:46 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 19:55:55 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 19:56:10 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 19:56:19 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 19:56:32 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 19:56:44 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 19:56:56 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 19:57:08 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 19:57:20 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 19:57:29 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 19:57:44 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 19:57:56 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 19:58:14 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 19:58:20 INFO mapred.JobClient:  map 24% reduce 1%
13/05/16 19:58:26 INFO mapred.JobClient:  map 24% reduce 3%
13/05/16 19:58:29 INFO mapred.JobClient:  map 24% reduce 4%
13/05/16 19:58:32 INFO mapred.JobClient:  map 25% reduce 6%
13/05/16 19:58:35 INFO mapred.JobClient:  map 25% reduce 7%
13/05/16 19:58:44 INFO mapred.JobClient:  map 26% reduce 7%
13/05/16 19:58:53 INFO mapred.JobClient:  map 27% reduce 7%
13/05/16 19:59:05 INFO mapred.JobClient:  map 28% reduce 7%
13/05/16 19:59:17 INFO mapred.JobClient:  map 29% reduce 7%
13/05/16 19:59:26 INFO mapred.JobClient:  map 30% reduce 7%
13/05/16 19:59:35 INFO mapred.JobClient:  map 31% reduce 7%
13/05/16 19:59:47 INFO mapred.JobClient:  map 32% reduce 7%
13/05/16 19:59:56 INFO mapred.JobClient:  map 33% reduce 7%
13/05/16 20:00:08 INFO mapred.JobClient:  map 34% reduce 7%
13/05/16 20:00:17 INFO mapred.JobClient:  map 35% reduce 7%
13/05/16 20:00:29 INFO mapred.JobClient:  map 36% reduce 7%
13/05/16 20:00:38 INFO mapred.JobClient:  map 37% reduce 7%
13/05/16 20:00:47 INFO mapred.JobClient:  map 38% reduce 7%
13/05/16 20:00:59 INFO mapred.JobClient:  map 39% reduce 7%
13/05/16 20:01:07 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 20:01:16 INFO mapred.JobClient:  map 41% reduce 7%
13/05/16 20:01:28 INFO mapred.JobClient:  map 42% reduce 7%
13/05/16 20:01:37 INFO mapred.JobClient:  map 43% reduce 7%
13/05/16 20:01:49 INFO mapred.JobClient:  map 44% reduce 7%
13/05/16 20:01:58 INFO mapred.JobClient:  map 45% reduce 7%
13/05/16 20:02:07 INFO mapred.JobClient:  map 46% reduce 7%
13/05/16 20:02:19 INFO mapred.JobClient:  map 47% reduce 7%
13/05/16 20:02:31 INFO mapred.JobClient:  map 47% reduce 8%
13/05/16 20:02:34 INFO mapred.JobClient:  map 48% reduce 8%
13/05/16 20:02:40 INFO mapred.JobClient:  map 48% reduce 9%
13/05/16 20:02:46 INFO mapred.JobClient:  map 48% reduce 10%
13/05/16 20:02:49 INFO mapred.JobClient:  map 48% reduce 11%
13/05/16 20:02:52 INFO mapred.JobClient:  map 49% reduce 14%
13/05/16 20:02:55 INFO mapred.JobClient:  map 49% reduce 15%
13/05/16 20:03:01 INFO mapred.JobClient:  map 50% reduce 15%
13/05/16 20:03:10 INFO mapred.JobClient:  map 51% reduce 15%
13/05/16 20:03:19 INFO mapred.JobClient:  map 52% reduce 15%
13/05/16 20:03:28 INFO mapred.JobClient:  map 53% reduce 15%
13/05/16 20:03:40 INFO mapred.JobClient:  map 54% reduce 15%
13/05/16 20:03:49 INFO mapred.JobClient:  map 55% reduce 15%
13/05/16 20:03:58 INFO mapred.JobClient:  map 56% reduce 15%
13/05/16 20:04:07 INFO mapred.JobClient:  map 57% reduce 15%
13/05/16 20:04:16 INFO mapred.JobClient:  map 58% reduce 15%
13/05/16 20:04:25 INFO mapred.JobClient:  map 59% reduce 15%
13/05/16 20:04:34 INFO mapred.JobClient:  map 60% reduce 15%
13/05/16 20:04:43 INFO mapred.JobClient:  map 61% reduce 15%
13/05/16 20:04:52 INFO mapred.JobClient:  map 62% reduce 15%
13/05/16 20:05:01 INFO mapred.JobClient:  map 63% reduce 15%
13/05/16 20:05:10 INFO mapred.JobClient:  map 64% reduce 15%
13/05/16 20:05:19 INFO mapred.JobClient:  map 65% reduce 15%
13/05/16 20:05:28 INFO mapred.JobClient:  map 66% reduce 15%
13/05/16 20:05:34 INFO mapred.JobClient:  map 67% reduce 15%
13/05/16 20:05:43 INFO mapred.JobClient:  map 68% reduce 15%
13/05/16 20:05:52 INFO mapred.JobClient:  map 69% reduce 15%
13/05/16 20:06:01 INFO mapred.JobClient:  map 70% reduce 15%
13/05/16 20:06:10 INFO mapred.JobClient:  map 71% reduce 15%
13/05/16 20:06:23 INFO mapred.JobClient:  map 71% reduce 16%
13/05/16 20:06:29 INFO mapred.JobClient:  map 72% reduce 17%
13/05/16 20:06:32 INFO mapred.JobClient:  map 72% reduce 18%
13/05/16 20:06:35 INFO mapred.JobClient:  map 72% reduce 19%
13/05/16 20:06:38 INFO mapred.JobClient:  map 72% reduce 20%
13/05/16 20:06:41 INFO mapred.JobClient:  map 73% reduce 21%
13/05/16 20:06:44 INFO mapred.JobClient:  map 73% reduce 22%
13/05/16 20:06:50 INFO mapred.JobClient:  map 74% reduce 23%
13/05/16 20:06:56 INFO mapred.JobClient:  map 75% reduce 23%
13/05/16 20:07:05 INFO mapred.JobClient:  map 76% reduce 23%
13/05/16 20:07:14 INFO mapred.JobClient:  map 77% reduce 23%
13/05/16 20:07:20 INFO mapred.JobClient:  map 78% reduce 23%
13/05/16 20:07:29 INFO mapred.JobClient:  map 79% reduce 23%
13/05/16 20:07:35 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 20:07:44 INFO mapred.JobClient:  map 81% reduce 23%
13/05/16 20:07:53 INFO mapred.JobClient:  map 82% reduce 23%
13/05/16 20:07:59 INFO mapred.JobClient:  map 83% reduce 23%
13/05/16 20:08:08 INFO mapred.JobClient:  map 84% reduce 23%
13/05/16 20:08:17 INFO mapred.JobClient:  map 85% reduce 23%
13/05/16 20:08:23 INFO mapred.JobClient:  map 86% reduce 23%
13/05/16 20:08:32 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 20:08:38 INFO mapred.JobClient:  map 88% reduce 23%
13/05/16 20:08:47 INFO mapred.JobClient:  map 89% reduce 23%
13/05/16 20:08:56 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 20:09:02 INFO mapred.JobClient:  map 91% reduce 23%
13/05/16 20:09:11 INFO mapred.JobClient:  map 92% reduce 23%
13/05/16 20:09:17 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 20:09:26 INFO mapred.JobClient:  map 94% reduce 23%
13/05/16 20:09:32 INFO mapred.JobClient:  map 95% reduce 23%
13/05/16 20:09:50 INFO mapred.JobClient:  map 96% reduce 24%
13/05/16 20:09:53 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 20:09:56 INFO mapred.JobClient:  map 96% reduce 27%
13/05/16 20:09:59 INFO mapred.JobClient:  map 96% reduce 28%
13/05/16 20:10:02 INFO mapred.JobClient:  map 96% reduce 29%
13/05/16 20:10:11 INFO mapred.JobClient:  map 96% reduce 30%
13/05/16 20:10:14 INFO mapred.JobClient:  map 96% reduce 31%
13/05/16 20:10:23 INFO mapred.JobClient:  map 97% reduce 31%
13/05/16 20:11:02 INFO mapred.JobClient:  map 98% reduce 31%
13/05/16 20:11:41 INFO mapred.JobClient:  map 99% reduce 31%
13/05/16 20:12:20 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 20:12:41 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 20:13:08 INFO mapred.JobClient:  map 100% reduce 50%
13/05/16 20:13:11 INFO mapred.JobClient:  map 100% reduce 59%
13/05/16 20:13:14 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 20:13:17 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 20:13:20 INFO mapred.JobClient:  map 100% reduce 71%
13/05/16 20:13:23 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 20:13:26 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 20:13:29 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 20:13:32 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 20:13:35 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 20:13:38 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 20:13:41 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 20:13:44 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 20:13:47 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 20:13:50 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 20:13:53 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 20:13:56 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 20:14:02 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 20:14:05 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 20:14:08 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 20:14:11 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 20:14:14 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 20:14:17 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 20:14:20 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 20:14:23 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 20:14:26 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 20:14:33 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 20:14:36 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 20:14:42 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 20:14:45 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 20:14:54 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 20:15:02 INFO mapred.JobClient: Job complete: job_201305161946_0001
13/05/16 20:15:02 INFO mapred.JobClient: Counters: 29
13/05/16 20:15:02 INFO mapred.JobClient:   Job Counters 
13/05/16 20:15:02 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 20:15:02 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=12134511
13/05/16 20:15:02 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 20:15:02 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 20:15:02 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 20:15:02 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 20:15:02 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=3976521
13/05/16 20:15:02 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 20:15:02 INFO mapred.JobClient:     Bytes Written=6495027576
13/05/16 20:15:02 INFO mapred.JobClient:   FileSystemCounters
13/05/16 20:15:02 INFO mapred.JobClient:     FILE_BYTES_READ=4785716282
13/05/16 20:15:02 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 20:15:02 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=6488431858
13/05/16 20:15:02 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027576
13/05/16 20:15:02 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 20:15:02 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 20:15:02 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 20:15:02 INFO mapred.JobClient:     Map output materialized bytes=1720113448
13/05/16 20:15:02 INFO mapred.JobClient:     Map input records=401920000
13/05/16 20:15:02 INFO mapred.JobClient:     Reduce shuffle bytes=1685794353
13/05/16 20:15:02 INFO mapred.JobClient:     Spilled Records=1515929852
13/05/16 20:15:02 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 20:15:02 INFO mapred.JobClient:     CPU time spent (ms)=12441280
13/05/16 20:15:02 INFO mapred.JobClient:     Total committed heap usage (bytes)=48809574400
13/05/16 20:15:02 INFO mapred.JobClient:     Combine input records=0
13/05/16 20:15:02 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 20:15:02 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 20:15:02 INFO mapred.JobClient:     Reduce input groups=401920000
13/05/16 20:15:02 INFO mapred.JobClient:     Combine output records=0
13/05/16 20:15:02 INFO mapred.JobClient:     Physical memory (bytes) snapshot=31378579456
13/05/16 20:15:02 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 20:15:02 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=1510033383424
13/05/16 20:15:02 INFO mapred.JobClient:     Map output records=401920000
Execution Time 1309870 ms

real	21m50.645s
user	0m3.393s
sys	0m0.481s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3      4915  0.0  0.0 106084  1396 ?        Ss   20:15   0:00 bash -c ps aux | grep java
jmg3      4931  0.0  0.0 103232   840 ?        S    20:15   0:00 grep java
jmg3     10622  0.0  0.0  59072  3528 pts/0    S+   20:15   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     10628  0.0  0.0 106084  1400 ?        Ss   20:15   0:00 bash -c ps aux | grep java
jmg3     10644  0.0  0.0 103232   844 ?        S    20:15   0:00 grep java
java: no process killed
java: no process killed
jmg3      4977  0.0  0.0 106084  1404 ?        Ss   20:15   0:00 bash -c ps aux | grep java
jmg3      4993  0.0  0.0 103232   840 ?        S    20:15   0:00 grep java
jmg3     10710  0.0  0.0  59072  3528 pts/0    S+   20:15   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     10716  0.0  0.0 106084  1400 ?        Ss   20:15   0:00 bash -c ps aux | grep java
jmg3     10732  0.0  0.0 103232   844 ?        S    20:15   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 24



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1298557.daman.davinci.rice.edu/logs
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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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
  <value>/tmp/1298557.daman.davinci.rice.edu/hadoop-${user.name}</value>
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

13/05/16 20:15:08 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 20:15:08 INFO util.GSet: VM type       = 64-bit
13/05/16 20:15:08 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 20:15:08 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 20:15:08 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 20:15:08 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 20:15:08 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 20:15:08 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 20:15:08 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 20:15:08 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 20:15:08 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 20:15:08 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 20:15:09 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 20:15:09 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

starting namenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/16 20:24:04 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 20:24:04 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 20:24:05 INFO mapred.JobClient: Running job: job_201305162015_0001
13/05/16 20:24:06 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 20:24:30 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 20:24:42 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 20:24:51 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 20:25:06 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 20:25:18 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 20:25:30 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 20:25:42 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 20:25:54 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 20:26:03 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 20:26:15 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 20:26:27 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 20:26:39 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 20:26:51 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 20:27:00 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 20:27:15 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 20:27:24 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 20:27:36 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 20:27:48 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 20:28:00 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 20:28:12 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 20:28:24 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 20:28:36 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 20:28:48 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 20:29:06 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 20:29:12 INFO mapred.JobClient:  map 24% reduce 1%
13/05/16 20:29:15 INFO mapred.JobClient:  map 24% reduce 2%
13/05/16 20:29:18 INFO mapred.JobClient:  map 24% reduce 3%
13/05/16 20:29:21 INFO mapred.JobClient:  map 24% reduce 4%
13/05/16 20:29:24 INFO mapred.JobClient:  map 25% reduce 5%
13/05/16 20:29:27 INFO mapred.JobClient:  map 25% reduce 6%
13/05/16 20:29:30 INFO mapred.JobClient:  map 25% reduce 7%
13/05/16 20:29:36 INFO mapred.JobClient:  map 26% reduce 7%
13/05/16 20:29:45 INFO mapred.JobClient:  map 27% reduce 7%
13/05/16 20:29:54 INFO mapred.JobClient:  map 28% reduce 7%
13/05/16 20:30:06 INFO mapred.JobClient:  map 29% reduce 7%
13/05/16 20:30:18 INFO mapred.JobClient:  map 30% reduce 7%
13/05/16 20:30:27 INFO mapred.JobClient:  map 31% reduce 7%
13/05/16 20:30:39 INFO mapred.JobClient:  map 32% reduce 7%
13/05/16 20:30:48 INFO mapred.JobClient:  map 33% reduce 7%
13/05/16 20:31:00 INFO mapred.JobClient:  map 34% reduce 7%
13/05/16 20:31:09 INFO mapred.JobClient:  map 35% reduce 7%
13/05/16 20:31:21 INFO mapred.JobClient:  map 36% reduce 7%
13/05/16 20:31:30 INFO mapred.JobClient:  map 37% reduce 7%
13/05/16 20:31:39 INFO mapred.JobClient:  map 38% reduce 7%
13/05/16 20:31:51 INFO mapred.JobClient:  map 39% reduce 7%
13/05/16 20:32:00 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 20:32:09 INFO mapred.JobClient:  map 41% reduce 7%
13/05/16 20:32:21 INFO mapred.JobClient:  map 42% reduce 7%
13/05/16 20:32:30 INFO mapred.JobClient:  map 43% reduce 7%
13/05/16 20:32:39 INFO mapred.JobClient:  map 44% reduce 7%
13/05/16 20:32:51 INFO mapred.JobClient:  map 45% reduce 7%
13/05/16 20:33:03 INFO mapred.JobClient:  map 46% reduce 7%
13/05/16 20:33:09 INFO mapred.JobClient:  map 47% reduce 7%
13/05/16 20:33:27 INFO mapred.JobClient:  map 47% reduce 8%
13/05/16 20:33:30 INFO mapred.JobClient:  map 48% reduce 8%
13/05/16 20:33:36 INFO mapred.JobClient:  map 48% reduce 10%
13/05/16 20:33:42 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 20:33:45 INFO mapred.JobClient:  map 49% reduce 14%
13/05/16 20:33:48 INFO mapred.JobClient:  map 49% reduce 15%
13/05/16 20:33:54 INFO mapred.JobClient:  map 50% reduce 15%
13/05/16 20:34:03 INFO mapred.JobClient:  map 51% reduce 15%
13/05/16 20:34:12 INFO mapred.JobClient:  map 52% reduce 15%
13/05/16 20:34:21 INFO mapred.JobClient:  map 53% reduce 15%
13/05/16 20:34:30 INFO mapred.JobClient:  map 54% reduce 15%
13/05/16 20:34:39 INFO mapred.JobClient:  map 55% reduce 15%
13/05/16 20:34:48 INFO mapred.JobClient:  map 56% reduce 15%
13/05/16 20:34:57 INFO mapred.JobClient:  map 57% reduce 15%
13/05/16 20:35:06 INFO mapred.JobClient:  map 58% reduce 15%
13/05/16 20:35:15 INFO mapred.JobClient:  map 59% reduce 15%
13/05/16 20:35:24 INFO mapred.JobClient:  map 60% reduce 15%
13/05/16 20:35:33 INFO mapred.JobClient:  map 61% reduce 15%
13/05/16 20:35:42 INFO mapred.JobClient:  map 62% reduce 15%
13/05/16 20:35:51 INFO mapred.JobClient:  map 63% reduce 15%
13/05/16 20:36:03 INFO mapred.JobClient:  map 64% reduce 15%
13/05/16 20:36:09 INFO mapred.JobClient:  map 65% reduce 15%
13/05/16 20:36:18 INFO mapred.JobClient:  map 66% reduce 15%
13/05/16 20:36:27 INFO mapred.JobClient:  map 67% reduce 15%
13/05/16 20:36:36 INFO mapred.JobClient:  map 68% reduce 15%
13/05/16 20:36:45 INFO mapred.JobClient:  map 69% reduce 15%
13/05/16 20:36:54 INFO mapred.JobClient:  map 70% reduce 15%
13/05/16 20:37:03 INFO mapred.JobClient:  map 71% reduce 15%
13/05/16 20:37:09 INFO mapred.JobClient:  map 71% reduce 16%
13/05/16 20:37:18 INFO mapred.JobClient:  map 72% reduce 16%
13/05/16 20:37:21 INFO mapred.JobClient:  map 72% reduce 17%
13/05/16 20:37:24 INFO mapred.JobClient:  map 72% reduce 18%
13/05/16 20:37:27 INFO mapred.JobClient:  map 72% reduce 19%
13/05/16 20:37:30 INFO mapred.JobClient:  map 72% reduce 20%
13/05/16 20:37:33 INFO mapred.JobClient:  map 73% reduce 21%
13/05/16 20:37:36 INFO mapred.JobClient:  map 73% reduce 22%
13/05/16 20:37:39 INFO mapred.JobClient:  map 73% reduce 23%
13/05/16 20:37:42 INFO mapred.JobClient:  map 74% reduce 23%
13/05/16 20:37:48 INFO mapred.JobClient:  map 75% reduce 23%
13/05/16 20:37:57 INFO mapred.JobClient:  map 76% reduce 23%
13/05/16 20:38:03 INFO mapred.JobClient:  map 77% reduce 23%
13/05/16 20:38:12 INFO mapred.JobClient:  map 78% reduce 23%
13/05/16 20:38:18 INFO mapred.JobClient:  map 79% reduce 23%
13/05/16 20:38:27 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 20:38:36 INFO mapred.JobClient:  map 81% reduce 23%
13/05/16 20:38:42 INFO mapred.JobClient:  map 82% reduce 23%
13/05/16 20:38:51 INFO mapred.JobClient:  map 83% reduce 23%
13/05/16 20:39:00 INFO mapred.JobClient:  map 84% reduce 23%
13/05/16 20:39:06 INFO mapred.JobClient:  map 85% reduce 23%
13/05/16 20:39:15 INFO mapred.JobClient:  map 86% reduce 23%
13/05/16 20:39:24 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 20:39:30 INFO mapred.JobClient:  map 88% reduce 23%
13/05/16 20:39:39 INFO mapred.JobClient:  map 89% reduce 23%
13/05/16 20:39:45 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 20:39:54 INFO mapred.JobClient:  map 91% reduce 23%
13/05/16 20:40:00 INFO mapred.JobClient:  map 92% reduce 23%
13/05/16 20:40:09 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 20:40:15 INFO mapred.JobClient:  map 94% reduce 23%
13/05/16 20:40:24 INFO mapred.JobClient:  map 95% reduce 23%
13/05/16 20:40:42 INFO mapred.JobClient:  map 96% reduce 24%
13/05/16 20:40:45 INFO mapred.JobClient:  map 96% reduce 28%
13/05/16 20:40:48 INFO mapred.JobClient:  map 96% reduce 29%
13/05/16 20:40:57 INFO mapred.JobClient:  map 96% reduce 30%
13/05/16 20:41:03 INFO mapred.JobClient:  map 96% reduce 31%
13/05/16 20:41:18 INFO mapred.JobClient:  map 97% reduce 31%
13/05/16 20:41:57 INFO mapred.JobClient:  map 98% reduce 31%
13/05/16 20:42:33 INFO mapred.JobClient:  map 99% reduce 31%
13/05/16 20:43:16 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 20:43:31 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 20:44:01 INFO mapred.JobClient:  map 100% reduce 50%
13/05/16 20:44:04 INFO mapred.JobClient:  map 100% reduce 59%
13/05/16 20:44:07 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 20:44:10 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 20:44:13 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 20:44:21 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 20:44:24 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 20:44:27 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 20:44:30 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 20:44:33 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 20:44:36 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 20:44:39 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 20:44:42 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 20:44:45 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 20:44:48 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 20:44:51 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 20:44:54 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 20:44:57 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 20:45:00 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 20:45:03 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 20:45:06 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 20:45:12 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 20:45:15 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 20:45:18 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 20:45:21 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 20:45:24 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 20:45:27 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 20:45:36 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 20:45:41 INFO mapred.JobClient: Job complete: job_201305162015_0001
13/05/16 20:45:41 INFO mapred.JobClient: Counters: 29
13/05/16 20:45:41 INFO mapred.JobClient:   Job Counters 
13/05/16 20:45:41 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 20:45:41 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=12118656
13/05/16 20:45:41 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 20:45:41 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 20:45:41 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 20:45:41 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 20:45:41 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=3938180
13/05/16 20:45:41 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 20:45:41 INFO mapred.JobClient:     Bytes Written=6495027576
13/05/16 20:45:41 INFO mapred.JobClient:   FileSystemCounters
13/05/16 20:45:41 INFO mapred.JobClient:     FILE_BYTES_READ=4785716282
13/05/16 20:45:41 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 20:45:41 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=6488431858
13/05/16 20:45:41 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027576
13/05/16 20:45:41 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 20:45:41 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 20:45:41 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 20:45:41 INFO mapred.JobClient:     Map output materialized bytes=1720113448
13/05/16 20:45:41 INFO mapred.JobClient:     Map input records=401920000
13/05/16 20:45:41 INFO mapred.JobClient:     Reduce shuffle bytes=1685794353
13/05/16 20:45:41 INFO mapred.JobClient:     Spilled Records=1515929852
13/05/16 20:45:41 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 20:45:41 INFO mapred.JobClient:     CPU time spent (ms)=12436950
13/05/16 20:45:41 INFO mapred.JobClient:     Total committed heap usage (bytes)=48147333120
13/05/16 20:45:41 INFO mapred.JobClient:     Combine input records=0
13/05/16 20:45:41 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 20:45:41 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 20:45:41 INFO mapred.JobClient:     Reduce input groups=401920000
13/05/16 20:45:41 INFO mapred.JobClient:     Combine output records=0
13/05/16 20:45:41 INFO mapred.JobClient:     Physical memory (bytes) snapshot=31344816128
13/05/16 20:45:41 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 20:45:41 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=1509897060352
13/05/16 20:45:41 INFO mapred.JobClient:     Map output records=401920000
Execution Time 1296855 ms

real	21m37.626s
user	0m3.397s
sys	0m0.464s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3      9681  0.0  0.0 106084  1396 ?        Ss   20:45   0:00 bash -c ps aux | grep java
jmg3      9697  0.0  0.0 103232   844 ?        S    20:45   0:00 grep java
jmg3     11914  0.0  0.0  59072  3524 pts/0    S+   20:45   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     11920  0.0  0.0 106084  1400 ?        Ss   20:45   0:00 bash -c ps aux | grep java
jmg3     11936  0.0  0.0 103232   844 ?        S    20:45   0:00 grep java
java: no process killed
java: no process killed
jmg3      9743  0.0  0.0 106084  1400 ?        Ss   20:45   0:00 bash -c ps aux | grep java
jmg3      9759  0.0  0.0 103232   844 ?        S    20:45   0:00 grep java
jmg3     12002  0.0  0.0  59072  3532 pts/0    S+   20:45   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     12008  0.0  0.0 106084  1404 ?        Ss   20:45   0:00 bash -c ps aux | grep java
jmg3     12024  0.0  0.0 103232   844 ?        S    20:45   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 24



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1298557.daman.davinci.rice.edu/logs
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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=0 -Dopencl.mapper.groups.cpu=0 -Dopencl.mapper.threadsPerGroup.gpu=0 -Dopencl.mapper.threadsPerGroup.cpu=0 -Dopencl.mapper.buffers.gpu=0 -Dopencl.mapper.buffers.cpu=0 -Dopencl.mapper.bufferSize.gpu=0 -Dopencl.mapper.bufferSize.cpu=0 -Dopencl.reducer.groups.gpu=0 -Dopencl.reducer.groups.cpu=0 -Dopencl.reducer.threadsPerGroup.gpu=0 -Dopencl.reducer.threadsPerGroup.cpu=0 -Dopencl.reducer.buffers.gpu=0 -Dopencl.reducer.buffers.cpu=0 -Dopencl.reducer.bufferSize.gpu=0 -Dopencl.reducer.bufferSize.cpu=0</value>
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
  <value>/tmp/1298557.daman.davinci.rice.edu/hadoop-${user.name}</value>
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

13/05/16 20:45:52 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 20:45:52 INFO util.GSet: VM type       = 64-bit
13/05/16 20:45:52 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 20:45:52 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 20:45:52 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 20:45:52 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 20:45:52 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 20:45:52 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 20:45:52 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 20:45:52 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 20:45:52 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 20:45:52 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 20:45:53 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 20:45:53 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

starting namenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-015.davinci.rice.edu.out
gpu-012: starting datanode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-012.davinci.rice.edu.out
gpu-015: starting secondarynamenode, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-015.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-015.davinci.rice.edu.out
gpu-012: starting tasktracker, logging to /tmp/1298557.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-012.davinci.rice.edu.out
gpu-012: Max num map slots is 12
Completed start all
Warning: $HADOOP_HOME is deprecated.

Warning: $HADOOP_HOME is deprecated.

13/05/16 20:52:18 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 20:52:19 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 20:52:19 INFO mapred.JobClient: Running job: job_201305162045_0001
13/05/16 20:52:20 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 20:52:43 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 20:52:55 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 20:53:04 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 20:53:19 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 20:53:31 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 20:53:43 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 20:53:55 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 20:54:04 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 20:54:16 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 20:54:28 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 20:54:40 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 20:54:52 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 20:55:04 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 20:55:16 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 20:55:25 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 20:55:37 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 20:55:49 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 20:56:01 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 20:56:13 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 20:56:25 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 20:56:37 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 20:56:49 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 20:57:01 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 20:57:16 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 20:57:25 INFO mapred.JobClient:  map 24% reduce 1%
13/05/16 20:57:28 INFO mapred.JobClient:  map 24% reduce 2%
13/05/16 20:57:31 INFO mapred.JobClient:  map 24% reduce 3%
13/05/16 20:57:34 INFO mapred.JobClient:  map 24% reduce 5%
13/05/16 20:57:37 INFO mapred.JobClient:  map 25% reduce 7%
13/05/16 20:57:40 INFO mapred.JobClient:  map 25% reduce 8%
13/05/16 20:57:49 INFO mapred.JobClient:  map 26% reduce 8%
13/05/16 20:57:58 INFO mapred.JobClient:  map 27% reduce 8%
13/05/16 20:58:10 INFO mapred.JobClient:  map 28% reduce 8%
13/05/16 20:58:22 INFO mapred.JobClient:  map 29% reduce 8%
13/05/16 20:58:31 INFO mapred.JobClient:  map 30% reduce 8%
13/05/16 20:58:41 INFO mapred.JobClient:  map 31% reduce 8%
13/05/16 20:58:53 INFO mapred.JobClient:  map 32% reduce 8%
13/05/16 20:59:02 INFO mapred.JobClient:  map 33% reduce 8%
13/05/16 20:59:14 INFO mapred.JobClient:  map 34% reduce 8%
13/05/16 20:59:23 INFO mapred.JobClient:  map 35% reduce 8%
13/05/16 20:59:35 INFO mapred.JobClient:  map 36% reduce 8%
13/05/16 20:59:44 INFO mapred.JobClient:  map 37% reduce 8%
13/05/16 20:59:53 INFO mapred.JobClient:  map 38% reduce 8%
13/05/16 21:00:05 INFO mapred.JobClient:  map 39% reduce 8%
13/05/16 21:00:14 INFO mapred.JobClient:  map 40% reduce 8%
13/05/16 21:00:26 INFO mapred.JobClient:  map 41% reduce 8%
13/05/16 21:00:35 INFO mapred.JobClient:  map 42% reduce 8%
13/05/16 21:00:44 INFO mapred.JobClient:  map 43% reduce 8%
13/05/16 21:00:56 INFO mapred.JobClient:  map 44% reduce 8%
13/05/16 21:01:05 INFO mapred.JobClient:  map 45% reduce 8%
13/05/16 21:01:14 INFO mapred.JobClient:  map 46% reduce 8%
13/05/16 21:01:26 INFO mapred.JobClient:  map 47% reduce 8%
13/05/16 21:01:44 INFO mapred.JobClient:  map 48% reduce 8%
13/05/16 21:01:47 INFO mapred.JobClient:  map 48% reduce 9%
13/05/16 21:01:50 INFO mapred.JobClient:  map 48% reduce 10%
13/05/16 21:01:53 INFO mapred.JobClient:  map 48% reduce 12%
13/05/16 21:01:56 INFO mapred.JobClient:  map 48% reduce 14%
13/05/16 21:01:59 INFO mapred.JobClient:  map 49% reduce 15%
13/05/16 21:02:05 INFO mapred.JobClient:  map 49% reduce 16%
13/05/16 21:02:08 INFO mapred.JobClient:  map 50% reduce 16%
13/05/16 21:02:17 INFO mapred.JobClient:  map 51% reduce 16%
13/05/16 21:02:26 INFO mapred.JobClient:  map 52% reduce 16%
13/05/16 21:02:35 INFO mapred.JobClient:  map 53% reduce 16%
13/05/16 21:02:44 INFO mapred.JobClient:  map 54% reduce 16%
13/05/16 21:02:53 INFO mapred.JobClient:  map 55% reduce 16%
13/05/16 21:03:05 INFO mapred.JobClient:  map 56% reduce 16%
13/05/16 21:03:14 INFO mapred.JobClient:  map 57% reduce 16%
13/05/16 21:03:23 INFO mapred.JobClient:  map 58% reduce 16%
13/05/16 21:03:32 INFO mapred.JobClient:  map 59% reduce 16%
13/05/16 21:03:41 INFO mapred.JobClient:  map 60% reduce 16%
13/05/16 21:03:47 INFO mapred.JobClient:  map 61% reduce 16%
13/05/16 21:03:56 INFO mapred.JobClient:  map 62% reduce 16%
13/05/16 21:04:05 INFO mapred.JobClient:  map 63% reduce 16%
13/05/16 21:04:14 INFO mapred.JobClient:  map 64% reduce 16%
13/05/16 21:04:23 INFO mapred.JobClient:  map 65% reduce 16%
13/05/16 21:04:32 INFO mapred.JobClient:  map 66% reduce 16%
13/05/16 21:04:41 INFO mapred.JobClient:  map 67% reduce 16%
13/05/16 21:04:50 INFO mapred.JobClient:  map 68% reduce 16%
13/05/16 21:04:59 INFO mapred.JobClient:  map 69% reduce 16%
13/05/16 21:05:08 INFO mapred.JobClient:  map 70% reduce 16%
13/05/16 21:05:17 INFO mapred.JobClient:  map 71% reduce 16%
13/05/16 21:05:32 INFO mapred.JobClient:  map 72% reduce 16%
13/05/16 21:05:35 INFO mapred.JobClient:  map 72% reduce 17%
13/05/16 21:05:38 INFO mapred.JobClient:  map 72% reduce 19%
13/05/16 21:05:44 INFO mapred.JobClient:  map 72% reduce 20%
13/05/16 21:05:47 INFO mapred.JobClient:  map 73% reduce 21%
13/05/16 21:05:53 INFO mapred.JobClient:  map 73% reduce 22%
13/05/16 21:05:56 INFO mapred.JobClient:  map 74% reduce 23%
13/05/16 21:06:05 INFO mapred.JobClient:  map 75% reduce 23%
13/05/16 21:06:15 INFO mapred.JobClient:  map 76% reduce 23%
13/05/16 21:06:21 INFO mapred.JobClient:  map 77% reduce 23%
13/05/16 21:06:30 INFO mapred.JobClient:  map 78% reduce 23%
13/05/16 21:06:36 INFO mapred.JobClient:  map 79% reduce 23%
13/05/16 21:06:45 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 21:06:51 INFO mapred.JobClient:  map 81% reduce 23%
13/05/16 21:07:00 INFO mapred.JobClient:  map 82% reduce 23%
13/05/16 21:07:06 INFO mapred.JobClient:  map 83% reduce 23%
13/05/16 21:07:15 INFO mapred.JobClient:  map 84% reduce 23%
13/05/16 21:07:21 INFO mapred.JobClient:  map 85% reduce 23%
13/05/16 21:07:30 INFO mapred.JobClient:  map 86% reduce 23%
13/05/16 21:07:36 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 21:07:45 INFO mapred.JobClient:  map 88% reduce 23%
13/05/16 21:07:51 INFO mapred.JobClient:  map 89% reduce 23%
13/05/16 21:08:00 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 21:08:06 INFO mapred.JobClient:  map 91% reduce 23%
13/05/16 21:08:15 INFO mapred.JobClient:  map 92% reduce 23%
13/05/16 21:08:25 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 21:08:31 INFO mapred.JobClient:  map 94% reduce 23%
13/05/16 21:08:40 INFO mapred.JobClient:  map 95% reduce 23%
13/05/16 21:08:46 INFO mapred.JobClient:  map 95% reduce 24%
13/05/16 21:08:55 INFO mapred.JobClient:  map 96% reduce 24%
13/05/16 21:08:58 INFO mapred.JobClient:  map 96% reduce 25%
13/05/16 21:09:01 INFO mapred.JobClient:  map 96% reduce 28%
13/05/16 21:09:04 INFO mapred.JobClient:  map 96% reduce 29%
13/05/16 21:09:16 INFO mapred.JobClient:  map 96% reduce 30%
13/05/16 21:09:19 INFO mapred.JobClient:  map 96% reduce 31%
13/05/16 21:09:28 INFO mapred.JobClient:  map 97% reduce 31%
13/05/16 21:10:07 INFO mapred.JobClient:  map 98% reduce 31%
13/05/16 21:10:46 INFO mapred.JobClient:  map 99% reduce 31%
13/05/16 21:11:28 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 21:11:37 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 21:12:15 INFO mapred.JobClient:  map 100% reduce 67%
13/05/16 21:12:26 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 21:12:29 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 21:12:32 INFO mapred.JobClient:  map 100% reduce 71%
13/05/16 21:12:35 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 21:12:38 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 21:12:41 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 21:12:44 INFO mapred.JobClient:  map 100% reduce 75%
13/05/16 21:12:47 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 21:12:50 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 21:12:53 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 21:12:56 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 21:12:59 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 21:13:02 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 21:13:05 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 21:13:08 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 21:13:11 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 21:13:14 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 21:13:17 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 21:13:20 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 21:13:23 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 21:13:26 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 21:13:29 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 21:13:32 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 21:13:35 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 21:13:38 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 21:13:41 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 21:13:44 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 21:13:50 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 21:13:55 INFO mapred.JobClient: Job complete: job_201305162045_0001
13/05/16 21:13:55 INFO mapred.JobClient: Counters: 29
13/05/16 21:13:55 INFO mapred.JobClient:   Job Counters 
13/05/16 21:13:55 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 21:13:55 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=12142260
13/05/16 21:13:55 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 21:13:55 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 21:13:55 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 21:13:55 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 21:13:55 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=3953414
13/05/16 21:13:55 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 21:13:55 INFO mapred.JobClient:     Bytes Written=6495027576
13/05/16 21:13:55 INFO mapred.JobClient:   FileSystemCounters
13/05/16 21:13:55 INFO mapred.JobClient:     FILE_BYTES_READ=4785716282
13/05/16 21:13:55 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 21:13:55 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=6488431858
13/05/16 21:13:55 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027576
13/05/16 21:13:55 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 21:13:55 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 21:13:55 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 21:13:55 INFO mapred.JobClient:     Map output materialized bytes=1720113448
13/05/16 21:13:55 INFO mapred.JobClient:     Map input records=401920000
13/05/16 21:13:55 INFO mapred.JobClient:     Reduce shuffle bytes=1694374204
13/05/16 21:13:55 INFO mapred.JobClient:     Spilled Records=1515929852
13/05/16 21:13:55 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 21:13:55 INFO mapred.JobClient:     CPU time spent (ms)=12453660
13/05/16 21:13:55 INFO mapred.JobClient:     Total committed heap usage (bytes)=52432273408
13/05/16 21:13:55 INFO mapred.JobClient:     Combine input records=0
13/05/16 21:13:55 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 21:13:55 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 21:13:55 INFO mapred.JobClient:     Reduce input groups=401920000
13/05/16 21:13:55 INFO mapred.JobClient:     Combine output records=0
13/05/16 21:13:55 INFO mapred.JobClient:     Physical memory (bytes) snapshot=33643560960
13/05/16 21:13:55 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 21:13:55 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=1509562568704
13/05/16 21:13:55 INFO mapred.JobClient:     Map output records=401920000
Execution Time 1296799 ms

real	21m37.577s
user	0m3.352s
sys	0m0.481s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     14475  0.0  0.0 106084  1396 ?        Ss   21:13   0:00 bash -c ps aux | grep java
jmg3     14491  0.0  0.0 103232   844 ?        S    21:13   0:00 grep java
jmg3     13218  0.0  0.0  59072  3528 pts/0    S+   21:13   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     13224  0.0  0.0 106084  1396 ?        Ss   21:13   0:00 bash -c ps aux | grep java
jmg3     13240  0.0  0.0 103232   840 ?        S    21:13   0:00 grep java
