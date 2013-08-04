import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import java.util.*;
import org.apache.hadoop.conf.*;

public class KMeansOutputReader {
    private static void readSequenceOutput(String outputFileName) throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(outputFileName), conf);

        DoubleWritable key = new DoubleWritable();
        DoubleWritable val = new DoubleWritable();

        while(reader.next(key, val)) {
            System.out.println(key.get()+" "+val.get());
        }
    }

    public static void main(String[] args) {
        try {
            readSequenceOutput(args[0]);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
