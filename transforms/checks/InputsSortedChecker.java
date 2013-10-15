package checks;

public class InputsSortedChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java InputsSortedChecker folder");
            return;
        }

        String input = args[0];
        final Configuration conf = new Configuration();
        final FileSystem fs;
        try {
            fs = FileSystem.get(conf);
        } catch(IOException io) {
            throw new RuntimeException(io);
        }

        int nThreads = 12;
        CheckerRunner[] runners = new CheckerRunner[nThreads];
        for (int t = 0; t < runners.length; t++) {
            runners[t] = new CheckerRunner();
        }
        ParallelFileIterator executor = new ParallelFileIterator(new File(intput),
                conf, fs, org.apache.hadoop.io.IntWritable.class,
                org.apache.hadoop.io.SparseVectorWritable.class);
        executor.run(runners);
    }

    public static class CheckerRunner extends
            ParallelFileIterator.ParallelFileRunner {
        protected void callback(File currentFile) {
            final Path inputPath = new Path(currentFile.getAbsolutePath());
            final SequenceFile.Reader reader;
            try {
                reader = new SequenceFile.Reader(fs, inputPath, conf);
            } catch(IOException io) {
                throw new RuntimeException(io);
            }
            final IntWritable key = new IntWritable();
            final org.apache.hadoop.io.SparseVectorWritable val =
                new org.apache.hadoop.io.SparseVectorWritable();

            try {
                int count = 0;
                while(reader.next(key, val)) {
                    boolean sorted = true;
                    int[] indices = val.indices();

                    for (int i = 1; i < val.size(); i++) {
                        if (indices[i] < indices[i-1]) {
                            sorted = false;
                            break;
                        }
                    }

                    if (!sorted) {
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < val.size(); i++) {
                            sb.append(indices[i]);
                            sb.append(" ");
                        }
                        this.print("Unsorted vector at offset "+count+
                                " in file "+currentFile.getAbsolutePath(): "+
                                sb.toString());
                    }
                    count++;
                }

                reader.close();

            } catch(IOException io) {
                throw new RuntimeException(io);
            }
        }
    }
}
