[![](https://travis-ci.org/BruceEckel/OnJava8-Examples.svg?branch=master)](https://travis-ci.org/BruceEckel/OnJava8-Examples)

To compile and run these programs, you only need JDK 8 installed. 
Invoking `gradlew` will automatically download and install Gradle.
Gradle will also install all additional libraries necessary to compile
and run the Java examples in the book.

To compile everything, the command is:

`gradlew classes`

To run everything (including compiling if necessary), the command is:

`gradlew run`

To compile only a single chapter (including dependencies), use for example:

`gradlew :strings:classes`

To run only a single chapter, say:

`gradlew :strings:run`

Gradle can also be used to run a single program. Here, we run the **ReplacingStringTokenizer.java**
program in the **strings** chapter subdirectory:

`gradlew  :strings:ReplacingStringTokenizer`

However, if the file name is unique throughout the book (the majority are), you can just give the
program name, like this:

`gradlew  ReplacingStringTokenizer`

Note that all these commands are run from the base directory where the example code is installed, and where you find the
`gradlew` script.

You can learn about other options by just typing `gradlew` with no arguments.
