#: staticchecking/PetSpeak.py
# ©2015 MindView LLC: see Copyright.txt
# Speaking pets in Python

class Pet:
    def speak(self): pass

class Cat(Pet):
    def speak(self):
       print("meow!")

class Dog(Pet):
    def speak(self):
       print("woof!")

def command(pet):
    pet.speak()

pets = [ Cat(), Dog() ]
for pet in pets:
    command(pet)
#:~
