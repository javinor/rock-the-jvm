package lectures.part2afp

object Monads extends App {

  trait Attempt[+A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }

  object Attempt {
    def apply[A](a: => A): Attempt[A] = {
      try {
        Success(a)
      } catch {
        case e: Throwable => Fail(e)
      }
    }
  }

  case class Success[+A](value: A) extends Attempt[A] {
    override def flatMap[B](f: A => Attempt[B]): Attempt[B] = {
      try {
        f(value)
      } catch {
        case e: Throwable => Fail(e)
      }
    }
  }

  case class Fail(e: Throwable) extends Attempt[Nothing] {
    override def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
  }


  object Lazy {
    def apply[A](a: => A): Lazy[A] = new Lazy(a)
  }

  class Lazy[A](a: => A) {
    private lazy val value = a

    def use: A = value

    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(value)
  }

  val lazyInstance = Lazy {
    println("I'm lazy...")
    42
  }

  def f(x: => Int) = {
    Lazy[Int] {
      println(s"I'm doubling $x")
      x * 2
    }
  }

  val result = lazyInstance.flatMap(f)
  println("evaluating lazyInstance:")
  println(result.use)
}
