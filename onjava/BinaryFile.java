// onjava/BinaryFile.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Utility for reading files in binary form.
package onjava;
import java.io.*;

public class BinaryFile {
  public static byte[] read(File bFile) throws IOException{
    try(BufferedInputStream bf = new BufferedInputStream(
          new FileInputStream(bFile))) {
      byte[] data = new byte[bf.available()];
      bf.read(data);
      return data;
    }
  }
  public static byte[]
  read(String bFile) throws IOException {
    return read(new File(bFile).getAbsoluteFile());
  }
}
