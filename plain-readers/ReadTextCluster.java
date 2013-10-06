import java.util.Iterator;
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
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.clustering.Cluster;

public class ReadTextCluster extends MyReader<Text, ClusterWritable> {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadTextCluster file");
            return;
        }
        new ReadTextCluster(args[0]).runRead();
    }

    public ReadTextCluster(String s) { super(s); }
    @Override
    protected String overrideKeyToString(Text key) {
        return key.toString();
    }

    private SparseVectorWritable clusterToSparseVector(
            org.apache.mahout.clustering.iterator.ClusterWritable cluster) {
        Iterator<Vector.Element> iter = cluster.getValue().getCenter().nonZeroes().iterator();
        int count = 0;
        while(iter.hasNext()) {
            iter.next();
            count++;
        }
        iter = cluster.getValue().getCenter().nonZeroes().iterator();
        int[] indices = new int[count];
        double[] vals = new double[count];
        count = 0;
        while(iter.hasNext()) {
            Vector.Element ele = iter.next();
            indices[count] = ele.index();
            vals[count] = ele.get();
            count++;
        }
        return new SparseVectorWritable(indices, vals);
    }

    @Override
    protected String overrideValToString(ClusterWritable val) {
        Cluster c = val.getValue();
        org.apache.mahout.math.Vector center = c.getCenter();
        return LimitedVectorToString(center, 100);
    }
    @Override
    protected Text getKeyObject() { return new Text(); }
    @Override
    protected ClusterWritable getValObject() { return new ClusterWritable(); }
}
