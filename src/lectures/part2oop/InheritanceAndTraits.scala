package lectures.part2oop

object InheritanceAndTraits extends App {

  class Animal {
    val creatureType = "wild animal"

    def eat = println("nomnom")

    private def privateEat = println("private nomnom")

    protected def protectedEat = println("protected nomnom")
  }

  // single class inheritance
  class Cat extends Animal {
    def crunch = {
      protectedEat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat
  //  cat.privateEat // doesn't have access
  //  cat.protectedEat // doesn't have access
  cat.crunch


  //  Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)


  // Overriding
  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType: String = "domesticated"
    override def eat = println("crunch, crunch")

    def superEat = super.eat

  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // goes to the most overriden version of eat - "crunch, crunch" on Dog

  // super
  dog.superEat


  /*
    Preventing overrides
    1. final on a method or field
    2. final on the class
    3. seal the class "sealed" - extend in this file, but prevent in other files
   */

}
