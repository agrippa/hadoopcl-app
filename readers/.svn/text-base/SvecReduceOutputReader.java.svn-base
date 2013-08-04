import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;

public class SvecReduceOutputReader {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("Usage: java SvecReduceOutputReader nToRead file1 file2 ...");
            return;
        }
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        int N = Integer.parseInt(args[0]);

        int count = 0;
        IntWritable key = new IntWritable();
        SparseVectorWritable val = new SparseVectorWritable();

        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;
        double meanLength = 0.0;

        HashSet<Integer> uniqueIndices = new HashSet<Integer>();

        for(int i = 1; i < args.length; i++) {
            Path path = new Path(args[i]);
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

            while((N == -1 || count < N) && reader.next(key, val)) {
                if(val.size() < minLength) minLength = val.size();
                if(val.size() > maxLength) maxLength = val.size();
                meanLength += val.size();
                System.out.println(key.get()+" : "+val.toString(10));
                count++;

                for(int j = 0 ; j < val.size(); j++) {
                    uniqueIndices.add(val.indices()[j]);
                }
            }
            reader.close();
        }
        meanLength /= count;

        double std = 0.0;
        for(int i = 1; i < args.length; i++) {
            Path path = new Path(args[i]);
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

            while((N == -1 || count < N) && reader.next(key, val)) {
                double diff = val.size() - meanLength;
                diff = diff * diff;
                std = std + diff;
            }
        }
        std = Math.sqrt(std / count);

        Random rand = new Random();
        rand.nextGaussian();
        
        System.out.println("Maximum length = "+maxLength+", minimum length = "+minLength+", mean length = "+meanLength+", std = "+std);
        System.out.println("Read "+count+" output pairs");
        System.out.println(uniqueIndices.size()+" unique indices");
    }
}
