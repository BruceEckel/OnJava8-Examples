//: remote/ptime/PerfectTimeI.java
// ©2015 MindView LLC: see Copyright.txt
// The PerfectTime remote interface
package remote.ptime;
import java.rmi.*;

interface PerfectTimeI extends Remote {
  long getPerfectTime() throws RemoteException;
} ///:~
