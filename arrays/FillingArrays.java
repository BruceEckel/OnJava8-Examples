// arrays/FillingArrays.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Using Arrays.fill()
import java.util.*;

public class FillingArrays {
  public static void main(String[] args) {
    int size = 6;
    boolean[] a1 = new boolean[size];
    byte[] a2 = new byte[size];
    char[] a3 = new char[size];
    short[] a4 = new short[size];
    int[] a5 = new int[size];
    long[] a6 = new long[size];
    float[] a7 = new float[size];
    double[] a8 = new double[size];
    String[] a9 = new String[size];
    Arrays.fill(a1, true);
    System.out.println("a1 = " + Arrays.toString(a1));
    Arrays.fill(a2, (byte)11);
    System.out.println("a2 = " + Arrays.toString(a2));
    Arrays.fill(a3, 'x');
    System.out.println("a3 = " + Arrays.toString(a3));
    Arrays.fill(a4, (short)17);
    System.out.println("a4 = " + Arrays.toString(a4));
    Arrays.fill(a5, 19);
    System.out.println("a5 = " + Arrays.toString(a5));
    Arrays.fill(a6, 23);
    System.out.println("a6 = " + Arrays.toString(a6));
    Arrays.fill(a7, 29);
    System.out.println("a7 = " + Arrays.toString(a7));
    Arrays.fill(a8, 47);
    System.out.println("a8 = " + Arrays.toString(a8));
    Arrays.fill(a9, "Hello");
    System.out.println("a9 = " + Arrays.toString(a9));
    // Manipulating ranges:
    Arrays.fill(a9, 3, 5, "World");
    System.out.println("a9 = " + Arrays.toString(a9));
  }
}
/* Output:
a1 = [true, true, true, true, true, true]
a2 = [11, 11, 11, 11, 11, 11]
a3 = [x, x, x, x, x, x]
a4 = [17, 17, 17, 17, 17, 17]
a5 = [19, 19, 19, 19, 19, 19]
a6 = [23, 23, 23, 23, 23, 23]
a7 = [29.0, 29.0, 29.0, 29.0, 29.0, 29.0]
a8 = [47.0, 47.0, 47.0, 47.0, 47.0, 47.0]
a9 = [Hello, Hello, Hello, Hello, Hello, Hello]
a9 = [Hello, Hello, Hello, World, World, Hello]
*/
