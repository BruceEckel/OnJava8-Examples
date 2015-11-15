// strings/JGrep.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A very simple version of the "grep" program.
// {Args: JGrep.java 'void|int|String'}
import java.util.regex.*;
import onjava.*;

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
    for(String line : new TextFile(args[0])) {
      m.reset(line);
      while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
    }
  }
}
/* Output:
0: 'void: 21
1: int: 27
2: String': 31
3: int: 19
4: int: 4
5: int: 21
6: 'void: 3
7: int: 3
8: String': 3
9: int: 3
10: int: 3
11: int: 3
12: 'void: 3
13: int: 3
14: String': 3
15: int: 3
16: int: 4
17: int: 4
18: 'void: 4
19: int: 4
20: String': 4
21: int: 4
22: int: 4
23: int: 4
*/
