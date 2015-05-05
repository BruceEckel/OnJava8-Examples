//: remote/ptime/PerfectTimeI.java
// The PerfectTime remote interface
package remote.ptime;
import java.rmi.*;

interface PerfectTimeI extends Remote {
  long getPerfectTime() throws RemoteException;
} ///:~
