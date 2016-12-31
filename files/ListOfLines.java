// files/ListOfLines.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.nio.file.*;

public class ListOfLines {
  public static void
  main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(
      Paths.get("../streams/Cheese.dat"));
    for(String line : lines) {
      if(line.startsWith("//"))
        continue;
      System.out.println(
        line.substring(0, line.length()/2));
    }

  }
}
/* Output:
Not much of a cheese
Finest in the
And what leads you
Well, it's
It's certainly uncon
*/
