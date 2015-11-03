// com/mindviewinc/atunit/Test.java
// The @Test tag.
package com.mindviewinc.atunit;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {}
