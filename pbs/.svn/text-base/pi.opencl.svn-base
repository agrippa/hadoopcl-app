run_opencl_test.sh: Running pi with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     24545  0.0  0.0 106084  1400 ?        Ss   12:07   0:00 bash -c ps aux | grep java
jmg3     24561  0.0  0.0 103232   840 ?        S    12:07   0:00 grep java
jmg3     18196  0.0  0.0  59072  3528 pts/0    S+   12:07   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     18202  0.0  0.0 106084  1400 ?        Ss   12:07   0:00 bash -c ps aux | grep java
jmg3     18218  0.0  0.0 103232   844 ?        S    12:07   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



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

13/05/17 12:07:38 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 12:07:38 INFO util.GSet: VM type       = 64-bit
13/05/17 12:07:38 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 12:07:38 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 12:07:38 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 12:07:38 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 12:07:38 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 12:07:38 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 12:07:38 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 12:07:38 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 12:07:38 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 12:07:39 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 12:07:39 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 12:07:39 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 12:09:37 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 12:09:37 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 12:09:38 INFO mapred.JobClient: Running job: job_201305171207_0001
13/05/17 12:09:39 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 12:09:57 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 12:10:00 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:10:06 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 12:10:11 INFO mapred.JobClient: Task Id : attempt_201305171207_0001_m_000003_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171207_0001_m_000003_0: Scope block from 31 to  64
13/05/17 12:10:21 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:10:30 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 12:10:33 INFO mapred.JobClient:  map 59% reduce 0%
13/05/17 12:10:36 INFO mapred.JobClient:  map 67% reduce 1%
13/05/17 12:10:39 INFO mapred.JobClient:  map 78% reduce 5%
13/05/17 12:10:42 INFO mapred.JobClient:  map 83% reduce 8%
13/05/17 12:10:48 INFO mapred.JobClient:  map 83% reduce 14%
13/05/17 12:10:51 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 12:11:01 INFO mapred.JobClient:  map 90% reduce 15%
13/05/17 12:11:07 INFO mapred.JobClient:  map 93% reduce 15%
13/05/17 12:11:10 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 12:11:13 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 12:11:19 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 12:11:22 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 12:11:25 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 12:11:28 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 12:11:31 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 12:11:37 INFO mapred.JobClient:  map 100% reduce 45%
13/05/17 12:11:40 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 12:11:49 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 12:12:05 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 12:12:08 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 12:12:11 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 12:12:14 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 12:12:17 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 12:12:20 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 12:12:23 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 12:12:26 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 12:12:35 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 12:12:40 INFO mapred.JobClient: Job complete: job_201305171207_0001
13/05/17 12:12:40 INFO mapred.JobClient: Counters: 29
13/05/17 12:12:40 INFO mapred.JobClient:   Job Counters 
13/05/17 12:12:40 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/17 12:12:40 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1042659
13/05/17 12:12:40 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 12:12:40 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 12:12:40 INFO mapred.JobClient:     Launched map tasks=31
13/05/17 12:12:40 INFO mapred.JobClient:     Data-local map tasks=31
13/05/17 12:12:40 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=444969
13/05/17 12:12:40 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 12:12:40 INFO mapred.JobClient:     Bytes Written=492
13/05/17 12:12:40 INFO mapred.JobClient:   FileSystemCounters
13/05/17 12:12:40 INFO mapred.JobClient:     FILE_BYTES_READ=1968768785
13/05/17 12:12:40 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 12:12:40 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2703500218
13/05/17 12:12:40 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=492
13/05/17 12:12:40 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 12:12:40 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 12:12:40 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 12:12:40 INFO mapred.JobClient:     Map output materialized bytes=735958345
13/05/17 12:12:40 INFO mapred.JobClient:     Map input records=120000000
13/05/17 12:12:40 INFO mapred.JobClient:     Reduce shuffle bytes=711424497
13/05/17 12:12:40 INFO mapred.JobClient:     Spilled Records=881357672
13/05/17 12:12:40 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 12:12:40 INFO mapred.JobClient:     CPU time spent (ms)=1015160
13/05/17 12:12:40 INFO mapred.JobClient:     Total committed heap usage (bytes)=38734135296
13/05/17 12:12:40 INFO mapred.JobClient:     Combine input records=0
13/05/17 12:12:40 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 12:12:40 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 12:12:40 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 12:12:40 INFO mapred.JobClient:     Combine output records=0
13/05/17 12:12:40 INFO mapred.JobClient:     Physical memory (bytes) snapshot=26375933952
13/05/17 12:12:40 INFO mapred.JobClient:     Reduce output records=2
13/05/17 12:12:40 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091715846144
13/05/17 12:12:40 INFO mapred.JobClient:     Map output records=240000000
Execution Time 183092 ms

real	3m3.861s
user	0m1.868s
sys	0m0.178s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     28062  0.0  0.0 106084  1400 ?        Ss   12:12   0:00 bash -c ps aux | grep java
jmg3     28078  0.0  0.0 103232   844 ?        S    12:12   0:00 grep java
jmg3     19383  0.0  0.0  59072  3528 pts/0    S+   12:12   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     19389  0.0  0.0 106084  1400 ?        Ss   12:12   0:00 bash -c ps aux | grep java
jmg3     19405  0.0  0.0 103232   844 ?        S    12:12   0:00 grep java
run_opencl_test.sh: Running pi with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     28104  0.0  0.0 106084  1400 ?        Ss   12:12   0:00 bash -c ps aux | grep java
jmg3     28120  0.0  0.0 103232   844 ?        S    12:12   0:00 grep java
jmg3     19442  0.0  0.0  59072  3532 pts/0    S+   12:12   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     19448  0.0  0.0 106084  1396 ?        Ss   12:12   0:00 bash -c ps aux | grep java
jmg3     19464  0.0  0.0 103232   844 ?        S    12:12   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



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

13/05/17 12:13:13 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 12:13:13 INFO util.GSet: VM type       = 64-bit
13/05/17 12:13:13 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 12:13:13 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 12:13:13 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 12:13:13 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 12:13:13 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 12:13:13 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 12:13:13 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 12:13:13 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 12:13:13 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 12:13:14 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 12:13:14 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 12:13:14 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 12:15:15 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 12:15:16 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 12:15:16 INFO mapred.JobClient: Running job: job_201305171213_0001
13/05/17 12:15:17 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 12:15:35 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 12:15:39 INFO mapred.JobClient:  map 33% reduce 0%
13/05/17 12:15:42 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:15:51 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 12:15:56 INFO mapred.JobClient: Task Id : attempt_201305171213_0001_m_000003_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171213_0001_m_000003_0: Scope block from 31 to  64
13/05/17 12:16:06 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:16:09 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 12:16:12 INFO mapred.JobClient:  map 56% reduce 0%
13/05/17 12:16:15 INFO mapred.JobClient:  map 63% reduce 2%
13/05/17 12:16:18 INFO mapred.JobClient:  map 82% reduce 5%
13/05/17 12:16:21 INFO mapred.JobClient:  map 83% reduce 10%
13/05/17 12:16:27 INFO mapred.JobClient:  map 83% reduce 11%
13/05/17 12:16:30 INFO mapred.JobClient:  map 83% reduce 13%
13/05/17 12:16:33 INFO mapred.JobClient:  map 83% reduce 14%
13/05/17 12:16:36 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 12:16:45 INFO mapred.JobClient:  map 90% reduce 14%
13/05/17 12:16:48 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 12:16:51 INFO mapred.JobClient:  map 100% reduce 22%
13/05/17 12:16:54 INFO mapred.JobClient:  map 100% reduce 24%
13/05/17 12:16:57 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 12:17:03 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 12:17:09 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 12:17:12 INFO mapred.JobClient:  map 100% reduce 44%
13/05/17 12:17:15 INFO mapred.JobClient:  map 100% reduce 58%
13/05/17 12:17:18 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 12:17:27 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 12:17:36 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 12:17:42 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 12:17:45 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 12:17:48 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 12:17:51 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 12:17:54 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 12:17:57 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 12:18:00 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 12:18:06 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 12:18:09 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 12:18:14 INFO mapred.JobClient: Job complete: job_201305171213_0001
13/05/17 12:18:14 INFO mapred.JobClient: Counters: 29
13/05/17 12:18:14 INFO mapred.JobClient:   Job Counters 
13/05/17 12:18:14 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/17 12:18:14 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1019205
13/05/17 12:18:14 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 12:18:14 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 12:18:14 INFO mapred.JobClient:     Launched map tasks=31
13/05/17 12:18:14 INFO mapred.JobClient:     Data-local map tasks=31
13/05/17 12:18:14 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=425367
13/05/17 12:18:14 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 12:18:14 INFO mapred.JobClient:     Bytes Written=492
13/05/17 12:18:14 INFO mapred.JobClient:   FileSystemCounters
13/05/17 12:18:14 INFO mapred.JobClient:     FILE_BYTES_READ=1968774554
13/05/17 12:18:14 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 12:18:14 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2703505987
13/05/17 12:18:14 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=492
13/05/17 12:18:14 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 12:18:14 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 12:18:14 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 12:18:14 INFO mapred.JobClient:     Map output materialized bytes=735958345
13/05/17 12:18:14 INFO mapred.JobClient:     Map input records=120000000
13/05/17 12:18:14 INFO mapred.JobClient:     Reduce shuffle bytes=711426858
13/05/17 12:18:14 INFO mapred.JobClient:     Spilled Records=881357672
13/05/17 12:18:14 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 12:18:14 INFO mapred.JobClient:     CPU time spent (ms)=993770
13/05/17 12:18:14 INFO mapred.JobClient:     Total committed heap usage (bytes)=38348783616
13/05/17 12:18:14 INFO mapred.JobClient:     Combine input records=0
13/05/17 12:18:14 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 12:18:14 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 12:18:14 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 12:18:14 INFO mapred.JobClient:     Combine output records=0
13/05/17 12:18:14 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27345711104
13/05/17 12:18:14 INFO mapred.JobClient:     Reduce output records=2
13/05/17 12:18:14 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091648737280
13/05/17 12:18:14 INFO mapred.JobClient:     Map output records=240000000
Execution Time 179013 ms

real	2m59.786s
user	0m1.854s
sys	0m0.209s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     31599  0.0  0.0 106084  1400 ?        Ss   12:18   0:00 bash -c ps aux | grep java
jmg3     31615  0.0  0.0 103232   840 ?        S    12:18   0:00 grep java
jmg3     20626  0.0  0.0  59072  3528 pts/0    S+   12:18   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     20632  0.0  0.0 106084  1400 ?        Ss   12:18   0:00 bash -c ps aux | grep java
jmg3     20648  0.0  0.0 103232   840 ?        S    12:18   0:00 grep java
run_opencl_test.sh: Running pi with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3     31641  0.0  0.0 106084  1400 ?        Ss   12:18   0:00 bash -c ps aux | grep java
jmg3     31657  0.0  0.0 103232   844 ?        S    12:18   0:00 grep java
jmg3     20685  0.0  0.0  59072  3528 pts/0    S+   12:18   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     20691  0.0  0.0 106084  1400 ?        Ss   12:18   0:00 bash -c ps aux | grep java
jmg3     20707  0.0  0.0 103232   844 ?        S    12:18   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



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

13/05/17 12:18:47 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 12:18:47 INFO util.GSet: VM type       = 64-bit
13/05/17 12:18:47 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 12:18:47 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 12:18:47 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 12:18:47 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 12:18:48 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 12:18:48 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 12:18:48 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 12:18:48 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 12:18:48 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 12:18:48 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 12:18:48 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 12:18:48 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 12:20:15 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 12:20:15 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 12:20:16 INFO mapred.JobClient: Running job: job_201305171218_0001
13/05/17 12:20:17 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 12:20:36 INFO mapred.JobClient:  map 36% reduce 0%
13/05/17 12:20:39 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:21:15 INFO mapred.JobClient:  map 59% reduce 0%
13/05/17 12:21:18 INFO mapred.JobClient:  map 77% reduce 1%
13/05/17 12:21:21 INFO mapred.JobClient:  map 85% reduce 5%
13/05/17 12:21:24 INFO mapred.JobClient:  map 86% reduce 6%
13/05/17 12:21:27 INFO mapred.JobClient:  map 86% reduce 11%
13/05/17 12:21:30 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 12:21:51 INFO mapred.JobClient:  map 96% reduce 16%
13/05/17 12:21:54 INFO mapred.JobClient:  map 100% reduce 20%
13/05/17 12:21:57 INFO mapred.JobClient:  map 96% reduce 22%
13/05/17 12:22:00 INFO mapred.JobClient:  map 96% reduce 26%
13/05/17 12:22:02 INFO mapred.JobClient: Task Id : attempt_201305171218_0001_m_000027_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171218_0001_m_000027_0: Scope block from 31 to  64
13/05/17 12:22:03 INFO mapred.JobClient:  map 96% reduce 28%
13/05/17 12:22:12 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 12:22:15 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 12:22:18 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 12:22:24 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 12:22:36 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 12:22:45 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 12:22:48 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 12:22:51 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 12:22:54 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 12:22:57 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 12:23:00 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 12:23:03 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 12:23:06 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 12:23:09 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 12:23:12 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 12:23:15 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 12:23:20 INFO mapred.JobClient: Job complete: job_201305171218_0001
13/05/17 12:23:20 INFO mapred.JobClient: Counters: 29
13/05/17 12:23:20 INFO mapred.JobClient:   Job Counters 
13/05/17 12:23:20 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/17 12:23:20 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1078390
13/05/17 12:23:20 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 12:23:20 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 12:23:20 INFO mapred.JobClient:     Launched map tasks=31
13/05/17 12:23:20 INFO mapred.JobClient:     Data-local map tasks=31
13/05/17 12:23:20 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=483624
13/05/17 12:23:20 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 12:23:20 INFO mapred.JobClient:     Bytes Written=492
13/05/17 12:23:20 INFO mapred.JobClient:   FileSystemCounters
13/05/17 12:23:20 INFO mapred.JobClient:     FILE_BYTES_READ=1968772156
13/05/17 12:23:20 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 12:23:20 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2703503589
13/05/17 12:23:20 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=492
13/05/17 12:23:20 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 12:23:20 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 12:23:20 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 12:23:20 INFO mapred.JobClient:     Map output materialized bytes=735958345
13/05/17 12:23:20 INFO mapred.JobClient:     Map input records=120000000
13/05/17 12:23:20 INFO mapred.JobClient:     Reduce shuffle bytes=711426862
13/05/17 12:23:20 INFO mapred.JobClient:     Spilled Records=881357672
13/05/17 12:23:20 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 12:23:20 INFO mapred.JobClient:     CPU time spent (ms)=1034420
13/05/17 12:23:20 INFO mapred.JobClient:     Total committed heap usage (bytes)=38515769344
13/05/17 12:23:20 INFO mapred.JobClient:     Combine input records=0
13/05/17 12:23:20 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 12:23:20 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 12:23:20 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 12:23:20 INFO mapred.JobClient:     Combine output records=0
13/05/17 12:23:20 INFO mapred.JobClient:     Physical memory (bytes) snapshot=25919111168
13/05/17 12:23:20 INFO mapred.JobClient:     Reduce output records=2
13/05/17 12:23:20 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091648737280
13/05/17 12:23:20 INFO mapred.JobClient:     Map output records=240000000
Execution Time 184853 ms

real	3m5.602s
user	0m1.882s
sys	0m0.174s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      2764  0.0  0.0 106084  1400 ?        Ss   12:23   0:00 bash -c ps aux | grep java
jmg3      2780  0.0  0.0 103232   844 ?        S    12:23   0:00 grep java
jmg3     21866  0.0  0.0  59072  3528 pts/0    S+   12:23   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     21872  0.0  0.0 106084  1400 ?        Ss   12:23   0:00 bash -c ps aux | grep java
jmg3     21888  0.0  0.0 103232   840 ?        S    12:23   0:00 grep java
run_opencl_test.sh: Running pi with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3      2806  0.0  0.0 106084  1396 ?        Ss   12:23   0:00 bash -c ps aux | grep java
jmg3      2822  0.0  0.0 103232   844 ?        S    12:23   0:00 grep java
jmg3     21925  0.0  0.0  59072  3528 pts/0    S+   12:23   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     21931  0.0  0.0 106084  1396 ?        Ss   12:23   0:00 bash -c ps aux | grep java
jmg3     21947  0.0  0.0 103232   840 ?        S    12:23   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



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

13/05/17 12:23:53 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 12:23:53 INFO util.GSet: VM type       = 64-bit
13/05/17 12:23:53 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 12:23:53 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 12:23:53 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 12:23:53 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 12:23:53 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 12:23:53 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 12:23:53 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 12:23:53 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 12:23:53 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 12:23:53 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 12:23:54 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 12:23:54 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 12:25:44 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 12:25:44 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 12:25:44 INFO mapred.JobClient: Running job: job_201305171223_0001
13/05/17 12:25:45 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 12:26:05 INFO mapred.JobClient:  map 34% reduce 0%
13/05/17 12:26:08 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:26:39 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 12:26:42 INFO mapred.JobClient:  map 65% reduce 0%
13/05/17 12:26:45 INFO mapred.JobClient:  map 66% reduce 2%
13/05/17 12:26:48 INFO mapred.JobClient:  map 83% reduce 5%
13/05/17 12:26:51 INFO mapred.JobClient:  map 86% reduce 8%
13/05/17 12:26:54 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 12:27:15 INFO mapred.JobClient:  map 90% reduce 15%
13/05/17 12:27:18 INFO mapred.JobClient:  map 96% reduce 16%
13/05/17 12:27:21 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 12:27:24 INFO mapred.JobClient:  map 100% reduce 25%
13/05/17 12:27:27 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 12:27:30 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 12:27:36 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 12:27:39 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 12:27:48 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 12:28:07 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 12:28:10 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 12:28:13 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 12:28:16 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 12:28:19 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 12:28:22 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 12:28:25 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 12:28:28 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 12:28:31 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 12:28:37 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 12:28:42 INFO mapred.JobClient: Job complete: job_201305171223_0001
13/05/17 12:28:42 INFO mapred.JobClient: Counters: 29
13/05/17 12:28:42 INFO mapred.JobClient:   Job Counters 
13/05/17 12:28:42 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/17 12:28:42 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1058783
13/05/17 12:28:42 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 12:28:42 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 12:28:42 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 12:28:42 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 12:28:42 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=434496
13/05/17 12:28:42 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 12:28:42 INFO mapred.JobClient:     Bytes Written=492
13/05/17 12:28:42 INFO mapred.JobClient:   FileSystemCounters
13/05/17 12:28:42 INFO mapred.JobClient:     FILE_BYTES_READ=1968767136
13/05/17 12:28:42 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 12:28:42 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2703498569
13/05/17 12:28:42 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=492
13/05/17 12:28:42 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 12:28:42 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 12:28:42 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 12:28:42 INFO mapred.JobClient:     Map output materialized bytes=735958345
13/05/17 12:28:42 INFO mapred.JobClient:     Map input records=120000000
13/05/17 12:28:42 INFO mapred.JobClient:     Reduce shuffle bytes=723620726
13/05/17 12:28:42 INFO mapred.JobClient:     Spilled Records=881357672
13/05/17 12:28:42 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 12:28:42 INFO mapred.JobClient:     CPU time spent (ms)=999120
13/05/17 12:28:42 INFO mapred.JobClient:     Total committed heap usage (bytes)=38179700736
13/05/17 12:28:42 INFO mapred.JobClient:     Combine input records=0
13/05/17 12:28:42 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 12:28:42 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 12:28:42 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 12:28:42 INFO mapred.JobClient:     Combine output records=0
13/05/17 12:28:42 INFO mapred.JobClient:     Physical memory (bytes) snapshot=27536674816
13/05/17 12:28:42 INFO mapred.JobClient:     Reduce output records=2
13/05/17 12:28:42 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091853221888
13/05/17 12:28:42 INFO mapred.JobClient:     Map output records=240000000
Execution Time 178877 ms

real	2m59.656s
user	0m1.835s
sys	0m0.201s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      6270  0.0  0.0 106084  1400 ?        Ss   12:28   0:00 bash -c ps aux | grep java
jmg3      6286  0.0  0.0 103232   840 ?        S    12:28   0:00 grep java
jmg3     23108  0.0  0.0  59072  3528 pts/0    S+   12:28   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     23114  0.0  0.0 106084  1400 ?        Ss   12:28   0:00 bash -c ps aux | grep java
jmg3     23130  0.0  0.0 103232   840 ?        S    12:28   0:00 grep java
run_opencl_test.sh: Running pi with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 16G
Killing
java: no process killed
java: no process killed
jmg3      6312  0.0  0.0 106084  1396 ?        Ss   12:28   0:00 bash -c ps aux | grep java
jmg3      6328  0.0  0.0 103232   840 ?        S    12:28   0:00 grep java
jmg3     23167  0.0  0.0  59072  3528 pts/0    S+   12:28   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     23173  0.0  0.0 106084  1400 ?        Ss   12:28   0:00 bash -c ps aux | grep java
jmg3     23189  0.0  0.0 103232   844 ?        S    12:28   0:00 grep java
Done
Setting path to /tmp/1298557.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 1 2 16



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

13/05/17 12:29:15 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 12:29:15 INFO util.GSet: VM type       = 64-bit
13/05/17 12:29:15 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 12:29:15 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 12:29:15 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 12:29:15 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 12:29:16 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 12:29:16 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 12:29:16 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 12:29:16 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 12:29:16 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 12:29:16 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 12:29:16 INFO common.Storage: Storage directory /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 12:29:16 INFO namenode.NameNode: SHUTDOWN_MSG: 
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

13/05/17 12:31:02 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 12:31:02 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 12:31:03 INFO mapred.JobClient: Running job: job_201305171229_0001
13/05/17 12:31:04 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 12:31:22 INFO mapred.JobClient:  map 32% reduce 0%
13/05/17 12:31:25 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 12:31:58 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 12:32:01 INFO mapred.JobClient:  map 63% reduce 0%
13/05/17 12:32:04 INFO mapred.JobClient:  map 80% reduce 1%
13/05/17 12:32:07 INFO mapred.JobClient:  map 86% reduce 2%
13/05/17 12:32:10 INFO mapred.JobClient:  map 86% reduce 7%
13/05/17 12:32:13 INFO mapred.JobClient:  map 86% reduce 8%
13/05/17 12:32:16 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 12:32:29 INFO mapred.JobClient:  map 86% reduce 15%
13/05/17 12:32:32 INFO mapred.JobClient:  map 90% reduce 15%
13/05/17 12:32:35 INFO mapred.JobClient:  map 99% reduce 17%
13/05/17 12:32:38 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 12:32:44 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 12:32:47 INFO mapred.JobClient:  map 100% reduce 25%
13/05/17 12:32:50 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 12:32:53 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 12:32:56 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 12:33:02 INFO mapred.JobClient:  map 100% reduce 58%
13/05/17 12:33:05 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 12:33:14 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 12:33:27 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 12:33:30 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 12:33:33 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 12:33:37 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 12:33:40 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 12:33:43 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 12:33:46 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 12:33:49 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 12:33:55 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 12:33:58 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 12:34:03 INFO mapred.JobClient: Job complete: job_201305171229_0001
13/05/17 12:34:03 INFO mapred.JobClient: Counters: 29
13/05/17 12:34:03 INFO mapred.JobClient:   Job Counters 
13/05/17 12:34:03 INFO mapred.JobClient:     Launched reduce tasks=5
13/05/17 12:34:03 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1065352
13/05/17 12:34:03 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 12:34:03 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 12:34:03 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 12:34:03 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 12:34:03 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=419244
13/05/17 12:34:03 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 12:34:03 INFO mapred.JobClient:     Bytes Written=492
13/05/17 12:34:03 INFO mapred.JobClient:   FileSystemCounters
13/05/17 12:34:03 INFO mapred.JobClient:     FILE_BYTES_READ=1968770953
13/05/17 12:34:03 INFO mapred.JobClient:     HDFS_BYTES_READ=1236931564
13/05/17 12:34:03 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=2703502386
13/05/17 12:34:03 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=492
13/05/17 12:34:03 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 12:34:03 INFO mapred.JobClient:     Bytes Read=1236927704
13/05/17 12:34:03 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 12:34:03 INFO mapred.JobClient:     Map output materialized bytes=735958345
13/05/17 12:34:03 INFO mapred.JobClient:     Map input records=120000000
13/05/17 12:34:03 INFO mapred.JobClient:     Reduce shuffle bytes=711424497
13/05/17 12:34:03 INFO mapred.JobClient:     Spilled Records=881357672
13/05/17 12:34:03 INFO mapred.JobClient:     Map output bytes=1920000000
13/05/17 12:34:03 INFO mapred.JobClient:     CPU time spent (ms)=1016480
13/05/17 12:34:03 INFO mapred.JobClient:     Total committed heap usage (bytes)=37696372736
13/05/17 12:34:03 INFO mapred.JobClient:     Combine input records=0
13/05/17 12:34:03 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3860
13/05/17 12:34:03 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 12:34:03 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 12:34:03 INFO mapred.JobClient:     Combine output records=0
13/05/17 12:34:03 INFO mapred.JobClient:     Physical memory (bytes) snapshot=25791893504
13/05/17 12:34:03 INFO mapred.JobClient:     Reduce output records=2
13/05/17 12:34:03 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3091715846144
13/05/17 12:34:03 INFO mapred.JobClient:     Map output records=240000000
Execution Time 180811 ms

real	3m1.572s
user	0m1.848s
sys	0m0.190s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/pi.output/pi.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1298557.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      9670  0.0  0.0 106084  1396 ?        Ss   12:34   0:00 bash -c ps aux | grep java
jmg3      9686  0.0  0.0 103232   840 ?        S    12:34   0:00 grep java
jmg3     24348  0.0  0.0  58312  3528 pts/0    S+   12:34   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     24354  0.0  0.0 106084  1396 ?        Ss   12:34   0:00 bash -c ps aux | grep java
jmg3     24370  0.0  0.0 103232   844 ?        S    12:34   0:00 grep java
