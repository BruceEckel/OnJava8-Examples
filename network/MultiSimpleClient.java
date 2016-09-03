// network/MultiSimpleClient.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Testing MultiSimpleServer with multiple clients.
// {ValidateByHand}
import java.net.*;
import java.io.*;
import onjava.*;

class SimpleClientThread implements Runnable {
  private InetAddress address;
  private static int counter = 0;
  private int id = counter++;
  private static int threadcount = 0;
  public static int threadCount() {
    return threadcount;
  }
  public SimpleClientThread(InetAddress address) {
    System.out.println("Making client " + id);
    this.address = address;
    threadcount++;
  }
  @Override
  public void run() {
    try (
      Socket socket =
        new Socket(address, MultiSimpleServer.PORT);
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            socket.getInputStream()));
      PrintWriter out =
        new PrintWriter(
          new BufferedWriter(
            new OutputStreamWriter(
              // Enable auto-flush:
              socket.getOutputStream())), true)
    ) {
      for (int i = 0; i < 25; i++) {
        out.println("Client " + id + ": " + i);
        String str = in.readLine();
        System.out.println(str);
      }
      out.println("END");
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    } finally {
      threadcount--; // Ending this thread
    }
  }
}

public class MultiSimpleClient {
  static final int MAX_THREADS = 40;
  public static void
  main(String[] args) throws IOException,
  InterruptedException {
    new TimedAbort(5); // Terminate after 5 seconds
    InetAddress address = InetAddress.getByName(null);
    while(true) {
      if(SimpleClientThread.threadCount() < MAX_THREADS)
        new SimpleClientThread(address);
      Thread.sleep(100);
    }
  }
}
