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

public class ReadTextVector extends MyReader<Text, VectorWritable> {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadTextVector file");
            return;
        }
        new ReadTextVector(args[0]).runRead();
    }

    public ReadTextVector(String s) { super(s); }
    @Override
    protected String overrideKeyToString(Text key) {
        return key.toString();
    }
    @Override
    protected String overrideValToString(VectorWritable val) {
        return LimitedVectorToString(val.get(), 50);
    }
    @Override
    protected Text getKeyObject() { return new Text(); }
    @Override
    protected VectorWritable getValObject() { return new VectorWritable(); }
}
