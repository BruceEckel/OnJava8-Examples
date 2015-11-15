// iostreams/TestEOF.java
// ©2016 MindView LLC: see Copyright.txt
// Testing for end of file while reading a byte at a time.
import java.io.*;

public class TestEOF {
  public static void main(String[] args)
  throws IOException {
    DataInputStream in = new DataInputStream(
      new BufferedInputStream(
        new FileInputStream("TestEOF.java")));
    while(in.available() != 0)
      System.out.write(in.readByte());
  }
}
/* Output: (Execute to see) */
