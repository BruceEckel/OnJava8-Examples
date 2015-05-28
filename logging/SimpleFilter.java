//: logging/SimpleFilter.java
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
} /* Output:
May 14, 2015 3:29:53 PM SimpleFilter sendLogMessages
WARNING: A duck in the house!
May 14, 2015 3:29:54 PM SimpleFilter sendLogMessages
WARNING: A Wombat at large!
May 14, 2015 3:29:54 PM SimpleFilter main
INFO: After setting filter..
May 14, 2015 3:29:54 PM SimpleFilter sendLogMessages
WARNING: A duck in the house!
*///:~
