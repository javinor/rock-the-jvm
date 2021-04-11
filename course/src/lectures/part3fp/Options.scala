package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)


  def unsafeMethod(): String = null

  val result = Option(unsafeMethod())
  println(result)


  // chained methods
  def backupMethod(): String = "a Valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)


  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("a valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)


  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE
  println(myFirstOption.map(_ + 1))
  println(myFirstOption.filter(_ % 2 == 0))
  println(myFirstOption.filter(_ % 2 != 0))

  val safeDiv = (numer: Int, denom: Int) => if (denom == 0) None else Option(numer / denom)
  println(myFirstOption.flatMap(safeDiv(_, 2)))
  println(myFirstOption.flatMap(safeDiv(_, 0)))


  // Exercise
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "This is the connection message..."
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  def attemptConnection(config: Map[String, String]) = {
    for {
      host <- config.get("host")
      port <- config.get("port")
      conn <- Connection(host, port)
    } yield conn.connect
  }

  println("Good config: " + attemptConnection(config))
  println("Bad Config: " + attemptConnection(Map()))

}
