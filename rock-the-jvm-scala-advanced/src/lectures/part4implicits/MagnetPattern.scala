package lectures.part4implicits

import scala.concurrent.Future

object MagnetPattern extends App {

  // method overloading

  class P2PRequest

  class P2PResponse

  class Serializer[T]

  trait Actor {
    def receive(statusCode: Int): Int

    def receive(request: P2PRequest): Int

    def receive(response: P2PResponse): Int

    def receive[T](message: T)(implicit serializer: Serializer[T]): Int

    def receive[T: Serializer](message: T, statusCode: Int): Int

    def receive(future: Future[P2PRequest]): Int

    // def receive(future: Future[P2PResponse]): Int // <-- won't compile because Java erases generic type at runtime

    // ... other overloads
  }




  /// Exercise
  class Handler {
    def handle(s : => String) = {
      println(s)
      println(s)
    }
  }

  trait HandleMagnet {
    def apply(): Unit
  }

  def handle(magnet: HandleMagnet) = magnet()

  implicit class StringHandle(s: => String) extends HandleMagnet {
    override def apply(): Unit = {
      println(s)
      println(s)

    }
  }

  def sideEffectMethod(): String = {
    println("this is a sideEffect")
    "avocado"
  }

  handle(sideEffectMethod())
  handle {
    println("this is a another sideEffect")
    "mango"
  }
}
