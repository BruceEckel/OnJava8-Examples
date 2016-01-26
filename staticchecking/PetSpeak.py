# staticchecking/PetSpeak.py
# (c)2016 MindView LLC: see Copyright.txt
# We make no guarantees that this code is fit for any purpose.
# Visit http://mindviewinc.com/Books/OnJava/ for more book information.
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

pets = [ Cat(), Dog() ] # (1)
for pet in pets: # (2)
    command(pet)

output = """
meow!
woof!
"""
