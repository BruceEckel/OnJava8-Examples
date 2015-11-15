// streams/FileToWords.java
// ©2016 MindView LLC: see Copyright.txt
import java.nio.file.*;
import java.util.stream.*;
import java.util.regex.Pattern;

public class FileToWords {
  public static Stream<String> stream(String filePath)
  throws Exception {
    return Files.lines(Paths.get(filePath))
      .skip(1) // First (comment) line
      .flatMap(line ->
        Pattern.compile("[ .,?]+").splitAsStream(line));
  }
}
