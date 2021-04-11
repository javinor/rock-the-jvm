package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)


  def unsafeMethod(): String = throw new RuntimeException("No String for you! ha, ha, ha!")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)


  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }


  // utilities
  println(potentialFailure.isSuccess)

  def backMethod(): String = "a valid result"

  val fallbackTry = Try(unsafeMethod()) orElse Try(backMethod())
  println(fallbackTry)


  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("a valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 2)))
  println(aSuccess.filter(_ > 10))


  // Exercise
  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted...")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String) = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("someone else took the port")
    }
  }

  def callRenderHTML(host: String, port: String) = {
    val response = for {
      conn <- Try(HttpService.getConnection(host, port))
      html <- Try(conn.get(s"https://$host:$port/index.html"))
    } yield html

    println(response)
  }

  callRenderHTML(hostname, port)


}
