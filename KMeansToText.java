import java.util.Random;
import java.io.File;
import java.util.List;
import java.util.LinkedList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.mahout.math.VectorWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.RandomAccessSparseVector;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class KMeansToText {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("usage: java KMeansToText points-folder clusters-file output-file");
            return;
        }

        String pointsFolder = args[0];
        String clustersFile = args[1];
        String outputFile = args[2];

        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        final IntWritable clusterId = new IntWritable();
        VectorWritable cluster = new VectorWritable();
        Reader reader = new Reader(fs, new Path(clustersFile), conf);
        while (reader.next(clusterId, cluster)) {
            Vector v = cluster.get();
            out.write(v.get(0)+" "+v.get(1));
            out.newLine();
            cluster = new VectorWritable();
        }
        reader.close();

        out.newLine();

        File folder = new File(pointsFolder);
        File[] allFiles = folder.listFiles(); 
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile() && allFiles[i].getName().charAt(0) != '.') {
                reader = new Reader(fs, new Path(pointsFolder+"/"+allFiles[i].getName()), conf);
                while (reader.next(clusterId, cluster)) {
                    Vector v = cluster.get();
                    out.write(v.get(0)+" "+v.get(1));
                    out.newLine();
                    cluster = new VectorWritable();
                }
                reader.close();
            }
        }

        out.close();
    }
}
