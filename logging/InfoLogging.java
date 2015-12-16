// logging/InfoLogging.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import java.util.logging.*;

public class InfoLogging {
  private static Logger logger =
    Logger.getLogger("InfoLogging");
  public static void main(String[] args) {
    logger.info("Logging: INFO-level message");
  }
}
/* Output:
___[ Error Output ]___
Dec 15, 2015 9:58:43 PM InfoLogging main
INFO: Logging: INFO-level message
___[ Error Output is Expected ]___
*/
