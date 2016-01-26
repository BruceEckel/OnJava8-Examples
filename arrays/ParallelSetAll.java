// arrays/ParallelSetAll.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import onjava.*;

public class ParallelSetAll {
  static final int SIZE = 20_000_000;
  static void intArray() {
    int[] ia = new int[SIZE];
    Arrays.setAll(ia, new Rand.int_()::get);
    Arrays.parallelSetAll(ia, new Rand.int_()::get);
  }
  static void longArray() {
    long[] la = new long[SIZE];
    Arrays.setAll(la, new Rand.long_()::get);
    Arrays.parallelSetAll(la, new Rand.long_()::get);
  }
  public static void main(String[] args) {
    intArray();
    longArray();
  }
}
