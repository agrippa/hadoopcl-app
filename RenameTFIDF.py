import os
import sys
from os import listdir
from os.path import isfile, join

# This seems to just copy files from the tfidf-dir to tfidf-transformed-dir
# with a slight rename. In addition it filters out CRC files.

if len(sys.argv) != 3:
    print 'usage: python RenameTFIDF.py tfidf-dir tfidf-transformed-dir'
    sys.exit(1)

tfidfDir = sys.argv[1]
outputDir = sys.argv[2]

count = 0

files = listdir(tfidfDir)
print 'Found '+str(len(files))+' input files'

for f in files:
    if f.find('crc') == -1:
        numStr = "%05d" % count
        os.system('cp '+tfidfDir+'/'+f+' '+outputDir+'/part-r-'+numStr)
        count = count + 1
        if count % 100 == 0:
            print count

