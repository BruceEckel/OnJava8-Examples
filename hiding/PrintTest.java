//: hiding/PrintTest.java
// ©2015 MindView LLC: see Copyright.txt
// Uses the static printing methods in Print.java.
import static com.mindviewinc.util.Print.*;

public class PrintTest {
  public static void main(String[] args) {
    print("Available from now on!");
    print(100);
    print(100L);
    print(3.14159);
  }
} /* Output:
Available from now on!
100
100
3.14159
*///:~
