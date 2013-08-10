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

public class ReadTextInt extends MyReader<Text, IntWritable> {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadTextInt file");
            return;
        }
        new ReadTextInt(args[0]).runRead();
    }

    public ReadTextInt(String s) { super(s); }
    @Override
    protected String overrideKeyToString(Text key) {
        return key.toString();
    }
    @Override
    protected String overrideValToString(IntWritable val) {
        return Integer.toString(val.get());
    }
    @Override
    protected Text getKeyObject() { return new Text(); }
    @Override
    protected IntWritable getValObject() { return new IntWritable(); }
}
