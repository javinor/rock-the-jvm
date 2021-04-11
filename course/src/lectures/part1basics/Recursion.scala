package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  def anotherFactorial(n: Int): BigInt = {
    def go(n: Int, acc: BigInt): BigInt = {
      if (n <= 1) acc
      else go(n - 1, acc * n)
    }

    go(n, 1)
  }

  println(anotherFactorial(20000))


  /*
    1. Concat a string n times
    2. IsPrime
    3. Fibo
   */

  def repeat(str: String, n: Int): String = {
    @tailrec
    def go(str: String, n: Int, acc: String): String = {
      if (n <= 0) acc
      else go(str, n - 1, acc + str)
    }

    go(str, n, "")
  }

  println("TA" + repeat("NA", 8) + " BATMAN!")


  def isPrime(n: Int): Boolean = {
    @tailrec
    def go(factor: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (factor <= 1) true
      else go(factor - 1, n % factor != 0 && isStillPrime)
    }

    go(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(13 * 17))


  def fibo(n: Int): BigInt = {
    @tailrec
    def go(a: BigInt, b: BigInt, i: Int): BigInt = {
      if (i >= n) b
      else go(b, a + b, i + 1)
    }

    go(0, 1, 1)
  }

  println(fibo(1))
  println(fibo(2))
  println(fibo(3))
  println(fibo(4))
  println(fibo(5))
  println(fibo(6))
  println(fibo(7))
}
