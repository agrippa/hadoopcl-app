from time import time

#overallStart = time()

import scipy
from scipy import optimize
import numpy
import array
import random
import sys
import math

# Some example usage, performing second-order polynomial regression on 1 variable:
# > python predict.py p 1 2 4 3 9 4 16 5 25 6 36 7 49 8 64 9 81 10 100
# > python predict.py e 1 -5.13084273e-08  -1.28393123e-07   1.00000000e+00 11

### Below functions for generating polynomial objects ###

def numpy_array_equal(a1, a2):
    if len(a1) != len(a2):
        return False
    for i in range(len(a1)):
        if a1[i] != a2[i]:
            return False
    return True

def is_in(numpy_array, collection):
    for arr in collection:
        if numpy_array_equal(numpy_array, arr):
            return True
    return False


def evaluate_term(term, sample):
    val = 1.0
    for i in range(len(term)):
        if term[i] > 0:
            val = val * pow(sample[i], term[i])
    return val

if len(sys.argv) < 2:
    print 'usage: python predict.py nsamples ...'
    sys.exit(-1)

nsamples = int(sys.argv[1])

sampleStr = sys.argv[2:]
nvariables = (len(sampleStr) / nsamples) - 1

if nsamples <= 0:
    print 'No samples'
    sys.exit(-1)

x_true = numpy.zeros( (nsamples, nvariables) )
y_true = numpy.zeros( (nsamples) )

for i in range(nsamples):
    for j in range(nvariables):
        x_true[i, j] = float(sampleStr[(i * (nvariables+1)) + j])

    y_true[i] = float(sampleStr[(i * (nvariables+1)) + nvariables])

def func(B, *args):
    x = args[0]
    y = args[1]

    y_model = numpy.zeros( (len(y)) )
    for i in range(len(x)):
        accum = 0.0
        xrow = x[i]
        for j in range(len(B)):
            accum = accum + (xrow[j] * B[j])
        y_model[i] = accum 

    error = y-y_model
    return sum(error**2)

initial_values = numpy.zeros(nvariables)

B, minimum, d = optimize.fmin_l_bfgs_b(func, x0=initial_values, args=(x_true,y_true), approx_grad=True, maxiter=100)

for b in B:
    sys.stdout.write(str(b)+' ')
sys.stdout.write('\n')

print str(sum(y_true - numpy.dot(x_true, B))**2)

    #print equation_string(B, fobj)

    #startTime = time()
    #svr = SVR(kernel="rbf", C=1e3, gamma=0.1)
    #y = svr.fit(x_true, y_true).predict(x_true)
    #stopTime = time()
    #print 'SVM Time: '+str(stopTime-startTime)+' s'
    #print 'Y True = '+str(y_true)
    #print 'Y Calculated = '+str(y)

#else:
#    bStr = sys.argv[4:4 + len(fobj)]
#    dataStr = sys.argv[4 + len(fobj):]
#    if len(dataStr) != nvariables:
#        print 'Must pass data points for each variable'
#        sys.exit()
#
#    data = numpy.zeros(len(dataStr))
#    for i in range(len(data)):
#        data[i] = float(dataStr[i])
#
#    B = numpy.zeros(len(fobj))
#    for i in range(len(fobj)):
#        B[i] = float(bStr[i])
#
#    extended = numpy.zeros( (len(fobj)) )
#
#    for j in range(len(fobj)):
#        term = fobj[j]
#        extended[j] = evaluate_term(term, data)
#
#    accum = 0.0
#    for i in range(len(fobj)):
#        accum = accum + (B[i] * extended[i])
#    print accum


#overallFinish = time()
#print (overallFinish-overallStart)
