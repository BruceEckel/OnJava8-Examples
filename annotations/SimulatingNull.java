// annotations/SimulatingNull.java
// ©2015 MindView LLC: see Copyright.txt
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {
  public int id() default -1;
  public String description() default "";
}
