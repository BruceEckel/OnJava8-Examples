// streams/RandInts.java
package streams;
import java.util.*;
import java.util.stream.*;

public class RandInts {
  private static int[] rints =
    new Random(47).ints(0, 1000).limit(100).toArray();
  public static IntStream rands() {
    return Arrays.stream(rints);
  }
}
