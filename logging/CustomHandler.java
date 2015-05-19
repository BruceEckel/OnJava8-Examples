//: logging/CustomHandler.java
// How to write custom handler
import java.util.logging.*;
import java.util.*;

public class CustomHandler {
  private static Logger logger =
    Logger.getLogger("CustomHandler");
  private static List<String> trace =
    new ArrayList<>();
  public static void main(String[] args) {
    logger.addHandler(new Handler() {
      @Override
      public void publish(LogRecord logRecord) {
        trace.add(logRecord.getLevel() + ":");
        trace.add(logRecord.getSourceClassName()
          + ":");
        trace.add(
          logRecord.getSourceMethodName() +":");
        trace.add("<" + logRecord.getMessage()
          + ">");
        trace.add("\n");
      }
      @Override
      public void flush() {}
      @Override
      public void close() {}
    });
    logger.warning("Logging Warning");
    logger.info("Logging Info");
    System.out.print(trace);
  }
} /* Output:
[WARNING:, CustomHandler:, main:, <Logging Warning>, 
, INFO:, CustomHandler:, main:, <Logging Info>, 
]
May 14, 2015 3:29:53 PM CustomHandler main
WARNING: Logging Warning
May 14, 2015 3:29:53 PM CustomHandler main
INFO: Logging Info
*///:~
