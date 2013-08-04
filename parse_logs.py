import sys
import os

if len(sys.argv) != 3:
    print 'usage: python parse_logs.py filename type'
    sys.exit()

filename = sys.argv[1]
fp = open(filename, 'r')
checkMapper = True
if sys.argv[2] == 'r':
    checkMapper = False

keys = [ ]
times = [ ]

if filename.find('java') != -1:
    # hadoop run
    for line in fp:
        if (checkMapper and line.find('Mapper') != -1) or (not checkMapper and line.find('Reducer') != -1):
            tokens = line.split()
            nkeys = int(tokens[1])
            time = int(tokens[7])
            keys.append(nkeys)
            times.append(time)
            
else:
    # hadoopcl run
    for line in fp:
        if (checkMapper and line.find('mapper') != -1) or (not checkMapper and line.find('reducer') != -1):
            launchesIndex = line.find('launches')
            launchesIndex = launchesIndex + 10
            sub = line[launchesIndex:]
            sub = sub[:sub.find(')')]
            launchTokens = sub.split()
            nkeys = 0
            for t in launchTokens:
                nkeys = nkeys + int(t)
            tokens = line.split()
            time = int(tokens[len(tokens)-1])
            keys.append(nkeys)
            times.append(time)

#for i in range(len(keys)):
#    print str(keys[i])+' '+str(times[i])+' '+str(float(keys[i]) / float(times[i]))
print 'Total nkeys '+str(sum(keys))
print 'Total time '+str(sum(times))
print float(sum(keys)) / float(sum(times))
