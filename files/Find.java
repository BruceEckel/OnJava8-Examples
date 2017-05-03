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
test\bag\foo\bar\baz\7676311745640921113.tmp
test\bag\foo\bar\baz\File.txt
test\bar\baz\bag\foo\5150002287600428217.tmp
test\bar\baz\bag\foo\File.txt
test\baz\bag\foo\bar\8782742629206515741.tmp
test\baz\bag\foo\bar\File.txt
test\dir.tmp
test\foo\bar\baz\bag\5650574120756265046.tmp
test\foo\bar\baz\bag\File.txt
***************
7676311745640921113.tmp
5150002287600428217.tmp
8782742629206515741.tmp
dir.tmp
5650574120756265046.tmp
***************
7676311745640921113.tmp
5150002287600428217.tmp
8782742629206515741.tmp
5650574120756265046.tmp
*/
