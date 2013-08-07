import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class HelloWorldCompressedInputGenerator extends CompressedInputGenerator {

    public void init() {
    }

    public Class getKeyClass() {
        return IntWritable.class;
    }
    public Class getValueClass() {
        return IntWritable.class;
    }

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        writer.append(new IntWritable(globalIndex), new IntWritable(globalIndex+2));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java HelloWorldCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new HelloWorldCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);
    }
}
