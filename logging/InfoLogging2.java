// logging/InfoLogging2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Guaranteeing proper class and method names
// {ErrorOutputExpected}
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
Jul 22, 2016 11:55:53 AM InfoLogging2 main
INFO: Logging an INFO-level message
*/
