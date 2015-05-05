//: logging/PrintableLogRecord.java
// Override LogRecord toString()
import java.util.ResourceBundle;
import java.util.logging.*;

public class PrintableLogRecord extends LogRecord {
  public PrintableLogRecord(Level level, String str) {
    super(level, str);
  }
  @Override
  public String toString() {
    String result = "Level<" + getLevel() + ">\n"
      + "LoggerName<" + getLoggerName() + ">\n"
      + "Message<" + getMessage() + ">\n"
      + "CurrentMillis<" + getMillis() + ">\n"
      + "Params";
    Object[] objParams = getParameters();
    if(objParams == null)
      result += "<null>\n";
    else
      for(int i = 0; i < objParams.length; i++)
        result += "  Param # <" + i + " value " +
          objParams[i].toString() + ">\n";
    result += "ResourceBundle<" + getResourceBundle()
      + ">\nResourceBundleName<" + getResourceBundleName()
      + ">\nSequenceNumber<" + getSequenceNumber()
      + ">\nSourceClassName<" + getSourceClassName()
      + ">\nSourceMethodName<" + getSourceMethodName()
      + ">\nThread Id<" + getThreadID()
      + ">\nThrown<" + getThrown() + ">";
    return result;
  }
  public static void main(String[] args) {
    PrintableLogRecord logRecord = new PrintableLogRecord(
      Level.FINEST, "Simple Log Record");
    System.out.println(logRecord);
  }
} ///:~
