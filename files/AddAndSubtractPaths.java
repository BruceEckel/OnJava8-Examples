// files/AddAndSubtractPaths.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.nio.file.*;
import java.io.IOException;

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
    try {
      System.out.println("RealPath: "
        + result.toRealPath());
    } catch(IOException e) {
      System.out.println(e);
    }
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
    show(11, Paths.get("nonexistent"));
  }
}
/* Output:
Windows 8.1
C:\Git
(1)r OnJava8\ExtractedExamples\files\AddAndSubtractPath
s.java
RealPath: C:\Git\OnJava8\ExtractedExamples\files\AddAnd
SubtractPaths.java
(2)r OnJava8\ExtractedExamples\strings\..\files
RealPath: C:\Git\OnJava8\ExtractedExamples\files
(3)r OnJava8\ExtractedExamples\files
RealPath: C:\Git\OnJava8\ExtractedExamples\files
(4)  ..\..
RealPath: C:\Git\OnJava8
(5)  ..\..
RealPath: C:\Git\OnJava8
(6)r OnJava8
RealPath: C:\Git\OnJava8
(7)r OnJava8\ExtractedExamples\files\.\..\..
RealPath: C:\Git\OnJava8
(8)r OnJava8
RealPath: C:\Git\OnJava8
(9)r OnJava8\ExtractedExamples\files
RealPath: C:\Git\OnJava8\ExtractedExamples\files
(10)r OnJava8\ExtractedExamples\strings
RealPath: C:\Git\OnJava8\ExtractedExamples\strings
(11)  nonexistent
java.nio.file.NoSuchFileException:
C:\Git\OnJava8\ExtractedExamples\files\nonexistent
*/
