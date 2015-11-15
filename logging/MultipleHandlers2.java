// logging/MultipleHandlers2.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.logging.*;

public class MultipleHandlers2 {
  private static Logger logger =
    Logger.getLogger("MultipleHandlers2");
  public static void
  main(String[] args) throws Exception {
    FileHandler logFile =
      new FileHandler("MultipleHandlers2.xml");
    logger.addHandler(logFile);
    logger.addHandler(new ConsoleHandler());
    logger.setUseParentHandlers(false);
    logger.warning(
      "Output to multiple handlers");
  }
}
/* Output:
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM MultipleHandlers2 main
WARNING: Output to multiple handlers
*/
