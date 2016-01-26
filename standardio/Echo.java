// standardio/Echo.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// How to read from standard input
// {TimeOutDuringTesting}
import java.io.*;

public class Echo {
  public static void
  main(String[] args) throws IOException {
    BufferedReader stdin = new BufferedReader(
      new InputStreamReader(System.in));
    String s;
    while((s=stdin.readLine()) != null &&
          s.length() != 0)
      System.out.println(s);
    // An empty line or Ctrl-Z terminates the program
  }
}
