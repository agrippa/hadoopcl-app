import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;
import org.apache.mahout.math.map.OpenIntIntHashMap;
import org.apache.mahout.math.function.IntIntProcedure;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import java.io.IOException;

public class ReadIntMap {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("usage: java ReadIntMap input");
      return;
    }

    Configuration conf = new Configuration();
    OpenIntIntHashMap map = Vectors.readAsIntMap(
        new Path(args[0]), conf);

    map.forEachPair(new Iter());
  }

  public static class Iter implements IntIntProcedure {
    private int count = 0;

    @Override
    public boolean apply(int key, int value) {
      System.out.println(count+": "+key+" -> "+value);
      count++;
      return true;
    }
  }
}
