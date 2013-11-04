import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class MapInputIvecInputGenerator extends CompressedInputGenerator {
    private java.util.Random rand = null;

    public void init() {
        rand = new Random(13456);
    }

    public Class getKeyClass() {
        return IntWritable.class;
    }

    public Class getValueClass() {
        return IntegerVectorWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        int[] vals = { globalIndex + 3, globalIndex + 4, globalIndex + 5 };
        writer.append(new IntWritable(globalIndex), 
                new IntegerVectorWritable(vals));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java MapInputIvecInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new MapInputIvecInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);

    }
}
