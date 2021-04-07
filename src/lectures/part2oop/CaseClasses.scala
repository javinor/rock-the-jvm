package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 34)

  // 1. class parameters are fields
  println(jim.name)
  println(jim.age)

  // 2. sensible toString
  println(jim.toString)
  println(jim) // ~ println(jim.toString)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. Companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)
  val rick = Person(age = 435, name = "Rick")
  println(mary)
  println(rick)

  // 6. serializable - how to show this?

  // 7. extractor patterns - can be used in pattern matching


}
