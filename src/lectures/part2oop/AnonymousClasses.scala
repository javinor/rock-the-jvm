package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahhahahahhh")
  }

  /*
  equivalent to:

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("ahahhahahahhh")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  funnyAnimal.eat
  println(funnyAnimal.getClass)
  println(funnyAnimal.hashCode)


  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, what can I do for you?")
  }
}
