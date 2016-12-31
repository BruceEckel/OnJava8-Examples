// references/SimplerMutableInteger.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A trivial wrapper class
import java.util.*;

class IntValue2 {
  public int n;
  public IntValue2(int n) { this.n = n; }
}

public class SimplerMutableInteger {
  public static void main(String[] args) {
    List<IntValue2> v = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      v.add(new IntValue2(i));
    for(IntValue2 i : v)
      System.out.print(i.n + " ");
    System.out.println();
    for(int i = 0; i < v.size(); i++)
      v.get(i).n = v.get(i).n + 1;
    for(IntValue2 i : v)
      System.out.print(i.n + " ");
  }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
1 2 3 4 5 6 7 8 9 10
*/
