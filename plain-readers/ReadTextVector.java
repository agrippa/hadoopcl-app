import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import java.nio.charset.Charset;
import java.net.URI;
import org.apache.mahout.utils.io.ChunkedWriter;
import org.apache.hadoop.fs.Path;
import javax.xml.parsers.*;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.apache.hadoop.io.*;
import java.util.concurrent.*;
import org.apache.mahout.common.StringTuple;
import org.apache.mahout.math.*;

public class ReadTextVector {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.out.println("usage: java ReadTextVector file");
            return;
        }

        final String filename = args[0];
        final Path path = new Path(filename);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

        final Text key = new Text();
        final VectorWritable val = new VectorWritable();

        long count = 0;
        while(reader.next(key, val)) {
            StringBuffer sb = new StringBuffer();
            sb.append("{ ");
            Iterator<org.apache.mahout.math.Vector.Element> iter = val.get().nonZeroes().iterator();
            while(iter.hasNext()) {
                org.apache.mahout.math.Vector.Element ele = iter.next();
                sb.append(ele.index());
                sb.append(":");
                sb.append(ele.get());
                sb.append(" ");
            }
            sb.append("}");
            System.out.println(count+": "+key.toString()+" => "+sb.toString());
        }

    }
}
