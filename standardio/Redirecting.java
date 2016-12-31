// standardio/Redirecting.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates standard I/O redirection
import java.io.*;

public class Redirecting {
  public static void
  main(String[] args) throws IOException {
    PrintStream console = System.out;
    try(
      BufferedInputStream in = new BufferedInputStream(
        new FileInputStream("Redirecting.java"));
      PrintStream out = new PrintStream(
        new BufferedOutputStream(
          new FileOutputStream("Redirecting.txt")))
    ) {
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
