// references/CloneArrayList.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The clone() operation works for only a few
// items in the standard Java library
import java.util.*;

class Int {
  private int i;
  public Int(int ii) { i = ii; }
  public void increment() { i++; }
  @Override
  public String toString() {
    return Integer.toString(i);
  }
}

public class CloneArrayList {
  public static void main(String[] args) {
    ArrayList<Int> v = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      v.add(new Int(i));
    System.out.println("v: " + v);
    @SuppressWarnings("unchecked")
    ArrayList<Int> v2 = (ArrayList<Int>)v.clone();
    // Increment all v2's elements:
    for(Int e : v2)
      e.increment();
    // See if it changed v's elements:
    System.out.println("v: " + v);
  }
}
/* Output:
v: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
v: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
*/
