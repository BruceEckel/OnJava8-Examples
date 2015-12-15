// strings/JGrep.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A very simple version of the "grep" program.
// {Args: WhitherStringBuilder.java 'return|for|String'}
import java.util.regex.*;
import java.nio.file.*;
import java.util.stream.*;

public class JGrep {
  public static void main(String[] args) throws Exception {
    if(args.length < 2) {
      System.out.println("Usage: java JGrep file regex");
      System.exit(0);
    }
    Pattern p = Pattern.compile(args[1]);
    // Iterate through the lines of the input file:
    int index = 0;
    Matcher m = p.matcher("");
    for(String line :
        Files.readAllLines(Paths.get(args[0]))) {
      m.reset(line);
      while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
    }
  }
}
/* Output:
0: String: 18
1: String: 20
2: String: 9
3: String: 25
4: String: 4
5: for: 4
6: String: 8
7: return: 4
8: String: 9
9: String: 25
10: String: 4
11: String: 31
12: for: 4
13: String: 8
14: return: 4
15: String: 20
*/
