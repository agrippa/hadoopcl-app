run_opencl_test.sh: Running kmeans with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     11278  0.0  0.0 106084  1400 ?        Ss   12:35   0:00 bash -c ps aux | grep java
jmg3     11294  0.0  0.0 103232   844 ?        S    12:35   0:00 grep java
jmg3     19890  0.0  0.0  59072  3528 pts/0    S+   12:35   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     19896  0.0  0.0 106084  1400 ?        Ss   12:35   0:00 bash -c ps aux | grep java
jmg3     19912  0.0  0.0 103232   844 ?        S    12:35   0:00 grep java
rm: cannot remove `/tmp/hsperfdata_ah29': Operation not permitted
Done
Setting path to /tmp/1295632.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1295632.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-009.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>2</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>268435456</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1295632.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-009.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-007
-----------------------------------------------------
gpu-009
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:35:25 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-009.davinci.rice.edu/192.168.110.209
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 12:35:26 INFO util.GSet: VM type       = 64-bit
13/05/16 12:35:26 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 12:35:26 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 12:35:26 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 12:35:26 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 12:35:26 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 12:35:26 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 12:35:26 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 12:35:26 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 12:35:26 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 12:35:26 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 12:35:26 INFO common.Storage: Storage directory /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 12:35:26 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-009.davinci.rice.edu/192.168.110.209
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-007: no tasktracker to stop
no namenode to stop
gpu-007: no datanode to stop
gpu-009: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-009.davinci.rice.edu.out
gpu-007: starting datanode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-007.davinci.rice.edu.out
gpu-009: starting secondarynamenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-009.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-009.davinci.rice.edu.out
gpu-007: starting tasktracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-007.davinci.rice.edu.out
gpu-007: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:37:02 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 12:37:02 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 12:37:02 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:37:02 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:37:02 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:37:02 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:37:02 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 12:37:02 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 12:37:03 INFO mapred.JobClient: Running job: job_201305161235_0001
13/05/16 12:37:04 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 12:37:29 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 12:37:32 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:37:47 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 12:37:52 INFO mapred.JobClient: Task Id : attempt_201305161235_0001_m_000002_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161235_0001_m_000002_0: Scope block from 31 to  64
13/05/16 12:38:00 INFO mapred.JobClient:  map 36% reduce 0%
13/05/16 12:38:03 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 12:38:05 INFO mapred.JobClient: Task Id : attempt_201305161235_0001_m_000007_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161235_0001_m_000007_0: Scope block from 31 to  64
13/05/16 12:38:15 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:38:36 INFO mapred.JobClient:  map 46% reduce 0%
13/05/16 12:38:45 INFO mapred.JobClient:  map 59% reduce 2%
13/05/16 12:38:48 INFO mapred.JobClient:  map 60% reduce 4%
13/05/16 12:38:51 INFO mapred.JobClient:  map 62% reduce 5%
13/05/16 12:38:54 INFO mapred.JobClient:  map 73% reduce 5%
13/05/16 12:38:57 INFO mapred.JobClient:  map 73% reduce 8%
13/05/16 12:39:00 INFO mapred.JobClient:  map 73% reduce 10%
13/05/16 12:39:03 INFO mapred.JobClient:  map 73% reduce 11%
13/05/16 12:39:06 INFO mapred.JobClient:  map 80% reduce 14%
13/05/16 12:39:15 INFO mapred.JobClient:  map 86% reduce 14%
13/05/16 12:39:21 INFO mapred.JobClient:  map 86% reduce 15%
13/05/16 12:39:30 INFO mapred.JobClient:  map 86% reduce 16%
13/05/16 12:39:33 INFO mapred.JobClient:  map 93% reduce 16%
13/05/16 12:39:39 INFO mapred.JobClient:  map 93% reduce 17%
13/05/16 12:39:42 INFO mapred.JobClient:  map 93% reduce 18%
13/05/16 12:39:49 INFO mapred.JobClient:  map 100% reduce 19%
13/05/16 12:39:55 INFO mapred.JobClient:  map 100% reduce 22%
13/05/16 12:40:07 INFO mapred.JobClient:  map 100% reduce 23%
13/05/16 12:40:13 INFO mapred.JobClient:  map 96% reduce 24%
13/05/16 12:40:16 INFO mapred.JobClient:  map 96% reduce 25%
13/05/16 12:40:18 INFO mapred.JobClient: Task Id : attempt_201305161235_0001_m_000028_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161235_0001_m_000028_0: Scope block from 31 to  64
13/05/16 12:40:19 INFO mapred.JobClient:  map 96% reduce 26%
13/05/16 12:40:28 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 12:40:34 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 12:40:37 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 12:40:40 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 12:40:55 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 12:41:04 INFO mapred.JobClient:  map 100% reduce 71%
13/05/16 12:41:07 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 12:41:13 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 12:41:18 INFO mapred.JobClient: Job complete: job_201305161235_0001
13/05/16 12:41:18 INFO mapred.JobClient: Counters: 29
13/05/16 12:41:18 INFO mapred.JobClient:   Job Counters 
13/05/16 12:41:18 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 12:41:18 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2069621
13/05/16 12:41:18 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 12:41:18 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 12:41:18 INFO mapred.JobClient:     Launched map tasks=33
13/05/16 12:41:18 INFO mapred.JobClient:     Data-local map tasks=33
13/05/16 12:41:18 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=777815
13/05/16 12:41:18 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 12:41:18 INFO mapred.JobClient:     Bytes Written=485190
13/05/16 12:41:18 INFO mapred.JobClient:   FileSystemCounters
13/05/16 12:41:18 INFO mapred.JobClient:     FILE_BYTES_READ=5332202275
13/05/16 12:41:18 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 12:41:18 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7607377211
13/05/16 12:41:18 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485190
13/05/16 12:41:18 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 12:41:18 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 12:41:18 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 12:41:18 INFO mapred.JobClient:     Map output materialized bytes=2154835665
13/05/16 12:41:18 INFO mapred.JobClient:     Map input records=120000000
13/05/16 12:41:18 INFO mapred.JobClient:     Reduce shuffle bytes=2083006701
13/05/16 12:41:18 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 12:41:18 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 12:41:18 INFO mapred.JobClient:     CPU time spent (ms)=1405730
13/05/16 12:41:18 INFO mapred.JobClient:     Total committed heap usage (bytes)=43706548224
13/05/16 12:41:18 INFO mapred.JobClient:     Combine input records=0
13/05/16 12:41:18 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 12:41:18 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 12:41:18 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 12:41:18 INFO mapred.JobClient:     Combine output records=0
13/05/16 12:41:18 INFO mapred.JobClient:     Physical memory (bytes) snapshot=35942641664
13/05/16 12:41:18 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 12:41:18 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091580575744
13/05/16 12:41:18 INFO mapred.JobClient:     Map output records=120000000
Execution Time 255951 ms

real	4m17.012s
user	0m2.983s
sys	0m0.289s
Grepping Logs
grep: /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     15116  0.0  0.0 106084  1396 ?        Ss   12:41   0:00 bash -c ps aux | grep java
jmg3     15132  0.0  0.0 103232   844 ?        S    12:41   0:00 grep java
jmg3     21023  0.0  0.0  59072  3532 pts/0    S+   12:41   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     21029  0.0  0.0 106084  1396 ?        Ss   12:41   0:00 bash -c ps aux | grep java
jmg3     21045  0.0  0.0 103232   840 ?        S    12:41   0:00 grep java
run_opencl_test.sh: Running kmeans with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     15158  0.0  0.0 106084  1404 ?        Ss   12:41   0:00 bash -c ps aux | grep java
jmg3     15174  0.0  0.0 103232   840 ?        S    12:41   0:00 grep java
jmg3     21082  0.0  0.0  59072  3528 pts/0    S+   12:41   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     21088  0.0  0.0 106084  1396 ?        Ss   12:41   0:00 bash -c ps aux | grep java
jmg3     21104  0.0  0.0 103232   844 ?        S    12:41   0:00 grep java
rm: cannot remove `/tmp/hsperfdata_ah29': Operation not permitted
Done
Setting path to /tmp/1295632.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1295632.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-009.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>2</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>268435456</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1295632.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-009.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-007
-----------------------------------------------------
gpu-009
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:41:40 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-009.davinci.rice.edu/192.168.110.209
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 12:41:41 INFO util.GSet: VM type       = 64-bit
13/05/16 12:41:41 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 12:41:41 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 12:41:41 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 12:41:41 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 12:41:41 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 12:41:41 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 12:41:41 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 12:41:41 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 12:41:41 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 12:41:41 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 12:41:41 INFO common.Storage: Storage directory /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 12:41:41 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-009.davinci.rice.edu/192.168.110.209
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-007: no tasktracker to stop
no namenode to stop
gpu-007: no datanode to stop
gpu-009: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-009.davinci.rice.edu.out
gpu-007: starting datanode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-007.davinci.rice.edu.out
gpu-009: starting secondarynamenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-009.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-009.davinci.rice.edu.out
gpu-007: starting tasktracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-007.davinci.rice.edu.out
gpu-007: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:43:25 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 12:43:25 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 12:43:25 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:43:25 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:43:25 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:43:25 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:43:25 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 12:43:25 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 12:43:26 INFO mapred.JobClient: Running job: job_201305161241_0001
13/05/16 12:43:27 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 12:43:50 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 12:43:53 INFO mapred.JobClient:  map 35% reduce 0%
13/05/16 12:43:56 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:44:45 INFO mapred.JobClient:  map 46% reduce 0%
13/05/16 12:45:00 INFO mapred.JobClient:  map 53% reduce 0%
13/05/16 12:45:03 INFO mapred.JobClient:  map 53% reduce 1%
13/05/16 12:45:06 INFO mapred.JobClient:  map 53% reduce 2%
13/05/16 12:45:09 INFO mapred.JobClient:  map 60% reduce 2%
13/05/16 12:45:12 INFO mapred.JobClient:  map 60% reduce 4%
13/05/16 12:45:15 INFO mapred.JobClient:  map 60% reduce 6%
13/05/16 12:45:18 INFO mapred.JobClient:  map 60% reduce 7%
13/05/16 12:45:21 INFO mapred.JobClient:  map 65% reduce 10%
13/05/16 12:45:24 INFO mapred.JobClient:  map 82% reduce 12%
13/05/16 12:45:27 INFO mapred.JobClient:  map 83% reduce 14%
13/05/16 12:45:34 INFO mapred.JobClient:  map 86% reduce 14%
13/05/16 12:45:40 INFO mapred.JobClient:  map 93% reduce 15%
13/05/16 12:45:43 INFO mapred.JobClient:  map 93% reduce 17%
13/05/16 12:45:52 INFO mapred.JobClient:  map 90% reduce 18%
13/05/16 12:45:55 INFO mapred.JobClient:  map 96% reduce 18%
13/05/16 12:45:57 INFO mapred.JobClient: Task Id : attempt_201305161241_0001_m_000020_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161241_0001_m_000020_0: Scope block from 31 to  64
13/05/16 12:46:04 INFO mapred.JobClient:  map 96% reduce 19%
13/05/16 12:46:10 INFO mapred.JobClient:  map 96% reduce 20%
13/05/16 12:46:19 INFO mapred.JobClient:  map 100% reduce 20%
13/05/16 12:46:28 INFO mapred.JobClient:  map 100% reduce 21%
13/05/16 12:46:31 INFO mapred.JobClient:  map 100% reduce 22%
13/05/16 12:46:34 INFO mapred.JobClient:  map 100% reduce 23%
13/05/16 12:46:37 INFO mapred.JobClient:  map 100% reduce 24%
13/05/16 12:46:40 INFO mapred.JobClient:  map 100% reduce 25%
13/05/16 12:46:43 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 12:46:46 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 12:46:49 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 12:46:55 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 12:46:58 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 12:47:01 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 12:47:07 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 12:47:14 INFO mapred.JobClient:  map 100% reduce 40%
13/05/16 12:47:17 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 12:47:20 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 12:47:26 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 12:47:31 INFO mapred.JobClient: Job complete: job_201305161241_0001
13/05/16 12:47:31 INFO mapred.JobClient: Counters: 29
13/05/16 12:47:31 INFO mapred.JobClient:   Job Counters 
13/05/16 12:47:31 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 12:47:31 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2156697
13/05/16 12:47:31 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 12:47:31 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 12:47:31 INFO mapred.JobClient:     Launched map tasks=31
13/05/16 12:47:31 INFO mapred.JobClient:     Data-local map tasks=31
13/05/16 12:47:31 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=733180
13/05/16 12:47:31 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 12:47:31 INFO mapred.JobClient:     Bytes Written=485190
13/05/16 12:47:31 INFO mapred.JobClient:   FileSystemCounters
13/05/16 12:47:31 INFO mapred.JobClient:     FILE_BYTES_READ=5332201117
13/05/16 12:47:31 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 12:47:31 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7607376053
13/05/16 12:47:31 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485190
13/05/16 12:47:31 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 12:47:31 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 12:47:31 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 12:47:31 INFO mapred.JobClient:     Map output materialized bytes=2154835665
13/05/16 12:47:31 INFO mapred.JobClient:     Map input records=120000000
13/05/16 12:47:31 INFO mapred.JobClient:     Reduce shuffle bytes=2083008381
13/05/16 12:47:31 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 12:47:31 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 12:47:31 INFO mapred.JobClient:     CPU time spent (ms)=1511030
13/05/16 12:47:31 INFO mapred.JobClient:     Total committed heap usage (bytes)=46375501824
13/05/16 12:47:31 INFO mapred.JobClient:     Combine input records=0
13/05/16 12:47:31 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 12:47:31 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 12:47:31 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 12:47:31 INFO mapred.JobClient:     Combine output records=0
13/05/16 12:47:31 INFO mapred.JobClient:     Physical memory (bytes) snapshot=34259406848
13/05/16 12:47:31 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 12:47:31 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091512414208
13/05/16 12:47:31 INFO mapred.JobClient:     Map output records=120000000
Execution Time 245795 ms

real	4m6.836s
user	0m2.961s
sys	0m0.303s
Grepping Logs
grep: /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     18688  0.0  0.0 106084  1396 ?        Ss   12:47   0:00 bash -c ps aux | grep java
jmg3     18704  0.0  0.0 103232   844 ?        S    12:47   0:00 grep java
jmg3     22206  0.0  0.0  59072  3528 pts/0    S+   12:47   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     22212  0.0  0.0 106084  1396 ?        Ss   12:47   0:00 bash -c ps aux | grep java
jmg3     22228  0.0  0.0 103232   840 ?        S    12:47   0:00 grep java
run_opencl_test.sh: Running kmeans with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     18730  0.0  0.0 106084  1396 ?        Ss   12:47   0:00 bash -c ps aux | grep java
jmg3     18746  0.0  0.0 103232   844 ?        S    12:47   0:00 grep java
jmg3     22265  0.0  0.0  59072  3524 pts/0    S+   12:47   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     22271  0.0  0.0 106084  1396 ?        Ss   12:47   0:00 bash -c ps aux | grep java
jmg3     22287  0.0  0.0 103232   840 ?        S    12:47   0:00 grep java
rm: cannot remove `/tmp/hsperfdata_ah29': Operation not permitted
Done
Setting path to /tmp/1295632.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1295632.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-009.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>2</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>268435456</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1295632.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-009.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-007
-----------------------------------------------------
gpu-009
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:47:53 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-009.davinci.rice.edu/192.168.110.209
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 12:47:53 INFO util.GSet: VM type       = 64-bit
13/05/16 12:47:53 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 12:47:53 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 12:47:53 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 12:47:53 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 12:47:53 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 12:47:53 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 12:47:53 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 12:47:53 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 12:47:53 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 12:47:53 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 12:47:54 INFO common.Storage: Storage directory /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 12:47:54 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-009.davinci.rice.edu/192.168.110.209
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-007: no tasktracker to stop
no namenode to stop
gpu-007: no datanode to stop
gpu-009: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-009.davinci.rice.edu.out
gpu-007: starting datanode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-007.davinci.rice.edu.out
gpu-009: starting secondarynamenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-009.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-009.davinci.rice.edu.out
gpu-007: starting tasktracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-007.davinci.rice.edu.out
gpu-007: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:49:42 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 12:49:42 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 12:49:42 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:49:42 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:49:42 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:49:42 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:49:42 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 12:49:42 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 12:49:44 INFO mapred.JobClient: Running job: job_201305161247_0001
13/05/16 12:49:45 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 12:50:06 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 12:50:10 INFO mapred.JobClient:  map 42% reduce 0%
13/05/16 12:50:13 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:51:13 INFO mapred.JobClient:  map 46% reduce 0%
13/05/16 12:51:19 INFO mapred.JobClient:  map 49% reduce 0%
13/05/16 12:51:22 INFO mapred.JobClient:  map 53% reduce 2%
13/05/16 12:51:25 INFO mapred.JobClient:  map 53% reduce 4%
13/05/16 12:51:28 INFO mapred.JobClient:  map 67% reduce 4%
13/05/16 12:51:31 INFO mapred.JobClient:  map 80% reduce 7%
13/05/16 12:51:34 INFO mapred.JobClient:  map 80% reduce 9%
13/05/16 12:51:37 INFO mapred.JobClient:  map 80% reduce 11%
13/05/16 12:51:40 INFO mapred.JobClient:  map 83% reduce 13%
13/05/16 12:51:43 INFO mapred.JobClient:  map 83% reduce 14%
13/05/16 12:51:46 INFO mapred.JobClient:  map 80% reduce 15%
13/05/16 12:51:49 INFO mapred.JobClient:  map 86% reduce 15%
13/05/16 12:51:51 INFO mapred.JobClient: Task Id : attempt_201305161247_0001_m_000020_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161247_0001_m_000020_0: Scope block from 31 to  64
13/05/16 12:52:07 INFO mapred.JobClient:  map 93% reduce 16%
13/05/16 12:52:10 INFO mapred.JobClient:  map 93% reduce 17%
13/05/16 12:52:19 INFO mapred.JobClient:  map 96% reduce 17%
13/05/16 12:52:31 INFO mapred.JobClient:  map 96% reduce 18%
13/05/16 12:52:34 INFO mapred.JobClient:  map 96% reduce 19%
13/05/16 12:52:37 INFO mapred.JobClient:  map 100% reduce 20%
13/05/16 12:52:40 INFO mapred.JobClient:  map 100% reduce 21%
13/05/16 12:52:46 INFO mapred.JobClient:  map 100% reduce 22%
13/05/16 12:52:49 INFO mapred.JobClient:  map 100% reduce 24%
13/05/16 12:52:59 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 12:53:02 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 12:53:14 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 12:53:17 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 12:53:20 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 12:53:23 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 12:53:29 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 12:53:32 INFO mapred.JobClient:  map 100% reduce 47%
13/05/16 12:53:35 INFO mapred.JobClient:  map 100% reduce 52%
13/05/16 12:53:38 INFO mapred.JobClient:  map 100% reduce 75%
13/05/16 12:53:41 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 12:53:44 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 12:53:50 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 12:53:55 INFO mapred.JobClient: Job complete: job_201305161247_0001
13/05/16 12:53:55 INFO mapred.JobClient: Counters: 29
13/05/16 12:53:55 INFO mapred.JobClient:   Job Counters 
13/05/16 12:53:55 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 12:53:55 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2170867
13/05/16 12:53:55 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 12:53:55 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 12:53:55 INFO mapred.JobClient:     Launched map tasks=31
13/05/16 12:53:55 INFO mapred.JobClient:     Data-local map tasks=31
13/05/16 12:53:55 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=762137
13/05/16 12:53:55 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 12:53:55 INFO mapred.JobClient:     Bytes Written=485190
13/05/16 12:53:55 INFO mapred.JobClient:   FileSystemCounters
13/05/16 12:53:55 INFO mapred.JobClient:     FILE_BYTES_READ=5332200639
13/05/16 12:53:55 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 12:53:55 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7607375575
13/05/16 12:53:55 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485190
13/05/16 12:53:55 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 12:53:55 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 12:53:55 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 12:53:55 INFO mapred.JobClient:     Map output materialized bytes=2154835665
13/05/16 12:53:55 INFO mapred.JobClient:     Map input records=120000000
13/05/16 12:53:55 INFO mapred.JobClient:     Reduce shuffle bytes=2083006701
13/05/16 12:53:55 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 12:53:55 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 12:53:55 INFO mapred.JobClient:     CPU time spent (ms)=1591660
13/05/16 12:53:55 INFO mapred.JobClient:     Total committed heap usage (bytes)=45880967168
13/05/16 12:53:55 INFO mapred.JobClient:     Combine input records=0
13/05/16 12:53:55 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 12:53:55 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 12:53:55 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 12:53:55 INFO mapred.JobClient:     Combine output records=0
13/05/16 12:53:55 INFO mapred.JobClient:     Physical memory (bytes) snapshot=36706893824
13/05/16 12:53:55 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 12:53:55 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091580575744
13/05/16 12:53:55 INFO mapred.JobClient:     Map output records=120000000
Execution Time 253148 ms

real	4m14.220s
user	0m2.928s
sys	0m0.297s
Grepping Logs
grep: /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     22257  0.0  0.0 106084  1396 ?        Ss   12:53   0:00 bash -c ps aux | grep java
jmg3     22273  0.0  0.0 103232   840 ?        S    12:53   0:00 grep java
jmg3     23400  0.0  0.0  59072  3528 pts/0    S+   12:53   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     23406  0.0  0.0 106084  1400 ?        Ss   12:53   0:00 bash -c ps aux | grep java
jmg3     23422  0.0  0.0 103232   844 ?        S    12:53   0:00 grep java
run_opencl_test.sh: Running kmeans with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     22299  0.0  0.0 106084  1400 ?        Ss   12:53   0:00 bash -c ps aux | grep java
jmg3     22315  0.0  0.0 103232   844 ?        S    12:53   0:00 grep java
jmg3     23459  0.0  0.0  59072  3528 pts/0    S+   12:53   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     23465  0.0  0.0 106084  1396 ?        Ss   12:53   0:00 bash -c ps aux | grep java
jmg3     23481  0.0  0.0 103232   840 ?        S    12:53   0:00 grep java
rm: cannot remove `/tmp/hsperfdata_ah29': Operation not permitted
Done
Setting path to /tmp/1295632.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1295632.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-009.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>2</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>268435456</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1295632.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-009.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-007
-----------------------------------------------------
gpu-009
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:54:17 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-009.davinci.rice.edu/192.168.110.209
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 12:54:17 INFO util.GSet: VM type       = 64-bit
13/05/16 12:54:17 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 12:54:17 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 12:54:17 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 12:54:17 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 12:54:17 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 12:54:17 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 12:54:17 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 12:54:17 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 12:54:17 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 12:54:18 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 12:54:18 INFO common.Storage: Storage directory /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 12:54:18 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-009.davinci.rice.edu/192.168.110.209
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-007: no tasktracker to stop
no namenode to stop
gpu-007: no datanode to stop
gpu-009: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-009.davinci.rice.edu.out
gpu-007: starting datanode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-007.davinci.rice.edu.out
gpu-009: starting secondarynamenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-009.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-009.davinci.rice.edu.out
gpu-007: starting tasktracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-007.davinci.rice.edu.out
gpu-007: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 12:56:18 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 12:56:18 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 12:56:18 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:56:18 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:56:18 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:56:18 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 12:56:18 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 12:56:18 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 12:56:21 INFO mapred.JobClient: Running job: job_201305161254_0001
13/05/16 12:56:22 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 12:56:45 INFO mapred.JobClient:  map 14% reduce 0%
13/05/16 12:56:48 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:57:06 INFO mapred.JobClient:  map 40% reduce 0%
13/05/16 12:57:11 INFO mapred.JobClient: Task Id : attempt_201305161254_0001_m_000005_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161254_0001_m_000005_0: Scope block from 31 to  64
13/05/16 12:57:21 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 12:57:48 INFO mapred.JobClient:  map 46% reduce 0%
13/05/16 12:57:54 INFO mapred.JobClient:  map 46% reduce 1%
13/05/16 12:57:57 INFO mapred.JobClient:  map 46% reduce 2%
13/05/16 12:58:00 INFO mapred.JobClient:  map 60% reduce 4%
13/05/16 12:58:03 INFO mapred.JobClient:  map 60% reduce 6%
13/05/16 12:58:09 INFO mapred.JobClient:  map 63% reduce 6%
13/05/16 12:58:12 INFO mapred.JobClient:  map 63% reduce 7%
13/05/16 12:58:18 INFO mapred.JobClient:  map 63% reduce 8%
13/05/16 12:58:21 INFO mapred.JobClient:  map 73% reduce 11%
13/05/16 12:58:24 INFO mapred.JobClient:  map 73% reduce 13%
13/05/16 12:58:30 INFO mapred.JobClient:  map 73% reduce 14%
13/05/16 12:58:42 INFO mapred.JobClient:  map 86% reduce 14%
13/05/16 12:59:07 INFO mapred.JobClient:  map 90% reduce 15%
13/05/16 12:59:13 INFO mapred.JobClient:  map 90% reduce 16%
13/05/16 12:59:16 INFO mapred.JobClient:  map 93% reduce 16%
13/05/16 12:59:19 INFO mapred.JobClient:  map 93% reduce 17%
13/05/16 12:59:22 INFO mapred.JobClient:  map 93% reduce 18%
13/05/16 12:59:25 INFO mapred.JobClient:  map 100% reduce 20%
13/05/16 12:59:31 INFO mapred.JobClient:  map 100% reduce 21%
13/05/16 12:59:43 INFO mapred.JobClient:  map 100% reduce 22%
13/05/16 12:59:46 INFO mapred.JobClient:  map 100% reduce 23%
13/05/16 12:59:58 INFO mapred.JobClient:  map 100% reduce 24%
13/05/16 13:00:01 INFO mapred.JobClient:  map 100% reduce 25%
13/05/16 13:00:07 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 13:00:10 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 13:00:16 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 13:00:22 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 13:00:28 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 13:00:34 INFO mapred.JobClient:  map 100% reduce 40%
13/05/16 13:00:37 INFO mapred.JobClient:  map 100% reduce 65%
13/05/16 13:00:40 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 13:00:43 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 13:00:46 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 13:00:49 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 13:00:54 INFO mapred.JobClient: Job complete: job_201305161254_0001
13/05/16 13:00:54 INFO mapred.JobClient: Counters: 29
13/05/16 13:00:54 INFO mapred.JobClient:   Job Counters 
13/05/16 13:00:54 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 13:00:54 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2497824
13/05/16 13:00:54 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 13:00:54 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 13:00:54 INFO mapred.JobClient:     Launched map tasks=31
13/05/16 13:00:54 INFO mapred.JobClient:     Data-local map tasks=31
13/05/16 13:00:54 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=886744
13/05/16 13:00:54 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 13:00:54 INFO mapred.JobClient:     Bytes Written=485190
13/05/16 13:00:54 INFO mapred.JobClient:   FileSystemCounters
13/05/16 13:00:54 INFO mapred.JobClient:     FILE_BYTES_READ=5332199589
13/05/16 13:00:54 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 13:00:54 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7607374525
13/05/16 13:00:54 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485190
13/05/16 13:00:54 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 13:00:54 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 13:00:54 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 13:00:54 INFO mapred.JobClient:     Map output materialized bytes=2154835665
13/05/16 13:00:54 INFO mapred.JobClient:     Map input records=120000000
13/05/16 13:00:54 INFO mapred.JobClient:     Reduce shuffle bytes=2083004641
13/05/16 13:00:54 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 13:00:54 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 13:00:54 INFO mapred.JobClient:     CPU time spent (ms)=1628240
13/05/16 13:00:54 INFO mapred.JobClient:     Total committed heap usage (bytes)=45375619072
13/05/16 13:00:54 INFO mapred.JobClient:     Combine input records=0
13/05/16 13:00:54 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 13:00:54 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 13:00:54 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 13:00:54 INFO mapred.JobClient:     Combine output records=0
13/05/16 13:00:54 INFO mapred.JobClient:     Physical memory (bytes) snapshot=36780453888
13/05/16 13:00:54 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 13:00:54 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091376091136
13/05/16 13:00:54 INFO mapred.JobClient:     Map output records=120000000
Execution Time 275694 ms

real	4m36.766s
user	0m2.968s
sys	0m0.290s
Grepping Logs
grep: /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     25863  0.0  0.0 106084  1396 ?        Ss   13:00   0:00 bash -c ps aux | grep java
jmg3     25879  0.0  0.0 103232   844 ?        S    13:00   0:00 grep java
jmg3     24589  0.0  0.0  59072  3528 pts/0    S+   13:00   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     24595  0.0  0.0 106084  1400 ?        Ss   13:00   0:00 bash -c ps aux | grep java
jmg3     24611  0.0  0.0 103232   844 ?        S    13:00   0:00 grep java
run_opencl_test.sh: Running kmeans with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     25905  0.0  0.0 106084  1400 ?        Ss   13:00   0:00 bash -c ps aux | grep java
jmg3     25921  0.0  0.0 103232   844 ?        S    13:00   0:00 grep java
jmg3     24648  0.0  0.0  59072  3528 pts/0    S+   13:00   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     24654  0.0  0.0 106084  1404 ?        Ss   13:00   0:00 bash -c ps aux | grep java
jmg3     24670  0.0  0.0 103232   840 ?        S    13:00   0:00 grep java
rm: cannot remove `/tmp/hsperfdata_ah29': Operation not permitted
Done
Setting path to /tmp/1295632.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1295632.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${CLASSPATH}:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-009.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>1</value>
  <name>opencl.reducer.cpumult</name><value>2</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx16G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
  <name>mapred.user.jobconf.limit</name><value>10485760</value>
-----------------------------------------------------
  <name>dfs.safemode.threshold.pct</name>
  <value>0</value>
  <name>dfs.replication</name><value>3</value>
  <name>dfs.block.size</name><value>268435456</value>
  <name>dfs.datanode.handler.count</name><value>3</value>
  <name>dfs.namenode.handler.count</name><value>10</value>
  <name>dfs.datanode.max.xcievers</name><value>256</value>
-----------------------------------------------------
  <name>hadoop.tmp.dir</name>
  <value>/tmp/1295632.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-009.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-007
-----------------------------------------------------
gpu-009
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/16 13:01:16 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-009.davinci.rice.edu/192.168.110.209
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 13:01:16 INFO util.GSet: VM type       = 64-bit
13/05/16 13:01:16 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 13:01:16 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 13:01:16 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 13:01:16 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 13:01:16 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 13:01:16 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 13:01:16 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 13:01:16 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 13:01:16 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 13:01:16 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 13:01:17 INFO common.Storage: Storage directory /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 13:01:17 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-009.davinci.rice.edu/192.168.110.209
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-007: no tasktracker to stop
no namenode to stop
gpu-007: no datanode to stop
gpu-009: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-009.davinci.rice.edu.out
gpu-007: starting datanode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-007.davinci.rice.edu.out
gpu-009: starting secondarynamenode, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-009.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-009.davinci.rice.edu.out
gpu-007: starting tasktracker, logging to /tmp/1295632.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-007.davinci.rice.edu.out
gpu-007: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 13:03:56 INFO util.NativeCodeLoader: Loaded the native-hadoop library
13/05/16 13:03:56 INFO zlib.ZlibFactory: Successfully loaded & initialized native-zlib library
13/05/16 13:03:56 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:03:56 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:03:56 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:03:56 INFO compress.CodecPool: Got brand-new decompressor
13/05/16 13:03:56 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 13:03:56 INFO input.FileInputFormat: Total input paths to process : 30
13/05/16 13:03:57 INFO mapred.JobClient: Running job: job_201305161301_0001
13/05/16 13:03:58 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 13:04:20 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 13:04:23 INFO mapred.JobClient:  map 43% reduce 0%
13/05/16 13:05:05 INFO mapred.JobClient:  map 46% reduce 0%
13/05/16 13:05:20 INFO mapred.JobClient:  map 50% reduce 0%
13/05/16 13:05:26 INFO mapred.JobClient:  map 50% reduce 1%
13/05/16 13:05:29 INFO mapred.JobClient:  map 50% reduce 4%
13/05/16 13:05:32 INFO mapred.JobClient:  map 60% reduce 5%
13/05/16 13:05:35 INFO mapred.JobClient:  map 63% reduce 6%
13/05/16 13:05:38 INFO mapred.JobClient:  map 63% reduce 8%
13/05/16 13:05:41 INFO mapred.JobClient:  map 70% reduce 9%
13/05/16 13:05:44 INFO mapred.JobClient:  map 70% reduce 10%
13/05/16 13:05:50 INFO mapred.JobClient:  map 70% reduce 12%
13/05/16 13:05:53 INFO mapred.JobClient:  map 70% reduce 15%
13/05/16 13:05:57 INFO mapred.JobClient:  map 73% reduce 15%
13/05/16 13:06:00 INFO mapred.JobClient:  map 90% reduce 15%
13/05/16 13:06:15 INFO mapred.JobClient:  map 90% reduce 16%
13/05/16 13:06:19 INFO mapred.JobClient:  map 86% reduce 18%
13/05/16 13:06:22 INFO mapred.JobClient:  map 90% reduce 18%
13/05/16 13:06:24 INFO mapred.JobClient: Task Id : attempt_201305161301_0001_m_000020_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161301_0001_m_000020_0: Scope block from 31 to  64
13/05/16 13:06:25 INFO mapred.JobClient:  map 96% reduce 18%
13/05/16 13:06:40 INFO mapred.JobClient:  map 100% reduce 18%
13/05/16 13:06:43 INFO mapred.JobClient:  map 100% reduce 20%
13/05/16 13:06:46 INFO mapred.JobClient:  map 100% reduce 21%
13/05/16 13:06:52 INFO mapred.JobClient:  map 96% reduce 22%
13/05/16 13:07:00 INFO mapred.JobClient: Task Id : attempt_201305161301_0001_m_000028_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305161301_0001_m_000028_0: Scope block from 31 to  64
13/05/16 13:07:05 INFO mapred.JobClient:  map 96% reduce 23%
13/05/16 13:07:14 INFO mapred.JobClient:  map 100% reduce 23%
13/05/16 13:07:20 INFO mapred.JobClient:  map 100% reduce 24%
13/05/16 13:07:29 INFO mapred.JobClient:  map 100% reduce 25%
13/05/16 13:07:35 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 13:07:38 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 13:07:41 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 13:07:50 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 13:07:53 INFO mapred.JobClient:  map 100% reduce 46%
13/05/16 13:07:56 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 13:07:59 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 13:08:02 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 13:08:05 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 13:08:08 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 13:08:11 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 13:08:16 INFO mapred.JobClient: Job complete: job_201305161301_0001
13/05/16 13:08:16 INFO mapred.JobClient: Counters: 29
13/05/16 13:08:16 INFO mapred.JobClient:   Job Counters 
13/05/16 13:08:16 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 13:08:16 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2206709
13/05/16 13:08:16 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 13:08:16 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 13:08:16 INFO mapred.JobClient:     Launched map tasks=32
13/05/16 13:08:16 INFO mapred.JobClient:     Data-local map tasks=32
13/05/16 13:08:16 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=832312
13/05/16 13:08:16 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 13:08:16 INFO mapred.JobClient:     Bytes Written=485190
13/05/16 13:08:16 INFO mapred.JobClient:   FileSystemCounters
13/05/16 13:08:16 INFO mapred.JobClient:     FILE_BYTES_READ=5332204381
13/05/16 13:08:16 INFO mapred.JobClient:     HDFS_BYTES_READ=1811534108
13/05/16 13:08:16 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7607379317
13/05/16 13:08:16 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=485190
13/05/16 13:08:16 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 13:08:16 INFO mapred.JobClient:     Bytes Read=1811530128
13/05/16 13:08:16 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 13:08:16 INFO mapred.JobClient:     Map output materialized bytes=2154835665
13/05/16 13:08:16 INFO mapred.JobClient:     Map input records=120000000
13/05/16 13:08:16 INFO mapred.JobClient:     Reduce shuffle bytes=2097339403
13/05/16 13:08:16 INFO mapred.JobClient:     Spilled Records=415050090
13/05/16 13:08:16 INFO mapred.JobClient:     Map output bytes=2400000000
13/05/16 13:08:16 INFO mapred.JobClient:     CPU time spent (ms)=1665900
13/05/16 13:08:16 INFO mapred.JobClient:     Total committed heap usage (bytes)=45632061440
13/05/16 13:08:16 INFO mapred.JobClient:     Combine input records=0
13/05/16 13:08:16 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3980
13/05/16 13:08:16 INFO mapred.JobClient:     Reduce input records=120000000
13/05/16 13:08:16 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 13:08:16 INFO mapred.JobClient:     Combine output records=0
13/05/16 13:08:16 INFO mapred.JobClient:     Physical memory (bytes) snapshot=38731083776
13/05/16 13:08:16 INFO mapred.JobClient:     Reduce output records=20000
13/05/16 13:08:16 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091512414208
13/05/16 13:08:16 INFO mapred.JobClient:     Map output records=120000000
Execution Time 259634 ms

real	4m20.706s
user	0m2.969s
sys	0m0.314s
Grepping Logs
grep: /tmp/1295632.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     29611  0.0  0.0 106084  1400 ?        Ss   13:08   0:00 bash -c ps aux | grep java
jmg3     29627  0.0  0.0 103232   840 ?        S    13:08   0:00 grep java
jmg3     25794  0.0  0.0  59072  3528 pts/0    S+   13:08   0:00 ssh -o ConnectTimeout=2 gpu-009 ps aux | grep java
jmg3     25800  0.0  0.0 106084  1400 ?        Ss   13:08   0:00 bash -c ps aux | grep java
jmg3     25816  0.0  0.0 103232   844 ?        S    13:08   0:00 grep java
