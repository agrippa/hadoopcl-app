if [ $# != 20 ]; then
    echo Need more arguments for the startup script $#
    echo usage: startup.sh map_slots device ngroups threadsPerGroup
    exit 1
fi

${HADOOP_HOME}/reconfigure-hadoop ${1} ${2} ${3} ${4} ${5} ${6} ${7} ${8} ${9} ${10} ${11} ${12} ${13} ${14} ${15} ${16} ${17} ${18} ${19} ${20}
echo Completed reconfiguring
${HADOOP_HOME}/bin/hadoop namenode -format
echo Completed namenode startup
${HADOOP_HOME}/bin/stop-all.sh
echo Completed stop all
${HADOOP_HOME}/bin/start-all.sh
echo Completed start all
