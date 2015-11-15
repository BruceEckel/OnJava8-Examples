// operators/StringOperators.java
// ©2016 MindView LLC: see Copyright.txt

public class StringOperators {
  public static void main(String[] args) {
    int x = 0, y = 1, z = 2;
    String s = "x, y, z ";
    System.out.println(s + x + y + z);
    System.out.println(x + " " + s); // Converts x to a String
    s += "(summed) = "; // Concatenation operator
    System.out.println(s + (x + y + z));
    System.out.println("" + x); // Shorthand for Integer.toString()
  }
}
/* Output:
x, y, z 012
0 x, y, z
x, y, z (summed) = 3
0
*/
