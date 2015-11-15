// concurrency/CloseResource.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Interrupting a blocked task by
// closing the underlying resource.
// {TimeOutDuringTesting}
import java.net.*;
import java.util.concurrent.*;
import java.io.*;

public class CloseResource {
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    ServerSocket server = new ServerSocket(8080);
    try(InputStream socketInput =
         new Socket("localhost", 8080).getInputStream()) {
      exec.execute(new IOBlocked(socketInput));
      exec.execute(new IOBlocked(System.in));
      TimeUnit.MILLISECONDS.sleep(100);
      System.out.println("Shutting down all threads");
      exec.shutdownNow();
      TimeUnit.SECONDS.sleep(1);
      System.out.println("Closing " + socketInput.getClass().getName());
      socketInput.close(); // Releases blocked thread
    }
    TimeUnit.SECONDS.sleep(1);
    System.out.println("Closing " + System.in.getClass().getName());
    System.in.close(); // Releases blocked thread
  }
}
/* Output:
Waiting for read():
Waiting for read():
Shutting down all threads
Closing java.net.SocketInputStream
Interrupted from blocked I/O
Exiting IOBlocked.run()
Closing java.io.BufferedInputStream
Exiting IOBlocked.run()
*/
