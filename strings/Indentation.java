// strings/Indentation.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 15

public class Indentation {
  public static final String NONE = """
          XXX
          YYY
          """; // No indentation
  public static final String TWO = """
          XXX
          YYY
        """;   // Produces indent of 2
  public static final String EIGHT = """
          XXX
          YYY
  """;         // Produces indent of 8
  public static void main(String[] args) {
    System.out.print(NONE);
    System.out.print(TWO);
    System.out.print(EIGHT);
  }
}
/* Output:
XXX
YYY
  XXX
  YYY
        XXX
        YYY
*/
