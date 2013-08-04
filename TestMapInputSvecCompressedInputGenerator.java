import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class TestMapInputSvecCompressedInputGenerator extends CompressedInputGenerator {
    private java.util.Random rand = null;

    public void init() {
        rand = new Random(13456);
    }

    public Class getKeyClass() {
        return IntWritable.class;
    }

    public Class getValueClass() {
        return SparseVectorWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        int[] newIndices = {0, 1, 2};
        double[] newVals = {globalIndex+3.0, globalIndex+4.0, globalIndex+5.0};
        writer.append(new IntWritable(globalIndex), 
                new SparseVectorWritable(newIndices, newVals));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java TestMapInputSvecCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new TestMapInputSvecCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);

    }
}
