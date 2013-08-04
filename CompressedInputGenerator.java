import org.apache.hadoop.fs.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import java.util.*;
import org.apache.hadoop.conf.*;
import com.hadoop.compression.lzo.*;

public abstract class CompressedInputGenerator {

    public abstract Class getKeyClass();
    public abstract Class getValueClass();
    public abstract void writeNextKeyValue(SequenceFile.Writer writer, int globalindex, int globalTotal) throws IOException;
    public abstract void init();

    public static String compressionTypeToString(SequenceFile.CompressionType compressionType) {
        String compressionTypeString = null;
        switch(compressionType) {
            case NONE:
                compressionTypeString = "block";
                break;
            case BLOCK:
                compressionTypeString = "block";
                break;
            case RECORD:
                compressionTypeString = "record";
                break;
        }
        return compressionTypeString;
    }

    public static String codecToString(CompressionCodec codec) {
        if(codec == null) {
            return "none";
        } else if(codec.getDefaultExtension().equals(".deflate")) {
            return "default";
        } else if(codec.getDefaultExtension().equals(".gz")) {
            return "gzip";
        } else if(codec.getDefaultExtension().equals(".bz2")) {
            return "bzip";
        } else if(codec.getDefaultExtension().equals(".snappy")) {
            return "snappy";
        } else if(codec.getDefaultExtension().equals(".lzo_deflate")) {
            return "lzo";
        } else if(codec.getDefaultExtension().equals(".lzo")) {
            return "lzop";
        } else {
            throw new RuntimeException("Unsupported coded "+codec.getDefaultExtension());
        }
    }

    public void writeAllTypes(String containingOutputFolder, int nFiles, int perFile) {

        SequenceFile.CompressionType[] compressionTypes = { 
            //SequenceFile.CompressionType.RECORD,
            SequenceFile.CompressionType.BLOCK 
        };
        CompressionCodec[] codecs = {
            /*new BZip2Codec(),*/ new DefaultCodec()/*, new GzipCodec(), new SnappyCodec(), new com.hadoop.compression.lzo.LzoCodec()*/ };

        init();
        writeType(SequenceFile.CompressionType.NONE, null, containingOutputFolder, nFiles, perFile);

        for(SequenceFile.CompressionType t : compressionTypes) {
            for(CompressionCodec c : codecs) {
                init();
                writeType(t, c, containingOutputFolder, nFiles, perFile);
            }
        }
    }
    
    private void writeType(SequenceFile.CompressionType compressionType, 
            CompressionCodec codec, String containingOutputFolder, int nFiles, int perFile) {
        Configuration conf = new Configuration();
        String compressionTypeString = compressionTypeToString(compressionType);
        String codecString = codecToString(codec);
        System.out.println("###Generating "+codecString+" "+compressionTypeString+"###");
        String outputFolder = containingOutputFolder+"/"+compressionTypeString+"."+
            codecString+"/";

        long start = System.currentTimeMillis();
        for(int f = 0; f < nFiles; f++) {
            Path path = new Path(outputFolder+"file."+Integer.toString(f));
            try {
                FileSystem fs = FileSystem.get(conf);
                SequenceFile.Writer writer = null;

                if(codec == null) {
                    writer = SequenceFile.createWriter(fs, conf, path, 
                            getKeyClass(), getValueClass());
                } else {
                    writer = SequenceFile.createWriter(fs, conf, path, 
                            getKeyClass(), getValueClass(), 
                            compressionType, codec);
                }

                for(int i = 0; i < perFile; i++) {
                    writeNextKeyValue(writer, f * perFile + i, perFile * nFiles);
                    //jlong val1 = rand.nextLong();
                    //jlong val2 = rand.nextLong();
                    //jif(val1 < 0) val1 = -1 * val1;
                    //jif(val2 < 0) val2 = -1 * val2;
                    //jwriter.append(new LongWritable(val1), new LongWritable(val2));
                }
                writer.close();
            } catch(Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println(codecString+" took "+(stop-start)+" ms to compress");
/*
        long totalFileSize = 0;
        for(int f = 0; f < nFiles; f++) {
            File file = new File(outputFolder+"file."+Integer.toString(f));
            long fileSize = file.length();
            totalFileSize += fileSize;
        }
        System.out.println(codecString+" compressed to size "+totalFileSize);

        start = System.currentTimeMillis();
        for(int f = 0; f < nFiles; f++) {
            Path path = new Path(outputFolder+"file."+Integer.toString(f));
            try {
                FileSystem fs = FileSystem.get(conf);
                SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
                LongWritable key = new LongWritable();
                LongWritable value = new LongWritable();
                while(reader.next(key, value)) {
                    ;
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println(codecString+" took "+(stop-start)+" ms to decompress");
        */
    }
}
