import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class GenerateRandomSvec extends CompressedInputGenerator {
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
        int[] indices = new int[5];
        double[] vals = new double[indices.length];
        for (int i =0 ; i < indices.length; i++) {
            indices[i] = rand.nextInt(100);
            vals[i] = rand.nextDouble() * 100.0;
        }
        writer.append(new IntWritable(globalIndex), new SparseVectorWritable(indices, vals));
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
