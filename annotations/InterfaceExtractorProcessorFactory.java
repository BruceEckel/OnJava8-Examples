//: annotations/InterfaceExtractorProcessorFactory.java
// APT-based annotation processing.
package annotations;
//import com.sun.mirror.apt.*;
import javax.annotation.processing.*;
//import com.sun.mirror.declaration.*;
import javax.lang.model.element.*;
import javax.lang.model.SourceVersion;
import java.util.*;

public class InterfaceExtractorProcessorFactory
//  implements AnnotationProcessorFactory {
  implements Processor {
  public void init(ProcessingEnvironment processingEnv) {

  }
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_7;
  }
//  public AnnotationProcessor getProcessorFor(
  public Processor getProcessorFor(
    Set<TypeElement> atds,
    ProcessingEnvironment env) {
    return new InterfaceExtractorProcessor(env);
  }
  public boolean process(Set<? extends TypeElement> annotations,
              RoundEnvironment roundEnv) {
    return new InterfaceExtractorProcessor(env);
  }
  public Set<String> getSupportedAnnotationTypes() {
    return
     Collections.singleton("annotations.ExtractInterface");
  }
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
  }
  public Iterable<? extends Completion> getCompletions(
    Element element, AnnotationMirror annotation,
    ExecutableElement member, String userText) {
      return Collections.emptyList();
  }
} ///:~
