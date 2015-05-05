//: logging/CustomHandler.java
// How to write custom handler
import java.util.logging.*;
import java.util.*;

public class CustomHandler {
  private static Logger logger =
    Logger.getLogger("CustomHandler");
  private static List strHolder = new ArrayList();
  public static void main(String[] args) {
    logger.addHandler(new Handler() {
      @Override
      public void publish(LogRecord logRecord) {
        strHolder.add(logRecord.getLevel() + ":");
        strHolder.add(logRecord.getSourceClassName()+":");
        strHolder.add(logRecord.getSourceMethodName()+":");
        strHolder.add("<" + logRecord.getMessage() + ">");
        strHolder.add("\n");
      }
      @Override
      public void flush() {}
      @Override
      public void close() {}
    });
    logger.warning("Logging Warning");
    logger.info("Logging Info");
    System.out.print(strHolder);
  }
} ///:~
