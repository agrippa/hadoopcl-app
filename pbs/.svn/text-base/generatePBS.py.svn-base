import os

appDir = os.getenv('HADOOP_APP_DIR')
benchmarks = [ 'bs', 'sort', 'pi', 'kmeans' ]

def compressedStr(compressed):
    if compressed:
        return 'compressed'
    else:
        return 'uncompressed'

def getOutputDirectory(benchmark, framework, compressed):
    return appDir+'/pbs/'+benchmark+'_'+framework+'_'+compressedStr(compressed)

def writefile(fp, b, framework, compressed):
    fp.write('#PBS -o '+getOutputDirectory(b, framework, compressed)+'/out\n')
    fp.write('#PBS -e '+getOutputDirectory(b, framework, compressed)+'/err\n')
    fp.write('#PBS -M jmg3@rice.edu\n')
    fp.write('#PBS -m bae\n')
    fp.write('#PBS -l nodes=2:ppn=12\n')
    if b == 'sort':
        fp.write('#PBS -l walltime=10:00:00\n')
    else:
        fp.write('#PBS -l walltime=3:00:00\n')
    fp.write('#PBS -q graphics\n')
    fp.write('#PBS -V\n')
    fp.write('#PBS -N '+b+'-'+framework+'-'+compressedStr(compressed)+'\n')
    fp.write('cd ${HADOOP_APP_DIR}\n')
    fp.write('for t in 1; do\n')
    if compressed:
        fp.write('    ./run_'+b+'_'+framework+'.sh default lzo\n')
    else:
        fp.write('    ./run_'+b+'_'+framework+'.sh none none\n')
    fp.write('done\n')

    if not os.path.isdir(getOutputDirectory(b, framework, compressed)):
        os.mkdir(getOutputDirectory(b, framework, compressed))

for b in benchmarks:
    opencl_compressed = open(b+'_opencl_compressed.pbs', 'w')
    opencl_uncompressed = open(b+'_opencl_uncompressed.pbs', 'w')
    java_compressed = open(b+'_java_compressed.pbs', 'w')
    java_uncompressed = open(b+'_java_uncompressed.pbs', 'w')

    writefile(java_compressed, b, 'java', True)
    writefile(java_uncompressed, b, 'java', False)
    writefile(opencl_compressed, b, 'opencl', True)
    writefile(opencl_uncompressed, b, 'opencl', False)

    opencl_compressed.close()
    opencl_uncompressed.close()
    java_compressed.close()
    java_uncompressed.close()
