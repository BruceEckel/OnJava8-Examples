// files/ReadLineStream.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.nio.file.*;

public class ReadLineStream {
  public static void
  main(String[] args) throws Exception {
    Files.lines(Paths.get("PathInfo.java"))
      .skip(13)
      .findFirst()
      .ifPresent(System.out::println);
  }
}
/* Output:
    show("RegularFile", Files.isRegularFile(p));
*/
