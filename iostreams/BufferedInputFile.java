// iostreams/BufferedInputFile.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.io.*;

public class BufferedInputFile {
  public static String
  read(String filename) throws IOException {
    try(BufferedReader in = new BufferedReader(
          new FileReader(filename))) {
      String s;
      StringBuilder sb = new StringBuilder();
      while((s = in.readLine())!= null)
        sb.append(s + "\n");
      return sb.toString();
    }
  }
  public static void
  main(String[] args) throws IOException {
    System.out.print(read("BufferedInputFile.java"));
  }
}
/* Output: (Execute to see) */
