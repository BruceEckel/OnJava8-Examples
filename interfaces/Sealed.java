// interfaces/Sealed.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 17

sealed class Base permits D1, D2 {}

final class D1 extends Base {}
final class D2 extends Base {}
// Illegal:
// final class D3 extends Base {}
