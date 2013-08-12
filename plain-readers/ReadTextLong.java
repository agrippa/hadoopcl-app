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

public class ReadTextLong extends MyReader<Text, LongWritable> {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: java ReadTextLong file");
            return;
        }
        new ReadTextLong(args[0]).runRead();
    }

    public ReadTextLong(String s) { super(s); }
    @Override
    protected String overrideKeyToString(Text key) {
        return key.toString();
    }
    @Override
    protected String overrideValToString(LongWritable val) {
        return Long.toString(val.get());
    }
    @Override
    protected Text getKeyObject() { return new Text(); }
    @Override
    protected LongWritable getValObject() { return new LongWritable(); }
}
