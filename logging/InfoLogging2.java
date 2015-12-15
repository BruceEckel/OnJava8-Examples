// logging/InfoLogging2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Guaranteeing proper class and method names
import java.util.logging.*;

public class InfoLogging2 {
  private static Logger logger =
    Logger.getLogger("InfoLogging2");
  public static void main(String[] args) {
    logger.logp(Level.INFO, "InfoLogging2",
      "main", "Logging an INFO-level message");
  }
}
/* Output:
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM InfoLogging2 main
INFO: Logging an INFO-level message
*/
