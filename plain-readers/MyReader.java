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
import org.apache.mahout.math.*;

public abstract class MyReader<KeyType extends Writable, ValueType extends Writable> {
    final protected String inputName;

    public MyReader(String setInputName) {
        this.inputName = setInputName;
    }

    abstract protected String overrideKeyToString(KeyType key);
    abstract protected String overrideValToString(ValueType val);
    abstract protected KeyType getKeyObject();
    abstract protected ValueType getValObject();

    private void runReadOfFile(Path path) throws IOException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        final SequenceFile.Reader reader;
        try {
            reader = new SequenceFile.Reader(fs, path, conf);
        } catch(Exception e) {
            // in case of non-seq files
            return;
        }
        System.out.println();
        System.out.println(path.toString());
        System.out.println();

        final KeyType key = getKeyObject();
        final ValueType val = getValObject();

        try {
            long count = 0;
            while(reader.next(key, val)) {
                System.out.println(count+": "+overrideKeyToString(key)+" => "+
                        overrideValToString(val));
                count++;
            }
            reader.close();
        } catch(Exception e) {
            System.out.println("Possible critical failure, continuing");
            return;
        }
    }

    protected void runRead() throws IOException {
        File file = new File(this.inputName);
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f : files) {
                runReadOfFile(new Path(f.getAbsolutePath()));
            }
        } else {
            runReadOfFile(new Path(file.getAbsolutePath()));
        }
    }

    protected String LimitedVectorToString(org.apache.mahout.math.Vector vec,
            int maxStringLength) {
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        boolean broke = false;
        Iterator<org.apache.mahout.math.Vector.Element> iter = vec.nonZeroes().iterator();
        while(iter.hasNext()) {
            org.apache.mahout.math.Vector.Element ele = iter.next();
            sb.append(ele.index());
            sb.append(":");
            sb.append(ele.get());
            sb.append(" ");
            if(sb.length() > 50) {
                broke = true;
                break;
            }
        }
        if(broke) sb.append("... ");
        sb.append("}");
        return sb.toString();

    }
}
