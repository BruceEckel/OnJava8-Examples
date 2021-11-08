// strings/BackSlashes.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class BackSlashes {
  public static void main(String[] args) {
    String one = "\\";
    String two = "\\\\";
    String three = "\\\\\\";
    System.out.println(one);
    System.out.println(two);
    System.out.println(three);
    System.out.println(one.matches("\\\\"));
    System.out.println(two.matches("\\\\\\\\"));
    System.out.println(three.matches("\\\\\\\\\\\\"));
  }
}
/* Output:
\
\\
\\\
true
true
true
*/
