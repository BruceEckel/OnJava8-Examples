// enumerations/SwitchExpression.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 14
import java.util.*;

public class SwitchExpression {
  static int colon(String s) {
    var result = switch(s) {
      case "i": yield 1;
      case "j": yield 2;
      case "k": yield 3;
      default:  yield 0;
    };
    return result;
  }
  static int arrow(String s) {
    var result = switch(s) {
      case "i" -> 1;
      case "j" -> 2;
      case "k" -> 3;
      default  -> 0;
    };
    return result;
  }
  public static void main(String[] args) {
    for(var s: new String[]{"i", "j", "k", "z"})
      System.out.format(
        "%s %d %d%n", s, colon(s), arrow(s));
  }
}
/* Output:
i 1 1
j 2 2
k 3 3
z 0 0
*/
