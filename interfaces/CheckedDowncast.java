// interfaces/CheckedDowncast.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 17

sealed interface II permits JJ {}
final class JJ implements II {}
class Something {}

public class CheckedDowncast {
  public void f() {
    II i = new JJ();
    JJ j = (JJ)i;
    // Something s = (Something)i;
    // error: incompatible types: II cannot
    // be converted to Something
  }
}
