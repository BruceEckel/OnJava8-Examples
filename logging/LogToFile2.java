// logging/LogToFile2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ErrorOutputExpected}
import java.util.logging.*;

public class LogToFile2 {
  private static Logger logger =
    Logger.getLogger("LogToFile2");
  public static void
  main(String[] args) throws Exception {
    FileHandler logFile =
      new FileHandler("LogToFile2.txt");
    logFile.setFormatter(new SimpleFormatter());
    logger.addHandler(logFile);
    logger.info("A message logged to the file");
  }
}
/* Output:
___[ Error Output ]___
Jul 22, 2016 11:55:53 AM LogToFile2 main
INFO: A message logged to the file
*/
