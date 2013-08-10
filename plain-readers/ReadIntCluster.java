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
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.clustering.Cluster;

public class ReadIntCluster extends MyReader<IntWritable, ClusterWritable> {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadIntCluster file");
            return;
        }
        new ReadIntCluster(args[0]).runRead();
    }

    public ReadIntCluster(String s) { super(s); }
    @Override
    protected String overrideKeyToString(IntWritable key) {
        return Integer.toString(key.get());
    }
    @Override
    protected String overrideValToString(ClusterWritable val) {
        Cluster c = val.getValue();
        org.apache.mahout.math.Vector center = c.getCenter();
        return LimitedVectorToString(center, 100);
    }
    @Override
    protected IntWritable getKeyObject() { return new IntWritable(); }
    @Override
    protected ClusterWritable getValObject() { return new ClusterWritable(); }
}
