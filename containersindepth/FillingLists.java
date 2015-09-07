// containersindepth/FillingLists.java
// ©2015 MindView LLC: see Copyright.txt
// The Collections.fill() & Collections.nCopies() methods.
import java.util.*;

class StringAddress {
  private String s;
  public StringAddress(String s) { this.s = s; }
  @Override
  public String toString() {
    return super.toString() + " " + s;
  }
}

public class FillingLists {
  public static void main(String[] args) {
    List<StringAddress> list = new ArrayList<>(
      Collections.nCopies(4, new StringAddress("Hello")));
    System.out.println(list);
    Collections.fill(list, new StringAddress("World!"));
    System.out.println(list);
  }
}
/* Output:
[StringAddress@19e0bfd Hello, StringAddress@19e0bfd Hello,
StringAddress@19e0bfd Hello, StringAddress@19e0bfd Hello]
[StringAddress@139a55 World!, StringAddress@139a55 World!,
StringAddress@139a55 World!, StringAddress@139a55 World!]
*/
