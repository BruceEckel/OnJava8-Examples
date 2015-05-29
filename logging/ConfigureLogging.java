//: logging/ConfigureLogging.java
// ©2015 MindView LLC: see Copyright.txt
// {JVMArgs: -Djava.util.logging.config.file=log.prop}
import java.util.logging.*;

public class ConfigureLogging {
  static Logger
  lgr = Logger.getLogger("net"),
  lgr2 = Logger.getLogger("net.mindview"),
  util= Logger.getLogger("net.mindview.util"),
  test= Logger.getLogger("net.mindview.test"),
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
} /* Output: (Sample)
 Logger Name : net Level: SEVERE
 Logger Name : net.mindview Level: FINEST
 Logger Name : net.mindview.util Level: INFO
 Logger Name : net.mindview.test Level: FINER
 Logger Name : random Level: SEVERE
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
SEVERE: Severe
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
FINEST: Finest
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
FINER: Finer
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
FINE: Fine
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
CONFIG: Config
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
INFO: Info
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
WARNING: Warning
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
SEVERE: Severe
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
INFO: Info
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
WARNING: Warning
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
SEVERE: Severe
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
FINER: Finer
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
FINE: Fine
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
CONFIG: Config
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
INFO: Info
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
WARNING: Warning
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
SEVERE: Severe
May 14, 2015 4:43:34 PM ConfigureLogging sendLogMessages
SEVERE: Severe
*///:~
