import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public class PiCompressedInputGenerator extends CompressedInputGenerator {

    private static class HaltonSequence {
        /** Bases */
        static final int[] P = {2, 3}; 
        /** Maximum number of digits allowed */
        static final int[] K = {63, 40}; 

        private long index;
        private double[] x;
        private double[][] q;
        private int[][] d;

        /** Initialize to H(startindex),
         * so the sequence begins with H(startindex+1).
         */
        HaltonSequence(long startindex) {
            index = startindex;
            x = new double[K.length];
            q = new double[K.length][];
            d = new int[K.length][];
            for(int i = 0; i < K.length; i++) {
                q[i] = new double[K[i]];
                d[i] = new int[K[i]];
            }

            for(int i = 0; i < K.length; i++) {
                long k = index;
                x[i] = 0;

                for(int j = 0; j < K[i]; j++) {
                    q[i][j] = (j == 0? 1.0: q[i][j-1])/P[i];
                    d[i][j] = (int)(k % P[i]);
                    k = (k - d[i][j])/P[i];
                    x[i] += d[i][j] * q[i][j];
                }
            }
        }

        /** Compute next point.
         * Assume the current point is H(index).
         * Compute H(index+1).
         * 
         * @return a 2-dimensional point with coordinates in [0,1)^2
         */
        double[] nextPoint() {
            index++;
            for(int i = 0; i < K.length; i++) {
                for(int j = 0; j < K[i]; j++) {
                    d[i][j]++;
                    x[i] += q[i][j];
                    if (d[i][j] < P[i]) {
                        break;
                    }
                    d[i][j] = 0;
                    x[i] -= (j == 0? 1.0: q[i][j-1]);
                }
            }
            return x;
        }
    }
    private HaltonSequence haltonsequence = null;

    public void init() {
        haltonsequence = new HaltonSequence(0);
        rand = new Random(13456);
    }

    public Class getKeyClass() {
        return LongWritable.class;
    }
    public Class getValueClass() {
        return LongWritable.class;
    }

    private Random rand = null;
    private long uint_max = 4294967295L;

    public void writeNextKeyValue(SequenceFile.Writer writer, int globalIndex, int globalTotal) throws IOException {
        //final double[] point = haltonsequence.nextPoint();
        //writer.append(new DoubleWritable(point[0]), new DoubleWritable(point[1]));
        long m_z = rand.nextLong();
        long m_w = rand.nextLong();
        m_z = m_z % uint_max;
        m_w = m_w % uint_max;
        writer.append(new LongWritable(m_z), new LongWritable(m_w));
    }

    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java PiCompressedInputGenerator outputFolderContainer nFiles elementsPerFile");
            return;
        }

        String outputFolderContainer = args[0];
        int nFiles = Integer.parseInt(args[1]);
        int perFile = Integer.parseInt(args[2]);

        new PiCompressedInputGenerator().writeAllTypes(outputFolderContainer, nFiles, perFile);
    }
}
