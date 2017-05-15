// onjava/ProcessFiles.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {ExcludeFromTravisCI}
// {java onjava.ProcessFiles}
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
/* Output: (First and Last 10 Lines)
.\ArrayShow.java
.\atunit\AtUnit.java
.\atunit\ClassNameFinder.java
.\atunit\Test.java
.\atunit\TestObjectCleanup.java
.\atunit\TestObjectCreate.java
.\atunit\TestProperty.java
.\BasicSupplier.java
.\CollectionMethodDifferences.java
.\ConvertTo.java
...________...________...________...________...
.\Stack.java
.\Suppliers.java
.\TimedAbort.java
.\Timer.java
.\Tuple.java
.\Tuple2.java
.\Tuple3.java
.\Tuple4.java
.\Tuple5.java
.\TypeCounter.java
*/
