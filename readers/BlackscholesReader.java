import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;

public class BlackscholesReader {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("usage: java BlackscholesReader nToRead file1 file2");
            return;
        }
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        int N = Integer.parseInt(args[0]);

        int count = 0;
        IntWritable key = new IntWritable();
        FloatWritable val = new FloatWritable();

        for(int i = 1; i < args.length; i++) {
            Path path = new Path(args[i]);
            SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

            while((N == -1 || count < N) && reader.next(key, val)) {
                System.out.println(key.get()+" : "+val.get());
                count++;
            }
            reader.close();
        }
        System.out.println("Read "+count+" output pairs");
    }
}
