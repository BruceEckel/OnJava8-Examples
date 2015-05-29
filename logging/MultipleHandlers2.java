//: logging/MultipleHandlers2.java
// ©2015 MindView LLC: see Copyright.txt
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
} /* Output:
May 14, 2015 3:29:53 PM MultipleHandlers2 main
WARNING: Output to multiple handlers
*///:~
