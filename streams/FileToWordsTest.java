// streams/FileToWordsTest.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.stream.*;

public class FileToWordsTest {
  public static void main(String[] args) throws Exception {
    FileToWords.stream("Cheese.dat")
      .limit(7)
      .forEach(s -> System.out.format("%s ", s));
    System.out.println();
    FileToWords.stream("Cheese.dat")
      .skip(7)
      .limit(2)
      .forEach(s -> System.out.format("%s ", s));
  }
}
/* Output:
Not much of a cheese shop really
is it
*/
