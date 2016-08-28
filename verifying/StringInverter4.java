// verifying/StringInverter4.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;
import static java.lang.Character.*;

public class StringInverter4 implements StringInverter {
  static final String allowed =
    "abcdefghijklmnopqrstuvwxyz ,." +
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public String invert(String str) {
    if(str.length() > 30)
      throw new RuntimeException("argument too long!");
    String result = "";
    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if(allowed.indexOf(c) == -1)
        throw new RuntimeException(c + " Not allowed");
      result += isUpperCase(c) ?
                toLowerCase(c) :
                toUpperCase(c);
    }
    return result;
  }
}
