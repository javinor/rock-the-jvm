package lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"the only element is $head")
    case _ =>
  }


  class Person(val name: String, val age: Int)

  object PersonPattern {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) Some((person.name, person.age))
      else None
  }

  object AnotherPerson {
    def unapply(person: Person): Option[String] = Some(person.name)
  }


  val bob = new Person("bob", 25)
  val greeting: String = bob match {
    case PersonPattern(name, age) => s"Hi! my name is $name and I am $age years old"
    case _ => s"I'm not Bob!"
  }
  println(greeting)

  val greeting2 = bob match {
    case AnotherPerson("mary") => s"Hi! I'm mary"
    case AnotherPerson("bob") => s"Hi! I'm bob"
  }
  println(greeting2)


  /*
    Exercise
   */
  object Even {
    def unapply(x: Int) = if (x % 2 == 0) Some(x) else None
  }

  object SingleDigit {
    def unapply(x: Int) = if (x < 10 && x >= 0) Some(x) else None
  }

  def mathProperty(x: Int) = x match {
    case SingleDigit(x) => s"$x has a single digit"
    case Even(x) => s"$x is an even number"
    case _ => "no property"
  }

  println(mathProperty(42))
  println(mathProperty(9))
  println(mathProperty(15))


  // this works with Boolean as well. Such cases are commonly written as lower case:
  object even_ {
    def unapply(x: Int) = x % 2 == 0
  }

  object singleDigit_ {
    def unapply(x: Int) = 0 <= x && x < 10
  }

  def mathProperty2(x: Int) = x match {
    case even_() => "even number"
    case singleDigit_() => "single digit!"
  }

  println(mathProperty2(42))
  println(mathProperty2(9))


  // Infix patterns
  // ==============

  case class Or[A, B](a: A, b: B)

  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDescription)


  // decomposing sequences
  // =====================
  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
  }
  println(vararg)

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq())
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList = Cons(3, Cons(2, Cons(1, Empty)))
  myList match {
    case MyList(3, 2, _*) => println("starting with 3, 2, ...")
  }


  // custom return types for unapply
  // - isEmpty
  // - get
  abstract class Wrapper[T] {
    def isEmpty: Boolean

    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This person's name is $n"
  })


}
