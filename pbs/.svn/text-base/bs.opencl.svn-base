run_opencl_test.sh: Running blackscholes with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     14594  0.0  0.0 106084  1404 ?        Ss   21:35   0:00 bash -c ps aux | grep java
jmg3     14610  0.0  0.0 103232   844 ?        S    21:35   0:00 grep java
jmg3     13370  0.0  0.0  59072  3528 pts/0    S+   21:35   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     13376  0.0  0.0 106084  1400 ?        Ss   21:35   0:00 bash -c ps aux | grep java
jmg3     13392  0.0  0.0 103232   840 ?        S    21:35   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 24



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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
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

13/05/16 21:36:18 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 21:36:18 INFO util.GSet: VM type       = 64-bit
13/05/16 21:36:18 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 21:36:18 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 21:36:18 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 21:36:18 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 21:36:18 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 21:36:18 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 21:36:18 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 21:36:18 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 21:36:18 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 21:36:18 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 21:36:18 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 21:36:18 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 21:43:05 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 21:43:06 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 21:43:06 INFO mapred.JobClient: Running job: job_201305162136_0001
13/05/16 21:43:07 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 21:43:24 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 21:43:27 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 21:43:30 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 21:43:33 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 21:43:35 INFO mapred.JobClient: Task Id : attempt_201305162136_0001_m_000008_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305162136_0001_m_000008_0: Scope block from 31 to  64
attempt_201305162136_0001_m_000008_0: Scope block from 56 to  274
13/05/16 21:43:45 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 21:44:03 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 21:44:06 INFO mapred.JobClient:  map 37% reduce 0%
13/05/16 21:44:09 INFO mapred.JobClient:  map 41% reduce 1%
13/05/16 21:44:12 INFO mapred.JobClient:  map 45% reduce 3%
13/05/16 21:44:15 INFO mapred.JobClient:  map 48% reduce 3%
13/05/16 21:44:18 INFO mapred.JobClient:  map 50% reduce 4%
13/05/16 21:44:21 INFO mapred.JobClient:  map 50% reduce 6%
13/05/16 21:44:25 INFO mapred.JobClient:  map 50% reduce 8%
13/05/16 21:44:28 INFO mapred.JobClient:  map 51% reduce 8%
13/05/16 21:44:31 INFO mapred.JobClient:  map 52% reduce 8%
13/05/16 21:44:49 INFO mapred.JobClient:  map 55% reduce 8%
13/05/16 21:44:52 INFO mapred.JobClient:  map 61% reduce 9%
13/05/16 21:44:55 INFO mapred.JobClient:  map 63% reduce 12%
13/05/16 21:44:58 INFO mapred.JobClient:  map 67% reduce 15%
13/05/16 21:45:01 INFO mapred.JobClient:  map 70% reduce 16%
13/05/16 21:45:04 INFO mapred.JobClient:  map 73% reduce 16%
13/05/16 21:45:07 INFO mapred.JobClient:  map 77% reduce 16%
13/05/16 21:45:10 INFO mapred.JobClient:  map 77% reduce 17%
13/05/16 21:45:31 INFO mapred.JobClient:  map 81% reduce 17%
13/05/16 21:45:34 INFO mapred.JobClient:  map 84% reduce 19%
13/05/16 21:45:37 INFO mapred.JobClient:  map 86% reduce 20%
13/05/16 21:45:40 INFO mapred.JobClient:  map 92% reduce 22%
13/05/16 21:45:43 INFO mapred.JobClient:  map 95% reduce 24%
13/05/16 21:45:46 INFO mapred.JobClient:  map 99% reduce 25%
13/05/16 21:46:15 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 21:46:18 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 21:46:21 INFO mapred.JobClient:  map 100% reduce 29%
13/05/16 21:46:24 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 21:46:27 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 21:46:30 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 21:46:54 INFO mapred.JobClient:  map 100% reduce 60%
13/05/16 21:46:57 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 21:47:00 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 21:47:03 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 21:47:06 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 21:47:09 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 21:47:12 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 21:47:15 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 21:47:18 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 21:47:21 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 21:47:24 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 21:47:27 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 21:47:30 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 21:47:33 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 21:47:36 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 21:47:39 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 21:47:42 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 21:47:45 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 21:47:48 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 21:47:51 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 21:47:54 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 21:47:57 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 21:48:03 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 21:48:09 INFO mapred.JobClient: Job complete: job_201305162136_0001
13/05/16 21:48:09 INFO mapred.JobClient: Counters: 29
13/05/16 21:48:09 INFO mapred.JobClient:   Job Counters 
13/05/16 21:48:09 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 21:48:09 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2107647
13/05/16 21:48:09 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 21:48:09 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 21:48:09 INFO mapred.JobClient:     Launched map tasks=51
13/05/16 21:48:09 INFO mapred.JobClient:     Data-local map tasks=51
13/05/16 21:48:09 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=1189002
13/05/16 21:48:09 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 21:48:09 INFO mapred.JobClient:     Bytes Written=6495027670
13/05/16 21:48:09 INFO mapred.JobClient:   FileSystemCounters
13/05/16 21:48:09 INFO mapred.JobClient:     FILE_BYTES_READ=5534792502
13/05/16 21:48:09 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 21:48:09 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7498995212
13/05/16 21:48:09 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027670
13/05/16 21:48:09 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 21:48:09 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 21:48:09 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 21:48:09 INFO mapred.JobClient:     Map output materialized bytes=1988268005
13/05/16 21:48:09 INFO mapred.JobClient:     Map input records=401920000
13/05/16 21:48:09 INFO mapred.JobClient:     Reduce shuffle bytes=1950334261
13/05/16 21:48:09 INFO mapred.JobClient:     Spilled Records=1515929855
13/05/16 21:48:09 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 21:48:09 INFO mapred.JobClient:     CPU time spent (ms)=2189530
13/05/16 21:48:09 INFO mapred.JobClient:     Total committed heap usage (bytes)=61439410176
13/05/16 21:48:09 INFO mapred.JobClient:     Combine input records=0
13/05/16 21:48:09 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 21:48:09 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 21:48:09 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 21:48:09 INFO mapred.JobClient:     Combine output records=0
13/05/16 21:48:09 INFO mapred.JobClient:     Physical memory (bytes) snapshot=45960015872
13/05/16 21:48:09 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 21:48:09 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=5331592585216
13/05/16 21:48:09 INFO mapred.JobClient:     Map output records=401920000
Execution Time 303252 ms

real	5m4.057s
user	0m2.036s
sys	0m0.227s
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     19777  0.0  0.0 106084  1396 ?        Ss   21:48   0:00 bash -c ps aux | grep java
jmg3     19793  0.0  0.0 103232   840 ?        S    21:48   0:00 grep java
jmg3     14546  0.0  0.0  59072  3528 pts/0    S+   21:48   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     14552  0.0  0.0 106084  1396 ?        Ss   21:48   0:00 bash -c ps aux | grep java
jmg3     14568  0.0  0.0 103232   844 ?        S    21:48   0:00 grep java
run_opencl_test.sh: Running blackscholes with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     19819  0.0  0.0 106084  1404 ?        Ss   21:48   0:00 bash -c ps aux | grep java
jmg3     19835  0.0  0.0 103232   844 ?        S    21:48   0:00 grep java
jmg3     14605  0.0  0.0  59072  3528 pts/0    S+   21:48   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     14611  0.0  0.0 106084  1404 ?        Ss   21:48   0:00 bash -c ps aux | grep java
jmg3     14627  0.0  0.0 103232   844 ?        S    21:48   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 24



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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
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

13/05/16 21:48:34 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 21:48:34 INFO util.GSet: VM type       = 64-bit
13/05/16 21:48:34 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 21:48:34 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 21:48:34 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 21:48:34 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 21:48:34 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 21:48:34 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 21:48:34 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 21:48:34 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 21:48:34 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 21:48:35 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 21:48:35 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 21:48:35 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 21:54:01 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 21:54:01 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 21:54:01 INFO mapred.JobClient: Running job: job_201305162148_0001
13/05/16 21:54:02 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 21:54:21 INFO mapred.JobClient:  map 10% reduce 0%
13/05/16 21:54:24 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 21:54:27 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 21:54:30 INFO mapred.JobClient:  map 23% reduce 0%
13/05/16 21:54:32 INFO mapred.JobClient: Task Id : attempt_201305162148_0001_m_000001_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305162148_0001_m_000001_0: Scope block from 31 to  64
attempt_201305162148_0001_m_000001_0: Scope block from 56 to  274
13/05/16 21:54:42 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 21:55:00 INFO mapred.JobClient:  map 27% reduce 0%
13/05/16 21:55:03 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 21:55:06 INFO mapred.JobClient:  map 36% reduce 1%
13/05/16 21:55:09 INFO mapred.JobClient:  map 42% reduce 1%
13/05/16 21:55:12 INFO mapred.JobClient:  map 44% reduce 3%
13/05/16 21:55:15 INFO mapred.JobClient:  map 48% reduce 3%
13/05/16 21:55:18 INFO mapred.JobClient:  map 50% reduce 6%
13/05/16 21:55:21 INFO mapred.JobClient:  map 50% reduce 8%
13/05/16 21:55:24 INFO mapred.JobClient:  map 51% reduce 8%
13/05/16 21:55:48 INFO mapred.JobClient:  map 57% reduce 9%
13/05/16 21:55:51 INFO mapred.JobClient:  map 62% reduce 13%
13/05/16 21:55:54 INFO mapred.JobClient:  map 66% reduce 15%
13/05/16 21:55:57 INFO mapred.JobClient:  map 69% reduce 17%
13/05/16 21:56:00 INFO mapred.JobClient:  map 71% reduce 17%
13/05/16 21:56:03 INFO mapred.JobClient:  map 75% reduce 17%
13/05/16 21:56:07 INFO mapred.JobClient: Task Id : attempt_201305162148_0001_m_000033_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305162148_0001_m_000033_0: Scope block from 31 to  64
attempt_201305162148_0001_m_000033_0: Scope block from 56 to  274
13/05/16 21:56:08 INFO mapred.JobClient:  map 76% reduce 17%
13/05/16 21:56:16 INFO mapred.JobClient:  map 77% reduce 17%
13/05/16 21:56:19 INFO mapred.JobClient:  map 78% reduce 17%
13/05/16 21:56:31 INFO mapred.JobClient:  map 83% reduce 18%
13/05/16 21:56:34 INFO mapred.JobClient:  map 87% reduce 19%
13/05/16 21:56:37 INFO mapred.JobClient:  map 89% reduce 22%
13/05/16 21:56:40 INFO mapred.JobClient:  map 94% reduce 25%
13/05/16 21:56:43 INFO mapred.JobClient:  map 98% reduce 25%
13/05/16 21:56:46 INFO mapred.JobClient:  map 100% reduce 25%
13/05/16 21:57:10 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 21:57:13 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 21:57:16 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 21:57:22 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 21:57:45 INFO mapred.JobClient:  map 100% reduce 53%
13/05/16 21:57:48 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 21:57:51 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 21:57:54 INFO mapred.JobClient:  map 100% reduce 71%
13/05/16 21:57:57 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 21:58:00 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 21:58:03 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 21:58:06 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 21:58:09 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 21:58:12 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 21:58:15 INFO mapred.JobClient:  map 100% reduce 83%
13/05/16 21:58:18 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 21:58:21 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 21:58:24 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 21:58:27 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 21:58:30 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 21:58:33 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 21:58:36 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 21:58:40 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 21:58:45 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 21:58:50 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 21:58:53 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 21:59:02 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 21:59:07 INFO mapred.JobClient: Job complete: job_201305162148_0001
13/05/16 21:59:07 INFO mapred.JobClient: Counters: 29
13/05/16 21:59:07 INFO mapred.JobClient:   Job Counters 
13/05/16 21:59:07 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 21:59:07 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2087555
13/05/16 21:59:07 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 21:59:07 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 21:59:07 INFO mapred.JobClient:     Launched map tasks=52
13/05/16 21:59:07 INFO mapred.JobClient:     Data-local map tasks=52
13/05/16 21:59:07 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=1173830
13/05/16 21:59:07 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 21:59:07 INFO mapred.JobClient:     Bytes Written=6495027670
13/05/16 21:59:07 INFO mapred.JobClient:   FileSystemCounters
13/05/16 21:59:07 INFO mapred.JobClient:     FILE_BYTES_READ=5534792502
13/05/16 21:59:07 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 21:59:07 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7498995212
13/05/16 21:59:07 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027670
13/05/16 21:59:07 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 21:59:07 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 21:59:07 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 21:59:07 INFO mapred.JobClient:     Map output materialized bytes=1988268005
13/05/16 21:59:07 INFO mapred.JobClient:     Map input records=401920000
13/05/16 21:59:07 INFO mapred.JobClient:     Reduce shuffle bytes=1950334261
13/05/16 21:59:07 INFO mapred.JobClient:     Spilled Records=1515929855
13/05/16 21:59:07 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 21:59:07 INFO mapred.JobClient:     CPU time spent (ms)=2209390
13/05/16 21:59:07 INFO mapred.JobClient:     Total committed heap usage (bytes)=64004358144
13/05/16 21:59:07 INFO mapred.JobClient:     Combine input records=0
13/05/16 21:59:07 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 21:59:07 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 21:59:07 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 21:59:07 INFO mapred.JobClient:     Combine output records=0
13/05/16 21:59:07 INFO mapred.JobClient:     Physical memory (bytes) snapshot=47844102144
13/05/16 21:59:07 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 21:59:07 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=5331728908288
13/05/16 21:59:07 INFO mapred.JobClient:     Map output records=401920000
Execution Time 305698 ms

real	5m6.467s
user	0m2.058s
sys	0m0.227s
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     25126  0.0  0.0 106084  1400 ?        Ss   21:59   0:00 bash -c ps aux | grep java
jmg3     25142  0.0  0.0 103232   844 ?        S    21:59   0:00 grep java
jmg3     15788  0.0  0.0  59072  3528 pts/0    S+   21:59   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     15794  0.0  0.0 106084  1396 ?        Ss   21:59   0:00 bash -c ps aux | grep java
jmg3     15810  0.0  0.0 103232   844 ?        S    21:59   0:00 grep java
run_opencl_test.sh: Running blackscholes with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     25168  0.0  0.0 106084  1400 ?        Ss   21:59   0:00 bash -c ps aux | grep java
jmg3     25184  0.0  0.0 103232   840 ?        S    21:59   0:00 grep java
jmg3     15847  0.0  0.0  58312  3528 pts/0    S+   21:59   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     15853  0.0  0.0 106084  1400 ?        Ss   21:59   0:00 bash -c ps aux | grep java
jmg3     15869  0.0  0.0 103232   844 ?        S    21:59   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 24



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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
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

13/05/16 21:59:30 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 21:59:30 INFO util.GSet: VM type       = 64-bit
13/05/16 21:59:30 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 21:59:30 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 21:59:30 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 21:59:31 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 21:59:31 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 21:59:31 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 21:59:31 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 21:59:31 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 21:59:31 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 21:59:31 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 21:59:31 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 21:59:31 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 22:05:04 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 22:05:04 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 22:05:05 INFO mapred.JobClient: Running job: job_201305162159_0001
13/05/16 22:05:06 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 22:05:22 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 22:05:25 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 22:05:28 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 22:05:31 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 22:06:01 INFO mapred.JobClient:  map 29% reduce 0%
13/05/16 22:06:04 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 22:06:07 INFO mapred.JobClient:  map 39% reduce 1%
13/05/16 22:06:10 INFO mapred.JobClient:  map 43% reduce 3%
13/05/16 22:06:13 INFO mapred.JobClient:  map 45% reduce 4%
13/05/16 22:06:16 INFO mapred.JobClient:  map 48% reduce 5%
13/05/16 22:06:19 INFO mapred.JobClient:  map 51% reduce 6%
13/05/16 22:06:22 INFO mapred.JobClient:  map 52% reduce 8%
13/05/16 22:06:46 INFO mapred.JobClient:  map 52% reduce 9%
13/05/16 22:06:49 INFO mapred.JobClient:  map 56% reduce 9%
13/05/16 22:06:52 INFO mapred.JobClient:  map 61% reduce 12%
13/05/16 22:06:55 INFO mapred.JobClient:  map 62% reduce 14%
13/05/16 22:06:58 INFO mapred.JobClient:  map 68% reduce 16%
13/05/16 22:07:01 INFO mapred.JobClient:  map 72% reduce 17%
13/05/16 22:07:04 INFO mapred.JobClient:  map 76% reduce 17%
13/05/16 22:07:07 INFO mapred.JobClient:  map 77% reduce 17%
13/05/16 22:07:28 INFO mapred.JobClient:  map 79% reduce 17%
13/05/16 22:07:31 INFO mapred.JobClient:  map 81% reduce 18%
13/05/16 22:07:34 INFO mapred.JobClient:  map 83% reduce 19%
13/05/16 22:07:37 INFO mapred.JobClient:  map 90% reduce 20%
13/05/16 22:07:40 INFO mapred.JobClient:  map 93% reduce 23%
13/05/16 22:07:43 INFO mapred.JobClient:  map 98% reduce 25%
13/05/16 22:07:46 INFO mapred.JobClient:  map 99% reduce 25%
13/05/16 22:08:04 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 22:08:10 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 22:08:13 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 22:08:16 INFO mapred.JobClient:  map 100% reduce 28%
13/05/16 22:08:19 INFO mapred.JobClient:  map 100% reduce 31%
13/05/16 22:08:22 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 22:08:25 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 22:08:46 INFO mapred.JobClient:  map 100% reduce 46%
13/05/16 22:08:49 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 22:08:57 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 22:09:00 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 22:09:03 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 22:09:06 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 22:09:09 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 22:09:12 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 22:09:15 INFO mapred.JobClient:  map 100% reduce 80%
13/05/16 22:09:18 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 22:09:21 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 22:09:27 INFO mapred.JobClient:  map 100% reduce 85%
13/05/16 22:09:30 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 22:09:33 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 22:09:36 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 22:09:39 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 22:09:42 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 22:09:45 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 22:09:48 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 22:09:51 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 22:09:57 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 22:10:03 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 22:10:09 INFO mapred.JobClient: Job complete: job_201305162159_0001
13/05/16 22:10:10 INFO mapred.JobClient: Counters: 29
13/05/16 22:10:10 INFO mapred.JobClient:   Job Counters 
13/05/16 22:10:10 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 22:10:10 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2077134
13/05/16 22:10:10 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 22:10:10 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 22:10:10 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 22:10:10 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 22:10:10 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=1167726
13/05/16 22:10:10 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 22:10:10 INFO mapred.JobClient:     Bytes Written=6495027670
13/05/16 22:10:10 INFO mapred.JobClient:   FileSystemCounters
13/05/16 22:10:10 INFO mapred.JobClient:     FILE_BYTES_READ=5534792502
13/05/16 22:10:10 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 22:10:10 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7498995212
13/05/16 22:10:10 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027670
13/05/16 22:10:10 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 22:10:10 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 22:10:10 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 22:10:10 INFO mapred.JobClient:     Map output materialized bytes=1988268005
13/05/16 22:10:10 INFO mapred.JobClient:     Map input records=401920000
13/05/16 22:10:10 INFO mapred.JobClient:     Reduce shuffle bytes=1950334261
13/05/16 22:10:10 INFO mapred.JobClient:     Spilled Records=1515929855
13/05/16 22:10:10 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 22:10:10 INFO mapred.JobClient:     CPU time spent (ms)=2219810
13/05/16 22:10:10 INFO mapred.JobClient:     Total committed heap usage (bytes)=62964039680
13/05/16 22:10:10 INFO mapred.JobClient:     Combine input records=0
13/05/16 22:10:10 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 22:10:10 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 22:10:10 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 22:10:10 INFO mapred.JobClient:     Combine output records=0
13/05/16 22:10:10 INFO mapred.JobClient:     Physical memory (bytes) snapshot=46575394816
13/05/16 22:10:10 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 22:10:10 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=5331592585216
13/05/16 22:10:10 INFO mapred.JobClient:     Map output records=401920000
Execution Time 305405 ms

real	5m6.176s
user	0m2.018s
sys	0m0.215s
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     30210  0.0  0.0 106084  1396 ?        Ss   22:10   0:00 bash -c ps aux | grep java
jmg3     30226  0.0  0.0 103232   844 ?        S    22:10   0:00 grep java
jmg3     17041  0.0  0.0  59072  3528 pts/0    S+   22:10   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     17047  0.0  0.0 106084  1400 ?        Ss   22:10   0:00 bash -c ps aux | grep java
jmg3     17063  0.0  0.0 103232   844 ?        S    22:10   0:00 grep java
run_opencl_test.sh: Running blackscholes with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     30252  0.0  0.0 106084  1400 ?        Ss   22:10   0:00 bash -c ps aux | grep java
jmg3     30268  0.0  0.0 103232   840 ?        S    22:10   0:00 grep java
jmg3     17100  0.0  0.0  59072  3532 pts/0    S+   22:10   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     17106  0.0  0.0 106084  1400 ?        Ss   22:10   0:00 bash -c ps aux | grep java
jmg3     17122  0.0  0.0 103232   840 ?        S    22:10   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 24



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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
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

13/05/16 22:10:34 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 22:10:34 INFO util.GSet: VM type       = 64-bit
13/05/16 22:10:34 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 22:10:34 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 22:10:34 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 22:10:35 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 22:10:35 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 22:10:35 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 22:10:35 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 22:10:35 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 22:10:35 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 22:10:35 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 22:10:35 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 22:10:35 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 22:15:46 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 22:15:46 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 22:15:46 INFO mapred.JobClient: Running job: job_201305162210_0001
13/05/16 22:15:47 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 22:16:04 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 22:16:07 INFO mapred.JobClient:  map 16% reduce 0%
13/05/16 22:16:10 INFO mapred.JobClient:  map 20% reduce 0%
13/05/16 22:16:13 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 22:16:46 INFO mapred.JobClient:  map 33% reduce 0%
13/05/16 22:16:49 INFO mapred.JobClient:  map 37% reduce 1%
13/05/16 22:16:52 INFO mapred.JobClient:  map 42% reduce 1%
13/05/16 22:16:55 INFO mapred.JobClient:  map 45% reduce 3%
13/05/16 22:16:59 INFO mapred.JobClient:  map 48% reduce 4%
13/05/16 22:17:02 INFO mapred.JobClient:  map 51% reduce 6%
13/05/16 22:17:05 INFO mapred.JobClient:  map 51% reduce 8%
13/05/16 22:17:33 INFO mapred.JobClient:  map 56% reduce 9%
13/05/16 22:17:36 INFO mapred.JobClient:  map 61% reduce 12%
13/05/16 22:17:39 INFO mapred.JobClient:  map 64% reduce 14%
13/05/16 22:17:42 INFO mapred.JobClient:  map 69% reduce 16%
13/05/16 22:17:45 INFO mapred.JobClient:  map 75% reduce 17%
13/05/16 22:17:48 INFO mapred.JobClient:  map 77% reduce 17%
13/05/16 22:17:51 INFO mapred.JobClient:  map 78% reduce 17%
13/05/16 22:18:19 INFO mapred.JobClient:  map 83% reduce 17%
13/05/16 22:18:22 INFO mapred.JobClient:  map 89% reduce 20%
13/05/16 22:18:25 INFO mapred.JobClient:  map 90% reduce 23%
13/05/16 22:18:28 INFO mapred.JobClient:  map 95% reduce 25%
13/05/16 22:18:31 INFO mapred.JobClient:  map 99% reduce 25%
13/05/16 22:18:36 INFO mapred.JobClient:  map 100% reduce 25%
13/05/16 22:18:57 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 22:19:00 INFO mapred.JobClient:  map 100% reduce 27%
13/05/16 22:19:03 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 22:19:06 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 22:19:31 INFO mapred.JobClient:  map 100% reduce 46%
13/05/16 22:19:34 INFO mapred.JobClient:  map 100% reduce 67%
13/05/16 22:19:37 INFO mapred.JobClient:  map 100% reduce 69%
13/05/16 22:19:40 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 22:19:43 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 22:19:46 INFO mapred.JobClient:  map 100% reduce 73%
13/05/16 22:19:49 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 22:19:52 INFO mapred.JobClient:  map 100% reduce 76%
13/05/16 22:19:55 INFO mapred.JobClient:  map 100% reduce 78%
13/05/16 22:19:58 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 22:20:02 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 22:20:05 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 22:20:08 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 22:20:11 INFO mapred.JobClient:  map 100% reduce 87%
13/05/16 22:20:14 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 22:20:17 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 22:20:20 INFO mapred.JobClient:  map 100% reduce 91%
13/05/16 22:20:23 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 22:20:26 INFO mapred.JobClient:  map 100% reduce 94%
13/05/16 22:20:29 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 22:20:32 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 22:20:35 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 22:20:41 INFO mapred.JobClient:  map 100% reduce 98%
13/05/16 22:20:44 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 22:20:49 INFO mapred.JobClient: Job complete: job_201305162210_0001
13/05/16 22:20:49 INFO mapred.JobClient: Counters: 29
13/05/16 22:20:49 INFO mapred.JobClient:   Job Counters 
13/05/16 22:20:49 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 22:20:49 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2138415
13/05/16 22:20:49 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 22:20:49 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 22:20:49 INFO mapred.JobClient:     Launched map tasks=50
13/05/16 22:20:49 INFO mapred.JobClient:     Data-local map tasks=50
13/05/16 22:20:49 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=1182353
13/05/16 22:20:49 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 22:20:49 INFO mapred.JobClient:     Bytes Written=6495027670
13/05/16 22:20:49 INFO mapred.JobClient:   FileSystemCounters
13/05/16 22:20:49 INFO mapred.JobClient:     FILE_BYTES_READ=5534792502
13/05/16 22:20:49 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 22:20:49 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7498995212
13/05/16 22:20:49 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027670
13/05/16 22:20:49 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 22:20:49 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 22:20:49 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 22:20:49 INFO mapred.JobClient:     Map output materialized bytes=1988268005
13/05/16 22:20:49 INFO mapred.JobClient:     Map input records=401920000
13/05/16 22:20:49 INFO mapred.JobClient:     Reduce shuffle bytes=1950334261
13/05/16 22:20:49 INFO mapred.JobClient:     Spilled Records=1515929855
13/05/16 22:20:49 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 22:20:49 INFO mapred.JobClient:     CPU time spent (ms)=2206270
13/05/16 22:20:49 INFO mapred.JobClient:     Total committed heap usage (bytes)=62907351040
13/05/16 22:20:49 INFO mapred.JobClient:     Combine input records=0
13/05/16 22:20:49 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 22:20:49 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 22:20:49 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 22:20:49 INFO mapred.JobClient:     Combine output records=0
13/05/16 22:20:49 INFO mapred.JobClient:     Physical memory (bytes) snapshot=47178366976
13/05/16 22:20:49 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 22:20:49 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=5331592585216
13/05/16 22:20:49 INFO mapred.JobClient:     Map output records=401920000
Execution Time 303403 ms

real	5m4.200s
user	0m2.039s
sys	0m0.192s
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      2909  0.0  0.0 106084  1396 ?        Ss   22:20   0:00 bash -c ps aux | grep java
jmg3      2925  0.0  0.0 103232   840 ?        S    22:20   0:00 grep java
jmg3     18273  0.0  0.0  59072  3524 pts/0    S+   22:20   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     18279  0.0  0.0 106084  1400 ?        Ss   22:20   0:00 bash -c ps aux | grep java
jmg3     18295  0.0  0.0 103232   840 ?        S    22:20   0:00 grep java
run_opencl_test.sh: Running blackscholes with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3      2953  0.0  0.0 106084  1396 ?        Ss   22:20   0:00 bash -c ps aux | grep java
jmg3      2969  0.0  0.0 103232   844 ?        S    22:20   0:00 grep java
jmg3     18332  0.0  0.0  59072  3528 pts/0    S+   22:20   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     18338  0.0  0.0 106084  1400 ?        Ss   22:20   0:00 bash -c ps aux | grep java
jmg3     18354  0.0  0.0 103232   840 ?        S    22:20   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 24



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
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
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

13/05/16 22:21:24 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Thu May 16 00:16:08 CDT 2013
************************************************************/
13/05/16 22:21:24 INFO util.GSet: VM type       = 64-bit
13/05/16 22:21:24 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/16 22:21:24 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/16 22:21:24 INFO util.GSet: recommended=2097152, actual=2097152
13/05/16 22:21:24 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/16 22:21:24 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/16 22:21:24 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/16 22:21:24 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/16 22:21:24 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/16 22:21:24 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/16 22:21:24 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/16 22:21:24 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/16 22:21:24 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/16 22:26:13 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/16 22:26:14 INFO input.FileInputFormat: Total input paths to process : 50
13/05/16 22:26:14 INFO mapred.JobClient: Running job: job_201305162221_0001
13/05/16 22:26:15 INFO mapred.JobClient:  map 0% reduce 0%
13/05/16 22:26:33 INFO mapred.JobClient:  map 9% reduce 0%
13/05/16 22:26:36 INFO mapred.JobClient:  map 15% reduce 0%
13/05/16 22:26:39 INFO mapred.JobClient:  map 21% reduce 0%
13/05/16 22:26:42 INFO mapred.JobClient:  map 25% reduce 0%
13/05/16 22:27:12 INFO mapred.JobClient:  map 31% reduce 0%
13/05/16 22:27:15 INFO mapred.JobClient:  map 34% reduce 1%
13/05/16 22:27:18 INFO mapred.JobClient:  map 39% reduce 1%
13/05/16 22:27:21 INFO mapred.JobClient:  map 43% reduce 4%
13/05/16 22:27:24 INFO mapred.JobClient:  map 46% reduce 4%
13/05/16 22:27:27 INFO mapred.JobClient:  map 49% reduce 6%
13/05/16 22:27:29 INFO mapred.JobClient: Task Id : attempt_201305162221_0001_m_000017_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305162221_0001_m_000017_0: Scope block from 31 to  64
attempt_201305162221_0001_m_000017_0: Scope block from 56 to  274
13/05/16 22:27:30 INFO mapred.JobClient:  map 50% reduce 8%
13/05/16 22:27:42 INFO mapred.JobClient:  map 51% reduce 8%
13/05/16 22:27:54 INFO mapred.JobClient:  map 51% reduce 9%
13/05/16 22:27:57 INFO mapred.JobClient:  map 57% reduce 9%
13/05/16 22:28:00 INFO mapred.JobClient:  map 60% reduce 12%
13/05/16 22:28:03 INFO mapred.JobClient:  map 62% reduce 14%
13/05/16 22:28:06 INFO mapred.JobClient:  map 67% reduce 16%
13/05/16 22:28:09 INFO mapred.JobClient:  map 71% reduce 16%
13/05/16 22:28:12 INFO mapred.JobClient:  map 75% reduce 16%
13/05/16 22:28:21 INFO mapred.JobClient:  map 75% reduce 17%
13/05/16 22:28:24 INFO mapred.JobClient:  map 77% reduce 17%
13/05/16 22:28:42 INFO mapred.JobClient:  map 82% reduce 18%
13/05/16 22:28:45 INFO mapred.JobClient:  map 85% reduce 21%
13/05/16 22:28:48 INFO mapred.JobClient:  map 87% reduce 23%
13/05/16 22:28:52 INFO mapred.JobClient:  map 96% reduce 25%
13/05/16 22:28:58 INFO mapred.JobClient:  map 99% reduce 25%
13/05/16 22:29:19 INFO mapred.JobClient:  map 99% reduce 26%
13/05/16 22:29:22 INFO mapred.JobClient:  map 100% reduce 26%
13/05/16 22:29:25 INFO mapred.JobClient:  map 100% reduce 30%
13/05/16 22:29:28 INFO mapred.JobClient:  map 100% reduce 32%
13/05/16 22:29:31 INFO mapred.JobClient:  map 100% reduce 33%
13/05/16 22:29:56 INFO mapred.JobClient:  map 100% reduce 40%
13/05/16 22:29:59 INFO mapred.JobClient:  map 100% reduce 46%
13/05/16 22:30:02 INFO mapred.JobClient:  map 100% reduce 60%
13/05/16 22:30:05 INFO mapred.JobClient:  map 100% reduce 68%
13/05/16 22:30:14 INFO mapred.JobClient:  map 100% reduce 70%
13/05/16 22:30:17 INFO mapred.JobClient:  map 100% reduce 72%
13/05/16 22:30:20 INFO mapred.JobClient:  map 100% reduce 74%
13/05/16 22:30:23 INFO mapred.JobClient:  map 100% reduce 75%
13/05/16 22:30:26 INFO mapred.JobClient:  map 100% reduce 77%
13/05/16 22:30:29 INFO mapred.JobClient:  map 100% reduce 79%
13/05/16 22:30:32 INFO mapred.JobClient:  map 100% reduce 81%
13/05/16 22:30:35 INFO mapred.JobClient:  map 100% reduce 82%
13/05/16 22:30:38 INFO mapred.JobClient:  map 100% reduce 84%
13/05/16 22:30:41 INFO mapred.JobClient:  map 100% reduce 86%
13/05/16 22:30:44 INFO mapred.JobClient:  map 100% reduce 88%
13/05/16 22:30:47 INFO mapred.JobClient:  map 100% reduce 89%
13/05/16 22:30:50 INFO mapred.JobClient:  map 100% reduce 90%
13/05/16 22:30:53 INFO mapred.JobClient:  map 100% reduce 92%
13/05/16 22:31:00 INFO mapred.JobClient:  map 100% reduce 93%
13/05/16 22:31:03 INFO mapred.JobClient:  map 100% reduce 95%
13/05/16 22:31:06 INFO mapred.JobClient:  map 100% reduce 96%
13/05/16 22:31:09 INFO mapred.JobClient:  map 100% reduce 97%
13/05/16 22:31:12 INFO mapred.JobClient:  map 100% reduce 99%
13/05/16 22:31:21 INFO mapred.JobClient:  map 100% reduce 100%
13/05/16 22:31:31 INFO mapred.JobClient: Job complete: job_201305162221_0001
13/05/16 22:31:31 INFO mapred.JobClient: Counters: 29
13/05/16 22:31:31 INFO mapred.JobClient:   Job Counters 
13/05/16 22:31:31 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/16 22:31:31 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=2143346
13/05/16 22:31:31 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/16 22:31:31 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/16 22:31:31 INFO mapred.JobClient:     Launched map tasks=51
13/05/16 22:31:31 INFO mapred.JobClient:     Data-local map tasks=51
13/05/16 22:31:31 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=1221690
13/05/16 22:31:31 INFO mapred.JobClient:   File Output Format Counters 
13/05/16 22:31:31 INFO mapred.JobClient:     Bytes Written=6495027670
13/05/16 22:31:31 INFO mapred.JobClient:   FileSystemCounters
13/05/16 22:31:31 INFO mapred.JobClient:     FILE_BYTES_READ=5534792502
13/05/16 22:31:31 INFO mapred.JobClient:     HDFS_BYTES_READ=901838762
13/05/16 22:31:31 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=7498995212
13/05/16 22:31:31 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=6495027670
13/05/16 22:31:31 INFO mapred.JobClient:   File Input Format Counters 
13/05/16 22:31:31 INFO mapred.JobClient:     Bytes Read=901831822
13/05/16 22:31:31 INFO mapred.JobClient:   Map-Reduce Framework
13/05/16 22:31:31 INFO mapred.JobClient:     Map output materialized bytes=1988268005
13/05/16 22:31:31 INFO mapred.JobClient:     Map input records=401920000
13/05/16 22:31:31 INFO mapred.JobClient:     Reduce shuffle bytes=1950356727
13/05/16 22:31:31 INFO mapred.JobClient:     Spilled Records=1515929855
13/05/16 22:31:31 INFO mapred.JobClient:     Map output bytes=3215360000
13/05/16 22:31:31 INFO mapred.JobClient:     CPU time spent (ms)=2209990
13/05/16 22:31:31 INFO mapred.JobClient:     Total committed heap usage (bytes)=64206471168
13/05/16 22:31:31 INFO mapred.JobClient:     Combine input records=0
13/05/16 22:31:31 INFO mapred.JobClient:     SPLIT_RAW_BYTES=6940
13/05/16 22:31:31 INFO mapred.JobClient:     Reduce input records=401920000
13/05/16 22:31:31 INFO mapred.JobClient:     Reduce input groups=0
13/05/16 22:31:31 INFO mapred.JobClient:     Combine output records=0
13/05/16 22:31:31 INFO mapred.JobClient:     Physical memory (bytes) snapshot=48330653696
13/05/16 22:31:31 INFO mapred.JobClient:     Reduce output records=401920000
13/05/16 22:31:31 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=5331592585216
13/05/16 22:31:31 INFO mapred.JobClient:     Map output records=401920000
Execution Time 317320 ms

real	5m18.094s
user	0m2.189s
sys	0m0.188s
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      3108  8.8  0.0      0     0 ?        Zl   22:21   0:54 [java] <defunct>
jmg3      8261  0.0  0.0 106084  1400 ?        Ss   22:31   0:00 bash -c ps aux | grep java
jmg3      8277  0.0  0.0 103232   844 ?        S    22:31   0:00 grep java
jmg3     19504  0.0  0.0  59072  3528 pts/0    S+   22:31   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     19510  0.0  0.0 106084  1396 ?        Ss   22:31   0:00 bash -c ps aux | grep java
jmg3     19526  0.0  0.0 103232   844 ?        S    22:31   0:00 grep java
