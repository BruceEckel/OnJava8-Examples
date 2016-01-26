// tasks/PipedIO.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Using pipes for inter-task I/O
import java.util.concurrent.*;
import java.io.*;
import java.util.*;

class Sender implements Runnable {
  private SplittableRandom rand = new SplittableRandom(47);
  private PipedWriter out = new PipedWriter();
  public PipedWriter getPipedWriter() { return out; }
  @Override
  public void run() {
    try {
      while(true)
        for(char c = 'A'; c <= 'z'; c++) {
          out.write(c);
          TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
        }
    } catch(IOException e) {
      System.out.println(e + " Sender write exception");
    } catch(InterruptedException e) {
      System.out.println(e + " Sender sleep interrupted");
    }
  }
}

class Receiver implements Runnable {
  private PipedReader in;
  public Receiver(Sender sender) throws IOException {
    in = new PipedReader(sender.getPipedWriter());
  }
  @Override
  public void run() {
    try {
      while(true) {
        // Blocks until characters are there:
        System.out.print(
          "Read: " + (char)in.read() + ", ");
      }
    } catch(IOException e) {
      System.out.println(e + " Receiver read exception");
    }
  }
}

public class PipedIO {
  public static void
  main(String[] args) throws Exception {
    Sender sender = new Sender();
    Receiver receiver = new Receiver(sender);
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(sender);
    es.execute(receiver);
    TimeUnit.SECONDS.sleep(4);
    es.shutdownNow();
  }
}
/* Output:
Read: A, Read: B, Read: C, Read: D, Read: E, Read: F, Read:
G, Read: H, Read: I, Read: J, Read: K, Read: L, Read: M,
java.lang.InterruptedException: sleep interrupted Sender
sleep interrupted
java.io.InterruptedIOException Receiver read exception
*/
