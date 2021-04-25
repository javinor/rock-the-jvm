package lectures.part4implicits

import scala.annotation.tailrec

object PimpMyLibrary extends App {

  class RichInt(val value: Int) {
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)
  }

  val num = new RichInt(144)
  println(num.isEven)
  println(num.sqrt)


  implicit class RicherInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)
  }

  println(36.isEven)
  println(36.sqrt)


  // compiler doesn't search
  implicit class RichestInt(richerInt: RicherInt) {
    def isOdd: Boolean = richerInt.value % 2 != 0
  }

  //  println(11.isOdd) // <-- doesn't check for two levels of implicit
  println(new RicherInt(36).isOdd)


  // Exercises
  // =========
  implicit class Exercise(val str: String) extends AnyVal {
    def asInt: Int = str.toInt

    def encrypt(key: Int): String =
      str
        .map(c => {
          val newChar = (c + key) // TODO wrap around z/Z
          newChar.toChar
        })
  }

  println("123".asInt)
  println("John".encrypt(2))


  implicit class BetterInt(val value: Int) extends AnyVal {
    def times(f: () => Unit): Unit = (1 to value).foreach(_ => f())

    def *[T](list: List[T]): List[T] = {
      @tailrec
      def go[T](n: Int, list: List[T], acc: List[T]): List[T] = {
        if (n <= 0) acc
        else go(n - 1, list, list ++ acc)
      }

      go(value, list, Nil)
    }
  }

  3.times(() => println("in times"))
  println(3 * List(1, 2))


  // equivalent to: implicit class RichAltInt(value: Int)
  class RichAltInt(value: Int)

  implicit def enrichInt(value: Int): RichAltInt = new RichAltInt(value)


  // danger zone
  implicit def intToBoolean(i: Int): Boolean = i == 1

  /*
    if (n) do something
    else do something else
   */
  val aConditionedValue = if (3) "OK" else "Something wrong"
  println(aConditionedValue) // very hard to trace back to implicit methods



}
