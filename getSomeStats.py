import os
import sys

def printStats(name, mes):
    print name
    if len(mes) > 0:
        sum = 0
        for t in sorted(mes.keys()):
            sum = sum + mes[t]
            print '  '+str(t)+'->'+str(mes[t])
        print ' '+str(sum / len(mes))
    else:
        print 'No measurements'


if len(sys.argv) != 2:
    print 'usage: python getSomeStats.py filename'
    sys.exit()

filename = sys.argv[1]


#cpuTimes = { } 
#gpuTimes = { } 
#javaTimes = { }

for stage in [ 'mapper', 'reducer' ]:
    print 'STAGE '+stage
    allTimes = { }
    fp = open(filename, 'r')
    for line in fp:
        if stage in line and 'Profiling not enabled' in line:
            idIndex = line.find('attempt')
            idSub = line[idIndex:]
            idSub = idSub[idSub.find('_')+1:]
            idSub = idSub[idSub.find('_')+1:]
            idSub = idSub[idSub.find('_')+1:]
            idSub = idSub[idSub.find('_')+1:]
            idSub = idSub[:idSub.find('_')]
            id = int(idSub)

            line = line[line.find('Profiling not enabled'):]
            line = line[line.find('('):]

            deviceSub = line[line.find('device'):]
            deviceTokens = deviceSub.split()
            deviceStr = deviceTokens[1]
            device = int(deviceStr[0:len(deviceStr)-1])

            totalTimeSub = line[line.find('Total time:'):]
            totalTimeTokens = totalTimeSub.split()
            totalTime = int(totalTimeTokens[2])

            if 'CPU' in line:
                deviceId = str(device)+' CPU'
                if not deviceId in allTimes:
                    allTimes[deviceId] = { }
                allTimes[deviceId][id] = totalTime
            elif 'GPU' in line:
                deviceId = str(device)+' GPU'
                if not deviceId in allTimes:
                    allTimes[deviceId] = { }
                allTimes[deviceId][id] = totalTime
            elif 'JAVA' in line:
                deviceId = str(device)+' JAVA'
                if not deviceId in allTimes:
                    allTimes[deviceId] = { }
                allTimes[deviceId][id] = totalTime

    for k in allTimes:
        printStats(k, allTimes[k])
    print
