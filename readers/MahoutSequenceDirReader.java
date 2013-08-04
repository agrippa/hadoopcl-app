import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;
import org.apache.hadoop.io.*;

public class MahoutSequenceDirReader {
    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("usage: java MahoutSequenceDirReader filename");
            return;
        }
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
       
        Text key = new Text();
        Text val = new Text();

        Path path = new Path(args[0]);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        while(reader.next(key, val)) {
            System.out.println(key.toString()+" : "+val.toString());
        }
    }
}
