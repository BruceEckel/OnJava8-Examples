// files/Find.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.nio.file.*;

public class Find {
  public static void main(String[] args) throws Exception {
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
test\bar\baz\bingo\foo\7502408684126514935.tmp
test\bar\baz\bingo\foo\File.txt
test\baz\bingo\foo\bar\5777895186852859683.tmp
test\baz\bingo\foo\bar\File.txt
test\bingo\foo\bar\baz\5734112745714386401.tmp
test\bingo\foo\bar\baz\File.txt
test\dir.tmp
test\foo\bar\baz\bingo\585102693362215677.tmp
test\foo\bar\baz\bingo\File.txt
***************
7502408684126514935.tmp
5777895186852859683.tmp
5734112745714386401.tmp
dir.tmp
585102693362215677.tmp
***************
7502408684126514935.tmp
5777895186852859683.tmp
5734112745714386401.tmp
585102693362215677.tmp
*/
