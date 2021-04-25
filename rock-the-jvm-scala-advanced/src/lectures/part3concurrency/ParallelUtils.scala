package lectures.part3concurrency

import scala.collection.parallel.immutable.ParVector

object ParallelUtils extends App {

  val parList = List(1,2,3).par

  val aParVector = ParVector[Int](4,5,6)


  def measure[T](op: => T): Long = {
    val time = System.currentTimeMillis()
    op
    System.currentTimeMillis() - time
  }

  val list = (1 to 10000000).toList

  val serialTime = measure {
    list.map(_ + 1)
  }
  println(s"serial time is $serialTime")

  val parallelTime = measure {
    list.par.map(_ + 1)
  }
  println(s"parallel time is $parallelTime")
}
