// logging/SimpleFormatterExample.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import java.util.logging.*;

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
          + record.getSourceClassName()
          + " -:- "
          + record.getSourceMethodName()
          + " -:- "
          + record.getMessage() + "\n";
      }
    });
    logger.addHandler(conHdlr);
    logMessages();
  }
}
/* Output:
___[ Error Output ]___
INFO  :  SimpleFormatterExample -:- logMessages -:- Line
One
INFO  :  SimpleFormatterExample -:- logMessages -:- Line
Two
___[ Error Output is Expected ]___
*/
