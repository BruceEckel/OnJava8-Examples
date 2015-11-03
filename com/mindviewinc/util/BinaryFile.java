// com/mindviewinc/util/BinaryFile.java
// Utility for reading files in binary form.
package com.mindviewinc.util;
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
