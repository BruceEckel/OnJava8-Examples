// remote/PerfectTime.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The PerfectTime remote interface
package remote;
import java.rmi.*;

public interface PerfectTime extends Remote {
  long getPerfectTime() throws RemoteException;
}
