// standardio/Redirecting.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates standard I/O redirection.
import java.io.*;

public class Redirecting {
  public static void main(String[] args)
  throws IOException {
    PrintStream console = System.out;
    BufferedInputStream in = new BufferedInputStream(
      new FileInputStream("Redirecting.java"));
    try(PrintStream out = new PrintStream(
          new BufferedOutputStream(
            new FileOutputStream("test.out")))) {
      System.setIn(in);
      System.setOut(out);
      System.setErr(out);
      BufferedReader br = new BufferedReader(
              new InputStreamReader(System.in));
      String s;
      while((s = br.readLine()) != null)
        System.out.println(s);
      out.close(); // Remember this!
    }
    System.setOut(console);
  }
}
/* Output: (None) */
