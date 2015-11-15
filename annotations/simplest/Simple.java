// annotations/simplest/Simple.java
// ©2016 MindView LLC: see Copyright.txt
// A bare-bones annotation.
package annotations.simplest;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD,
         ElementType.CONSTRUCTOR,
         ElementType.ANNOTATION_TYPE,
         ElementType.PACKAGE, ElementType.FIELD,
         ElementType.LOCAL_VARIABLE})
public @interface Simple {
    String value() default "-default-";
}
