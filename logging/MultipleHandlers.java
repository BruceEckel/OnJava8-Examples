// logging/MultipleHandlers.java
import java.util.logging.*;

public class MultipleHandlers {
  private static Logger logger =
    Logger.getLogger("MultipleHandlers");
  public static void
  main(String[] args) throws Exception {
    FileHandler logFile =
      new FileHandler("MultipleHandlers.xml");
    logger.addHandler(logFile);
    logger.addHandler(new ConsoleHandler());
    logger.warning(
      "Output to multiple handlers");
  }
}
/* Output:
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM MultipleHandlers main
WARNING: Output to multiple handlers
Jun 15, 2015 3:47:52 PM MultipleHandlers main
WARNING: Output to multiple handlers
*/
