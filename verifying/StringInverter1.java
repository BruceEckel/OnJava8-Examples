// verifying/StringInverter1.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;

public class StringInverter1 implements StringInverter {
  public String invert(String str) { return ""; }
}
/* Output:
StringInverter1 has 3 FAILURES:
Failure 1:
allowedCharacters_Fail(StringInverterTest)
Expected exception: java.lang.RuntimeException
Failure 2:
basicInversion_Succeed(StringInverterTest)
expected:<[]> but was:<[eXIT, pURSUED BY A bEAR.]>
Failure 3:
lengthLessThan26_Fail(StringInverterTest)
Expected exception: java.lang.RuntimeException
*/
