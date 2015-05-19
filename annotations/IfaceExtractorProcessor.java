//: annotations/IfaceExtractorProcessor.java
// javac-based annotation processing.
package annotations;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;
import java.io.*;

@SupportedAnnotationTypes(
  "annotations.ExtractInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class IfaceExtractorProcessor
extends AbstractProcessor {
  private ArrayList<Element>
    interfaceMethods = new ArrayList<>();
  private ProcessingEnvironment processingEnv;
  @Override public void
  init(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
  }
  @Override public boolean
  process(Set<? extends TypeElement> annotations,
  RoundEnvironment env) {
    for(Element elem :
      env.getElementsAnnotatedWith(ExtractInterface.class)) {
      if(elem.getModifiers().contains(Modifier.PUBLIC) &&
         !(elem.getModifiers().contains(Modifier.STATIC)))
        interfaceMethods.add(elem);
      if(interfaceMethods.size() > 0) {
        for(Element el : interfaceMethods)
          System.out.println(el.getKind() +
            " : " + el.getModifiers() +
            " : " + el.getSimpleName() +
            " : " + el.asType());
        try {
          try(Writer writer =
            processingEnv.getFiler()
            .createSourceFile("Filename.txt")
            .openWriter()) {
  /*          writer.write("package " +
                    typeDecl.getPackage().getQualifiedName() +";");
            writer.write("public interface " +
                    annot.value() + " {");
            for(MethodDeclaration m : interfaceMethods) {
              writer.print("  public ");
              writer.print(m.getReturnType() + " ");
              writer.print(m.getSimpleName() + " (");
              int i = 0;
              for(ParameterDeclaration parm :
                      m.getParameters()) {
                writer.print(parm.getType() + " " +
                        parm.getSimpleName());
                if(++i < m.getParameters().size())
                  writer.print(", ");
              }
              writer.write(");");
            }
  */          writer.write("}");
          }
        } catch(Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
    return false;
  }
} ///:~
