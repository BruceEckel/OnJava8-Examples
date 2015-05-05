//: logging/SimpleFormatterExample.java
// {CompileTimeError}
import java.util.logging.*;
import java.util.*;

public class SimpleFormatterExample {
  private static Logger logger =
    Logger.getLogger("SimpleFormatterExample");
  private static void logMessages() {
    logger.info("Line One");
    logger.info("Line Two");
  }
  public static void main(String[] args) {
    logger.setUseParentHandlers(false);
    Handler conHdlr = new ConsoleHandler();
    conHdlr.setFormatter(new Formatter() {
      public String format(LogRecord record) {
        return record.getLevel()  + "  :  "
          + record.getSourceClassName()  + " -:- "
          + record.getSourceMethodName()  + " -:- "
          + record.getMessage() + "\n";
      }
    });
    logger.addHandler(conHdlr);
    logMessages();
  }
} ///:~
