import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;
import org.apache.mahout.common.StringTuple;
import org.apache.commons.io.FileUtils;
v
public class TransformMahoutInput {
    private static List<File> getInputFiles(String folderName) {
        List<File> inputFiles = new ArrayList<File>();
        File folder = new File(folderName);

        File[] containedFiles = folder.listFiles();
        for(File f : containedFiles) {
            if(f.getName().indexOf("part-r-") == 0) {
                inputFiles.add(f);
            }
        }
        return inputFiles;
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("usage: java TransformMahoutInput 
            System.out.println("usage: java TransformMahoutInput reuters-folder");
            return;
        }

        String rFolder = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        File hadoopclDir = new File(rFolder+"/hadoopcl-input");
        hadoopclDir.mkdirs();

        FileUtils.cleanDirectory(hadoopclDir);

        Path fileMappingPath = new Path(hadoopclDir.getAbsolutePath()+"/filemapping");
        SequenceFile.Writer fileMappingWriter = SequenceFile.createWriter(fs, conf, 
                fileMappingPath, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.Text.class);

        int fileId = 0;
        List<File> inputFiles = getInputFiles(rFolder+"/tfidf-vectors");
        System.out.println("Converting:");
        for(File input : inputFiles) {
            Path inputPath = new Path(input.getAbsolutePath());
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, inputPath, conf);

            Text inputKey = new Text();
            org.apache.mahout.math.VectorWritable inputVal = 
                new org.apache.mahout.math.VectorWritable();

            Path outputPath = new Path(hadoopclDir.getAbsolutePath()+"/"+inputPath.getName());
            SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, outputPath,
                    org.apache.hadoop.io.IntWritable.class, org.apache.hadoop.io.SparseVectorWritable.class);

            int maxLength = -1;
            int count = 0;
            while(reader.next(inputKey, inputVal)) {
                org.apache.mahout.math.Vector vec = inputVal.get();
                int length = vec.getNumNonZeroElements();
                int[] indices = new int[length];
                double[] vals = new double[length];

                int countEles = 0;
                Iterator<org.apache.mahout.math.Vector.Element> iter = vec.nonZeroes().iterator();
                while(iter.hasNext()) {
                    org.apache.mahout.math.Vector.Element ele = iter.next();
                    indices[countEles] = ele.index();
                    vals[countEles] = ele.get();
                    countEles++;
                }

                int thisFileId = fileId++;

                writer.append(new IntWritable(thisFileId), 
                        new SparseVectorWritable(indices, vals));

                fileMappingWriter.append(new IntWritable(thisFileId), inputKey);
                count++;
            }
            writer.close();
            reader.close();
            System.out.println("    "+input.getAbsolutePath()+" : "+count+" files");
        }
        fileMappingWriter.close();
    }
}
