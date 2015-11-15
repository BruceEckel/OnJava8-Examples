// remote/PerfectTime.java
// ©2016 MindView LLC: see Copyright.txt
// The PerfectTime remote interface
import java.rmi.*;

public interface PerfectTime extends Remote {
  long getPerfectTime() throws RemoteException;
}
