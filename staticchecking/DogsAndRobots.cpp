// staticchecking/DogsAndRobots.cpp
// ©2015 MindView LLC: see Copyright.txt
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
