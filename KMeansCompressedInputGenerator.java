import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class KMeansCompressedInputGenerator extends CompressedInputGenerator {

    java.util.Random rand = null;

    public void init() {
        rand = new java.util.Random(123456);
    }

    public Class getKeyClass() {
        return DoubleWritable.class;
    }
    public Class getValueClass() {
        return DoubleWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        writer.append(new DoubleWritable(rand.nextDouble()*100.0), new DoubleWritable(rand.nextDouble()*100.0));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java KMeansCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new KMeansCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);
    }
}
