// staticchecking/dogsandrobots.go
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package main
import "fmt"

type Dog struct {}
func (this Dog) talk() { fmt.Printf("woof!\n")}
func (this Dog) reproduce() {}

type Robot struct {}
func (this Robot) talk() { fmt.Printf("Click!\n") }
func (this Robot) oilChange() {}

func speak(speaker interface { talk() }) {
  speaker.talk();
}

func main() {
  speak(Dog{})
  speak(Robot{})
}
