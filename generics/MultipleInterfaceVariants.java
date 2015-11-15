// generics/MultipleInterfaceVariants.java
// ©2016 MindView LLC: see Copyright.txt
// {CompileTimeError} (Won't compile)

interface Payable<T> {}

class Employee implements Payable<Employee> {}
class Hourly extends Employee
  implements Payable<Hourly> {}
