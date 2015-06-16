//: com/mindviewinc/atunit/TestProperty.java
// ©2015 MindView LLC: see Copyright.txt
// The @Unit @TestProperty tag.
package com.mindviewinc.atunit;
import java.lang.annotation.*;

// Both fields and methods can be tagged as properties:
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestProperty {} ///:~
