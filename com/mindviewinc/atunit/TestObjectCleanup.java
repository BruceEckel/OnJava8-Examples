//: com/mindviewinc/atunit/TestObjectCleanup.java
// ©2015 MindView LLC: see Copyright.txt
// The @Unit @TestObjectCleanup tag.
package com.mindviewinc.atunit;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCleanup {} ///:~
