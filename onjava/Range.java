// onjava/Range.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Create arrays initialized with integer values.
package onjava;

public class Range {
  // Produce sequence [start..end) incrementing by step
  public static
  int[] range(int start, int end, int step) {
    if (step == 0)
      throw new
        IllegalArgumentException("Step cannot be zero");
    int sz = Math.max(0, step >= 0 ?
        (end + step - 1 - start) / step
      : (end + step + 1 - start) / step);
    int[] result = new int[sz];
    for(int i = 0; i < sz; i++)
      result[i] = start + (i * step);
    return result;
  }  // Produce a sequence [start..end)
  public static int[] range(int start, int end) {
    return range(start, end, 1);
  }
  // Produce a sequence [0..n)
  public static int[] range(int n) {
    return range(0, n);
  }
}
