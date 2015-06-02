//: remote/PerfectTimeServer.java
// ©2015 MindView LLC: see Copyright.txt
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
} ///:~
