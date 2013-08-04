import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;
import org.apache.mahout.common.StringTuple;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.clustering.iterator.ClusterWritable;

public class MahoutReader {
    private static void readDictFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path dictPath = new Path(folder+"/dictionary.file-0");
        SequenceFile.Reader dictReader = new SequenceFile.Reader(fs, dictPath, conf);

        Text key = new Text();
        IntWritable val = new IntWritable();

        while(dictReader.next(key, val)) {
            System.out.println(key.toString()+" : "+val.get());
        }
    }

    private static void readFreqFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path freqPath = new Path(folder+"/frequency.file-0");
        SequenceFile.Reader freqReader = new SequenceFile.Reader(fs, freqPath, conf);

        IntWritable key = new IntWritable();
        LongWritable val = new LongWritable();

        while(freqReader.next(key, val)) {
            System.out.println(key.get()+" : "+val.get());
        }
    }

    private static void readWordCountFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path countPath = new Path(folder+"/wordcount/part-r-00000");
        SequenceFile.Reader countReader = new SequenceFile.Reader(fs, countPath, conf);

        Text key = new Text();
        LongWritable val = new LongWritable();

        while(countReader.next(key, val)) {
            System.out.println(key.toString()+" : "+val.get());
        }
    }

    private static void readTokenizedFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path tokenPath = new Path(folder+"/tokenized-documents/part-m-00000");
        SequenceFile.Reader tokenReader = new SequenceFile.Reader(fs, tokenPath, conf);

        Text key = new Text();
        StringTuple val = new StringTuple();

        while(tokenReader.next(key, val)) {
            StringBuffer tupleString = new StringBuffer();
            tupleString.append("{ ");
            for(String s : val.getEntries()) {
                tupleString.append(s);
                tupleString.append(" ");
            }
            tupleString.append("}");
            System.out.println(key.toString()+" : "+tupleString.toString());
        }
    }

    private static void readTFIDFFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path tfidfPath = new Path(folder+"/tfidf-vectors/part-r-00000");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, tfidfPath, conf);

        Text key = new Text();
        org.apache.mahout.math.VectorWritable val = new org.apache.mahout.math.VectorWritable();

        while(reader.next(key, val)) {
            System.out.println(key.toString()+" : "+val.toString());
        }
    }

    private static void readTFFile(String folder, Configuration conf, FileSystem fs) throws IOException {
        Path tfPath = new Path(folder+"/tf-vectors/part-r-00000");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, tfPath, conf);

        Text key = new Text();
        org.apache.mahout.math.VectorWritable val = new org.apache.mahout.math.VectorWritable();

        while(reader.next(key, val)) {
            System.out.println(key.toString()+" : "+val.toString());
        }
    }

    private static String clusterToString(ClusterWritable val) {
        Cluster c = val.getValue();
        Vector center = c.getCenter();

        int count = 0;

        Iterator<Vector.Element> iter = center.nonZeroes().iterator();
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        while(iter.hasNext()) {
            Vector.Element ele = iter.next();
            sb.append(ele.index());
            sb.append(":");
            sb.append(ele.get());
            sb.append(" ");
            count++;
        }
        sb.append("}");
        return sb.toString();
    }

    private static int clusterLength(ClusterWritable val) {
        int count = 0;
        Iterator<Vector.Element> iter = val.getValue().getCenter().nonZeroes().iterator();
        while(iter.hasNext()) {
            iter.next();
            count++;
        }
        return count;
    }

    private static void readSeed(String file, Configuration conf, FileSystem fs) throws IOException {
        Path path = new Path(file);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        Text key = new Text();
        ClusterWritable val = 
            new ClusterWritable();

        while(reader.next(key, val)) {
            System.out.println(key.toString()+" : length="+clusterLength(val)+" : "+clusterToString(val));
        }
    }


    private static Profile getElementsInSeed(String folder, Configuration conf,
            FileSystem fs) throws IOException {
        Path seedPath = new Path(folder+"/mahout-work-jmg3/reuters-kmeans-clusters/part-randomSeed");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, seedPath, conf);
        Text key = new Text();
        ClusterWritable val = 
            new ClusterWritable();

        TreeSet<ComparableInteger> uniqueElements = new TreeSet<ComparableInteger>();
        int nClusters = 0;
        while(reader.next(key, val)) {
            Iterator<Vector.Element> iter = val.getValue().getCenter().nonZeroes().iterator();
            while(iter.hasNext()) {
                Vector.Element tmp = iter.next();
                uniqueElements.add(new ComparableInteger(tmp.index()));
            }
            nClusters++;
        }
        return new Profile(uniqueElements, nClusters);
    }

    private static Profile getElementsInOutput(String folder, Configuration conf,
            FileSystem fs) throws IOException {
        TreeSet<ComparableInteger> uniqueElements = new TreeSet<ComparableInteger>();
        int nClusters = 0;

        File folderObj = new File(folder+"/mahout-kmeans-output/clusters-2-final/");
        File[] containedFiles = folderObj.listFiles();
        for(File f : containedFiles) {
            if(f.getName().indexOf("part-") == 0) {
                Path clusterPath = new Path(f.getAbsolutePath());
                SequenceFile.Reader reader = new SequenceFile.Reader(fs, clusterPath, conf);

                IntWritable key = new IntWritable();
                ClusterWritable val = new
                    ClusterWritable();

                while(reader.next(key, val)) {
                    Iterator<Vector.Element> iter = 
                        val.getValue().getCenter().nonZeroes().iterator();
                    while(iter.hasNext()) {
                        Vector.Element tmp = iter.next();
                        uniqueElements.add(new ComparableInteger(tmp.index()));
                    }
                    nClusters++;
                }
            }
        }
        return new Profile(uniqueElements, nClusters);
    }

    //private static Profile getElementsInInput(String folder, Configuration conf, 
    //        FileSystem fs) throws IOException {
    //    Path inputPath = new Path(folder+"/mahout-work-jmg3/reuters-out-seqdir-sparse-kmeans/tfidf-vectors/part-r-00000");
    //    SequenceFile.Reader reader = new SequenceFile.Reader(fs, inputPath, conf);

    //    Text key = new Text();
    //    VectorWritable val = new VectorWritable();
    //    while(reader.next(key, val)) {
    //        Iterator<Vector.Element> iter = val.get().nonZeroes().iterator();
    //    }
    //}

    private static void readClusters(String folder, Configuration conf, 
            FileSystem fs) throws IOException {
        File folderObj = new File(folder);
        File[] containedFiles = folderObj.listFiles();
        double meanLength = 0;
        int totalNumClusters = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;
        for(File f : containedFiles) {
            if(f.getName().indexOf("part-") == 0) {
                Path clusterPath = new Path(f.getAbsolutePath());
                SequenceFile.Reader reader = new SequenceFile.Reader(fs, clusterPath, conf);
                IntWritable key = new IntWritable();
                ClusterWritable val = new
                    ClusterWritable();

                while(reader.next(key, val)) {
                    System.out.println(key.get()+" : "+clusterToString(val));

                    int count = clusterLength(val);
                    if(count > maxLength) maxLength = count;
                    if(count < minLength) minLength = count;
                    meanLength += count;
                    totalNumClusters++;
                }
                reader.close();
            }
        }
        meanLength = meanLength / totalNumClusters;

        System.out.println("Maximum length = "+maxLength+", minimum length = "+minLength+", mean length = "+meanLength);
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.println("usage: java MahoutReader folder print-option-0 ...");
            return;
        }
        String folder = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        for(int i = 1; i < args.length; i++) {
            String select = args[i];
            if(select.equals("dict")) {
                System.out.println("\nDumping dictionary");
                readDictFile(folder, conf, fs);
            } else if(select.equals("freq")) {
                System.out.println("\nDumping frequency");
                readFreqFile(folder, conf, fs);
            } else if(select.equals("count")) {
                System.out.println("\nDumping word counts");
                readWordCountFile(folder, conf, fs);
            } else if(select.equals("token")) {
                System.out.println("\nDumping tokens");
                readTokenizedFile(folder, conf, fs);
            } else if(select.equals("tfidf")) {
                System.out.println("\nDumping TFIDF");
                readTFIDFFile(folder, conf, fs);
            } else if(select.equals("tf")) {
                System.out.println("\nDumping TF");
                readTFFile(folder, conf, fs);
            } else if(select.equals("cluster")) {
                System.out.println("\nDumping clusters");
                readClusters(folder, conf, fs);
            } else if(select.equals("seed")) {
                System.out.println("\nDumping initial seed");
                readSeed(folder, conf, fs);
            } else if(select.equals("compare")) {
                System.out.println("\nComparing Mahout input clusters to output");
                Profile seed = getElementsInSeed(folder, conf, fs);
                Profile output = getElementsInOutput(folder, conf, fs);
                System.out.println("Input has "+seed.nClusters()+" clusters, output has "+output.nClusters());
                System.out.println();
                for(ComparableInteger ci : seed.uniqueIndices()) {
                    if(!output.uniqueIndices().contains(ci)) {
                        System.out.println("Seed has "+ci+" but output does not");
                    }
                }
                System.out.println();
                for(ComparableInteger ci : output.uniqueIndices()) {
                    if(!seed.uniqueIndices().contains(ci)) {
                        System.out.println("Output has "+ci+" but seed does not");
                    }
                }

            }
        }
    }

    public static class ComparableInteger implements Comparable<ComparableInteger> {
        private final int val;
        public ComparableInteger(int val) {
            this.val = val;
        }
        public int get() {
            return val;
        }

        @Override
        public int compareTo(ComparableInteger other) {
            if(this.val < other.val) {
                return -1;
            } else if (this.val == other.val) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int hashCode() {
            return val;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ComparableInteger) {
                ComparableInteger other = (ComparableInteger)obj;
                if(this.compareTo(other) == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return Integer.toString(this.val);
        }
    }

    public static class Profile {
        private final TreeSet<ComparableInteger> uniqueIndices;
        private final int nClusters;

        public Profile(TreeSet<ComparableInteger> indices,
                int nClusters) {
            this.uniqueIndices = indices;
            this.nClusters = nClusters;
        }

        public TreeSet<ComparableInteger> uniqueIndices() {
            return this.uniqueIndices;
        }

        public int nClusters() {
            return this.nClusters;
        }
    }
}

