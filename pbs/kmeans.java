java: no process killed
java: no process killed
jmg3      7098  0.0  0.0 106084  1396 ?        Ss   13:10   0:00 bash -c ps aux | grep java
jmg3      7114  0.0  0.0 103232   840 ?        S    13:10   0:00 grep java
jmg3     32215  0.0  0.0  59072  3532 pts/0    S+   13:10   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     32221  0.0  0.0 106084  1396 ?        Ss   13:10   0:00 bash -c ps aux | grep java
jmg3     32237  0.0  0.0 103232   844 ?        S    13:10   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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

13/05/16 13:10:38 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 13:10:38 INFO util.GSet: VM type       = 64-bit
13/05/16 13:10:38 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 13:10:38 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 13:10:38 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 13:10:38 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 13:10:38 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 13:10:38 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 13:10:38 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 13:10:38 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 13:10:38 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 13:10:38 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 13:10:38 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 13:10:38 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/16 13:12:29 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 13:12:29 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 13:12:29 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:12:29 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:12:29 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:12:29 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:12:30 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 13:12:30 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 13:12:31 INFO mapred.JobClient: Running job: job_201305161310_0001
13/05/16 13:12:32 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 13:13:18 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 13:13:48 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 13:14:03 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 13:14:30 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 13:14:45 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 13:15:15 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 13:15:42 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 13:15:57 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 13:16:27 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 13:16:54 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 13:17:12 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 13:17:42 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 13:17:57 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 13:18:24 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 13:18:54 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 13:19:09 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 13:19:36 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 13:19:54 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 13:20:21 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 13:20:49 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 13:21:04 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 13:21:34 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 13:21:49 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 13:22:19 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 13:22:46 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 13:23:04 INFO mapred.JobClient:  map 26% reduce 0%
13/05/16 13:23:31 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 13:23:49 INFO mapred.JobClient:  map 28% reduce 0%
13/05/16 13:24:16 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 13:24:43 INFO mapred.JobClient:  map 30% reduce 0%
13/05/16 13:25:01 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 13:25:28 INFO mapred.JobClient:  map 32% reduce 0%
13/05/16 13:25:43 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 13:26:13 INFO mapred.JobClient:  map 34% reduce 0%
13/05/16 13:26:27 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 13:26:54 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 13:27:24 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 13:27:39 INFO mapred.JobClient:  map 38% reduce 0%
13/05/16 13:28:06 INFO mapred.JobClient:  map 39% reduce 0%
13/05/16 13:28:49 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 13:28:57 INFO mapred.JobClient:  map 40% reduce 1%
13/05/16 13:29:00 INFO mapred.JobClient:  map 40% reduce 3%
13/05/16 13:29:03 INFO mapred.JobClient:  map 40% reduce 4%
13/05/16 13:29:06 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 13:29:09 INFO mapred.JobClient:  map 40% reduce 9%
13/05/16 13:29:12 INFO mapred.JobClient:  map 40% reduce 11%
13/05/16 13:29:15 INFO mapred.JobClient:  map 41% reduce 13%
13/05/16 13:29:43 INFO mapred.JobClient:  map 42% reduce 13%
13/05/16 13:30:01 INFO mapred.JobClient:  map 43% reduce 13%
13/05/16 13:30:32 INFO mapred.JobClient:  map 44% reduce 13%
13/05/16 13:30:56 INFO mapred.JobClient:  map 45% reduce 13%
13/05/16 13:31:17 INFO mapred.JobClient:  map 46% reduce 13%
13/05/16 13:31:44 INFO mapred.JobClient:  map 47% reduce 13%
13/05/16 13:32:02 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 13:32:29 INFO mapred.JobClient:  map 49% reduce 13%
13/05/16 13:32:50 INFO mapred.JobClient:  map 50% reduce 13%
13/05/16 13:33:11 INFO mapred.JobClient:  map 51% reduce 13%
13/05/16 13:33:38 INFO mapred.JobClient:  map 52% reduce 13%
13/05/16 13:33:56 INFO mapred.JobClient:  map 53% reduce 13%
13/05/16 13:34:23 INFO mapred.JobClient:  map 54% reduce 13%
13/05/16 13:34:50 INFO mapred.JobClient:  map 55% reduce 13%
13/05/16 13:35:11 INFO mapred.JobClient:  map 56% reduce 13%
13/05/16 13:35:38 INFO mapred.JobClient:  map 57% reduce 13%
13/05/16 13:35:56 INFO mapred.JobClient:  map 58% reduce 13%
13/05/16 13:36:23 INFO mapred.JobClient:  map 59% reduce 13%
13/05/16 13:36:47 INFO mapred.JobClient:  map 60% reduce 13%
13/05/16 13:37:08 INFO mapred.JobClient:  map 61% reduce 13%
13/05/16 13:37:35 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 13:37:50 INFO mapred.JobClient:  map 63% reduce 13%
13/05/16 13:38:20 INFO mapred.JobClient:  map 64% reduce 13%
13/05/16 13:38:44 INFO mapred.JobClient:  map 65% reduce 13%
13/05/16 13:39:05 INFO mapred.JobClient:  map 66% reduce 13%
13/05/16 13:39:32 INFO mapred.JobClient:  map 67% reduce 13%
13/05/16 13:39:50 INFO mapred.JobClient:  map 68% reduce 13%
13/05/16 13:40:17 INFO mapred.JobClient:  map 69% reduce 13%
13/05/16 13:40:42 INFO mapred.JobClient:  map 70% reduce 13%
13/05/16 13:41:03 INFO mapred.JobClient:  map 71% reduce 13%
13/05/16 13:41:30 INFO mapred.JobClient:  map 72% reduce 13%
13/05/16 13:41:48 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 13:42:15 INFO mapred.JobClient:  map 74% reduce 13%
13/05/16 13:42:42 INFO mapred.JobClient:  map 75% reduce 13%
13/05/16 13:43:00 INFO mapred.JobClient:  map 76% reduce 13%
13/05/16 13:43:27 INFO mapred.JobClient:  map 77% reduce 13%
13/05/16 13:43:41 INFO mapred.JobClient:  map 78% reduce 13%
13/05/16 13:44:11 INFO mapred.JobClient:  map 79% reduce 13%
13/05/16 13:44:50 INFO mapred.JobClient:  map 80% reduce 16%
13/05/16 13:44:53 INFO mapred.JobClient:  map 80% reduce 18%
13/05/16 13:44:56 INFO mapred.JobClient:  map 80% reduce 20%
13/05/16 13:44:59 INFO mapred.JobClient:  map 80% reduce 22%
13/05/16 13:45:02 INFO mapred.JobClient:  map 80% reduce 24%
13/05/16 13:45:05 INFO mapred.JobClient:  map 80% reduce 26%
13/05/16 13:45:33 INFO mapred.JobClient:  map 81% reduce 26%
13/05/16 13:46:18 INFO mapred.JobClient:  map 82% reduce 26%
13/05/16 13:47:03 INFO mapred.JobClient:  map 83% reduce 26%
13/05/16 13:47:48 INFO mapred.JobClient:  map 84% reduce 26%
13/05/16 13:48:39 INFO mapred.JobClient:  map 85% reduce 26%
13/05/16 13:49:24 INFO mapred.JobClient:  map 86% reduce 26%
13/05/16 13:50:06 INFO mapred.JobClient:  map 87% reduce 26%
13/05/16 13:50:55 INFO mapred.JobClient:  map 88% reduce 26%
13/05/16 13:51:37 INFO mapred.JobClient:  map 89% reduce 26%
13/05/16 13:52:31 INFO mapred.JobClient:  map 90% reduce 26%
13/05/16 13:53:16 INFO mapred.JobClient:  map 91% reduce 26%
13/05/16 13:54:01 INFO mapred.JobClient:  map 92% reduce 26%
13/05/16 13:54:46 INFO mapred.JobClient:  map 93% reduce 26%
13/05/16 13:55:31 INFO mapred.JobClient:  map 94% reduce 26%
13/05/16 13:56:25 INFO mapred.JobClient:  map 95% reduce 26%
13/05/16 13:57:07 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 13:57:52 INFO mapred.JobClient:  map 97% reduce 26%
13/05/16 13:58:37 INFO mapred.JobClient:  map 98% reduce 26%
13/05/16 13:59:22 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 14:00:22 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 14:00:28 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 14:00:31 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 14:00:34 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 14:00:46 INFO mapred.JobClient:  map 100% reduce 53%
13/05/16 14:00:49 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 14:00:52 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 14:00:55 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 14:00:58 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 14:01:01 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 14:01:06 INFO mapred.JobClient: Job complete: job_201305161310_0001
13/05/16 14:01:06 INFO mapred.JobClient: Counters: 29
13/05/16 14:01:06 INFO mapred.JobClient:   Job Counters 
13/05/16 14:01:06 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 14:01:06 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=28622147
13/05/16 14:01:06 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 14:01:06 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 14:01:06 INFO mapred.JobClient:     Launched map tasks=30
13/05/16 14:01:06 INFO mapred.JobClient:     Data-local map tasks=30
13/05/16 14:01:06 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=7725975
13/05/16 14:01:06 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 14:01:06 INFO mapred.JobClient:     Bytes Written=485112
13/05/16 14:01:06 INFO mapred.JobClient:   FileSystemCounters
13/05/16 14:01:06 INFO mapred.JobClient:     FILE_BYTES_READ=5338553405
13/05/16 14:01:06 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 14:01:06 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7612009824
13/05/16 14:01:06 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485112
13/05/16 14:01:06 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 14:01:06 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 14:01:06 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 14:01:06 INFO mapred.JobClient:     Map output materialized bytes=2155156955
13/05/16 14:01:06 INFO mapred.JobClient:     Map input records=120000000
13/05/16 14:01:06 INFO mapred.JobClient:     Reduce shuffle bytes=2083317125
13/05/16 14:01:06 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 14:01:06 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 14:01:06 INFO mapred.JobClient:     CPU time spent (ms)=28695770
13/05/16 14:01:06 INFO mapred.JobClient:     Total committed heap usage (bytes)=46921351168
13/05/16 14:01:06 INFO mapred.JobClient:     Combine input records=0
13/05/16 14:01:06 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 14:01:06 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 14:01:06 INFO mapred.JobClient:     Reduce input groups=20000
13/05/16 14:01:06 INFO mapred.JobClient:     Combine output records=0
13/05/16 14:01:06 INFO mapred.JobClient:     Physical memory (bytes) snapshot=40052817920
13/05/16 14:01:06 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 14:01:06 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658642493440
13/05/16 14:01:06 INFO mapred.JobClient:     Map output records=120000000
Execution Time 2916970 ms

real	48m38.147s
user	0m6.238s
sys	0m1.106s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     10174  0.0  0.0 106084  1400 ?        Ss   14:01   0:00 bash -c ps aux | grep java
jmg3     10190  0.0  0.0 103232   844 ?        S    14:01   0:00 grep java
jmg3      1008  0.0  0.0  59072  3524 pts/0    S+   14:01   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      1014  0.0  0.0 106084  1396 ?        Ss   14:01   0:00 bash -c ps aux | grep java
jmg3      1030  0.0  0.0 103232   832 ?        S    14:01   0:00 grep java
java: no process killed
java: no process killed
jmg3     10237  0.0  0.0 106084  1400 ?        Ss   14:01   0:00 bash -c ps aux | grep java
jmg3     10253  0.0  0.0 103232   844 ?        S    14:01   0:00 grep java
jmg3      1098  0.0  0.0  59072  3532 pts/0    S+   14:01   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      1104  0.0  0.0 106084  1400 ?        Ss   14:01   0:00 bash -c ps aux | grep java
jmg3      1120  0.0  0.0 103236   844 ?        S    14:01   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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

13/05/16 14:01:09 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 14:01:09 INFO util.GSet: VM type       = 64-bit
13/05/16 14:01:09 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 14:01:09 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 14:01:09 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 14:01:10 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 14:01:10 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 14:01:10 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 14:01:10 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 14:01:10 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 14:01:10 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 14:01:10 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 14:01:10 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 14:01:10 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/16 14:03:09 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 14:03:09 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 14:03:09 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:03:09 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:03:09 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:03:09 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:03:10 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 14:03:10 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 14:03:11 INFO mapred.JobClient: Running job: job_201305161401_0001
13/05/16 14:03:12 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 14:03:55 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 14:04:25 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 14:04:40 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 14:05:10 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 14:05:25 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 14:05:52 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 14:06:22 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 14:06:40 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 14:07:07 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 14:07:37 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 14:07:52 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 14:08:18 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 14:08:33 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 14:09:03 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 14:09:30 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 14:09:48 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 14:10:15 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 14:10:30 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 14:11:00 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 14:11:28 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 14:11:43 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 14:12:13 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 14:12:31 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 14:12:58 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 14:13:25 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 14:13:43 INFO mapred.JobClient:  map 26% reduce 0%
13/05/16 14:14:10 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 14:14:28 INFO mapred.JobClient:  map 28% reduce 0%
13/05/16 14:14:55 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 14:15:22 INFO mapred.JobClient:  map 30% reduce 0%
13/05/16 14:15:40 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 14:16:07 INFO mapred.JobClient:  map 32% reduce 0%
13/05/16 14:16:22 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 14:16:50 INFO mapred.JobClient:  map 34% reduce 0%
13/05/16 14:17:08 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 14:17:35 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 14:18:02 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 14:18:20 INFO mapred.JobClient:  map 38% reduce 0%
13/05/16 14:18:47 INFO mapred.JobClient:  map 39% reduce 0%
13/05/16 14:19:26 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 14:19:32 INFO mapred.JobClient:  map 40% reduce 1%
13/05/16 14:19:38 INFO mapred.JobClient:  map 40% reduce 3%
13/05/16 14:19:41 INFO mapred.JobClient:  map 40% reduce 5%
13/05/16 14:19:44 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 14:19:47 INFO mapred.JobClient:  map 40% reduce 9%
13/05/16 14:19:50 INFO mapred.JobClient:  map 40% reduce 11%
13/05/16 14:19:53 INFO mapred.JobClient:  map 40% reduce 13%
13/05/16 14:19:56 INFO mapred.JobClient:  map 41% reduce 13%
13/05/16 14:20:21 INFO mapred.JobClient:  map 42% reduce 13%
13/05/16 14:20:39 INFO mapred.JobClient:  map 43% reduce 13%
13/05/16 14:21:10 INFO mapred.JobClient:  map 44% reduce 13%
13/05/16 14:21:34 INFO mapred.JobClient:  map 45% reduce 13%
13/05/16 14:21:52 INFO mapred.JobClient:  map 46% reduce 13%
13/05/16 14:22:22 INFO mapred.JobClient:  map 47% reduce 13%
13/05/16 14:22:37 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 14:23:04 INFO mapred.JobClient:  map 49% reduce 13%
13/05/16 14:23:31 INFO mapred.JobClient:  map 50% reduce 13%
13/05/16 14:23:49 INFO mapred.JobClient:  map 51% reduce 13%
13/05/16 14:24:16 INFO mapred.JobClient:  map 52% reduce 13%
13/05/16 14:24:34 INFO mapred.JobClient:  map 53% reduce 13%
13/05/16 14:25:01 INFO mapred.JobClient:  map 54% reduce 13%
13/05/16 14:25:31 INFO mapred.JobClient:  map 55% reduce 13%
13/05/16 14:25:49 INFO mapred.JobClient:  map 56% reduce 13%
13/05/16 14:26:16 INFO mapred.JobClient:  map 57% reduce 13%
13/05/16 14:26:31 INFO mapred.JobClient:  map 58% reduce 13%
13/05/16 14:27:01 INFO mapred.JobClient:  map 59% reduce 13%
13/05/16 14:27:25 INFO mapred.JobClient:  map 60% reduce 13%
13/05/16 14:27:43 INFO mapred.JobClient:  map 61% reduce 13%
13/05/16 14:28:13 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 14:28:28 INFO mapred.JobClient:  map 63% reduce 13%
13/05/16 14:28:55 INFO mapred.JobClient:  map 64% reduce 13%
13/05/16 14:29:22 INFO mapred.JobClient:  map 65% reduce 13%
13/05/16 14:29:43 INFO mapred.JobClient:  map 66% reduce 13%
13/05/16 14:30:10 INFO mapred.JobClient:  map 67% reduce 13%
13/05/16 14:30:28 INFO mapred.JobClient:  map 68% reduce 13%
13/05/16 14:30:55 INFO mapred.JobClient:  map 69% reduce 13%
13/05/16 14:31:19 INFO mapred.JobClient:  map 70% reduce 13%
13/05/16 14:31:40 INFO mapred.JobClient:  map 71% reduce 13%
13/05/16 14:32:07 INFO mapred.JobClient:  map 72% reduce 13%
13/05/16 14:32:22 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 14:32:49 INFO mapred.JobClient:  map 74% reduce 13%
13/05/16 14:33:16 INFO mapred.JobClient:  map 75% reduce 13%
13/05/16 14:33:34 INFO mapred.JobClient:  map 76% reduce 13%
13/05/16 14:34:01 INFO mapred.JobClient:  map 77% reduce 13%
13/05/16 14:34:19 INFO mapred.JobClient:  map 78% reduce 13%
13/05/16 14:34:49 INFO mapred.JobClient:  map 79% reduce 13%
13/05/16 14:35:28 INFO mapred.JobClient:  map 80% reduce 15%
13/05/16 14:35:31 INFO mapred.JobClient:  map 80% reduce 17%
13/05/16 14:35:34 INFO mapred.JobClient:  map 80% reduce 20%
13/05/16 14:35:37 INFO mapred.JobClient:  map 80% reduce 22%
13/05/16 14:35:40 INFO mapred.JobClient:  map 80% reduce 24%
13/05/16 14:35:43 INFO mapred.JobClient:  map 80% reduce 26%
13/05/16 14:36:11 INFO mapred.JobClient:  map 81% reduce 26%
13/05/16 14:36:59 INFO mapred.JobClient:  map 82% reduce 26%
13/05/16 14:37:41 INFO mapred.JobClient:  map 83% reduce 26%
13/05/16 14:38:26 INFO mapred.JobClient:  map 84% reduce 26%
13/05/16 14:39:17 INFO mapred.JobClient:  map 85% reduce 26%
13/05/16 14:40:02 INFO mapred.JobClient:  map 86% reduce 26%
13/05/16 14:40:50 INFO mapred.JobClient:  map 87% reduce 26%
13/05/16 14:41:32 INFO mapred.JobClient:  map 88% reduce 26%
13/05/16 14:42:18 INFO mapred.JobClient:  map 89% reduce 26%
13/05/16 14:43:09 INFO mapred.JobClient:  map 90% reduce 26%
13/05/16 14:43:54 INFO mapred.JobClient:  map 91% reduce 26%
13/05/16 14:44:42 INFO mapred.JobClient:  map 92% reduce 26%
13/05/16 14:45:27 INFO mapred.JobClient:  map 93% reduce 26%
13/05/16 14:46:09 INFO mapred.JobClient:  map 94% reduce 26%
13/05/16 14:47:00 INFO mapred.JobClient:  map 95% reduce 26%
13/05/16 14:47:48 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 14:48:33 INFO mapred.JobClient:  map 97% reduce 26%
13/05/16 14:49:18 INFO mapred.JobClient:  map 98% reduce 26%
13/05/16 14:50:00 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 14:51:03 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 14:51:06 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 14:51:12 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 14:51:15 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 14:51:18 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 14:51:24 INFO mapred.JobClient:  map 100% reduce 43%
13/05/16 14:51:27 INFO mapred.JobClient:  map 100% reduce 55%
13/05/16 14:51:30 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 14:51:33 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 14:51:36 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 14:51:39 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 14:51:42 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 14:51:45 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 14:51:50 INFO mapred.JobClient: Job complete: job_201305161401_0001
13/05/16 14:51:50 INFO mapred.JobClient: Counters: 29
13/05/16 14:51:50 INFO mapred.JobClient:   Job Counters 
13/05/16 14:51:50 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 14:51:50 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=28624822
13/05/16 14:51:50 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 14:51:50 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 14:51:50 INFO mapred.JobClient:     Launched map tasks=30
13/05/16 14:51:50 INFO mapred.JobClient:     Data-local map tasks=30
13/05/16 14:51:50 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=7741976
13/05/16 14:51:50 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 14:51:50 INFO mapred.JobClient:     Bytes Written=485112
13/05/16 14:51:50 INFO mapred.JobClient:   FileSystemCounters
13/05/16 14:51:50 INFO mapred.JobClient:     FILE_BYTES_READ=5338561133
13/05/16 14:51:50 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 14:51:50 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7612017552
13/05/16 14:51:50 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485112
13/05/16 14:51:50 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 14:51:50 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 14:51:50 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 14:51:50 INFO mapred.JobClient:     Map output materialized bytes=2155156955
13/05/16 14:51:50 INFO mapred.JobClient:     Map input records=120000000
13/05/16 14:51:50 INFO mapred.JobClient:     Reduce shuffle bytes=2083317125
13/05/16 14:51:50 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 14:51:50 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 14:51:50 INFO mapred.JobClient:     CPU time spent (ms)=28680350
13/05/16 14:51:50 INFO mapred.JobClient:     Total committed heap usage (bytes)=43232395264
13/05/16 14:51:50 INFO mapred.JobClient:     Combine input records=0
13/05/16 14:51:50 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 14:51:50 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 14:51:50 INFO mapred.JobClient:     Reduce input groups=20000
13/05/16 14:51:50 INFO mapred.JobClient:     Combine output records=0
13/05/16 14:51:50 INFO mapred.JobClient:     Physical memory (bytes) snapshot=36085338112
13/05/16 14:51:50 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 14:51:50 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658440114176
13/05/16 14:51:50 INFO mapred.JobClient:     Map output records=120000000
Execution Time 2920870 ms

real	48m41.912s
user	0m6.185s
sys	0m1.137s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     13305  0.0  0.0 106084  1404 ?        Ss   14:51   0:00 bash -c ps aux | grep java
jmg3     13321  0.0  0.0 103232   840 ?        S    14:51   0:00 grep java
jmg3      2400  0.0  0.0  59072  3528 pts/0    S+   14:51   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      2406  0.0  0.0 106084  1396 ?        Ss   14:51   0:00 bash -c ps aux | grep java
jmg3      2422  0.0  0.0 103232   844 ?        S    14:51   0:00 grep java
java: no process killed
java: no process killed
jmg3     13367  0.0  0.0 106084  1404 ?        Ss   14:51   0:00 bash -c ps aux | grep java
jmg3     13383  0.0  0.0 103232   840 ?        S    14:51   0:00 grep java
jmg3      2488  0.0  0.0  59072  3528 pts/0    S+   14:51   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      2494  0.0  0.0 106084  1396 ?        Ss   14:51   0:00 bash -c ps aux | grep java
jmg3      2510  0.0  0.0 103232   844 ?        S    14:51   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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

13/05/16 14:51:53 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 14:51:53 INFO util.GSet: VM type       = 64-bit
13/05/16 14:51:53 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 14:51:53 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 14:51:53 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 14:51:53 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 14:51:53 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 14:51:53 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 14:51:53 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 14:51:53 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 14:51:53 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 14:51:53 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 14:51:54 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 14:51:54 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/16 14:55:05 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 14:55:05 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 14:55:05 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:55:05 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:55:05 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:55:05 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 14:55:06 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 14:55:06 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 14:55:07 INFO mapred.JobClient: Running job: job_201305161451_0001
13/05/16 14:55:08 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 14:55:51 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 14:56:21 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 14:56:36 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 14:57:06 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 14:57:21 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 14:57:51 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 14:58:21 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 14:58:36 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 14:59:03 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 14:59:33 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 14:59:48 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 15:00:15 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 15:00:30 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 15:01:00 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 15:01:27 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 15:01:42 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 15:02:12 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 15:02:27 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 15:02:56 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 15:03:21 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 15:03:42 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 15:04:09 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 15:04:27 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 15:04:54 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 15:05:21 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 15:05:39 INFO mapred.JobClient:  map 26% reduce 0%
13/05/16 15:06:06 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 15:06:24 INFO mapred.JobClient:  map 28% reduce 0%
13/05/16 15:06:51 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 15:07:18 INFO mapred.JobClient:  map 30% reduce 0%
13/05/16 15:07:36 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 15:08:03 INFO mapred.JobClient:  map 32% reduce 0%
13/05/16 15:08:18 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 15:08:45 INFO mapred.JobClient:  map 34% reduce 0%
13/05/16 15:09:03 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 15:09:33 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 15:10:00 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 15:10:15 INFO mapred.JobClient:  map 38% reduce 0%
13/05/16 15:10:45 INFO mapred.JobClient:  map 39% reduce 0%
13/05/16 15:11:24 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 15:11:33 INFO mapred.JobClient:  map 40% reduce 1%
13/05/16 15:11:36 INFO mapred.JobClient:  map 40% reduce 3%
13/05/16 15:11:39 INFO mapred.JobClient:  map 40% reduce 5%
13/05/16 15:11:42 INFO mapred.JobClient:  map 40% reduce 6%
13/05/16 15:11:45 INFO mapred.JobClient:  map 40% reduce 9%
13/05/16 15:11:48 INFO mapred.JobClient:  map 40% reduce 11%
13/05/16 15:11:51 INFO mapred.JobClient:  map 41% reduce 13%
13/05/16 15:12:21 INFO mapred.JobClient:  map 42% reduce 13%
13/05/16 15:12:40 INFO mapred.JobClient:  map 43% reduce 13%
13/05/16 15:13:07 INFO mapred.JobClient:  map 44% reduce 13%
13/05/16 15:13:31 INFO mapred.JobClient:  map 45% reduce 13%
13/05/16 15:13:52 INFO mapred.JobClient:  map 46% reduce 13%
13/05/16 15:14:19 INFO mapred.JobClient:  map 47% reduce 13%
13/05/16 15:14:37 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 15:15:04 INFO mapred.JobClient:  map 49% reduce 13%
13/05/16 15:15:28 INFO mapred.JobClient:  map 50% reduce 13%
13/05/16 15:15:49 INFO mapred.JobClient:  map 51% reduce 13%
13/05/16 15:16:16 INFO mapred.JobClient:  map 52% reduce 13%
13/05/16 15:16:34 INFO mapred.JobClient:  map 53% reduce 13%
13/05/16 15:17:01 INFO mapred.JobClient:  map 54% reduce 13%
13/05/16 15:17:22 INFO mapred.JobClient:  map 55% reduce 13%
13/05/16 15:17:46 INFO mapred.JobClient:  map 56% reduce 13%
13/05/16 15:18:13 INFO mapred.JobClient:  map 57% reduce 13%
13/05/16 15:18:30 INFO mapred.JobClient:  map 58% reduce 13%
13/05/16 15:18:57 INFO mapred.JobClient:  map 59% reduce 13%
13/05/16 15:19:21 INFO mapred.JobClient:  map 60% reduce 13%
13/05/16 15:19:42 INFO mapred.JobClient:  map 61% reduce 13%
13/05/16 15:20:09 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 15:20:27 INFO mapred.JobClient:  map 63% reduce 13%
13/05/16 15:20:54 INFO mapred.JobClient:  map 64% reduce 13%
13/05/16 15:21:18 INFO mapred.JobClient:  map 65% reduce 13%
13/05/16 15:21:39 INFO mapred.JobClient:  map 66% reduce 13%
13/05/16 15:22:06 INFO mapred.JobClient:  map 67% reduce 13%
13/05/16 15:22:24 INFO mapred.JobClient:  map 68% reduce 13%
13/05/16 15:22:52 INFO mapred.JobClient:  map 69% reduce 13%
13/05/16 15:23:13 INFO mapred.JobClient:  map 70% reduce 13%
13/05/16 15:23:37 INFO mapred.JobClient:  map 71% reduce 13%
13/05/16 15:24:04 INFO mapred.JobClient:  map 72% reduce 13%
13/05/16 15:24:22 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 15:24:49 INFO mapred.JobClient:  map 74% reduce 13%
13/05/16 15:25:13 INFO mapred.JobClient:  map 75% reduce 13%
13/05/16 15:25:37 INFO mapred.JobClient:  map 76% reduce 13%
13/05/16 15:26:01 INFO mapred.JobClient:  map 77% reduce 13%
13/05/16 15:26:19 INFO mapred.JobClient:  map 78% reduce 13%
13/05/16 15:26:46 INFO mapred.JobClient:  map 79% reduce 13%
13/05/16 15:27:22 INFO mapred.JobClient:  map 80% reduce 13%
13/05/16 15:27:28 INFO mapred.JobClient:  map 80% reduce 14%
13/05/16 15:27:31 INFO mapred.JobClient:  map 80% reduce 17%
13/05/16 15:27:34 INFO mapred.JobClient:  map 80% reduce 19%
13/05/16 15:27:37 INFO mapred.JobClient:  map 80% reduce 21%
13/05/16 15:27:40 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 15:27:43 INFO mapred.JobClient:  map 80% reduce 25%
13/05/16 15:27:46 INFO mapred.JobClient:  map 80% reduce 26%
13/05/16 15:28:12 INFO mapred.JobClient:  map 81% reduce 26%
13/05/16 15:28:57 INFO mapred.JobClient:  map 82% reduce 26%
13/05/16 15:29:38 INFO mapred.JobClient:  map 83% reduce 26%
13/05/16 15:30:23 INFO mapred.JobClient:  map 84% reduce 26%
13/05/16 15:31:11 INFO mapred.JobClient:  map 85% reduce 26%
13/05/16 15:31:59 INFO mapred.JobClient:  map 86% reduce 26%
13/05/16 15:32:44 INFO mapred.JobClient:  map 87% reduce 26%
13/05/16 15:33:30 INFO mapred.JobClient:  map 88% reduce 26%
13/05/16 15:34:12 INFO mapred.JobClient:  map 89% reduce 26%
13/05/16 15:35:03 INFO mapred.JobClient:  map 90% reduce 26%
13/05/16 15:35:54 INFO mapred.JobClient:  map 91% reduce 26%
13/05/16 15:36:39 INFO mapred.JobClient:  map 92% reduce 26%
13/05/16 15:37:21 INFO mapred.JobClient:  map 93% reduce 26%
13/05/16 15:38:06 INFO mapred.JobClient:  map 94% reduce 26%
13/05/16 15:38:57 INFO mapred.JobClient:  map 95% reduce 26%
13/05/16 15:39:45 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 15:40:30 INFO mapred.JobClient:  map 97% reduce 26%
13/05/16 15:41:12 INFO mapred.JobClient:  map 98% reduce 26%
13/05/16 15:41:57 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 15:42:57 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 15:43:03 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 15:43:06 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 15:43:09 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 15:43:12 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 15:43:18 INFO mapred.JobClient:  map 100% reduce 41%
13/05/16 15:43:21 INFO mapred.JobClient:  map 100% reduce 44%
13/05/16 15:43:24 INFO mapred.JobClient:  map 100% reduce 53%
13/05/16 15:43:28 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 15:43:40 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 15:43:45 INFO mapred.JobClient: Job complete: job_201305161451_0001
13/05/16 15:43:45 INFO mapred.JobClient: Counters: 29
13/05/16 15:43:45 INFO mapred.JobClient:   Job Counters 
13/05/16 15:43:45 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 15:43:45 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=28622457
13/05/16 15:43:45 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 15:43:45 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 15:43:45 INFO mapred.JobClient:     Launched map tasks=30
13/05/16 15:43:45 INFO mapred.JobClient:     Data-local map tasks=30
13/05/16 15:43:45 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=7736620
13/05/16 15:43:45 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 15:43:45 INFO mapred.JobClient:     Bytes Written=485112
13/05/16 15:43:45 INFO mapred.JobClient:   FileSystemCounters
13/05/16 15:43:45 INFO mapred.JobClient:     FILE_BYTES_READ=5338567567
13/05/16 15:43:45 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 15:43:45 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7612023986
13/05/16 15:43:45 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485112
13/05/16 15:43:45 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 15:43:45 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 15:43:45 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 15:43:45 INFO mapred.JobClient:     Map output materialized bytes=2155156955
13/05/16 15:43:45 INFO mapred.JobClient:     Map input records=120000000
13/05/16 15:43:45 INFO mapred.JobClient:     Reduce shuffle bytes=2083311647
13/05/16 15:43:45 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 15:43:45 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 15:43:45 INFO mapred.JobClient:     CPU time spent (ms)=28704090
13/05/16 15:43:45 INFO mapred.JobClient:     Total committed heap usage (bytes)=45429489664
13/05/16 15:43:45 INFO mapred.JobClient:     Combine input records=0
13/05/16 15:43:45 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 15:43:45 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 15:43:45 INFO mapred.JobClient:     Reduce input groups=20000
13/05/16 15:43:45 INFO mapred.JobClient:     Combine output records=0
13/05/16 15:43:45 INFO mapred.JobClient:     Physical memory (bytes) snapshot=38555983872
13/05/16 15:43:45 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 15:43:45 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658507223040
13/05/16 15:43:45 INFO mapred.JobClient:     Map output records=120000000
Execution Time 2919896 ms

real	48m40.953s
user	0m6.343s
sys	0m1.036s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     16437  0.0  0.0 106084  1400 ?        Ss   15:43   0:00 bash -c ps aux | grep java
jmg3     16453  0.0  0.0 103232   844 ?        S    15:43   0:00 grep java
jmg3      3831  0.0  0.0  59072  3528 pts/0    S+   15:43   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      3837  0.0  0.0 106084  1400 ?        Ss   15:43   0:00 bash -c ps aux | grep java
jmg3      3853  0.0  0.0 103232   840 ?        S    15:43   0:00 grep java
java: no process killed
java: no process killed
jmg3     16499  0.0  0.0 106084  1396 ?        Ss   15:43   0:00 bash -c ps aux | grep java
jmg3     16515  0.0  0.0 103232   840 ?        S    15:43   0:00 grep java
jmg3      3919  0.0  0.0  59072  3528 pts/0    S+   15:43   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      3925  0.0  0.0 106084  1396 ?        Ss   15:43   0:00 bash -c ps aux | grep java
jmg3      3941  0.0  0.0 103232   844 ?        S    15:43   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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

13/05/16 15:43:49 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 15:43:49 INFO util.GSet: VM type       = 64-bit
13/05/16 15:43:49 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 15:43:49 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 15:43:49 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 15:43:49 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 15:43:49 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 15:43:49 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 15:43:49 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 15:43:49 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 15:43:49 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 15:43:50 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 15:43:50 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 15:43:50 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/16 15:50:11 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 15:50:11 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 15:50:11 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 15:50:11 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 15:50:11 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 15:50:11 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 15:50:11 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 15:50:11 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 15:50:14 INFO mapred.JobClient: Running job: job_201305161543_0001
13/05/16 15:50:15 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 15:50:58 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 15:51:28 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 15:51:43 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 15:52:13 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 15:52:28 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 15:52:58 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 15:53:26 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 15:53:41 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 15:54:11 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 15:54:38 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 15:54:53 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 15:55:23 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 15:55:38 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 15:56:08 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 15:56:35 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 15:56:50 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 15:57:20 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 15:57:35 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 15:58:02 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 15:58:32 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 15:58:47 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 15:59:14 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 15:59:33 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 16:00:03 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 16:00:30 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 16:00:45 INFO mapred.JobClient:  map 26% reduce 0%
13/05/16 16:01:15 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 16:01:30 INFO mapred.JobClient:  map 28% reduce 0%
13/05/16 16:01:57 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 16:02:24 INFO mapred.JobClient:  map 30% reduce 0%
13/05/16 16:02:42 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 16:03:09 INFO mapred.JobClient:  map 32% reduce 0%
13/05/16 16:03:27 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 16:03:54 INFO mapred.JobClient:  map 34% reduce 0%
13/05/16 16:04:12 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 16:04:39 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 16:05:06 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 16:05:24 INFO mapred.JobClient:  map 38% reduce 0%
13/05/16 16:05:50 INFO mapred.JobClient:  map 39% reduce 0%
13/05/16 16:06:32 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 16:06:35 INFO mapred.JobClient:  map 40% reduce 1%
13/05/16 16:06:38 INFO mapred.JobClient:  map 40% reduce 3%
13/05/16 16:06:41 INFO mapred.JobClient:  map 40% reduce 4%
13/05/16 16:06:44 INFO mapred.JobClient:  map 40% reduce 6%
13/05/16 16:06:47 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 16:06:50 INFO mapred.JobClient:  map 40% reduce 10%
13/05/16 16:06:56 INFO mapred.JobClient:  map 40% reduce 13%
13/05/16 16:07:02 INFO mapred.JobClient:  map 41% reduce 13%
13/05/16 16:07:23 INFO mapred.JobClient:  map 42% reduce 13%
13/05/16 16:07:44 INFO mapred.JobClient:  map 43% reduce 13%
13/05/16 16:08:11 INFO mapred.JobClient:  map 44% reduce 13%
13/05/16 16:08:38 INFO mapred.JobClient:  map 45% reduce 13%
13/05/16 16:08:59 INFO mapred.JobClient:  map 46% reduce 13%
13/05/16 16:09:26 INFO mapred.JobClient:  map 47% reduce 13%
13/05/16 16:09:45 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 16:10:12 INFO mapred.JobClient:  map 49% reduce 13%
13/05/16 16:10:35 INFO mapred.JobClient:  map 50% reduce 13%
13/05/16 16:10:56 INFO mapred.JobClient:  map 51% reduce 13%
13/05/16 16:11:20 INFO mapred.JobClient:  map 52% reduce 13%
13/05/16 16:11:38 INFO mapred.JobClient:  map 53% reduce 13%
13/05/16 16:12:05 INFO mapred.JobClient:  map 54% reduce 13%
13/05/16 16:12:32 INFO mapred.JobClient:  map 55% reduce 13%
13/05/16 16:12:50 INFO mapred.JobClient:  map 56% reduce 13%
13/05/16 16:13:17 INFO mapred.JobClient:  map 57% reduce 13%
13/05/16 16:13:38 INFO mapred.JobClient:  map 58% reduce 13%
13/05/16 16:14:05 INFO mapred.JobClient:  map 59% reduce 13%
13/05/16 16:14:29 INFO mapred.JobClient:  map 60% reduce 13%
13/05/16 16:14:50 INFO mapred.JobClient:  map 61% reduce 13%
13/05/16 16:15:14 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 16:15:32 INFO mapred.JobClient:  map 63% reduce 13%
13/05/16 16:15:59 INFO mapred.JobClient:  map 64% reduce 13%
13/05/16 16:16:26 INFO mapred.JobClient:  map 65% reduce 13%
13/05/16 16:16:44 INFO mapred.JobClient:  map 66% reduce 13%
13/05/16 16:17:11 INFO mapred.JobClient:  map 67% reduce 13%
13/05/16 16:17:29 INFO mapred.JobClient:  map 68% reduce 13%
13/05/16 16:17:59 INFO mapred.JobClient:  map 69% reduce 13%
13/05/16 16:18:23 INFO mapred.JobClient:  map 70% reduce 13%
13/05/16 16:18:44 INFO mapred.JobClient:  map 71% reduce 13%
13/05/16 16:19:08 INFO mapred.JobClient:  map 72% reduce 13%
13/05/16 16:19:29 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 16:19:57 INFO mapred.JobClient:  map 74% reduce 13%
13/05/16 16:20:21 INFO mapred.JobClient:  map 75% reduce 13%
13/05/16 16:20:42 INFO mapred.JobClient:  map 76% reduce 13%
13/05/16 16:21:09 INFO mapred.JobClient:  map 77% reduce 13%
13/05/16 16:21:27 INFO mapred.JobClient:  map 78% reduce 13%
13/05/16 16:21:51 INFO mapred.JobClient:  map 79% reduce 13%
13/05/16 16:22:27 INFO mapred.JobClient:  map 80% reduce 13%
13/05/16 16:22:30 INFO mapred.JobClient:  map 80% reduce 14%
13/05/16 16:22:33 INFO mapred.JobClient:  map 80% reduce 16%
13/05/16 16:22:36 INFO mapred.JobClient:  map 80% reduce 19%
13/05/16 16:22:39 INFO mapred.JobClient:  map 80% reduce 20%
13/05/16 16:22:42 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 16:22:45 INFO mapred.JobClient:  map 80% reduce 25%
13/05/16 16:22:48 INFO mapred.JobClient:  map 80% reduce 26%
13/05/16 16:23:15 INFO mapred.JobClient:  map 81% reduce 26%
13/05/16 16:23:57 INFO mapred.JobClient:  map 82% reduce 26%
13/05/16 16:24:42 INFO mapred.JobClient:  map 83% reduce 26%
13/05/16 16:25:30 INFO mapred.JobClient:  map 84% reduce 26%
13/05/16 16:26:18 INFO mapred.JobClient:  map 85% reduce 26%
13/05/16 16:27:06 INFO mapred.JobClient:  map 86% reduce 26%
13/05/16 16:27:51 INFO mapred.JobClient:  map 87% reduce 26%
13/05/16 16:28:33 INFO mapred.JobClient:  map 88% reduce 26%
13/05/16 16:29:18 INFO mapred.JobClient:  map 89% reduce 26%
13/05/16 16:30:09 INFO mapred.JobClient:  map 90% reduce 26%
13/05/16 16:30:58 INFO mapred.JobClient:  map 91% reduce 26%
13/05/16 16:31:40 INFO mapred.JobClient:  map 92% reduce 26%
13/05/16 16:32:25 INFO mapred.JobClient:  map 93% reduce 26%
13/05/16 16:33:10 INFO mapred.JobClient:  map 94% reduce 26%
13/05/16 16:34:01 INFO mapred.JobClient:  map 95% reduce 26%
13/05/16 16:34:49 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 16:35:34 INFO mapred.JobClient:  map 97% reduce 26%
13/05/16 16:36:19 INFO mapred.JobClient:  map 98% reduce 26%
13/05/16 16:37:04 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 16:38:07 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 16:38:10 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 16:38:13 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 16:38:16 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 16:38:28 INFO mapred.JobClient:  map 100% reduce 60%
13/05/16 16:38:31 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 16:38:34 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 16:38:37 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 16:38:40 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 16:38:43 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 16:38:46 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 16:38:51 INFO mapred.JobClient: Job complete: job_201305161543_0001
13/05/16 16:38:51 INFO mapred.JobClient: Counters: 29
13/05/16 16:38:51 INFO mapred.JobClient:   Job Counters 
13/05/16 16:38:51 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 16:38:51 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=28601548
13/05/16 16:38:51 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 16:38:51 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 16:38:51 INFO mapred.JobClient:     Launched map tasks=30
13/05/16 16:38:51 INFO mapred.JobClient:     Data-local map tasks=30
13/05/16 16:38:51 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=7727671
13/05/16 16:38:51 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 16:38:51 INFO mapred.JobClient:     Bytes Written=485112
13/05/16 16:38:51 INFO mapred.JobClient:   FileSystemCounters
13/05/16 16:38:51 INFO mapred.JobClient:     FILE_BYTES_READ=5338562928
13/05/16 16:38:51 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 16:38:51 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7612019347
13/05/16 16:38:51 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485112
13/05/16 16:38:51 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 16:38:51 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 16:38:51 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 16:38:51 INFO mapred.JobClient:     Map output materialized bytes=2155156955
13/05/16 16:38:51 INFO mapred.JobClient:     Map input records=120000000
13/05/16 16:38:51 INFO mapred.JobClient:     Reduce shuffle bytes=2083311647
13/05/16 16:38:51 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 16:38:51 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 16:38:51 INFO mapred.JobClient:     CPU time spent (ms)=28698490
13/05/16 16:38:51 INFO mapred.JobClient:     Total committed heap usage (bytes)=50226331648
13/05/16 16:38:51 INFO mapred.JobClient:     Combine input records=0
13/05/16 16:38:51 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 16:38:51 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 16:38:51 INFO mapred.JobClient:     Reduce input groups=20000
13/05/16 16:38:51 INFO mapred.JobClient:     Combine output records=0
13/05/16 16:38:51 INFO mapred.JobClient:     Physical memory (bytes) snapshot=43197521920
13/05/16 16:38:51 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 16:38:51 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658373005312
13/05/16 16:38:51 INFO mapred.JobClient:     Map output records=120000000
Execution Time 2920369 ms

real	48m41.419s
user	0m6.331s
sys	0m1.036s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     19577  0.0  0.0 106084  1400 ?        Ss   16:38   0:00 bash -c ps aux | grep java
jmg3     19593  0.0  0.0 103232   840 ?        S    16:38   0:00 grep java
jmg3      5196  0.0  0.0  59072  3528 pts/0    S+   16:38   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      5202  0.0  0.0 106084  1400 ?        Ss   16:38   0:00 bash -c ps aux | grep java
jmg3      5218  0.0  0.0 103232   844 ?        S    16:38   0:00 grep java
java: no process killed
java: no process killed
jmg3     19639  0.0  0.0 106084  1396 ?        Ss   16:38   0:00 bash -c ps aux | grep java
jmg3     19655  0.0  0.0 103232   840 ?        S    16:38   0:00 grep java
jmg3      5284  0.0  0.0  59072  3528 pts/0    S+   16:38   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      5290  0.0  0.0 106084  1400 ?        Ss   16:38   0:00 bash -c ps aux | grep java
jmg3      5306  0.0  0.0 103232   844 ?        S    16:38   0:00 grep java
Setting path to /tmp/1298557.daman.davinci.rice.edu
12 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 67108864 1 1 1 1 16



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

13/05/16 16:38:54 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 16:38:55 INFO util.GSet: VM type       = 64-bit
13/05/16 16:38:55 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 16:38:55 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 16:38:55 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 16:38:55 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 16:38:55 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 16:38:55 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 16:38:55 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 16:38:55 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 16:38:55 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 16:38:55 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 16:38:55 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 16:38:55 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/16 16:45:37 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 16:45:37 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 16:45:37 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 16:45:37 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 16:45:37 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 16:45:37 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 16:45:37 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 16:45:37 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 16:45:38 INFO mapred.JobClient: Running job: job_201305161639_0001
13/05/16 16:45:39 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 16:46:23 INFO mapred.JobClient:  map 1% reduce 0%
13/05/16 16:46:53 INFO mapred.JobClient:  map 2% reduce 0%
13/05/16 16:47:08 INFO mapred.JobClient:  map 3% reduce 0%
13/05/16 16:47:38 INFO mapred.JobClient:  map 4% reduce 0%
13/05/16 16:47:53 INFO mapred.JobClient:  map 5% reduce 0%
13/05/16 16:48:20 INFO mapred.JobClient:  map 6% reduce 0%
13/05/16 16:48:50 INFO mapred.JobClient:  map 7% reduce 0%
13/05/16 16:49:07 INFO mapred.JobClient:  map 8% reduce 0%
13/05/16 16:49:34 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 16:50:04 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 16:50:19 INFO mapred.JobClient:  map 11% reduce 0%
13/05/16 16:50:46 INFO mapred.JobClient:  map 12% reduce 0%
13/05/16 16:51:01 INFO mapred.JobClient:  map 13% reduce 0%
13/05/16 16:51:31 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 16:52:00 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 16:52:15 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 16:52:45 INFO mapred.JobClient:  map 17% reduce 0%
13/05/16 16:53:00 INFO mapred.JobClient:  map 18% reduce 0%
13/05/16 16:53:27 INFO mapred.JobClient:  map 19% reduce 0%
13/05/16 16:53:57 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 16:54:12 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 16:54:42 INFO mapred.JobClient:  map 22% reduce 0%
13/05/16 16:54:57 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 16:55:24 INFO mapred.JobClient:  map 24% reduce 0%
13/05/16 16:55:54 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 16:56:12 INFO mapred.JobClient:  map 26% reduce 0%
13/05/16 16:56:39 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 16:56:54 INFO mapred.JobClient:  map 28% reduce 0%
13/05/16 16:57:23 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 16:57:50 INFO mapred.JobClient:  map 30% reduce 0%
13/05/16 16:58:05 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 16:58:35 INFO mapred.JobClient:  map 32% reduce 0%
13/05/16 16:58:50 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 16:59:17 INFO mapred.JobClient:  map 34% reduce 0%
13/05/16 16:59:35 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 17:00:02 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 17:00:29 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 17:00:50 INFO mapred.JobClient:  map 38% reduce 0%
13/05/16 17:01:14 INFO mapred.JobClient:  map 39% reduce 0%
13/05/16 17:01:57 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 17:02:00 INFO mapred.JobClient:  map 40% reduce 1%
13/05/16 17:02:03 INFO mapred.JobClient:  map 40% reduce 3%
13/05/16 17:02:06 INFO mapred.JobClient:  map 40% reduce 4%
13/05/16 17:02:09 INFO mapred.JobClient:  map 40% reduce 5%
13/05/16 17:02:12 INFO mapred.JobClient:  map 40% reduce 7%
13/05/16 17:02:15 INFO mapred.JobClient:  map 40% reduce 10%
13/05/16 17:02:18 INFO mapred.JobClient:  map 40% reduce 11%
13/05/16 17:02:21 INFO mapred.JobClient:  map 40% reduce 13%
13/05/16 17:02:24 INFO mapred.JobClient:  map 41% reduce 13%
13/05/16 17:02:48 INFO mapred.JobClient:  map 42% reduce 13%
13/05/16 17:03:09 INFO mapred.JobClient:  map 43% reduce 13%
13/05/16 17:03:36 INFO mapred.JobClient:  map 44% reduce 13%
13/05/16 17:04:00 INFO mapred.JobClient:  map 45% reduce 13%
13/05/16 17:04:21 INFO mapred.JobClient:  map 46% reduce 13%
13/05/16 17:04:45 INFO mapred.JobClient:  map 47% reduce 13%
13/05/16 17:05:06 INFO mapred.JobClient:  map 48% reduce 13%
13/05/16 17:05:33 INFO mapred.JobClient:  map 49% reduce 13%
13/05/16 17:05:57 INFO mapred.JobClient:  map 50% reduce 13%
13/05/16 17:06:18 INFO mapred.JobClient:  map 51% reduce 13%
13/05/16 17:06:45 INFO mapred.JobClient:  map 52% reduce 13%
13/05/16 17:07:03 INFO mapred.JobClient:  map 53% reduce 13%
13/05/16 17:07:30 INFO mapred.JobClient:  map 54% reduce 13%
13/05/16 17:07:54 INFO mapred.JobClient:  map 55% reduce 13%
13/05/16 17:08:15 INFO mapred.JobClient:  map 56% reduce 13%
13/05/16 17:08:42 INFO mapred.JobClient:  map 57% reduce 13%
13/05/16 17:09:00 INFO mapred.JobClient:  map 58% reduce 13%
13/05/16 17:09:27 INFO mapred.JobClient:  map 59% reduce 13%
13/05/16 17:09:51 INFO mapred.JobClient:  map 60% reduce 13%
13/05/16 17:10:12 INFO mapred.JobClient:  map 61% reduce 13%
13/05/16 17:10:36 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 17:10:57 INFO mapred.JobClient:  map 63% reduce 13%
13/05/16 17:11:27 INFO mapred.JobClient:  map 64% reduce 13%
13/05/16 17:11:45 INFO mapred.JobClient:  map 65% reduce 13%
13/05/16 17:12:13 INFO mapred.JobClient:  map 66% reduce 13%
13/05/16 17:12:37 INFO mapred.JobClient:  map 67% reduce 13%
13/05/16 17:12:55 INFO mapred.JobClient:  map 68% reduce 13%
13/05/16 17:13:22 INFO mapred.JobClient:  map 69% reduce 13%
13/05/16 17:13:46 INFO mapred.JobClient:  map 70% reduce 13%
13/05/16 17:14:07 INFO mapred.JobClient:  map 71% reduce 13%
13/05/16 17:14:34 INFO mapred.JobClient:  map 72% reduce 13%
13/05/16 17:14:52 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 17:15:19 INFO mapred.JobClient:  map 74% reduce 13%
13/05/16 17:15:43 INFO mapred.JobClient:  map 75% reduce 13%
13/05/16 17:16:07 INFO mapred.JobClient:  map 76% reduce 13%
13/05/16 17:16:31 INFO mapred.JobClient:  map 77% reduce 13%
13/05/16 17:16:49 INFO mapred.JobClient:  map 78% reduce 13%
13/05/16 17:17:18 INFO mapred.JobClient:  map 79% reduce 13%
13/05/16 17:17:36 INFO mapred.JobClient:  map 80% reduce 13%
13/05/16 17:17:48 INFO mapred.JobClient:  map 80% reduce 15%
13/05/16 17:17:57 INFO mapred.JobClient:  map 80% reduce 16%
13/05/16 17:18:00 INFO mapred.JobClient:  map 80% reduce 18%
13/05/16 17:18:03 INFO mapred.JobClient:  map 80% reduce 20%
13/05/16 17:18:06 INFO mapred.JobClient:  map 80% reduce 21%
13/05/16 17:18:09 INFO mapred.JobClient:  map 80% reduce 23%
13/05/16 17:18:12 INFO mapred.JobClient:  map 80% reduce 26%
13/05/16 17:18:37 INFO mapred.JobClient:  map 81% reduce 26%
13/05/16 17:19:22 INFO mapred.JobClient:  map 82% reduce 26%
13/05/16 17:20:07 INFO mapred.JobClient:  map 83% reduce 26%
13/05/16 17:20:52 INFO mapred.JobClient:  map 84% reduce 26%
13/05/16 17:21:37 INFO mapred.JobClient:  map 85% reduce 26%
13/05/16 17:22:29 INFO mapred.JobClient:  map 86% reduce 26%
13/05/16 17:23:14 INFO mapred.JobClient:  map 87% reduce 26%
13/05/16 17:24:02 INFO mapred.JobClient:  map 88% reduce 26%
13/05/16 17:24:44 INFO mapred.JobClient:  map 89% reduce 26%
13/05/16 17:25:28 INFO mapred.JobClient:  map 90% reduce 26%
13/05/16 17:26:19 INFO mapred.JobClient:  map 91% reduce 26%
13/05/16 17:27:04 INFO mapred.JobClient:  map 92% reduce 26%
13/05/16 17:27:49 INFO mapred.JobClient:  map 93% reduce 26%
13/05/16 17:28:34 INFO mapred.JobClient:  map 94% reduce 26%
13/05/16 17:29:19 INFO mapred.JobClient:  map 95% reduce 26%
13/05/16 17:30:07 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 17:30:58 INFO mapred.JobClient:  map 97% reduce 26%
13/05/16 17:31:43 INFO mapred.JobClient:  map 98% reduce 26%
13/05/16 17:32:25 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 17:33:14 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 17:33:26 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 17:33:29 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 17:33:35 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 17:33:38 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 17:33:44 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 17:33:53 INFO mapred.JobClient:  map 100% reduce 51%
13/05/16 17:33:56 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 17:33:59 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 17:34:02 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 17:34:05 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 17:34:08 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 17:34:13 INFO mapred.JobClient: Job complete: job_201305161639_0001
13/05/16 17:34:13 INFO mapred.JobClient: Counters: 29
13/05/16 17:34:13 INFO mapred.JobClient:   Job Counters 
13/05/16 17:34:13 INFO mapred.JobClient:     Launched reduce tasks=4
13/05/16 17:34:13 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=28597410
13/05/16 17:34:13 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 17:34:13 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 17:34:13 INFO mapred.JobClient:     Launched map tasks=30
13/05/16 17:34:13 INFO mapred.JobClient:     Data-local map tasks=30
13/05/16 17:34:13 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=7734351
13/05/16 17:34:13 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 17:34:13 INFO mapred.JobClient:     Bytes Written=485112
13/05/16 17:34:13 INFO mapred.JobClient:   FileSystemCounters
13/05/16 17:34:13 INFO mapred.JobClient:     FILE_BYTES_READ=5338561738
13/05/16 17:34:13 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 17:34:13 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7612018157
13/05/16 17:34:13 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485112
13/05/16 17:34:13 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 17:34:13 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 17:34:13 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 17:34:13 INFO mapred.JobClient:     Map output materialized bytes=2155156955
13/05/16 17:34:13 INFO mapred.JobClient:     Map input records=120000000
13/05/16 17:34:13 INFO mapred.JobClient:     Reduce shuffle bytes=2083311647
13/05/16 17:34:13 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 17:34:13 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 17:34:13 INFO mapred.JobClient:     CPU time spent (ms)=28644980
13/05/16 17:34:13 INFO mapred.JobClient:     Total committed heap usage (bytes)=43883495424
13/05/16 17:34:13 INFO mapred.JobClient:     Combine input records=0
13/05/16 17:34:13 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 17:34:13 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 17:34:13 INFO mapred.JobClient:     Reduce input groups=20000
13/05/16 17:34:13 INFO mapred.JobClient:     Combine output records=0
13/05/16 17:34:13 INFO mapred.JobClient:     Physical memory (bytes) snapshot=35299397632
13/05/16 17:34:13 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 17:34:13 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=658303791104
13/05/16 17:34:13 INFO mapred.JobClient:     Map output records=120000000
Execution Time 2915905 ms

real	48m36.960s
user	0m6.300s
sys	0m1.054s
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
jmg3     22713  0.0  0.0 106084  1404 ?        Ss   17:34   0:00 bash -c ps aux | grep java
jmg3     22729  0.0  0.0 103232   840 ?        S    17:34   0:00 grep java
jmg3      6570  0.0  0.0  59072  3528 pts/0    S+   17:34   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      6576  0.0  0.0 106084  1396 ?        Ss   17:34   0:00 bash -c ps aux | grep java
jmg3      6592  0.0  0.0 103232   840 ?        S    17:34   0:00 grep java
