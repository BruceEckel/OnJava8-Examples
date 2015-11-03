// iostreams/FileOutputShortcut.java
import java.io.*;

public class FileOutputShortcut {
  static String file = "FileOutputShortcut.out";
  public static void main(String[] args)
  throws IOException {
    BufferedReader in = new BufferedReader(
      new StringReader(BufferedInputFile.read(
        "FileOutputShortcut.java")));
    // Here's the shortcut:
    try(PrintWriter out = new PrintWriter(file)) {
      int lineCount = 1;
      String s;
      while((s = in.readLine()) != null )
        out.println(lineCount++ + ": " + s);
    }
    // Show the stored file:
    System.out.println(BufferedInputFile.read(file));
  }
}
/* Output: (Execute to see) */
