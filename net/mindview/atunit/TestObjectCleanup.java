//: net/mindview/atunit/TestObjectCleanup.java
// ©2015 MindView LLC: see Copyright.txt
// The @Unit @TestObjectCleanup tag.
package net.mindview.atunit;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCleanup {} ///:~
