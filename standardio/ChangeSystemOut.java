// standardio/ChangeSystemOut.java
// ©2016 MindView LLC: see Copyright.txt
// Turn System.out into a PrintWriter.
import java.io.*;

public class ChangeSystemOut {
  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out, true);
    out.println("Hello, world");
  }
}
/* Output:
Hello, world
*/
