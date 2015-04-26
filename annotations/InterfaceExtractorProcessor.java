//: annotations/InterfaceExtractorProcessor.java
// APT-based annotation processing.
// {Exec: apt -factory
// annotations.InterfaceExtractorProcessorFactory
// Multiplier.java -s ../annotations}
package annotations;
//import com.sun.mirror.apt.*;
import javax.annotation.processing.*;
//import com.sun.mirror.declaration.*;
import javax.lang.model.element.*;
import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.*;

public class InterfaceExtractorProcessor
  implements Processor {
  private final ProcessingEnvironment env;
  private ArrayList<ExecutableElement> interfaceMethods =
    new ArrayList<ExecutableElement>();
  public void init(ProcessingEnvironment processingEnv) {

  }
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_7;
  }
  public InterfaceExtractorProcessor(
    ProcessingEnvironment env) { this.env = env; }
  public boolean process(Set<? extends TypeElement> annotations,
              RoundEnvironment roundEnv) {
    for(TypeElement typeElem : env.getElementUtils()) {
      ExtractInterface annot =
        typeElem.getAnnotation(ExtractInterface.class);
      if(annot == null)
        break;
      for(ExecutableElement m : typeElem.getMethods())
        if(m.getModifiers().contains(Modifier.PUBLIC) &&
           !(m.getModifiers().contains(Modifier.STATIC)))
          interfaceMethods.add(m);
      if(interfaceMethods.size() > 0) {
        try {
          PrintWriter writer =
            env.getFiler().createSourceFile(annot.value());
          writer.println("package " +
            typeElem.getPackage().getQualifiedName() +";");
          writer.println("public interface " +
            annot.value() + " {");
          for(ExecutableElement m : interfaceMethods) {
            writer.print("  public ");
            writer.print(m.getReturnType() + " ");
            writer.print(m.getSimpleName() + " (");
            int i = 0;
            for(VariableElement parm :
              m.getParameters()) {
              writer.print(parm.getKind() + " " +
                parm.getSimpleName());
              if(++i < m.getParameters().size())
                writer.print(", ");
            }
            writer.println(");");
          }
          writer.println("}");
          writer.close();
        } catch(IOException ioe) {
          throw new RuntimeException(ioe);
        }
      }
    }
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
