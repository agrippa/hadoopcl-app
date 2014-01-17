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
import java.util.*;

public abstract class MySortedReader<KeyType extends WritableComparable, ValueType extends WritableComparable> {
    final protected String inputName;
    protected boolean enablePrinting = true;
    private TreeSet<Pair<KeyType, ValueType>> sorted = new TreeSet<Pair<KeyType, ValueType>>();

    public MySortedReader(String setInputName) {
        this.inputName = setInputName;
    }

    abstract protected String overrideKeyToString(KeyType key);
    abstract protected String overrideValToString(ValueType val);
    abstract protected KeyType getKeyObject();
    abstract protected ValueType getValObject();

    protected void finish() { }

    private void runReadOfFile(Path path) throws IOException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        final SequenceFile.Reader reader;
        try {
            reader = new SequenceFile.Reader(fs, path, conf);
        } catch(Exception e) {
            // in case of non-seq files
            System.out.println("Skipping non-sequence file "+path.toString()+" due to:");
            e.printStackTrace();
            return;
        }
        if (enablePrinting) {
            System.out.println();
            System.out.println(path.toString());
            System.out.println();
        }

        try {
            KeyType key = getKeyObject();
            ValueType val = getValObject();

            while(reader.next(key, val)) {
                this.sorted.add(new Pair(key, val));

                // String keyString = overrideKeyToString(key);
                // String valString = overrideValToString(val);
                // if (enablePrinting) {
                //     System.out.println(count+": "+keyString+" => "+
                //             valString);
                // }
                key = getKeyObject();
                val = getValObject();
            }
            reader.close();
        } catch(Exception e) {
            System.out.println("Possible critical failure, continuing");
            e.printStackTrace();
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

        long count = 0;
        for (Pair<KeyType, ValueType> p : sorted) {
            String keyString = overrideKeyToString(p.key);
            String valString = overrideValToString(p.value);
            if (enablePrinting) {
                System.out.println(count+": "+keyString+" => "+valString);
            }
            count++;
        }
        finish();
    }

    protected String LimitedVectorToString(org.apache.mahout.math.Vector vec,
            int maxStringLength) {
        StringBuffer sb = new StringBuffer();
        sb.append("(nonzeros = ");
        sb.append(vec.getNumNonZeroElements());
        sb.append(", size=");
        sb.append(vec.size());
        sb.append(") { ");
        boolean broke = false;
        Iterator<org.apache.mahout.math.Vector.Element> iter = vec.nonZeroes().iterator();
        while(iter.hasNext()) {
            org.apache.mahout.math.Vector.Element ele = iter.next();
            sb.append(ele.index());
            sb.append(":");
            sb.append(ele.get());
            sb.append(" ");
            if(sb.length() > maxStringLength) {
                broke = true;
                break;
            }
        }
        if(broke) sb.append("... ");
        sb.append("}");
        return sb.toString();
    }

    protected String LimitedBSparseVectorToString(BSparseVectorWritable vec,
            int maxStringLength) {
        StringBuffer sb = new StringBuffer();
        sb.append("(length = ");
        sb.append(vec.size());
        sb.append(") { ");
        boolean broke = false;
        int[] indices = vec.indices();
        double[] vals = vec.vals();
        for(int i = 0; i < vec.size(); i++) {
            sb.append(indices[i]);
            sb.append(":");
            sb.append(vals[i]);
            sb.append(" ");
            if(sb.length() > maxStringLength) {
                broke = true;
                break;
            }
        }
        if(broke) sb.append("... ");
        sb.append("}");
        return sb.toString();
    }

    protected String LimitedSparseVectorToString(SparseVectorWritable vec,
            int maxStringLength) {
        StringBuffer sb = new StringBuffer();
        sb.append("(length = ");
        sb.append(vec.size());
        sb.append(") { ");
        boolean broke = false;
        int[] indices = vec.indices();
        double[] vals = vec.vals();
        for(int i = 0; i < vec.size(); i++) {
            sb.append(indices[i]);
            sb.append(":");
            sb.append(vals[i]);
            sb.append(" ");
            if(sb.length() > maxStringLength) {
                broke = true;
                break;
            }
        }
        if(broke) sb.append("... ");
        sb.append("}");
        return sb.toString();
    }

    protected String LimitedFSparseVectorToString(FSparseVectorWritable vec,
            int maxStringLength) {
        StringBuffer sb = new StringBuffer();
        sb.append("(length = ");
        sb.append(vec.size());
        sb.append(") { ");
        boolean broke = false;
        int[] indices = vec.indices();
        float[] vals = vec.vals();
        for(int i = 0; i < vec.size(); i++) {
            sb.append(indices[i]);
            sb.append(":");
            sb.append(vals[i]);
            sb.append(" ");
            if(sb.length() > maxStringLength) {
                broke = true;
                break;
            }
        }
        if(broke) sb.append("... ");
        sb.append("}");
        return sb.toString();
    }

    public static class Pair<K extends WritableComparable, V extends WritableComparable> implements Comparable<Pair<K, V>> {
        public final K key;
        public final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<K, V> other) {
            return this.key.compareTo(other.key);
        }
    }
}
