package lectures.part2oop

object AbstractDataTypes extends App {


  // abstract
  abstract class Animal {
    val creatureType: String = "wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunchity-crunch")
  }


  //  traits
  trait Carnivore {
    def eat(animal: Animal): Unit

    def preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded


  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    def eat: Unit = println("snap snap snap")

    def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I'm eating a ${animal.creatureType}")

  }

  val carn = new Carnivore {
    override def eat(animal: Animal): Unit = println("anonymous eating")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc eat dog


  /*
    Traits vs abstract classes
    1. Traits do not have constructor parameters
    2. Multiple traits may be inherited by the same class
    3. traits are behavior, classes are "things"
   */


}
