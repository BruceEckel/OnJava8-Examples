// logging/LogToFile2.java
// ©2015 MindView LLC: see Copyright.txt
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
Jun 15, 2015 3:47:52 PM LogToFile2 main
INFO: A message logged to the file
*/
