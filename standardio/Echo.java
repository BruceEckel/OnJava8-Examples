// standardio/Echo.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// How to read from standard input
import java.io.*;
import onjava.TimedAbort;

public class Echo {
  public static void
  main(String[] args) throws IOException {
    new TimedAbort(4);
    BufferedReader stdin = new BufferedReader(
      new InputStreamReader(System.in));
    String s;
    while((s=stdin.readLine()) != null &&
          s.length() != 0)
      System.out.println(s);
    // An empty line or Ctrl-Z terminates the program
  }
}
