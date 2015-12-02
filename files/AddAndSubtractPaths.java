// files/AddAndSubtractPaths.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.nio.file.*;

public class AddAndSubtractPaths {
  static Path base = Paths.get("..", "..", "..")
    .toAbsolutePath()
    .normalize();
  static void show(int id, Path result) {
    if(result.isAbsolute())
      System.out.println("(" + id + ")r " +
        base.relativize(result));
    else
      System.out.println("(" + id + ")  " + result);
  }
  public static void main(String[] args) {
    System.out.println(System.getProperty("os.name"));
    System.out.println(base);
    Path p = Paths.get("AddAndSubtractPaths.java")
      .toAbsolutePath();
    show(1, p);
    Path convoluted = p.getParent().getParent()
      .resolve("strings")
      .resolve("..")
      .resolve(p.getParent().getFileName());
    show(2, convoluted);
    show(3, convoluted.normalize());

    Path p2 = Paths.get("..", "..");
    show(4, p2);
    show(5, p2.normalize());
    show(6, p2.toAbsolutePath().normalize());

    Path p3 = Paths.get(".").toAbsolutePath();
    Path p4 = p3.resolve(p2);
    show(7, p4);
    show(8, p4.normalize());

    Path p5 = Paths.get("").toAbsolutePath();
    show(9, p5);
    show(10, p5.resolveSibling("strings"));
  }
}
/* Output:
Windows 10
C:\Users\Bruce\Documents\Git
(1)r on-
java\ExtractedExamples\files\AddAndSubtractPaths.java
(2)r on-java\ExtractedExamples\strings\..\files
(3)r on-java\ExtractedExamples\files
(4)  ..\..
(5)  ..\..
(6)r on-java
(7)r on-java\ExtractedExamples\files\.\..\..
(8)r on-java
(9)r on-java\ExtractedExamples\files
(10)r on-java\ExtractedExamples\strings
*/
