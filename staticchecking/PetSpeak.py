# staticchecking/PetSpeak.py
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
