import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class SortCompressedInputGenerator extends CompressedInputGenerator {

    java.util.Random rand = null;

    public void init() {
        rand = new java.util.Random(123456);
    }

    public Class getKeyClass() {
        return LongWritable.class;
    }
    public Class getValueClass() {
        return LongWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        long val1 = rand.nextLong();
        long val2 = rand.nextLong();
        if(val1 < 0) val1 = -1 * val1;
        if(val2 < 0) val2 = -1 * val2;
        writer.append(new LongWritable(val1), new LongWritable(val2));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java SortCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new SortCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);
    }
}
