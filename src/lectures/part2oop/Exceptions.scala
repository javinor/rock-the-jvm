package lectures.part2oop

import lectures.part2oop.Generics.MyList

object Exceptions extends App {

  val x: String = null
  //  println(x.length) // throws NullPointerException


  // 1. throwing exceptions
  //  val aWeirdValue : String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes


  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you, one year!")
    else 42

  val potentialFail = try {
    getInt(false)
  } catch {
    //    case e: RuntimeException => println("caught a Runtime exception")
    case e: NullPointerException => 17
  } finally {
    println("finally")
  }

  println(potentialFail)


  // 3. defining exceptions
  class MyException extends Exception

  val exception = new MyException
  //  throw exception


  //////////////////////////////////////////////
  // Exercises

  //  val oom = Array.ofDim(Int.MaxValue)

  def crashWithSO: Int = 1 + crashWithSO

  //  crashWithSO

  class OverflowException extends Exception

  class UnderflowException extends Exception

  class MathCalculationException extends Exception


  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def sub(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def mul(x: Int, y: Int): Int = {
      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def div(x: Int, y: Int): Int =
      if (y == 0)
        throw new MathCalculationException
      else
        x / y

  }

}
