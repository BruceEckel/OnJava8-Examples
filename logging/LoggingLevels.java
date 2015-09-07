// logging/LoggingLevels.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevels {
  private static Logger
  lgr = Logger.getLogger("com"),
  lgr2 = Logger.getLogger("com.mindviewinc"),
  util= Logger.getLogger("com.mindviewinc.util"),
  test= Logger.getLogger("com.mindviewinc.test"),
  rand = Logger.getLogger("random");
  private static void logMessages() {
    lgr.info("com : info");
    lgr2.info("com.mindviewinc : info");
    util.info("util : info");
    test.severe("test : severe");
    rand.info("random : info");
  }
  public static void main(String[] args) {
    lgr.setLevel(Level.SEVERE);
    System.out.println("com level: SEVERE");
    logMessages();
    util.setLevel(Level.FINEST);
    test.setLevel(Level.FINEST);
    rand.setLevel(Level.FINEST);
    System.out.println(
        "individual loggers set to FINEST");
    logMessages();
    lgr.setLevel(Level.SEVERE);
    System.out.println("com level: SEVERE");
    logMessages();
  }
}
/* Output:
com level: SEVERE
individual loggers set to FINEST
com level: SEVERE
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
SEVERE: test : severe
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
INFO: random : info
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
INFO: util : info
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
SEVERE: test : severe
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
INFO: random : info
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
INFO: util : info
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
SEVERE: test : severe
Jun 15, 2015 3:47:52 PM LoggingLevels logMessages
INFO: random : info
*/
