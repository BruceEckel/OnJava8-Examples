// newio/BufferToText.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Converting text to and from ByteBuffers
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;

public class BufferToText {
  private static final int BSIZE = 1024;
  public static void
  main(String[] args) throws Exception {
    try(FileChannel fc = new FileOutputStream(
          "data2.txt").getChannel()) {
      fc.write(ByteBuffer.wrap("Some text".getBytes()));
    }
    ByteBuffer buff = ByteBuffer.allocate(BSIZE);
    try(FileChannel fc = new FileInputStream(
          "data2.txt").getChannel()) {
      fc.read(buff);
    }
    buff.flip();
    // Doesn't work:
    System.out.println(buff.asCharBuffer());
    // Decode using this system's default Charset:
    buff.rewind();
    String encoding = System.getProperty("file.encoding");
    System.out.println("Decoded using " + encoding + ": "
      + Charset.forName(encoding).decode(buff));
    // Or, we could encode with something that prints:
    try(FileChannel fc = new FileOutputStream(
          "data2.txt").getChannel()) {
      fc.write(ByteBuffer.wrap(
        "Some text".getBytes("UTF-16BE")));
    }
    // Now try reading again:
    buff.clear();
    try(FileChannel fc = new FileInputStream(
          "data2.txt").getChannel()) {
      fc.read(buff);
    }
    buff.flip();
    System.out.println(buff.asCharBuffer());
    // Use a CharBuffer to write through:
    buff = ByteBuffer.allocate(24); // More than needed
    buff.asCharBuffer().put("Some text");
    try(FileChannel fc = new FileOutputStream(
          "data2.txt").getChannel()) {
      fc.write(buff);
    }
    // Read and display:
    buff.clear();
    try(FileChannel fc = new FileInputStream(
          "data2.txt").getChannel()) {
      fc.read(buff);
    }
    buff.flip();
    System.out.println(buff.asCharBuffer());
  }
}
/* Output:
????
Decoded using windows-1252: Some text
Some text
Some textNULNULNUL
*/
