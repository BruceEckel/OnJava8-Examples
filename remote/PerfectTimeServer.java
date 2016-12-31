// remote/PerfectTimeServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Server for the PerfectTime remote object
// {ValidateByHand}
package remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PerfectTimeServer {
  public static void
  main(String[] args) throws RemoteException {
    PerfectTimeImpl pt = new PerfectTimeImpl();
    PerfectTime stub = (PerfectTime)
      UnicastRemoteObject.exportObject(pt, 0);
    Registry registry = LocateRegistry.getRegistry();
    registry.rebind("PerfectTime", stub);
    System.out.println("Ready to do time");
  }
}
