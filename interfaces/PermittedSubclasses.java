// interfaces/PermittedSubclasses.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 17

sealed class Color permits Red, Green, Blue {}
final class Red extends Color {}
final class Green extends Color {}
final class Blue extends Color {}

public class PermittedSubclasses {
  public static void main(String[] args) {
    for(var p: Color.class.getPermittedSubclasses())
      System.out.println(p.getSimpleName());
  }
}
/* Output:
Red
Green
Blue
*/
