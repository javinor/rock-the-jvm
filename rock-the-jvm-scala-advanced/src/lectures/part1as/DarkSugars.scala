package lectures.part1as

import scala.util.Try

object DarkSugars extends App {

  // syntax sugar #1: methods with single param
  // ================

  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    // write some code
    42
  }

  val aTryInstance = Try { // the apply method of Try
    throw new RuntimeException
  }

  List(1, 2, 3).map { x =>
    x + 1
  }


  // syntax sugar #2: single abstract method pattern
  // ================

  trait Action {
    def act(x: Int): Int
  }

  val anInstance = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  // example: Runnables (huh !?)
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello, scala")
  })
  val aShorterThread = new Thread(() => println("hello, scala, but shorter"))

  abstract class anAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  val anAbstractInstance: anAbstractType = (a: Int) => println("sweet!")


  // syntax sugar #3: the :: and #:: methods are special
  // ================

  val prependedList = 2 :: List(3, 4)

  // the associativity of a method is determined by the last character.
  // if it ends with a : is is right associative

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // put actual implementation here
  }


  // syntax sugar #4: multi-word method naming
  // ================
  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"


  // syntax sugar #5: infix types
  // ================

  class Composite[A, B]

  val composite: Composite[Int, String] = new Composite
  val composite2: Int Composite String = new Composite

  class -->[A, B]

  val towards: Int --> String = new -->


  // syntax sugar #6: update() is very special, much like apply()
  // ================
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewritten to anArray.update(2, 7)
  // used in mutable collections


  // syntax sugar #7: setters for mutable containers
  // ================

  class Mutable {
    private var internalMember: Int = 0

    def memberqwe = internalMember

    def memberqwe_=(value: Int): Unit = internalMember = value
  }

  val aMutableContainer = new Mutable
  aMutableContainer.memberqwe = 42
  println(aMutableContainer.memberqwe)


}
