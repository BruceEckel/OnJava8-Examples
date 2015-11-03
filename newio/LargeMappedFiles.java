// newio/LargeMappedFiles.java
// Creating a very large file using mapping.
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class LargeMappedFiles {
  static int length = 0x8000000; // 128 MB
  public static void main(String[] args) throws Exception {
    MappedByteBuffer out =
      new RandomAccessFile("test.dat", "rw").getChannel()
      .map(FileChannel.MapMode.READ_WRITE, 0, length);
    for(int i = 0; i < length; i++)
      out.put((byte)'x');
    System.out.println("Finished writing");
    for(int i = length/2; i < length/2 + 6; i++)
      System.out.print((char)out.get(i));
  }
}
/* Output:
Finished writing
xxxxxx
*/
