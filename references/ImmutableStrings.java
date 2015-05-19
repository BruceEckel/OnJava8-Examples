//: references/ImmutableStrings.java
// Demonstrating StringBuffer.

public class ImmutableStrings {
  public static void main(String[] args) {
    String foo = "foo";
    String s = "abc" + foo + "def"
      + Integer.toString(47);
    System.out.println(s);
    // The "equivalent" using StringBuffer:
    StringBuffer sb =
      new StringBuffer("abc"); // Creates String
    sb.append(foo);
    sb.append("def"); // Creates String
    sb.append(Integer.toString(47));
    System.out.println(sb);
  }
} /* Output:
abcfoodef47
abcfoodef47
*///:~
