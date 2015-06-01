//: remote/PerfectTime.java
// The PerfectTime remote interface
import java.rmi.*;

public interface PerfectTime extends Remote {
  long getPerfectTime() throws RemoteException;
} ///:~
