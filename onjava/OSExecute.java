// onjava/OSExecute.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Run an operating system command
// and send the output to the console
package onjava;
import java.io.*;

public class OSExecute {
  public static void command(String command) {
    boolean err = false;
    try {
      Process process =
        new ProcessBuilder(command.split(" ")).start();
      try(
        BufferedReader results = new BufferedReader(
          new InputStreamReader(process.getInputStream()));
        BufferedReader errors = new BufferedReader(
          new InputStreamReader(
            process.getErrorStream()))
      ) {
        String s;
        while((s = results.readLine())!= null)
          System.out.println(s);
        // Report errors and return nonzero value
        // to calling process if there are problems:
        while((s = errors.readLine())!= null) {
          System.err.println(s);
          err = true;
        }
      }
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException(
        "Errors executing " + command);
  }
}
