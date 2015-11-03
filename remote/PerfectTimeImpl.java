// remote/PerfectTimeImpl.java
// The implementation of the PerfectTime
// remote object.
import java.rmi.RemoteException;

public class PerfectTimeImpl implements PerfectTime {
  public long getPerfectTime() throws RemoteException {
    return System.currentTimeMillis();
  }
}
