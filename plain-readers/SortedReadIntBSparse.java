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

public class SortedReadIntBSparse extends MySortedReader<IntWritable, BSparseVectorWritable> {
    private HashMap<Integer, MutableInteger> counts = new HashMap<Integer, MutableInteger>();

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java SortedReadIntBSparse file");
            return;
        }
        SortedReadIntBSparse runner = new SortedReadIntBSparse(args[0]);
        // runner.enablePrinting = false;
        runner.runRead();
        // runner.done();
    }

    public SortedReadIntBSparse(String s) { super(s); }
    @Override
    protected String overrideKeyToString(IntWritable key) {
        // return "";
        return Integer.toString(key.get());
    }
    @Override
    protected String overrideValToString(BSparseVectorWritable val) {
        // int[] indices = val.indices();
        // for (int i = 0; i < val.size(); i++) {
        //     if (!counts.containsKey(indices[i])) {
        //         counts.put(indices[i], new MutableInteger());
        //     }
        //     counts.get(indices[i]).incr();
        // }
        // return "";
        return LimitedBSparseVectorToString(val, 100);
    }
    @Override
    protected IntWritable getKeyObject() { return new IntWritable(); }
    @Override
    protected BSparseVectorWritable getValObject() { return new BSparseVectorWritable(); }

    private void done() {
        for (Map.Entry<Integer, MutableInteger> entry : counts.entrySet()) {
            System.out.println(entry.getKey()+" -> "+entry.getValue().get());
        }
    }

    class MutableInteger {
        private int val = 0;
        public void incr() { this.val = this.val + 1; }
        public int get() { return this.val; }
    }
}
