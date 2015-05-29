//: io/BufferedInputFile.java
// ©2015 MindView LLC: see Copyright.txt
import java.io.*;

public class BufferedInputFile {
  // Throw exceptions to console:
  public static String
  read(String filename) throws IOException {
    StringBuilder sb;
    try ( // Reading input by lines:
            BufferedReader in = new BufferedReader(
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
} /* (Execute to see output) *///:~
