javac -g -classpath ${CLASSPATH} -d cpu-pairwise-classes/ pairwiseSimilarityCpu.java
jar cvf pairwiseSimilarityCpu.jar -C cpu-pairwise-classes/ . SetupInputCompression.class

FOLDERS="/home/yiskylee/mahout/trunk/math/target/classes /home/yiskylee/mahout/trunk/core/target/classes"
for folder in ${FOLDERS}; do
  files=`cd ${folder} && find . -name "*.class"`
  for f in ${files}; do
    jar uf pairwiseSimilarityCpu.jar -C ${folder} ${f}
  done
done
