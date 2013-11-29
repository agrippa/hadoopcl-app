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
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector;

public class ReadIntVector extends MyReader<IntWritable, VectorWritable> {
  private static int maxVectorWidth = 0;
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadIntVector file");
            return;
        }
        new ReadIntVector(args[0]).runRead();
    }

    public ReadIntVector(String s) { super(s); }
    @Override
    protected String overrideKeyToString(IntWritable key) {
        return Integer.toString(key.get());
    }
    @Override
    protected String overrideValToString(VectorWritable val) {
        org.apache.mahout.math.Vector v = val.get();
        if (v.getNumNonZeroElements() > maxVectorWidth) {
          maxVectorWidth = v.getNumNonZeroElements();
        }
        return LimitedVectorToString(v, 100);
    }
    @Override
    protected IntWritable getKeyObject() { return new IntWritable(); }
    @Override
    protected VectorWritable getValObject() { return new VectorWritable(); }

    @Override
    protected void finish() {
      System.out.println("Max vector lengths="+maxVectorWidth);
    }
}
