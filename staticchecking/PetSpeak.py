# staticchecking/PetSpeak.py
# ©2016 MindView LLC: see Copyright.txt
# We make no guarantees that this code is fit for any purpose.
# Visit http://mindviewinc.com/Books/OnJava/ for more book information.
# Speaking pets in Python

class Pet:
    def speak(self): pass

class Cat(Pet):
    def speak(self):
       System.out.println("meow!")

class Dog(Pet):
    def speak(self):
       System.out.println("woof!")

def command(pet):
    pet.speak()

pets = [ Cat(), Dog() ]
for pet in pets:
    command(pet)
