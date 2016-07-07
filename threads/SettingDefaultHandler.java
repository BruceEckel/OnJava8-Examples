// threads/SettingDefaultHandler.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;
import onjava.TimedAbort;

public class SettingDefaultHandler {
  public static void main(String[] args) {
    new TimedAbort(4);
    Thread.setDefaultUncaughtExceptionHandler(
      new MyUncaughtExceptionHandler());
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(new ExceptionThread());
  }
}
/* Output:
caught java.lang.RuntimeException
*/
