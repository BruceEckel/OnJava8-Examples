// references/ImmutableInteger.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The Integer class cannot be changed
import java.util.*;

public class ImmutableInteger {
  public static void main(String[] args) {
    List<Integer> v = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      v.add(Integer.valueOf(i));
    // But how do you change the int
    // inside the Integer?
  }
}
