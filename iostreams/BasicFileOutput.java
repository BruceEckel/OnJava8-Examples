// iostreams/BasicFileOutput.java
// ©2016 MindView LLC: see Copyright.txt
import java.io.*;

public class BasicFileOutput {
  static String file = "BasicFileOutput.out";
  public static void main(String[] args)
  throws IOException {
    BufferedReader in = new BufferedReader(
      new StringReader(
        BufferedInputFile.read("BasicFileOutput.java")));
    try(PrintWriter out = new PrintWriter(
          new BufferedWriter(new FileWriter(file)))) {
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
