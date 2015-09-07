// io/BufferedInputFile.java
// ©2015 MindView LLC: see Copyright.txt
import java.io.*;

public class BufferedInputFile {
  // Throw exceptions to console:
  public static String
  read(String filename) throws IOException {
    StringBuilder sb;
    // Reading input by lines:
    try(BufferedReader in = new BufferedReader(
        new FileReader(filename))) {
      String s;
      sb = new StringBuilder();
      while((s = in.readLine())!= null)
        sb.append(s + "\n");
    }
    return sb.toString();
  }
  public static void main(String[] args)
  throws IOException {
    System.out.print(read("BufferedInputFile.java"));
  }
}
/* Output: (Execute to see) */
