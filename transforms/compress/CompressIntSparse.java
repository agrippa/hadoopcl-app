package compress;

import common.*;
import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class CompressIntSparse extends MyCompressor {
    public Class<? extends Writable> getKeyClass() {
        return org.apache.hadoop.io.IntWritable.class;
    }
    public Class<? extends Writable> getValueClass() {
        return org.apache.hadoop.io.SparseVectorWritable.class;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java CompressIntSparse input output");
            return;
        }
        new CompressIntSparse().run(new DefaultCodec(), args[0], args[1]);
    }
}
