//: logging/LogToFile2.java
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
} /* Output:
May 14, 2015 3:29:53 PM LogToFile2 main
INFO: A message logged to the file
*///:~
