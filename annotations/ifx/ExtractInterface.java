// annotations/ifx/ExtractInterface.java
// javac-based annotation processing.
package annotations.ifx;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
  public String interfaceName() default "-!!-";
}
