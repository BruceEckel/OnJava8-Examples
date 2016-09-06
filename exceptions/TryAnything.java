// exceptions/TryAnything.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {CompileTimeError} (Will not compile)

class Anything {}

public class TryAnything {
  public static void main(String[] args) {
    try(
      Anything a = new Anything()
    ) {
    }
  }
}
