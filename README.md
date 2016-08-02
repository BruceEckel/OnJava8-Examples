To compile and run these programs, you only need JDK 8 installed. 
Invoking `gradlew` will automatically download and install Gradle.
Gradle will also install all additional libraries necessary to compile
and run the Java examples in the book.

To compile everything, the command is:

`gradlew build`

To run everything (including compiling if necessary), the command is:

`gradlew run`

To compile only a single chapter (including dependencies), use for example:

`gradlew :strings:build`

To run only a single chapter, say:

`gradlew :strings:run`
