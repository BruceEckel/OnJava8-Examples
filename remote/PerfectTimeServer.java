// remote/PerfectTimeServer.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Server for the PerfectTime remote object.
// {ValidateByHand}
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PerfectTimeServer {
  public static
  void main(String[] args) throws RemoteException {
    PerfectTimeImpl pt = new PerfectTimeImpl();
    PerfectTime stub = (PerfectTime)
      UnicastRemoteObject.exportObject(pt, 0);
    Registry registry = LocateRegistry.getRegistry();
    registry.rebind("PerfectTime", stub);
    System.out.println("Ready to do time");
  }
}
