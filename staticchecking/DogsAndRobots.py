#: staticchecking/DogsAndRobots.py
# ©2015 MindView LLC: see Copyright.txt

def speak(anything):
    anything.talk()

class Dog:
    def talk(self):  print("Arf!")
    def reproduce(self): pass

class Robot:
    def talk(self): print("Click!")
    def oilChange(self): pass

a = Dog()
b = Robot()

speak(a)
speak(b)
#:~
