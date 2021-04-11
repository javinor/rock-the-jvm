package lectures.part4pm

import exercises.{Cons, MyList, Nil}

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "a singleton object"
  }

  println(constants)


  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => "matches on anything"
  }

  // 2.2 variables
  val matchAVariable = x match {
    case something => s"I've found a $something"
  }


  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) => "one and one"
    case (x, 2) => s"$x was the pair of 2"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"found a nested $v"
  }


  // 4 - case classes - data constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Nil))
  val matchAList = aList match {
    case Nil => "empty!"
    case Cons(1, _) => "list begins with 1!"
    case _ => "something else"
  }
  println(matchAList)


  // 5 - List patterns
  val aStandardList = List(1, 2, 3, 42)
  val matchStandardList = aStandardList match {
    case List(1, _, _, _) => "List begins with 1" // List is an extractor
    case List(1, _*) => "arbitrary length of list" // vararg pattern
    case 1 :: List(_) => "infix pattern"
    case List(1, 2, 3) :+ 42 => "also an infix pattern"
  }


  // 6 - type specifiers
  val unknown: Any = 2
  val matchUnknown = unknown match {
    case list: List[Int] => "this is a list due to the type specifier"
    case _ => "didn't match"
  }


  // 7 - name binding
  val matchNameBinding = aList match {
    case nonEmptyList@Cons(_, _) => "got a name for the entire match - as-pattern in Haskell"
    case Cons(1, rest@Cons(2, _)) => "tail, if it starts with 2"
  }


  // 8 - multi-patterns
  val multipattern = aList match {
    case Nil | Cons(0, _) => "both patterns will return the same expression"
    case _ => "didn't match"
  }


  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "using a guard"
  }

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings : List[String] => "a list of strings"
    case listOfInts : List[Int] => "a list of ints"
    case _ => "anything else"
  }

  println(numbersMatch) // JVM trick question - cannot match on generic types - due to type erasure
}
