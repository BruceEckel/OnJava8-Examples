// remote/PerfectTimeImpl.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Implementing the PerfectTime remote object
import java.rmi.RemoteException;

public class PerfectTimeImpl implements PerfectTime {
  public long getPerfectTime() throws RemoteException {
    return System.currentTimeMillis();
  }
}
