# generics/DogsAndRobots.py
# ©2016 MindView LLC: see Copyright.txt

class Dog:
    def speak(self):
        System.out.println("Arf!")
    def sit(self):
        System.out.println("Sitting")
    def reproduce(self):
        pass

class Robot:
    def speak(self):
        System.out.println("Click!")
    def sit(self):
        System.out.println("Clank!")
    def oilChange(self):
        pass

def perform(anything):
    anything.speak()
    anything.sit()

a = Dog()
b = Robot()
perform(a)
perform(b)
