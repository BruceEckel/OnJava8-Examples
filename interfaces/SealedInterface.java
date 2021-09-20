// interfaces/SealedInterface.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 17

sealed interface Ifc permits Imp1, Imp2 {}
final class Imp1 implements Ifc {}
final class Imp2 implements Ifc {}

sealed abstract class AC permits X {}
final class X extends AC {}
