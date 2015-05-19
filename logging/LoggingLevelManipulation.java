//: logging/LoggingLevelManipulation.java
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevelManipulation {
  private static Logger
  lgr = Logger.getLogger("net"),
  lgr2 = Logger.getLogger("net.mindview"),
  util= Logger.getLogger("net.mindview.util"),
  test= Logger.getLogger("net.mindview.test"),
  rand = Logger.getLogger("random");
  static void printLogMessages(Logger logger) {
    logger.finest(logger.getName() + " Finest");
    logger.finer(logger.getName() + " Finer");
    logger.fine(logger.getName() + " Fine");
    logger.config(logger.getName() + " Config");
    logger.info(logger.getName() + " Info");
    logger.warning(logger.getName()+" Warning");
    logger.severe(logger.getName() + " Severe");
  }
  static void logMessages() {
    printLogMessages(lgr);
    printLogMessages(lgr2);
    printLogMessages(util);
    printLogMessages(test);
    printLogMessages(rand);
  }
  static void printLevels() {
    System.out.println(" -- printing levels -- "
      + lgr.getName()
      + " : " + lgr.getLevel()
      + " " + lgr2.getName()
      + " : " + lgr2.getLevel()
      + " " + util.getName()
      + " : " + util.getLevel()
      + " " + test.getName()
      + " : " + test.getLevel()
      + " " + rand.getName()
      + " : " + rand.getLevel());
  }
  public static void main(String[] args) {
    printLevels();
    lgr.setLevel(Level.SEVERE);
    printLevels();
    System.out.println("net level: SEVERE");
    logMessages();
    util.setLevel(Level.FINEST);
    test.setLevel(Level.FINEST);
    rand.setLevel(Level.FINEST);
    printLevels();
    System.out.println(
      "individual loggers set to FINEST");
    logMessages();
    lgr.setLevel(Level.FINEST);
    printLevels();
    System.out.println("net level: FINEST");
    logMessages();
  }
} /* Output:
-- printing levels -- net : null net.mindview : null net.mindview.util : null net.mindview.test : null random : null
 -- printing levels -- net : SEVERE net.mindview : null net.mindview.util : null net.mindview.test : null random : null
net level: SEVERE
 -- printing levels -- net : SEVERE net.mindview : null net.mindview.util : FINEST net.mindview.test : FINEST random : FINEST
individual loggers set to FINEST
 -- printing levels -- net : FINEST net.mindview : null net.mindview.util : FINEST net.mindview.test : FINEST random : FINEST
net level: FINEST
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
SEVERE: net Severe
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview Severe
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.util Severe
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.test Severe
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
INFO: random Info
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
WARNING: random Warning
May 14, 2015 4:21:45 PM LoggingLevelManipulation printLogMessages
SEVERE: random Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net.mindview.util Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net.mindview.util Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.util Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net.mindview.test Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net.mindview.test Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.test Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: random Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: random Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: random Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net.mindview Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net.mindview Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net.mindview.util Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net.mindview.util Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.util Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: net.mindview.test Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: net.mindview.test Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: net.mindview.test Severe
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
INFO: random Info
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
WARNING: random Warning
May 14, 2015 4:21:46 PM LoggingLevelManipulation printLogMessages
SEVERE: random Severe
*///:~
