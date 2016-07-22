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
test\bag\foo\bar\baz\6081303161062291208.tmp
test\bag\foo\bar\baz\File.txt
test\bar\baz\bag\foo\771297659941286896.tmp
test\bar\baz\bag\foo\File.txt
test\baz\bag\foo\bar\1769334171376700049.tmp
test\baz\bag\foo\bar\File.txt
test\dir.tmp
test\foo\bar\baz\bag\4483950466184961816.tmp
test\foo\bar\baz\bag\File.txt
***************
6081303161062291208.tmp
771297659941286896.tmp
1769334171376700049.tmp
dir.tmp
4483950466184961816.tmp
***************
6081303161062291208.tmp
771297659941286896.tmp
1769334171376700049.tmp
4483950466184961816.tmp
*/
