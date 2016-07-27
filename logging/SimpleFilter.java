// logging/SimpleFilter.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import java.util.logging.*;

public class SimpleFilter {
  private static Logger logger =
    Logger.getLogger("SimpleFilter");
  static class Duck {};
  static class Wombat {};
  static void sendLogMessages() {
    logger.log(Level.WARNING,
      "A duck in the house!", new Duck());
    logger.log(Level.WARNING,
      "A Wombat at large!", new Wombat());
  }
  public static void main(String[] args) {
    sendLogMessages();
    logger.setFilter(record -> {
      Object[] params =
        record.getParameters();
      if(params == null)
        return true; // No parameters
      if(record.getParameters()[0]
         instanceof Duck)
        return true;  // Only log Ducks
      return false;
    });
    logger.info("After setting filter..");
    sendLogMessages();
  }
}
/* Output:
___[ Error Output ]___
Jul 27, 2016 10:50:44 AM SimpleFilter sendLogMessages
WARNING: A duck in the house!
Jul 27, 2016 10:50:44 AM SimpleFilter sendLogMessages
WARNING: A Wombat at large!
Jul 27, 2016 10:50:44 AM SimpleFilter main
INFO: After setting filter..
Jul 27, 2016 10:50:44 AM SimpleFilter sendLogMessages
WARNING: A duck in the house!
*/
