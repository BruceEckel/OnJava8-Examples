// verifying/StringInverter3.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;
import static java.lang.Character.*;

public class StringInverter3 implements StringInverter {
  public String invert(String str) {
    if(str.length() > 25)
      throw new RuntimeException("argument too long!");
    String result = "";
    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      result += isUpperCase(c) ?
                toLowerCase(c) :
                toUpperCase(c);
    }
    return result;
  }
}
/* Output:
StringInverter3 has 1 FAILURES:
Failure 1:
allowedCharacters_Fail(StringInverterTest)
Expected exception: java.lang.RuntimeException
*/
