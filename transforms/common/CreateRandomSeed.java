package common;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import java.io.IOException;

public class CreateRandomSeed {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("usage: java CreateRandomSeed input-folder output-folder n-clusters");
            return;
        }

        String input = args[0];
        String output = args[1];
        int nClusters = Integer.parseInt(args[2]);
        Path inputFolder = new Path(input);
        Path outputFolder = new Path(output);
        Configuration conf = new Configuration();

        RandomSeedGenerator.buildRandom(conf, inputFolder,
                outputFolder, nClusters, new CosineDistanceMeasure());
    }
}
