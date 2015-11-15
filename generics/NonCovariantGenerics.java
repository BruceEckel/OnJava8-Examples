// generics/NonCovariantGenerics.java
// ©2016 MindView LLC: see Copyright.txt
// {CompileTimeError} (Won't compile)
import java.util.*;

public class NonCovariantGenerics {
  // Compile Error: incompatible types:
  List<Fruit> flist = new ArrayList<Apple>();
}
