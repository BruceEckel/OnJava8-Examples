//: housekeeping/ArrayInit.java
// ©2015 MindView LLC: see Copyright.txt
// Array initialization.
import java.util.*;

public class ArrayInit {
  public static void main(String[] args) {
    Integer[] a = {
      1, 2,
      3, // Autoboxing
    };
    Integer[] b = new Integer[]{
      1, 2,
      3, // Autoboxing
    };
    System.out.println(Arrays.toString(a));
    System.out.println(Arrays.toString(b));
  }
} /* Output:
[1, 2, 3]
[1, 2, 3]
*///:~
