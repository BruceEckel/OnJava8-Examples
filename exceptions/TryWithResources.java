// exceptions/TryWithResources.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.io.*;

public class TryWithResources {
  public static void main(String[] args) {
    try(InputStream in = new FileInputStream(
          new File("TryWithResources.java"))) {
      int contents = in.read();
      // Process contents
    } catch(IOException e) {
      // Handle the error
    }
  }
}
