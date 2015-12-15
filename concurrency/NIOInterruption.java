// concurrency/NIOInterruption.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Interrupting a blocked NIO channel.
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;
import java.io.*;

class NIOBlocked implements Runnable {
  private final SocketChannel sc;
  public NIOBlocked(SocketChannel sc) { this.sc = sc; }
  @Override
  public void run() {
    try {
      System.out.println("Waiting for read() in " + this);
      sc.read(ByteBuffer.allocate(1));
    } catch(ClosedByInterruptException e) {
      System.out.println("ClosedByInterruptException");
    } catch(AsynchronousCloseException e) {
      System.out.println("AsynchronousCloseException");
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Exiting NIOBlocked.run() " + this);
  }
}

public class NIOInterruption {
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    InetSocketAddress isa =
      new InetSocketAddress("localhost", 8080);
    try(ServerSocket server = new ServerSocket(8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa)) {
      Future<?> f = exec.submit(new NIOBlocked(sc1));
      exec.execute(new NIOBlocked(sc2));
      exec.shutdown();
      TimeUnit.SECONDS.sleep(1);
      // Produce an interrupt via cancel:
      f.cancel(true);
      TimeUnit.SECONDS.sleep(1);
      // Release the block by closing the channel:
    }
  }
}
/* Output:
Waiting for read() in NIOBlocked@122af49
Waiting for read() in NIOBlocked@1feeb4a
ClosedByInterruptException
Exiting NIOBlocked.run() NIOBlocked@122af49
AsynchronousCloseException
Exiting NIOBlocked.run() NIOBlocked@1feeb4a
*/
