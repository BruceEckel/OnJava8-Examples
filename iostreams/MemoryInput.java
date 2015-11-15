// iostreams/MemoryInput.java
// ©2016 MindView LLC: see Copyright.txt
import java.io.*;

public class MemoryInput {
  public static void main(String[] args)
  throws IOException {
    StringReader in = new StringReader(
      BufferedInputFile.read("MemoryInput.java"));
    int c;
    while((c = in.read()) != -1)
      System.out.print((char)c);
  }
}
/* Output: (Execute to see) */
