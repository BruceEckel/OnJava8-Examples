// iostreams/FormattedMemoryInput.java
import java.io.*;

public class FormattedMemoryInput {
  public static void main(String[] args)
  throws IOException {
    try {
      DataInputStream in = new DataInputStream(
        new ByteArrayInputStream(
         BufferedInputFile.read(
          "FormattedMemoryInput.java").getBytes()));
      while(true)
        System.out.write((char)in.readByte());
    } catch(EOFException e) {
      System.out.println("End of stream");
    }
  }
}
/* Output: (Execute to see) */
