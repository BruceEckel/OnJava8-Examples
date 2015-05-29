//: logging/InfoLogging.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.logging.*;

public class InfoLogging {
  private static Logger logger =
    Logger.getLogger("InfoLogging");
  public static void main(String[] args) {
    logger.info("Logging: INFO-level message");
  }
} /* Output:
May 13, 2015 6:26:41 PM InfoLogging main
INFO: Logging: INFO-level message
*///:~
