package lectures.part2oop

// Scala Applications = Scala object with
//   def main(args: Array[String]): Unit

object Objects { // <-- no "extends App" since we defined the main method ourselves

  // Scala does not have class level functionality - no statics

  object Person {
    // "static/class" level functionality
    val N_EYES = 2

    def canFly = false


    // Factory method - purpose is to build Persons
    def from(mother: Person, father: Person) = new Person("Bobbie")

    // Factory methods are often named "apply" to Person can be called like a function
    def apply(mother: Person, father: Person) = new Person("Bobbie")
  }

  class Person(name: String) {
    // instance level functionality

  }

  // When classes and objects are together they are called Companions

  println(Person.N_EYES)
  println(Person.canFly)

  def main(args: Array[String]): Unit = {
    // Scala object is a Singleton Instance
    val oMary = Person
    val oJohn = Person

    val mary = new Person("Mary") // Hah! Go-to-def sends us to Person from OOBasics! if class Person is not defined here
    val john = new Person("John")

    println(oMary == oJohn)
    println(oMary)
    println(oJohn)

    println(mary == john)
    println(mary)
    println(john)

    object Avocado

    //  val avocadoRick = new Avocado  // doesn't work!


    val bobbie = Person.from(mary, john)
    val bobbie2 = Person(mary, john)


  }

}
