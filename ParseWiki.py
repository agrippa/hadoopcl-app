import os
import sys

if len(sys.argv) != 4:
    print 'usage: python ParseWiki.py input output-folder start length'
    sys.exit()

input = open(sys.argv[0], 'r')
iter = 0

