// staticchecking/DogsAndRobots.cpp
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
#include <iostream>
using namespace std;

class Dog {
public:
  void talk() { cout << "Arf!" << endl; }
  void reproduce() {}
};

class Robot {
public:
  void talk() { cout << "Click!" << endl; }
  void oilChange() {}
};

template<class T> void speak(T speaker) {
  speaker.talk();
}

int main() {
  Dog d;
  Robot r;
  speak(d);
  speak(r);
}
/* Output:
Arf!
Click!
*/
