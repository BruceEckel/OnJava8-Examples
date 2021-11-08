// enumerations/ArrowInSwitch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 14
import static java.util.stream.IntStream.range;

public class ArrowInSwitch {
  static void colons(int i) {
    switch(i) {
      case 1: System.out.println("one");
              break;
      case 2: System.out.println("two");
              break;
      case 3: System.out.println("three");
              break;
      default: System.out.println("default");
    }
  }
  static void arrows(int i) {
    switch(i) {
      case 1 -> System.out.println("one");
      case 2 -> System.out.println("two");
      case 3 -> System.out.println("three");
      default -> System.out.println("default");
    }
  }
  public static void main(String[] args) {
    range(0, 4).forEach(i -> colons(i));
    range(0, 4).forEach(i -> arrows(i));
  }
}
/* Output:
default
one
two
three
default
one
two
three
*/
