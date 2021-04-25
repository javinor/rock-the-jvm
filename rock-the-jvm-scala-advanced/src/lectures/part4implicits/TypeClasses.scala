package lectures.part4implicits

object TypeClasses extends App {

  case class Person(name: String, age: Int)

  trait Equal[T] {
    def ==(lhs: T, rhs: T): Boolean
  }

  implicit object NameEq extends Equal[Person] {
    override def ==(lhs: Person, rhs: Person): Boolean = lhs.name == rhs.name
  }

  object FullEq extends Equal[Person] {
    override def ==(lhs: Person, rhs: Person): Boolean = lhs.name == rhs.name && lhs.age == rhs.age
  }


  object ImplicitEquals {
    def apply[T](a: T, b: T)(implicit eq: Equal[T]): Boolean = {
      eq.==(a, b)
    }
  }

  val john = Person("John", 35)
  val anotherJohn = Person("John", 46)
  println(ImplicitEquals(john, anotherJohn))

}
