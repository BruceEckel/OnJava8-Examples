// strings/JGrep.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A very simple version of the "grep" program
// {java JGrep
// WhitherStringBuilder.java "return|for|String"}
import java.util.regex.*;
import java.nio.file.*;
import java.util.stream.*;

public class JGrep {
  public static void
  main(String[] args) throws Exception {
    if(args.length < 2) {
      System.out.println(
        "Usage: java JGrep file regex");
      System.exit(0);
    }
    Pattern p = Pattern.compile(args[1]);
    Matcher m = p.matcher("");
    // Iterate through the lines of the input file:
    Files.readAllLines(Paths.get(args[0])).forEach(
      line -> {
        m.reset(line);
        while(m.find())
          System.out.println(
            m.group() + ": " + m.start());
      }
    );
  }
}
/* Output:
String: 18
String: 20
String: 9
String: 25
String: 4
for: 4
String: 8
return: 4
String: 9
String: 25
String: 4
String: 31
for: 4
String: 8
return: 4
String: 20
*/
