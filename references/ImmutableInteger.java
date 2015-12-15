// references/ImmutableInteger.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// The Integer class cannot be changed.
import java.util.*;

public class ImmutableInteger {
  public static void main(String[] args) {
    List<Integer> v = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      v.add(new Integer(i));
    // But how do you change the int
    // inside the Integer?
  }
}
