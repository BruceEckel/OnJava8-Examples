// control/TestWithReturn.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

public class TestWithReturn {
  static int test(int testval, int target) {
    if(testval > target)
      return +1;
    if(testval < target)
      return -1;
    return 0; // Match
  }
  public static void main(String[] args) {
    System.out.println(test(10, 5));
    System.out.println(test(5, 10));
    System.out.println(test(5, 5));
  }
}
/* Output:
1
-1
0
*/
