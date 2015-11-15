// logging/LoggingLevelManipulation.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingLevelManipulation {
  private static Logger
  lgr = Logger.getLogger("com"),
  lgr2 = Logger.getLogger("com.mindviewinc"),
  util= Logger.getLogger("onjava"),
  test= Logger.getLogger("com.mindviewinc.test"),
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
}
/* Output:
-- printing levels -- net : null com.mindviewinc : null
onjava : null com.mindviewinc.test : null
random : null
-- printing levels -- net : SEVERE com.mindviewinc : null
onjava : null com.mindviewinc.test : null
random : null
net level: SEVERE
-- printing levels -- net : SEVERE com.mindviewinc : null
onjava : FINEST com.mindviewinc.test : FINEST
random : FINEST
individual loggers set to FINEST
-- printing levels -- net : FINEST com.mindviewinc : null
onjava : FINEST com.mindviewinc.test : FINEST
random : FINEST
net level: FINEST
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: net Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: onjava Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: onjava Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: onjava Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc.test Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc.test Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc.test Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: random Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: random Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: random Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: net Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: onjava Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: onjava Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: onjava Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc.test Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc.test Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc.test Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: random Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: random Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: random Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: net Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: net Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: net Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: onjava Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: onjava Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: onjava Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: com.mindviewinc.test Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: com.mindviewinc.test Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: com.mindviewinc.test Severe
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
INFO: random Info
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
WARNING: random Warning
Jun 15, 2015 3:47:52 PM LoggingLevelManipulation
printLogMessages
SEVERE: random Severe
*/
