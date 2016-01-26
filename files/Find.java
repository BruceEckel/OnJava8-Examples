// files/Find.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.nio.file.*;

public class Find {
  public static void
  main(String[] args) throws Exception {
    Path test = Paths.get("test");
    Directories.refreshTestDir();
    Directories.populateTestDir();
    // Creating a *directory*, not a file:
    Files.createDirectory(test.resolve("dir.tmp"));

    PathMatcher matcher = FileSystems.getDefault()
      .getPathMatcher("glob:**/*.{tmp,txt}");
    Files.walk(test)
      .filter(matcher::matches)
      .forEach(System.out::println);
    System.out.println("***************");

    PathMatcher matcher2 = FileSystems.getDefault()
      .getPathMatcher("glob:*.tmp");
    Files.walk(test)
      .map(Path::getFileName)
      .filter(matcher2::matches)
      .forEach(System.out::println);
    System.out.println("***************");

    Files.walk(test) // Only look for files
      .filter(Files::isRegularFile)
      .map(Path::getFileName)
      .filter(matcher2::matches)
      .forEach(System.out::println);
  }
}
/* Output:
test\bag\foo\bar\baz\7875645296823748474.tmp
test\bag\foo\bar\baz\File.txt
test\bar\baz\bag\foo\8495339501512512468.tmp
test\bar\baz\bag\foo\File.txt
test\baz\bag\foo\bar\4657930969137717517.tmp
test\baz\bag\foo\bar\File.txt
test\dir.tmp
test\foo\bar\baz\bag\5205870604020775563.tmp
test\foo\bar\baz\bag\File.txt
***************
7875645296823748474.tmp
8495339501512512468.tmp
4657930969137717517.tmp
dir.tmp
5205870604020775563.tmp
***************
7875645296823748474.tmp
8495339501512512468.tmp
4657930969137717517.tmp
5205870604020775563.tmp
*/
