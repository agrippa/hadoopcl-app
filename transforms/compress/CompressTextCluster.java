package compress;

import common.*;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class CompressTextCluster extends MyCompressor {
    public Class<? extends Writable> getKeyClass() {
        return org.apache.hadoop.io.Text.class;
    }
    public Class<? extends Writable> getValueClass() {
        return org.apache.mahout.math.VectorWritable.class;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java CompressTextCluster input output");
            return;
        }
        new CompressTextCluster().run(new DefaultCodec(), args[0], args[1]);
    }
}
