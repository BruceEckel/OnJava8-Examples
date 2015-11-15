// remote/PerfectTime.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// The PerfectTime remote interface
import java.rmi.*;

public interface PerfectTime extends Remote {
  long getPerfectTime() throws RemoteException;
}
