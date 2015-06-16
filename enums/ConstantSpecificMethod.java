//: enums/ConstantSpecificMethod.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;
import java.text.*;

public enum ConstantSpecificMethod {
  DATE_TIME {
    @Override
    String getInfo() {
      return
        DateFormat.getDateInstance().format(new Date());
    }
  },
  CLASSPATH {
    @Override
    String getInfo() {
      return System.getenv("CLASSPATH");
    }
  },
  VERSION {
    @Override
    String getInfo() {
      return System.getProperty("java.version");
    }
  };
  abstract String getInfo();
  public static void main(String[] args) {
    for(ConstantSpecificMethod csm : values())
      System.out.println(csm.getInfo());
  }
} /* Output:
Jun 15, 2015
.;..;C:\Program Files (x86)\Java\xom-1.2.10.jar;C:\Program
Files (x86)\Java\jdk1.8.0_45\lib\tools.jar;C:\Program Files
(x86)\Java\jre1.8.0_45\lib\javaws.jar;C:\Program Files
(x86)\Java\swt-4.4.2-win32-win32-x86\swt.jar;C:\Program
Files (x86)\Java\javassist.jar;C:\Users\Bruce\Dropbox\___On
Java\ExtractedExamples;C:\Program Files
(x86)\Java\junit-4.12.jar;C:\Program Files (x86)\Java
\hamcrest-core-1.3.jar;
1.8.0_45
*///:~
