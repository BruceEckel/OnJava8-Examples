// onjava/ProcessFiles.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ValidateByHand}
// {main: onjava.ProcessFiles}
package onjava;
import java.io.*;
import java.nio.file.*;

public class ProcessFiles {
  public interface Strategy {
    void process(File file);
  }
  private Strategy strategy;
  private String ext;
  public ProcessFiles(Strategy strategy, String ext) {
    this.strategy = strategy;
    this.ext = ext;
  }
  public void start(String[] args) {
    try {
      if(args.length == 0)
        processDirectoryTree(new File("."));
      else
        for(String arg : args) {
          File fileArg = new File(arg);
          if(fileArg.isDirectory())
            processDirectoryTree(fileArg);
          else {
            // Allow user to leave off extension:
            if(!arg.endsWith("." + ext))
              arg += "." + ext;
            strategy.process(
              new File(arg).getCanonicalFile());
          }
        }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void
  processDirectoryTree(File root) throws IOException {
    PathMatcher matcher = FileSystems.getDefault()
      .getPathMatcher("glob:**/*.{" + ext + "}");
    Files.walk(root.toPath())
      .filter(matcher::matches)
      .forEach(p -> strategy.process(p.toFile()));
  }
  // Demonstration of how to use it:
  public static void main(String[] args) {
    new ProcessFiles(file -> System.out.println(file),
      "java").start(args);
  }
}
