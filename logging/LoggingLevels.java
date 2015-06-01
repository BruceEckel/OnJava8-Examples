//: logging/LoggingLevels.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevels {
  private static Logger
  lgr = Logger.getLogger("com"),
  lgr2 = Logger.getLogger("net.mindview"),
  util= Logger.getLogger("net.mindview.util"),
  test= Logger.getLogger("net.mindview.test"),
  rand = Logger.getLogger("random");
  private static void logMessages() {
    lgr.info("com : info");
    lgr2.info("net.mindview : info");
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
} /* Output:
com level: SEVERE
individual loggers set to FINEST
com level: SEVERE
May 13, 2015 10:43:36 AM LoggingLevels logMessages
SEVERE: test : severe
May 13, 2015 10:43:36 AM LoggingLevels logMessages
INFO: random : info
May 13, 2015 10:43:36 AM LoggingLevels logMessages
INFO: util : info
May 13, 2015 10:43:36 AM LoggingLevels logMessages
SEVERE: test : severe
May 13, 2015 10:43:36 AM LoggingLevels logMessages
INFO: random : info
May 13, 2015 10:43:36 AM LoggingLevels logMessages
INFO: util : info
May 13, 2015 10:43:36 AM LoggingLevels logMessages
SEVERE: test : severe
May 13, 2015 10:43:36 AM LoggingLevels logMessages
INFO: random : info
*///:~
