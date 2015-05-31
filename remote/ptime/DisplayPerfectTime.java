//: remote/ptime/DisplayPerfectTime.java
// ©2015 MindView LLC: see Copyright.txt
// Uses remote object PerfectTime
// {ValidateByHand}
package remote.ptime;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class DisplayPerfectTime {
  public static void main(String[] args) {
    System.setSecurityManager(new SecurityManager());
    try {
      PerfectTimeI t =
        (PerfectTimeI)Naming.lookup(
          "//MindviewToshibaLaptop:2005/PerfectTime");
      for(int i = 0; i < 10; i++)
        System.out.println("Perfect time = " +
          t.getPerfectTime());
    } catch(NotBoundException |
            MalformedURLException | 
            RemoteException e) {
      e.printStackTrace();
    }
  }
} ///:~
