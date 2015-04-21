//: annotations/InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
package annotations;
/*import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;*/
import java.util.*;

public class InterfaceExtractorProcessorFactory
  implements javax.annotation.processing.Processor {
  public javax.annotation.processing.Processor getProcessorFor(
    Set<javax.lang.model.element.TypeElement> atds,
    javax.annotation.processing.ProcessingEnvironment env) {
    return new InterfaceExtractorProcessor(env);
  }
  public Collection<String> supportedAnnotationTypes() {
    return
     Collections.singleton("annotations.ExtractInterface");
  }
  public Collection<String> supportedOptions() {
    return Collections.emptySet();
  }
} ///:~
