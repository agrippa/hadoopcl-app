import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class BlacksholesCompressedInputGenerator extends CompressedInputGenerator {

    java.util.Random rand = null;

    public void init() {
        rand = new java.util.Random(123456);
    }

    public Class getKeyClass() {
        return IntWritable.class;
    }
    public Class getValueClass() {
        return FloatWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        float val = globalIndex * 1.0f / globalTotal;
        writer.append(new IntWritable(globalIndex), new FloatWritable(val));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java BlacksholesCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new BlacksholesCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);
    }
}
