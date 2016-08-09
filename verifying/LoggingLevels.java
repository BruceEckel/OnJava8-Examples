// verifying/LoggingLevels.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.spi.*;
import org.apache.logging.log4j.core.config.Configurator;

public class LoggingLevels {
  private static Logger
  lgr = LogManager.getLogger("com"),
  lgr2 = LogManager.getLogger("com.mindviewinc"),
  util= LogManager.getLogger("onjava"),
  test= LogManager.getLogger("com.mindviewinc.test"),
  rand = LogManager.getLogger("random");
  private static void logMessages() {
    lgr.info("com : info");
    lgr2.info("com.mindviewinc : info");
    util.info("util : info");
    test.fatal("test : fatal");
    rand.info("random : info");
  }
  public static void main(String[] args) {
    Configurator.setLevel("com", Level.FATAL);
    System.out.println("com level: FATAL");
    logMessages();
    Configurator.setLevel("onjava", Level.TRACE);
    Configurator.setLevel("com.mindviewinc.test", Level.TRACE);
    Configurator.setLevel("random", Level.TRACE);
    System.out.println(
        "individual loggers set to TRACE");
    logMessages();
    Configurator.setLevel("com", Level.FATAL);
    System.out.println("com level: FATAL");
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
