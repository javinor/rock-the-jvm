package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ aSequence)
  println((aSequence ++ aSequence) sorted)

  val aRange : Seq[Int] = 1 to 10
  val anExclusiveRange : Seq[Int] = 1 until 10
  anExclusiveRange.foreach(println)


  // List
  val aList = List(1,2,3,4)
  val prepended = 42 :: aList
  val sandwich = 98 +: aList :+ 89
  println(prepended)
  println(sandwich)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(apples5.mkString("-|-"))


  // Array
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(numbers)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0
  numbers.update(3, 17)
  println(numbers.mkString("-"))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)


  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)


  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]) : Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()

      collection.updated(r.nextInt(maxCapacity), r.nextInt())

      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = numbersList.toVector
  val numbersVector2 = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
  println(getWriteTime(numbersVector2)) // Why is the second vector so much faster? even when swapping the vector invocations?

}
