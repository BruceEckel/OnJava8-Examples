// files/Find.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
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
test\bag\foo\bar\baz\369594599218802549.tmp
test\bag\foo\bar\baz\File.txt
test\bar\baz\bag\foo\2372197258227988384.tmp
test\bar\baz\bag\foo\File.txt
test\baz\bag\foo\bar\1091363470697614580.tmp
test\baz\bag\foo\bar\File.txt
test\dir.tmp
test\foo\bar\baz\bag\48863440153181406.tmp
test\foo\bar\baz\bag\File.txt
***************
369594599218802549.tmp
2372197258227988384.tmp
1091363470697614580.tmp
dir.tmp
48863440153181406.tmp
***************
369594599218802549.tmp
2372197258227988384.tmp
1091363470697614580.tmp
48863440153181406.tmp
*/
