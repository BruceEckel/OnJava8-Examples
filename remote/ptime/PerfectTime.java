//: remote/ptime/PerfectTime.java
// The implementation of the PerfectTime
// remote object.
// {RunByHand}
package remote.ptime;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class PerfectTime
    extends UnicastRemoteObject
    implements PerfectTimeI {
  // Implementation of the interface:
  @Override
  public long getPerfectTime()
      throws RemoteException {
    return System.currentTimeMillis();
  }
  // Must implement constructor to throw
  // RemoteException:
  public PerfectTime() throws RemoteException {
    // super(); // Called automatically
  }
  // Registration for RMI serving:
  public static void main(String[] args) {
    System.setSecurityManager(new SecurityManager());
    try {
      PerfectTime pt = new PerfectTime();
      Naming.bind(
        "//MindviewToshibaLaptop:2005/PerfectTime", pt);
      System.out.println("Ready to do time");
    } catch(RemoteException |
            AlreadyBoundException |
            MalformedURLException e) {
      e.printStackTrace();
    }
  }
} ///:~
