// annotations/UseCase.java
// ©2016 MindView LLC: see Copyright.txt
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
  public int id();
  public String description() default "no description";
}
