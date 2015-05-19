//: logging/InfoLogging2.java
// Guaranteeing proper class and method names
import java.util.logging.*;

public class InfoLogging2 {
  private static Logger logger =
    Logger.getLogger("InfoLogging2");
  public static void main(String[] args) {
    logger.logp(Level.INFO, "InfoLogging2",
      "main", "Logging an INFO-level message");
  }
} /* Output:
May 13, 2015 10:43:36 AM InfoLogging2 main
INFO: Logging an INFO-level message
*///:~
