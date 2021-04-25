package lectures.part4implicits

object OrganizingImplicits extends App {

  implicit def reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)

  //  implicit val normalOrdering: Ordering[Int] = Ordering.fromLessThan(_ < _)
  println(List(1, 4, 2, 5, 3).sorted)

  /*
    Implicits:
      - val/var
      - object
      - accessor methods (defs with no parens)
   */

  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  //  implicit val nameOrdering : Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) < 0)
  //  println(persons.sorted)


  /*
    Implicit scope
      - normal scope: Local Scope
      - imported scope
      - companion objects of all types involved in the method signature
   */

  //  This will not work!
  //  object SomeObject {
  //    implicit val nameOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) < 0)
  //  }


  // Best practice for implicit val (1)
  //   - if there is a single possible value for it
  //   - you can edit the code for the type
  //   ==> define the implicit in the companion
  object Person {
    implicit val nameOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) < 0)
  }

  println(persons.sorted)

  // Best practice for implicit val (2)
  //   - if there are many possible values for it
  //   - you can edit the code for the type
  //   ==> define the best implicit in the companion, and package other implementation separately locally or in other objects
  {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.age < p2.age)

    println(persons.sorted)
  }
  // or
  {
    object ReverseAlphabeticOrdering {
      implicit val reverseAlphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) > 0)
    }

    object AgeOrdering {
      implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.age < p2.age)
    }

    import ReverseAlphabeticOrdering._
    println(persons.sorted)
  }

}
