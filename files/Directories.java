// files/Directories.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.nio.file.*;
import onjava.RmDir;

public class Directories {
  static Path test = Paths.get("test");
  static String sep =
    FileSystems.getDefault().getSeparator();
  static List<String> parts =
    Arrays.asList("foo", "bar", "baz", "bingo");
  static Path makeVariant() {
    Collections.rotate(parts, 1);
    return Paths.get("test", String.join(sep, parts));
  }
  static void refreshTestDir() throws Exception {
    if(Files.exists(test))
      RmDir.rmdir(test);
    if(!Files.exists(test))
      Files.createDirectory(test);
  }
  public static void main(String[] args) throws Exception {
    refreshTestDir();
    Files.createFile(test.resolve("Hello.txt"));
    Path variant = makeVariant();
    // Throws exception (too many levels):
    try {
      Files.createDirectory(variant);
    } catch(Exception e) {
      System.out.println("Nope, that doesn't work.");
    }
    populateTestDir();
    Path tempdir =
      Files.createTempDirectory(test, "dirprefix");
    Files.createTempFile(tempdir, "tpre", ".non");
    Files.newDirectoryStream(test)
      .forEach(System.out::println);
    System.out.println("*********");
    Files.walk(test).forEach(System.out::println);
  }
  static void populateTestDir() throws Exception {
    for(int i = 0; i < parts.size(); i++) {
      Path variant = makeVariant();
      if(!Files.exists(variant)) {
        Files.createDirectories(variant);
        Files.copy(Paths.get("Directories.java"),
          variant.resolve("File.txt"));
        Files.createTempFile(variant, null, null);
      }
    }
  }
}
/* Output:
Nope, that doesn't work.
test\bar
test\baz
test\bingo
test\dirprefix7027855145419397204
test\foo
test\Hello.txt
*********
test
test\bar
test\bar\baz
test\bar\baz\bingo
test\bar\baz\bingo\foo
test\bar\baz\bingo\foo\1220228689745443997.tmp
test\bar\baz\bingo\foo\File.txt
test\baz
test\baz\bingo
test\baz\bingo\foo
test\baz\bingo\foo\bar
test\baz\bingo\foo\bar\5075758669780325498.tmp
test\baz\bingo\foo\bar\File.txt
test\bingo
test\bingo\foo
test\bingo\foo\bar
test\bingo\foo\bar\baz
test\bingo\foo\bar\baz\8539170686730128217.tmp
test\bingo\foo\bar\baz\File.txt
test\dirprefix7027855145419397204
test\dirprefix7027855145419397204\tpre8937492273233690134.n
on
test\foo
test\foo\bar
test\foo\bar\baz
test\foo\bar\baz\bingo
test\foo\bar\baz\bingo\4151379795190009123.tmp
test\foo\bar\baz\bingo\File.txt
test\Hello.txt
*/
