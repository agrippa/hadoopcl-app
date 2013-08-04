import os 
import sys
from os import listdir
from os.path import isfile, join

if len(sys.argv) != 2:
    print 'usage: python aggregateRecordings.py recordings-directory'
    sys.exit()

dir = sys.argv[1]
allFileNames = [ f for f in listdir(dir) if isfile(join(dir+'/'+f)) ]

saved = open(dir+'/recordings.saved', 'a')
launches = open(dir+'/launches.saved', 'a')

for file in allFileNames:
    if file != 'recordings.saved' and file != 'launches.saved':
        count = 0
        fp = open(dir+'/'+file, 'r')
        if file.find('.recordings') != -1:
            for line in fp:
                count = count + 1
                saved.write(line)
        elif file.find('.launches') != -1:
            for line in fp:
                count = count + 1
                launches.write(line)
        else:
            print 'Unable to identify file type for '+file
        fp.close()
        os.remove(dir+'/'+file)
        print file+': '+str(count)


saved.close()
