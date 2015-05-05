//: logging/InfoLogging.java
import java.util.logging.*;
import java.io.*;

public class InfoLogging {
  private static Logger logger =
    Logger.getLogger("InfoLogging");
  public static void main(String[] args) {
    logger.info("Logging an INFO-level message");
  }
} ///:~
