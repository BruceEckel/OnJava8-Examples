// collections/FinalFields.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16
import java.util.*;

record FinalFields(int i) {
  int timesTen() { return i * 10; }
  // void tryToChange() { i++; } // Error:
  // cannot assign a value to final variable i
}
