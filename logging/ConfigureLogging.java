// logging/ConfigureLogging.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {JVMArgs: -Djava.util.logging.config.file=log.prop}
import java.util.logging.*;

public class ConfigureLogging {
  static Logger
  lgr = Logger.getLogger("com"),
  lgr2 = Logger.getLogger("com.mindviewinc"),
  util= Logger.getLogger("onjava"),
  test= Logger.getLogger("com.mindviewinc.test"),
  rand = Logger.getLogger("random");
  public ConfigureLogging() {
    /*
       Set Additional formatters, Filters and
       Handlers for the loggers here. You cannot
       specify the Handlers for loggers except
       the root logger from the configuration
       file.
    */
  }
  public static void main(String[] args) {
    sendLogMessages(lgr);
    sendLogMessages(lgr2);
    sendLogMessages(util);
    sendLogMessages(test);
    sendLogMessages(rand);
  }
  private static void
  sendLogMessages(Logger logger) {
    System.out.println(" Logger Name : "
      + logger.getName() + " Level: "
      + logger.getLevel());
    logger.finest("Finest");
    logger.finer("Finer");
    logger.fine("Fine");
    logger.config("Config");
    logger.info("Info");
    logger.warning("Warning");
    logger.severe("Severe");
  }
}
/* Output:
Logger Name : net Level: SEVERE
Logger Name : com.mindviewinc Level: FINEST
Logger Name : onjava Level: INFO
Logger Name : com.mindviewinc.test Level: FINER
Logger Name : random Level: SEVERE
___[ Error Output ]___
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
SEVERE: Severe
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
FINEST: Finest
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
FINER: Finer
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
FINE: Fine
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
CONFIG: Config
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
INFO: Info
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
WARNING: Warning
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
SEVERE: Severe
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
INFO: Info
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
WARNING: Warning
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
SEVERE: Severe
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
FINER: Finer
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
FINE: Fine
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
CONFIG: Config
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
INFO: Info
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
WARNING: Warning
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
SEVERE: Severe
Jun 15, 2015 3:47:52 PM ConfigureLogging sendLogMessages
SEVERE: Severe
*/
