// streams/FileToWordsTest.java
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
