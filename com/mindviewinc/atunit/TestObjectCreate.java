// com/mindviewinc/atunit/TestObjectCreate.java
// The @Unit @TestObjectCreate tag.
package com.mindviewinc.atunit;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCreate {}
