import java.io.IOException;
import java.util.*;
import java.lang.*;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.OpenCLMapper;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.lang.StringBuilder;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;

public class SetupInputCompression {
    public static HashMap<String, String> codecs = new HashMap<String, String>();

    static {
        codecs.put("lzo", "com.hadoop.compression.lzo.LzoCodec");
        codecs.put("snappy", "org.apache.hadoop.io.compress.SnappyCodec");
        codecs.put("default", "org.apache.hadoop.io.compress.DefaultCodec");
        codecs.put("gzip", "org.apache.hadoop.io.compress.GzipCodec");
        codecs.put("bzip", "org.apache.hadoop.io.compress.BZip2Codec");
    }

    public static void setupCompression(Configuration conf, String[] args) {
        if(!args[2].equals("none")) {
            conf.set("mapred.compress.map.output","true");
            conf.set("mapred.map.output.compression.codec",codecs.get(args[2]));
        }
    }
}
