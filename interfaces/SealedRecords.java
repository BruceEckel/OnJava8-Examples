// interfaces/SealedRecords.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 17

sealed interface Employee
  permits CLevel, Programmer {}
record CLevel(String type)
  implements Employee {}
record Programmer(String experience)
  implements Employee {}
