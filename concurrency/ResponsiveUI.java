//: concurrency/ResponsiveUI.java
// ©2015 MindView LLC: see Copyright.txt
// User interface responsiveness.
// {TimeOutDuringTesting}

class UnresponsiveUI {
  private volatile double d = 1;
  public UnresponsiveUI() throws Exception {
    while(d > 0)
      d += (Math.PI + Math.E) / d;
    System.in.read(); // Never gets here
  }
}

public class ResponsiveUI extends Thread {
  private static volatile double d = 1;
  public ResponsiveUI() {
    setDaemon(true);
    start();
  }
  @Override
  public void run() {
    while(true) {
      d += (Math.PI + Math.E) / d;
    }
  }
  public static void main(String[] args) throws Exception {
    //! new UnresponsiveUI(); // Must kill this process
    new ResponsiveUI();
    System.in.read();
    System.out.println(d); // Shows progress
  }
} ///:~
