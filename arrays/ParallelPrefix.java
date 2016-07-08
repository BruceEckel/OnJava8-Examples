// arrays/ParallelPrefix.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

public class ParallelPrefix {
  static final int SIZE = 10_000_000;
  public static void main(String[] args) {
    long[] nums = new long[SIZE];
    Arrays.setAll(nums, n -> n);
    Arrays.parallelPrefix(nums, Long::sum);
    System.out.println("First 20: " + nums[19]);
    System.out.println("First 200: " + nums[199]);
    System.out.println("All: " + nums[nums.length-1]);
  }
}
/* Output:
First 20: 190
First 200: 19900
All: 199999990000000
*/
