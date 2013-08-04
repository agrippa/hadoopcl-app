run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 3145728 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3      2541  0.0  0.0 106084  1400 ?        Ss   15:06   0:00 bash -c ps aux | grep java
jmg3      2557  0.0  0.0 103232   840 ?        S    15:06   0:00 grep java
jmg3      4562  0.0  0.0  59072  3528 pts/0    S+   15:06   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      4568  0.0  0.0 106084  1400 ?        Ss   15:06   0:00 bash -c ps aux | grep java
jmg3      4584  0.0  0.0 103232   844 ?        S    15:06   0:00 grep java
Done
Setting path to /tmp/1300891.daman.davinci.rice.edu
13 3 36 36 256 256 3 3 3145728 3145728 36 36 256 256 3 3 3145728 3145728 268435456 12 1 10 -10 24



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
  <name>mapred.reduce.tasks</name><value>3</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>3</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=3145728 -Dopencl.mapper.bufferSize.cpu=3145728 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=3145728 -Dopencl.reducer.bufferSize.cpu=3145728</value>
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

13/05/17 15:06:45 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:06:46 INFO util.GSet: VM type       = 64-bit
13/05/17 15:06:46 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:06:46 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:06:46 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:06:46 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:06:46 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:06:46 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:06:46 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:06:46 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:06:46 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:06:46 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:06:46 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:06:46 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/17 15:09:11 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:09:12 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:09:12 INFO mapred.JobClient: Running job: job_201305171506_0001
13/05/17 15:09:13 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:09:31 INFO mapred.JobClient:  map 37% reduce 0%
13/05/17 15:09:34 INFO mapred.JobClient:  map 41% reduce 0%
13/05/17 15:09:37 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:09:40 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 15:09:45 INFO mapred.JobClient: Task Id : attempt_201305171506_0001_m_000009_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171506_0001_m_000009_0: Scope block from 31 to  64
13/05/17 15:09:58 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:10:14 INFO mapred.JobClient:  map 53% reduce 0%
13/05/17 15:10:17 INFO mapred.JobClient:  map 79% reduce 1%
13/05/17 15:10:19 INFO mapred.JobClient: Task Id : attempt_201305171506_0001_m_000023_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 126.
Throwable.getMessage: Task process exit with nonzero status of 126.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 126.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

13/05/17 15:10:19 WARN mapred.JobClient: Error reading task outputhttp://gpu-012.davinci.rice.edu:50060/tasklog?plaintext=true&attemptid=attempt_201305171506_0001_m_000023_0&filter=stdout
13/05/17 15:10:19 WARN mapred.JobClient: Error reading task outputhttp://gpu-012.davinci.rice.edu:50060/tasklog?plaintext=true&attemptid=attempt_201305171506_0001_m_000023_0&filter=stderr
13/05/17 15:10:20 INFO mapred.JobClient:  map 80% reduce 2%
13/05/17 15:10:23 INFO mapred.JobClient:  map 80% reduce 3%
13/05/17 15:10:26 INFO mapred.JobClient:  map 80% reduce 5%
13/05/17 15:10:29 INFO mapred.JobClient:  map 83% reduce 5%
13/05/17 15:10:32 INFO mapred.JobClient:  map 83% reduce 7%
13/05/17 15:10:38 INFO mapred.JobClient:  map 83% reduce 8%
13/05/17 15:10:41 INFO mapred.JobClient:  map 83% reduce 10%
13/05/17 15:10:44 INFO mapred.JobClient:  map 83% reduce 11%
13/05/17 15:10:47 INFO mapred.JobClient:  map 83% reduce 12%
13/05/17 15:10:50 INFO mapred.JobClient:  map 83% reduce 14%
13/05/17 15:10:53 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 15:11:11 INFO mapred.JobClient:  map 86% reduce 15%
13/05/17 15:11:14 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 15:11:17 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 15:11:20 INFO mapred.JobClient:  map 100% reduce 19%
13/05/17 15:11:27 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 15:11:30 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 15:11:33 INFO mapred.JobClient:  map 100% reduce 24%
13/05/17 15:11:36 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 15:11:39 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 15:11:42 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 15:12:07 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 15:12:10 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 15:12:47 INFO mapred.JobClient:  map 100% reduce 44%
13/05/17 15:12:50 INFO mapred.JobClient:  map 100% reduce 67%
13/05/17 15:12:53 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 15:12:56 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 15:12:59 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 15:13:02 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 15:13:05 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 15:13:08 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 15:13:11 INFO mapred.JobClient:  map 100% reduce 78%
13/05/17 15:13:14 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 15:13:17 INFO mapred.JobClient:  map 100% reduce 81%
13/05/17 15:13:20 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 15:13:26 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 15:13:29 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 15:13:37 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 15:13:40 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 15:13:43 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 15:13:46 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 15:13:49 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 15:13:52 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 15:13:55 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 15:14:01 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 15:14:04 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 15:14:17 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 15:14:22 INFO mapred.JobClient: Job complete: job_201305171506_0001
13/05/17 15:14:22 INFO mapred.JobClient: Counters: 29
13/05/17 15:14:22 INFO mapred.JobClient:   Job Counters 
13/05/17 15:14:22 INFO mapred.JobClient:     Launched reduce tasks=3
13/05/17 15:14:22 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1475775
13/05/17 15:14:22 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 15:14:22 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 15:14:22 INFO mapred.JobClient:     Launched map tasks=32
13/05/17 15:14:22 INFO mapred.JobClient:     Data-local map tasks=32
13/05/17 15:14:22 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=718612
13/05/17 15:14:22 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 15:14:22 INFO mapred.JobClient:     Bytes Written=4848000259
13/05/17 15:14:22 INFO mapred.JobClient:   FileSystemCounters
13/05/17 15:14:22 INFO mapred.JobClient:     FILE_BYTES_READ=8558684336
13/05/17 15:14:22 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 15:14:22 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11718663866
13/05/17 15:14:22 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000259
13/05/17 15:14:22 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 15:14:22 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 15:14:22 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 15:14:22 INFO mapred.JobClient:     Map output materialized bytes=3166533455
13/05/17 15:14:22 INFO mapred.JobClient:     Map input records=120000000
13/05/17 15:14:22 INFO mapred.JobClient:     Reduce shuffle bytes=3060983434
13/05/17 15:14:22 INFO mapred.JobClient:     Spilled Records=904771314
13/05/17 15:14:22 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 15:14:22 INFO mapred.JobClient:     CPU time spent (ms)=1596820
13/05/17 15:14:22 INFO mapred.JobClient:     Total committed heap usage (bytes)=41987866624
13/05/17 15:14:22 INFO mapred.JobClient:     Combine input records=0
13/05/17 15:14:22 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 15:14:22 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 15:14:22 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 15:14:22 INFO mapred.JobClient:     Combine output records=0
13/05/17 15:14:22 INFO mapred.JobClient:     Physical memory (bytes) snapshot=38527098880
13/05/17 15:14:22 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 15:14:22 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3198914654208
13/05/17 15:14:22 INFO mapred.JobClient:     Map output records=240000000
Execution Time 311405 ms

real	5m12.168s
user	0m2.055s
sys	0m0.194s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      6162  0.0  0.0 106084  1400 ?        Ss   15:14   0:00 bash -c ps aux | grep java
jmg3      6178  0.0  0.0 103232   844 ?        S    15:14   0:00 grep java
jmg3      5766  0.0  0.0  59072  3532 pts/0    S+   15:14   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      5772  0.0  0.0 106084  1400 ?        Ss   15:14   0:00 bash -c ps aux | grep java
jmg3      5788  0.0  0.0 103232   840 ?        S    15:14   0:00 grep java
run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 3145728 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3      6204  0.0  0.0 106084  1400 ?        Ss   15:14   0:00 bash -c ps aux | grep java
jmg3      6220  0.0  0.0 103232   840 ?        S    15:14   0:00 grep java
jmg3      5825  0.0  0.0  59072  3524 pts/0    S+   15:14   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      5831  0.0  0.0 106084  1400 ?        Ss   15:14   0:00 bash -c ps aux | grep java
jmg3      5847  0.0  0.0 103232   844 ?        S    15:14   0:00 grep java
Done
Setting path to /tmp/1300891.daman.davinci.rice.edu
13 3 36 36 256 256 3 3 3145728 3145728 36 36 256 256 3 3 3145728 3145728 268435456 12 1 10 -10 24



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
  <name>mapred.reduce.tasks</name><value>3</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>3</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=3145728 -Dopencl.mapper.bufferSize.cpu=3145728 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=3145728 -Dopencl.reducer.bufferSize.cpu=3145728</value>
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

13/05/17 15:15:13 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:15:13 INFO util.GSet: VM type       = 64-bit
13/05/17 15:15:13 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:15:13 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:15:13 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:15:13 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:15:13 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:15:13 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:15:13 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:15:13 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:15:13 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:15:14 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:15:14 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:15:14 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/17 15:17:11 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:17:11 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:17:11 INFO mapred.JobClient: Running job: job_201305171515_0001
13/05/17 15:17:12 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:17:34 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 15:17:37 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:18:16 INFO mapred.JobClient:  map 81% reduce 0%
13/05/17 15:18:19 INFO mapred.JobClient:  map 85% reduce 0%
13/05/17 15:18:22 INFO mapred.JobClient:  map 86% reduce 1%
13/05/17 15:18:25 INFO mapred.JobClient:  map 86% reduce 2%
13/05/17 15:18:28 INFO mapred.JobClient:  map 86% reduce 3%
13/05/17 15:18:31 INFO mapred.JobClient:  map 86% reduce 5%
13/05/17 15:18:37 INFO mapred.JobClient:  map 86% reduce 7%
13/05/17 15:18:40 INFO mapred.JobClient:  map 86% reduce 8%
13/05/17 15:18:43 INFO mapred.JobClient:  map 86% reduce 9%
13/05/17 15:18:46 INFO mapred.JobClient:  map 86% reduce 10%
13/05/17 15:18:49 INFO mapred.JobClient:  map 86% reduce 11%
13/05/17 15:18:52 INFO mapred.JobClient:  map 86% reduce 13%
13/05/17 15:18:55 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 15:19:14 INFO mapred.JobClient:  map 100% reduce 14%
13/05/17 15:19:17 INFO mapred.JobClient:  map 100% reduce 15%
13/05/17 15:19:20 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 15:19:23 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 15:19:26 INFO mapred.JobClient:  map 100% reduce 20%
13/05/17 15:19:29 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 15:19:32 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 15:19:35 INFO mapred.JobClient:  map 100% reduce 24%
13/05/17 15:19:38 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 15:19:41 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 15:19:44 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 15:19:54 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 15:19:57 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 15:20:00 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 15:20:33 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 15:20:36 INFO mapred.JobClient:  map 100% reduce 55%
13/05/17 15:20:39 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 15:20:50 INFO mapred.JobClient:  map 100% reduce 69%
13/05/17 15:20:57 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 15:21:00 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 15:21:07 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 15:21:10 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 15:21:13 INFO mapred.JobClient:  map 100% reduce 76%
13/05/17 15:21:16 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 15:21:19 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 15:21:22 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 15:21:25 INFO mapred.JobClient:  map 100% reduce 82%
13/05/17 15:21:28 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 15:21:31 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 15:21:34 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 15:21:37 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 15:21:40 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 15:21:43 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 15:21:46 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 15:21:49 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 15:22:01 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 15:22:12 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 15:22:15 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 15:22:21 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 15:22:24 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 15:22:29 INFO mapred.JobClient: Job complete: job_201305171515_0001
13/05/17 15:22:29 INFO mapred.JobClient: Counters: 29
13/05/17 15:22:29 INFO mapred.JobClient:   Job Counters 
13/05/17 15:22:29 INFO mapred.JobClient:     Launched reduce tasks=3
13/05/17 15:22:29 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1451852
13/05/17 15:22:29 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 15:22:29 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 15:22:29 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 15:22:29 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 15:22:29 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=740074
13/05/17 15:22:29 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 15:22:29 INFO mapred.JobClient:     Bytes Written=4848000259
13/05/17 15:22:29 INFO mapred.JobClient:   FileSystemCounters
13/05/17 15:22:29 INFO mapred.JobClient:     FILE_BYTES_READ=8558685369
13/05/17 15:22:29 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 15:22:29 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11718664899
13/05/17 15:22:29 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000259
13/05/17 15:22:29 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 15:22:29 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 15:22:29 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 15:22:29 INFO mapred.JobClient:     Map output materialized bytes=3166533455
13/05/17 15:22:29 INFO mapred.JobClient:     Map input records=120000000
13/05/17 15:22:29 INFO mapred.JobClient:     Reduce shuffle bytes=3060983434
13/05/17 15:22:29 INFO mapred.JobClient:     Spilled Records=904771314
13/05/17 15:22:29 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 15:22:29 INFO mapred.JobClient:     CPU time spent (ms)=1605650
13/05/17 15:22:29 INFO mapred.JobClient:     Total committed heap usage (bytes)=50763726848
13/05/17 15:22:29 INFO mapred.JobClient:     Combine input records=0
13/05/17 15:22:29 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 15:22:29 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 15:22:29 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 15:22:29 INFO mapred.JobClient:     Combine output records=0
13/05/17 15:22:29 INFO mapred.JobClient:     Physical memory (bytes) snapshot=45640925184
13/05/17 15:22:29 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 15:22:29 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3198914654208
13/05/17 15:22:29 INFO mapred.JobClient:     Map output records=240000000
Execution Time 318087 ms

real	5m18.855s
user	0m2.100s
sys	0m0.228s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      9538  0.0  0.0 106084  1400 ?        Ss   15:22   0:00 bash -c ps aux | grep java
jmg3      9554  0.0  0.0 103232   840 ?        S    15:22   0:00 grep java
jmg3      7040  0.0  0.0  59072  3528 pts/0    S+   15:22   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      7046  0.0  0.0 106084  1404 ?        Ss   15:22   0:00 bash -c ps aux | grep java
jmg3      7062  0.0  0.0 103232   844 ?        S    15:22   0:00 grep java
run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 3145728 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3      9580  0.0  0.0 106084  1400 ?        Ss   15:22   0:00 bash -c ps aux | grep java
jmg3      9596  0.0  0.0 103232   840 ?        S    15:22   0:00 grep java
jmg3      7099  0.0  0.0  59072  3528 pts/0    S+   15:22   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      7105  0.0  0.0 106084  1396 ?        Ss   15:22   0:00 bash -c ps aux | grep java
jmg3      7121  0.0  0.0 103232   840 ?        S    15:22   0:00 grep java
Done
Setting path to /tmp/1300891.daman.davinci.rice.edu
13 3 36 36 256 256 3 3 3145728 3145728 36 36 256 256 3 3 3145728 3145728 268435456 12 1 10 -10 24



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
  <name>mapred.reduce.tasks</name><value>3</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>3</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=3145728 -Dopencl.mapper.bufferSize.cpu=3145728 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=3145728 -Dopencl.reducer.bufferSize.cpu=3145728</value>
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

13/05/17 15:23:03 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:23:03 INFO util.GSet: VM type       = 64-bit
13/05/17 15:23:03 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:23:03 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:23:03 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:23:03 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:23:03 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:23:03 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:23:03 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:23:03 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:23:03 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:23:03 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:23:04 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:23:04 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/17 15:25:24 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:25:25 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:25:25 INFO mapred.JobClient: Running job: job_201305171523_0001
13/05/17 15:25:26 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:25:42 INFO mapred.JobClient:  map 40% reduce 0%
13/05/17 15:25:45 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:25:51 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 15:25:56 INFO mapred.JobClient: Task Id : attempt_201305171523_0001_m_000003_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171523_0001_m_000003_0: Scope block from 31 to  64
13/05/17 15:26:06 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:26:18 INFO mapred.JobClient:  map 46% reduce 0%
13/05/17 15:26:21 INFO mapred.JobClient:  map 66% reduce 0%
13/05/17 15:26:24 INFO mapred.JobClient:  map 81% reduce 1%
13/05/17 15:26:27 INFO mapred.JobClient:  map 83% reduce 2%
13/05/17 15:26:30 INFO mapred.JobClient:  map 83% reduce 3%
13/05/17 15:26:33 INFO mapred.JobClient:  map 83% reduce 4%
13/05/17 15:26:36 INFO mapred.JobClient:  map 83% reduce 5%
13/05/17 15:26:41 INFO mapred.JobClient:  map 83% reduce 7%
13/05/17 15:26:44 INFO mapred.JobClient:  map 83% reduce 8%
13/05/17 15:26:47 INFO mapred.JobClient:  map 83% reduce 9%
13/05/17 15:26:50 INFO mapred.JobClient:  map 83% reduce 11%
13/05/17 15:26:56 INFO mapred.JobClient:  map 86% reduce 12%
13/05/17 15:26:59 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 15:27:11 INFO mapred.JobClient:  map 93% reduce 15%
13/05/17 15:27:14 INFO mapred.JobClient:  map 93% reduce 17%
13/05/17 15:27:17 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 15:27:20 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 15:27:23 INFO mapred.JobClient:  map 100% reduce 20%
13/05/17 15:27:26 INFO mapred.JobClient:  map 100% reduce 22%
13/05/17 15:27:29 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 15:27:32 INFO mapred.JobClient:  map 100% reduce 24%
13/05/17 15:27:35 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 15:27:38 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 15:27:41 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 15:27:53 INFO mapred.JobClient:  map 100% reduce 30%
13/05/17 15:27:56 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 15:28:02 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 15:28:42 INFO mapred.JobClient:  map 100% reduce 56%
13/05/17 15:28:46 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 15:28:49 INFO mapred.JobClient:  map 100% reduce 70%
13/05/17 15:28:52 INFO mapred.JobClient:  map 100% reduce 71%
13/05/17 15:28:55 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 15:29:08 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 15:29:11 INFO mapred.JobClient:  map 100% reduce 78%
13/05/17 15:29:14 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 15:29:17 INFO mapred.JobClient:  map 100% reduce 81%
13/05/17 15:29:20 INFO mapred.JobClient:  map 100% reduce 82%
13/05/17 15:29:23 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 15:29:26 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 15:29:29 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 15:29:32 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 15:29:35 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 15:29:38 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 15:29:41 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 15:29:44 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 15:29:47 INFO mapred.JobClient:  map 100% reduce 94%
13/05/17 15:29:50 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 15:29:53 INFO mapred.JobClient:  map 100% reduce 97%
13/05/17 15:29:59 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 15:30:12 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 15:30:17 INFO mapred.JobClient: Job complete: job_201305171523_0001
13/05/17 15:30:17 INFO mapred.JobClient: Counters: 29
13/05/17 15:30:17 INFO mapred.JobClient:   Job Counters 
13/05/17 15:30:17 INFO mapred.JobClient:     Launched reduce tasks=3
13/05/17 15:30:17 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1357806
13/05/17 15:30:17 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 15:30:17 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 15:30:17 INFO mapred.JobClient:     Launched map tasks=31
13/05/17 15:30:17 INFO mapred.JobClient:     Data-local map tasks=31
13/05/17 15:30:17 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=676505
13/05/17 15:30:17 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 15:30:17 INFO mapred.JobClient:     Bytes Written=4848000259
13/05/17 15:30:17 INFO mapred.JobClient:   FileSystemCounters
13/05/17 15:30:17 INFO mapred.JobClient:     FILE_BYTES_READ=8558685844
13/05/17 15:30:17 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 15:30:17 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11718665374
13/05/17 15:30:17 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000259
13/05/17 15:30:17 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 15:30:17 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 15:30:17 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 15:30:17 INFO mapred.JobClient:     Map output materialized bytes=3166533455
13/05/17 15:30:17 INFO mapred.JobClient:     Map input records=120000000
13/05/17 15:30:17 INFO mapred.JobClient:     Reduce shuffle bytes=3060983434
13/05/17 15:30:17 INFO mapred.JobClient:     Spilled Records=904771314
13/05/17 15:30:17 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 15:30:17 INFO mapred.JobClient:     CPU time spent (ms)=1566980
13/05/17 15:30:17 INFO mapred.JobClient:     Total committed heap usage (bytes)=45008879616
13/05/17 15:30:17 INFO mapred.JobClient:     Combine input records=0
13/05/17 15:30:17 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 15:30:17 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 15:30:17 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 15:30:17 INFO mapred.JobClient:     Combine output records=0
13/05/17 15:30:17 INFO mapred.JobClient:     Physical memory (bytes) snapshot=38469091328
13/05/17 15:30:17 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 15:30:17 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3198914654208
13/05/17 15:30:17 INFO mapred.JobClient:     Map output records=240000000
Execution Time 292345 ms

real	4m53.345s
user	0m1.988s
sys	0m0.221s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3      9858  3.1  0.0      0     0 ?        Zl   15:23   0:13 [java] <defunct>
jmg3     13053  0.0  0.0 106084  1396 ?        Ss   15:30   0:00 bash -c ps aux | grep java
jmg3     13069  0.0  0.0 103232   844 ?        S    15:30   0:00 grep java
jmg3      8296  0.0  0.0  59072  3532 pts/0    S+   15:30   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      8302  0.0  0.0 106084  1396 ?        Ss   15:30   0:00 bash -c ps aux | grep java
jmg3      8318  0.0  0.0 103232   840 ?        S    15:30   0:00 grep java
run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 3145728 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     13095  0.0  0.0 106084  1400 ?        Ss   15:30   0:00 bash -c ps aux | grep java
jmg3     13111  0.0  0.0 103232   840 ?        S    15:30   0:00 grep java
jmg3      8355  0.0  0.0  59072  3528 pts/0    S+   15:30   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      8361  0.0  0.0 106084  1400 ?        Ss   15:30   0:00 bash -c ps aux | grep java
jmg3      8377  0.0  0.0 103232   840 ?        S    15:30   0:00 grep java
Done
Setting path to /tmp/1300891.daman.davinci.rice.edu
13 3 36 36 256 256 3 3 3145728 3145728 36 36 256 256 3 3 3145728 3145728 268435456 12 1 10 -10 24



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
  <name>mapred.reduce.tasks</name><value>3</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>3</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=3145728 -Dopencl.mapper.bufferSize.cpu=3145728 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=3145728 -Dopencl.reducer.bufferSize.cpu=3145728</value>
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

13/05/17 15:30:58 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:30:58 INFO util.GSet: VM type       = 64-bit
13/05/17 15:30:58 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:30:58 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:30:58 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:30:58 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:30:58 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:30:58 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:30:58 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:30:58 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:30:58 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:30:59 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:30:59 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:30:59 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/17 15:33:20 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:33:20 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:33:21 INFO mapred.JobClient: Running job: job_201305171531_0001
13/05/17 15:33:22 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:33:46 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:34:25 INFO mapred.JobClient:  map 50% reduce 0%
13/05/17 15:34:28 INFO mapred.JobClient:  map 68% reduce 1%
13/05/17 15:34:31 INFO mapred.JobClient:  map 84% reduce 1%
13/05/17 15:34:34 INFO mapred.JobClient:  map 86% reduce 3%
13/05/17 15:34:37 INFO mapred.JobClient:  map 86% reduce 4%
13/05/17 15:34:40 INFO mapred.JobClient:  map 86% reduce 5%
13/05/17 15:34:43 INFO mapred.JobClient:  map 86% reduce 7%
13/05/17 15:34:50 INFO mapred.JobClient:  map 86% reduce 8%
13/05/17 15:34:53 INFO mapred.JobClient:  map 86% reduce 10%
13/05/17 15:34:56 INFO mapred.JobClient:  map 86% reduce 11%
13/05/17 15:34:59 INFO mapred.JobClient:  map 86% reduce 12%
13/05/17 15:35:02 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 15:35:21 INFO mapred.JobClient:  map 86% reduce 15%
13/05/17 15:35:24 INFO mapred.JobClient:  map 96% reduce 15%
13/05/17 15:35:27 INFO mapred.JobClient:  map 96% reduce 17%
13/05/17 15:35:31 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 15:35:34 INFO mapred.JobClient:  map 100% reduce 19%
13/05/17 15:35:37 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 15:35:40 INFO mapred.JobClient:  map 100% reduce 22%
13/05/17 15:35:43 INFO mapred.JobClient:  map 100% reduce 23%
13/05/17 15:35:46 INFO mapred.JobClient:  map 100% reduce 25%
13/05/17 15:35:49 INFO mapred.JobClient:  map 100% reduce 26%
13/05/17 15:35:52 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 15:35:55 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 15:36:01 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 15:36:04 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 15:36:07 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 15:36:45 INFO mapred.JobClient:  map 100% reduce 33%
13/05/17 15:36:48 INFO mapred.JobClient:  map 100% reduce 66%
13/05/17 15:36:51 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 15:36:54 INFO mapred.JobClient:  map 100% reduce 69%
13/05/17 15:36:58 INFO mapred.JobClient:  map 100% reduce 71%
13/05/17 15:37:01 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 15:37:04 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 15:37:07 INFO mapred.JobClient:  map 100% reduce 76%
13/05/17 15:37:10 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 15:37:15 INFO mapred.JobClient:  map 100% reduce 78%
13/05/17 15:37:18 INFO mapred.JobClient:  map 100% reduce 79%
13/05/17 15:37:31 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 15:37:34 INFO mapred.JobClient:  map 100% reduce 83%
13/05/17 15:37:37 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 15:37:40 INFO mapred.JobClient:  map 100% reduce 86%
13/05/17 15:37:43 INFO mapred.JobClient:  map 100% reduce 87%
13/05/17 15:37:46 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 15:37:49 INFO mapred.JobClient:  map 100% reduce 90%
13/05/17 15:37:52 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 15:37:55 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 15:37:58 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 15:38:01 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 15:38:04 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 15:38:10 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 15:38:15 INFO mapred.JobClient: Job complete: job_201305171531_0001
13/05/17 15:38:15 INFO mapred.JobClient: Counters: 29
13/05/17 15:38:15 INFO mapred.JobClient:   Job Counters 
13/05/17 15:38:15 INFO mapred.JobClient:     Launched reduce tasks=3
13/05/17 15:38:15 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1497090
13/05/17 15:38:15 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 15:38:15 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 15:38:15 INFO mapred.JobClient:     Launched map tasks=30
13/05/17 15:38:15 INFO mapred.JobClient:     Data-local map tasks=30
13/05/17 15:38:15 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=689915
13/05/17 15:38:15 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 15:38:15 INFO mapred.JobClient:     Bytes Written=4848000259
13/05/17 15:38:15 INFO mapred.JobClient:   FileSystemCounters
13/05/17 15:38:15 INFO mapred.JobClient:     FILE_BYTES_READ=8558685486
13/05/17 15:38:15 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 15:38:15 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11718665016
13/05/17 15:38:15 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000259
13/05/17 15:38:15 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 15:38:15 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 15:38:15 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 15:38:15 INFO mapred.JobClient:     Map output materialized bytes=3166533455
13/05/17 15:38:15 INFO mapred.JobClient:     Map input records=120000000
13/05/17 15:38:15 INFO mapred.JobClient:     Reduce shuffle bytes=3060983434
13/05/17 15:38:15 INFO mapred.JobClient:     Spilled Records=904771314
13/05/17 15:38:15 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 15:38:15 INFO mapred.JobClient:     CPU time spent (ms)=1616000
13/05/17 15:38:15 INFO mapred.JobClient:     Total committed heap usage (bytes)=45460422656
13/05/17 15:38:15 INFO mapred.JobClient:     Combine input records=0
13/05/17 15:38:15 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 15:38:15 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 15:38:15 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 15:38:15 INFO mapred.JobClient:     Combine output records=0
13/05/17 15:38:15 INFO mapred.JobClient:     Physical memory (bytes) snapshot=40539127808
13/05/17 15:38:15 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 15:38:15 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3198982815744
13/05/17 15:38:15 INFO mapred.JobClient:     Map output records=240000000
Execution Time 294566 ms

real	4m55.339s
user	0m1.987s
sys	0m0.232s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     13373  3.1  0.0      0     0 ?        Zl   15:31   0:14 [java] <defunct>
jmg3     16426  0.0  0.0 106084  1400 ?        Ss   15:38   0:00 bash -c ps aux | grep java
jmg3     16442  0.0  0.0 103232   844 ?        S    15:38   0:00 grep java
jmg3      9544  0.0  0.0  59072  3532 pts/0    S+   15:38   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      9550  0.0  0.0 106084  1400 ?        Ss   15:38   0:00 bash -c ps aux | grep java
jmg3      9566  0.0  0.0 103232   844 ?        S    15:38   0:00 grep java
run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 3145728 bytes, Java heap size 24G
Killing
java: no process killed
java: no process killed
jmg3     16468  0.0  0.0 106084  1400 ?        Ss   15:38   0:00 bash -c ps aux | grep java
jmg3     16484  0.0  0.0 103232   844 ?        S    15:38   0:00 grep java
jmg3      9603  0.0  0.0  59204  3528 pts/0    S+   15:38   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3      9609  0.0  0.0 106084  1396 ?        Ss   15:38   0:00 bash -c ps aux | grep java
jmg3      9625  0.0  0.0 103232   840 ?        S    15:38   0:00 grep java
Done
Setting path to /tmp/1300891.daman.davinci.rice.edu
13 3 36 36 256 256 3 3 3145728 3145728 36 36 256 256 3 3 3145728 3145728 268435456 12 1 10 -10 24



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
  <name>mapred.reduce.tasks</name><value>3</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>3</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx24G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=3145728 -Dopencl.mapper.bufferSize.cpu=3145728 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=3145728 -Dopencl.reducer.bufferSize.cpu=3145728</value>
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

13/05/17 15:38:59 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-015.davinci.rice.edu/192.168.110.215
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Fri May 17 10:34:38 CDT 2013
************************************************************/
13/05/17 15:38:59 INFO util.GSet: VM type       = 64-bit
13/05/17 15:38:59 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/17 15:38:59 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/17 15:38:59 INFO util.GSet: recommended=2097152, actual=2097152
13/05/17 15:38:59 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/17 15:38:59 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/17 15:38:59 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/17 15:38:59 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/17 15:38:59 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/17 15:38:59 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/17 15:38:59 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/17 15:38:59 INFO common.Storage: Storage directory /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/17 15:38:59 INFO namenode.NameNode: SHUTDOWN_MSG: 
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
gpu-012: Max num map slots is 13
Completed start all
Putting inputs
Warning: $HADOOP_HOME is deprecated.

Running Application
Warning: $HADOOP_HOME is deprecated.

13/05/17 15:41:53 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
13/05/17 15:41:54 INFO input.FileInputFormat: Total input paths to process : 30
13/05/17 15:41:55 INFO mapred.JobClient: Running job: job_201305171539_0001
13/05/17 15:41:56 INFO mapred.JobClient:  map 0% reduce 0%
13/05/17 15:42:14 INFO mapred.JobClient:  map 38% reduce 0%
13/05/17 15:42:17 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:42:23 INFO mapred.JobClient:  map 39% reduce 0%
13/05/17 15:42:28 INFO mapred.JobClient: Task Id : attempt_201305171539_0001_m_000011_0, Status : FAILED
Throwable.toString: java.io.IOException: Task process exit with nonzero status of 139.
Throwable.getMessage: Task process exit with nonzero status of 139.
baos: java.lang.Throwable: Child Error
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:274)
Caused by: java.io.IOException: Task process exit with nonzero status of 139.
	at org.apache.hadoop.mapred.TaskRunner.run(TaskRunner.java:261)

attempt_201305171539_0001_m_000011_0: Scope block from 31 to  64
13/05/17 15:42:38 INFO mapred.JobClient:  map 43% reduce 0%
13/05/17 15:42:53 INFO mapred.JobClient:  map 70% reduce 0%
13/05/17 15:42:56 INFO mapred.JobClient:  map 79% reduce 1%
13/05/17 15:42:59 INFO mapred.JobClient:  map 82% reduce 2%
13/05/17 15:43:05 INFO mapred.JobClient:  map 83% reduce 4%
13/05/17 15:43:08 INFO mapred.JobClient:  map 83% reduce 5%
13/05/17 15:43:12 INFO mapred.JobClient:  map 83% reduce 6%
13/05/17 15:43:18 INFO mapred.JobClient:  map 83% reduce 8%
13/05/17 15:43:22 INFO mapred.JobClient:  map 83% reduce 10%
13/05/17 15:43:28 INFO mapred.JobClient:  map 83% reduce 11%
13/05/17 15:43:31 INFO mapred.JobClient:  map 86% reduce 13%
13/05/17 15:43:37 INFO mapred.JobClient:  map 86% reduce 14%
13/05/17 15:43:53 INFO mapred.JobClient:  map 100% reduce 15%
13/05/17 15:43:56 INFO mapred.JobClient:  map 100% reduce 17%
13/05/17 15:43:59 INFO mapred.JobClient:  map 100% reduce 18%
13/05/17 15:44:02 INFO mapred.JobClient:  map 100% reduce 20%
13/05/17 15:44:05 INFO mapred.JobClient:  map 100% reduce 21%
13/05/17 15:44:08 INFO mapred.JobClient:  map 100% reduce 22%
13/05/17 15:44:11 INFO mapred.JobClient:  map 100% reduce 24%
13/05/17 15:44:14 INFO mapred.JobClient:  map 100% reduce 25%
13/05/17 15:44:17 INFO mapred.JobClient:  map 100% reduce 27%
13/05/17 15:44:23 INFO mapred.JobClient:  map 100% reduce 28%
13/05/17 15:44:29 INFO mapred.JobClient:  map 100% reduce 29%
13/05/17 15:44:32 INFO mapred.JobClient:  map 100% reduce 31%
13/05/17 15:44:35 INFO mapred.JobClient:  map 100% reduce 32%
13/05/17 15:45:11 INFO mapred.JobClient:  map 100% reduce 56%
13/05/17 15:45:14 INFO mapred.JobClient:  map 100% reduce 68%
13/05/17 15:45:17 INFO mapred.JobClient:  map 100% reduce 69%
13/05/17 15:45:20 INFO mapred.JobClient:  map 100% reduce 71%
13/05/17 15:45:23 INFO mapred.JobClient:  map 100% reduce 72%
13/05/17 15:45:26 INFO mapred.JobClient:  map 100% reduce 73%
13/05/17 15:45:44 INFO mapred.JobClient:  map 100% reduce 74%
13/05/17 15:45:47 INFO mapred.JobClient:  map 100% reduce 75%
13/05/17 15:45:50 INFO mapred.JobClient:  map 100% reduce 77%
13/05/17 15:45:53 INFO mapred.JobClient:  map 100% reduce 78%
13/05/17 15:45:56 INFO mapred.JobClient:  map 100% reduce 80%
13/05/17 15:45:59 INFO mapred.JobClient:  map 100% reduce 81%
13/05/17 15:46:02 INFO mapred.JobClient:  map 100% reduce 82%
13/05/17 15:46:05 INFO mapred.JobClient:  map 100% reduce 84%
13/05/17 15:46:12 INFO mapred.JobClient:  map 100% reduce 85%
13/05/17 15:46:15 INFO mapred.JobClient:  map 100% reduce 88%
13/05/17 15:46:18 INFO mapred.JobClient:  map 100% reduce 89%
13/05/17 15:46:21 INFO mapred.JobClient:  map 100% reduce 91%
13/05/17 15:46:24 INFO mapred.JobClient:  map 100% reduce 92%
13/05/17 15:46:27 INFO mapred.JobClient:  map 100% reduce 93%
13/05/17 15:46:30 INFO mapred.JobClient:  map 100% reduce 95%
13/05/17 15:46:33 INFO mapred.JobClient:  map 100% reduce 96%
13/05/17 15:46:36 INFO mapred.JobClient:  map 100% reduce 98%
13/05/17 15:46:42 INFO mapred.JobClient:  map 100% reduce 99%
13/05/17 15:46:48 INFO mapred.JobClient:  map 100% reduce 100%
13/05/17 15:46:53 INFO mapred.JobClient: Job complete: job_201305171539_0001
13/05/17 15:46:53 INFO mapred.JobClient: Counters: 29
13/05/17 15:46:53 INFO mapred.JobClient:   Job Counters 
13/05/17 15:46:53 INFO mapred.JobClient:     Launched reduce tasks=3
13/05/17 15:46:53 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=1378131
13/05/17 15:46:53 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
13/05/17 15:46:53 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
13/05/17 15:46:53 INFO mapred.JobClient:     Launched map tasks=31
13/05/17 15:46:53 INFO mapred.JobClient:     Data-local map tasks=31
13/05/17 15:46:53 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=709679
13/05/17 15:46:53 INFO mapred.JobClient:   File Output Format Counters 
13/05/17 15:46:53 INFO mapred.JobClient:     Bytes Written=4848000259
13/05/17 15:46:53 INFO mapred.JobClient:   FileSystemCounters
13/05/17 15:46:53 INFO mapred.JobClient:     FILE_BYTES_READ=8558684926
13/05/17 15:46:53 INFO mapred.JobClient:     HDFS_BYTES_READ=1921012190
13/05/17 15:46:53 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=11718664456
13/05/17 15:46:53 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=4848000259
13/05/17 15:46:53 INFO mapred.JobClient:   File Input Format Counters 
13/05/17 15:46:53 INFO mapred.JobClient:     Bytes Read=1921008270
13/05/17 15:46:53 INFO mapred.JobClient:   Map-Reduce Framework
13/05/17 15:46:53 INFO mapred.JobClient:     Map output materialized bytes=3166533455
13/05/17 15:46:53 INFO mapred.JobClient:     Map input records=120000000
13/05/17 15:46:53 INFO mapred.JobClient:     Reduce shuffle bytes=3060983434
13/05/17 15:46:53 INFO mapred.JobClient:     Spilled Records=904771314
13/05/17 15:46:53 INFO mapred.JobClient:     Map output bytes=2880000000
13/05/17 15:46:53 INFO mapred.JobClient:     CPU time spent (ms)=1606430
13/05/17 15:46:53 INFO mapred.JobClient:     Total committed heap usage (bytes)=51021545472
13/05/17 15:46:53 INFO mapred.JobClient:     Combine input records=0
13/05/17 15:46:53 INFO mapred.JobClient:     SPLIT_RAW_BYTES=3920
13/05/17 15:46:53 INFO mapred.JobClient:     Reduce input records=240000000
13/05/17 15:46:53 INFO mapred.JobClient:     Reduce input groups=0
13/05/17 15:46:53 INFO mapred.JobClient:     Combine output records=0
13/05/17 15:46:53 INFO mapred.JobClient:     Physical memory (bytes) snapshot=44702769152
13/05/17 15:46:53 INFO mapred.JobClient:     Reduce output records=240000000
13/05/17 15:46:53 INFO mapred.JobClient:     Virtual memory (bytes) snapshot=3198983868416
13/05/17 15:46:53 INFO mapred.JobClient:     Map output records=240000000
Execution Time 300013 ms

real	5m0.807s
user	0m2.017s
sys	0m0.229s
Retrieving Outputs
Warning: $HADOOP_HOME is deprecated.

get: Target /scratch/jmg3/sort.output/sort.output/_SUCCESS already exists
Grepping Logs
grep: /tmp/1300891.daman.davinci.rice.edu/hadoop-jmg3/mapred/local/userlogs/*: No such file or directory
Done, Killing
jmg3     19931  0.0  0.0 106084  1396 ?        Ss   15:47   0:00 bash -c ps aux | grep java
jmg3     19947  0.0  0.0 103232   844 ?        S    15:47   0:00 grep java
jmg3     10804  0.0  0.0  59072  3528 pts/0    S+   15:47   0:00 ssh -o ConnectTimeout=2 gpu-015 ps aux | grep java
jmg3     10810  0.0  0.0 106084  1400 ?        Ss   15:47   0:00 bash -c ps aux | grep java
jmg3     10826  0.0  0.0 103232   844 ?        S    15:47   0:00 grep java
