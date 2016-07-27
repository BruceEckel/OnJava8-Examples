// logging/CustomHandler.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// How to write custom handler
// {ErrorOutputExpected}
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
}
/* Output:
[WARNING:, CustomHandler:, main:, <Logging Warning>,
, INFO:, CustomHandler:, main:, <Logging Info>,
]
___[ Error Output ]___
Jul 27, 2016 10:50:39 AM CustomHandler main
WARNING: Logging Warning
Jul 27, 2016 10:50:39 AM CustomHandler main
INFO: Logging Info
*/
