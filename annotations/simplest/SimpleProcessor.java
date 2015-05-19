//: annotations/simplest/SimpleProcessor.java
// A bare-bones annotation processor.
package annotations.simplest;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;

@SupportedAnnotationTypes(
  "annotations.simplest.Simple")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleProcessor
extends AbstractProcessor {
  @Override public boolean
  process(Set<? extends TypeElement> annotations,
  RoundEnvironment env) {
    for(TypeElement t : annotations)
      System.out.println(t);
    for(Element el :
      env.getElementsAnnotatedWith(Simple.class)){
        System.out.println(el.getKind() +
          " : " + el.getModifiers() +
          " : " + el.getSimpleName() +
          " : " + el.asType());
    }
    return false;
  }
} ///:~
