// hiding/foreign/Foreign.java
// {CompileTimeError} (Won't compile)
package hiding.foreign;
import hiding.local.*;

public class Foreign {
   public static void main(String[] args) {
      PackagedClass pc = new PackagedClass();
   }
}
