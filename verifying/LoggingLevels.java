// verifying/LoggingLevels.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevels {
  private static Logger
  lgr = Logger.getLogger("com"),
  lgr2 = Logger.getLogger("com.mindviewinc"),
  util= Logger.getLogger("onjava"),
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
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: util : info
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
SEVERE: test : severe
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: random : info
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: util : info
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
SEVERE: test : severe
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: random : info
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: util : info
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
SEVERE: test : severe
Jul 27, 2016 10:50:42 AM LoggingLevels logMessages
INFO: random : info
*/
