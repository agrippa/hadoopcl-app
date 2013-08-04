run_opencl_test.sh: Running sort with input compression default, intermediate compression lzo
Buffer size 1048576 bytes, Java heap size 8G
Killing
java: no process killed
java: no process killed
jmg3     27611  0.0  0.0 106084  1396 ?        Ss   14:44   0:00 bash -c ps aux | grep java
jmg3     27627  0.0  0.0 103232   844 ?        S    14:44   0:00 grep java
jmg3     18685  0.0  0.0  59072  3532 pts/0    S    14:44   0:00 ssh -o ConnectTimeout=2 gpu-011 ps aux | grep java
jmg3     18691  0.0  0.0 106084  1400 ?        Ss   14:44   0:00 bash -c ps aux | grep java
jmg3     18707  0.0  0.0 103232   844 ?        S    14:44   0:00 grep java
Done
Setting path to /tmp/1270937.daman.davinci.rice.edu
13 5 36 36 256 256 3 3 1048576 1048576 36 36 256 256 3 3 1048576 1048576 268435456 12 1 10 -10 8



Here are the results:
export JAVA_HOME=/opt/apps/jdk/1.6
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true
export HADOOP_LOG_DIR=/tmp/1270937.daman.davinci.rice.edu/logs
export HADOOP_CLASSPATH=/home/jmg3/hadoop-gpl-compression-read-only/build/hadoop-gpl-compression-0.2.0-dev.jar:${HADOOP_CLASSPATH}
export JAVA_LIBRARY_PATH=/home/jmg3/lzo-install/lib:${JAVA_LIBRARY_PATH}
-----------------------------------------------------
  <name>mapred.job.tracker</name>
  <value>gpu-011.davinci.rice.edu:54311</value>
  <name>mapred.reduce.parallel.copies</name><value>5</value>
  <name>task.tracker.http.threads</name><value>40</value>
  <name>mapred.reduce.tasks</name><value>5</value>
  <name>mapred.map.tasks</name><value>13</value>
  <name>opencl.mapper.gpumult</name><value>12</value>
  <name>opencl.mapper.cpumult</name><value>1</value>
  <name>opencl.reducer.gpumult</name><value>10</value>
  <name>opencl.reducer.cpumult</name><value>-10</value>
  <name>mapred.tasktracker.map.tasks.maximum</name><value>13</value>
  <name>mapred.tasktracker.reduce.tasks.maximum</name><value>5</value>
  <name>mapred.map.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.reduce.tasks.speculative.execution</name>
  <value>true</value>
  <name>mapred.task.timeout</name><value>1200000</value>
  <name>mapred.child.java.opts</name><value>-Xmx8G -Dopencl.mapper.groups.gpu=36 -Dopencl.mapper.groups.cpu=36 -Dopencl.mapper.threadsPerGroup.gpu=256 -Dopencl.mapper.threadsPerGroup.cpu=256 -Dopencl.mapper.buffers.gpu=3 -Dopencl.mapper.buffers.cpu=3 -Dopencl.mapper.bufferSize.gpu=1048576 -Dopencl.mapper.bufferSize.cpu=1048576 -Dopencl.reducer.groups.gpu=36 -Dopencl.reducer.groups.cpu=36 -Dopencl.reducer.threadsPerGroup.gpu=256 -Dopencl.reducer.threadsPerGroup.cpu=256 -Dopencl.reducer.buffers.gpu=3 -Dopencl.reducer.buffers.cpu=3 -Dopencl.reducer.bufferSize.gpu=1048576 -Dopencl.reducer.bufferSize.cpu=1048576</value>
  <name>mapred.map.tasks.speculative.execution</name><value>false</value>
  <name>mapred.reduce.tasks.speculative.execution</name><value>false</value>
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
  <value>/tmp/1270937.daman.davinci.rice.edu/hadoop-${user.name}</value>
  <name>fs.default.name</name>
  <value>hdfs://gpu-011.davinci.rice.edu:54310</value>
<name>io.compression.codecs</name>
<value>
</value>
    <name>io.compression.codec.lzo.class</name>
    <value>com.hadoop.compression.lzo.LzoCodec</value>
-----------------------------------------------------
gpu-008
-----------------------------------------------------
gpu-011
-----------------------------------------------------
Completed reconfiguring
Warning: $HADOOP_HOME is deprecated.

13/05/04 14:45:11 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************
STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = gpu-011.davinci.rice.edu/192.168.110.211
STARTUP_MSG:   args = [-format]
STARTUP_MSG:   version = 1.0.4-SNAPSHOT
STARTUP_MSG:   build =  -r ; compiled by 'jmg3' on Wed Apr 24 08:01:03 CDT 2013
************************************************************/
13/05/04 14:45:11 INFO util.GSet: VM type       = 64-bit
13/05/04 14:45:11 INFO util.GSet: 2% max memory = 17.77875 MB
13/05/04 14:45:11 INFO util.GSet: capacity      = 2^21 = 2097152 entries
13/05/04 14:45:11 INFO util.GSet: recommended=2097152, actual=2097152
13/05/04 14:45:12 INFO namenode.FSNamesystem: fsOwner=jmg3
13/05/04 14:45:12 INFO namenode.FSNamesystem: supergroup=supergroup
13/05/04 14:45:12 INFO namenode.FSNamesystem: isPermissionEnabled=true
13/05/04 14:45:12 INFO namenode.FSNamesystem: dfs.block.invalidate.limit=100
13/05/04 14:45:12 INFO namenode.FSNamesystem: isAccessTokenEnabled=false accessKeyUpdateInterval=0 min(s), accessTokenLifetime=0 min(s)
13/05/04 14:45:12 INFO namenode.NameNode: Caching file names occuring more than 10 times 
13/05/04 14:45:12 INFO common.Storage: Image file of size 110 saved in 0 seconds.
13/05/04 14:45:12 INFO common.Storage: Storage directory /tmp/1270937.daman.davinci.rice.edu/hadoop-jmg3/dfs/name has been successfully formatted.
13/05/04 14:45:12 INFO namenode.NameNode: SHUTDOWN_MSG: 
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at gpu-011.davinci.rice.edu/192.168.110.211
************************************************************/
Completed namenode startup
Warning: $HADOOP_HOME is deprecated.

no jobtracker to stop
gpu-008: no tasktracker to stop
no namenode to stop
gpu-008: no datanode to stop
gpu-011: no secondarynamenode to stop
Completed stop all
Warning: $HADOOP_HOME is deprecated.

starting namenode, logging to /tmp/1270937.daman.davinci.rice.edu/logs/hadoop-jmg3-namenode-gpu-011.davinci.rice.edu.out
gpu-008: starting datanode, logging to /tmp/1270937.daman.davinci.rice.edu/logs/hadoop-jmg3-datanode-gpu-008.davinci.rice.edu.out
gpu-011: starting secondarynamenode, logging to /tmp/1270937.daman.davinci.rice.edu/logs/hadoop-jmg3-secondarynamenode-gpu-011.davinci.rice.edu.out
starting jobtracker, logging to /tmp/1270937.daman.davinci.rice.edu/logs/hadoop-jmg3-jobtracker-gpu-011.davinci.rice.edu.out
gpu-008: starting tasktracker, logging to /tmp/1270937.daman.davinci.rice.edu/logs/hadoop-jmg3-tasktracker-gpu-008.davinci.rice.edu.out
gpu-008: Max num map slots is 13
Completed start all
