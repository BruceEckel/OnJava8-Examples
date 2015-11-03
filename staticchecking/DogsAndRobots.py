# staticchecking/DogsAndRobots.py

def speak(anything):
    anything.talk()

class Dog:
    def talk(self):  System.out.println("Arf!")
    def reproduce(self): pass

class Robot:
    def talk(self): System.out.println("Click!")
    def oilChange(self): pass

a = Dog()
b = Robot()

speak(a)
speak(b)
