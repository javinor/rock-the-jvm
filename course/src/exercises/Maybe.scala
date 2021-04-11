package exercises

abstract class Maybe[+A] {
  def map[B](f: A => B): Maybe[B]

  def flatMap[B](f: A => Maybe[B]): Maybe[B]

  def filter[B >: A](f: B => Boolean): Maybe[A]
}

case object Nothing extends Maybe[Nothing] {
  override def map[B](f: Nothing => B): Maybe[B] = this

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = this

  override def filter[B](f: B => Boolean): Maybe[Nothing] = this
}

case class Just[+A](value: A) extends Maybe[A] {
  override def map[B](f: A => B): Maybe[B] = Just(f(value))

  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(value)

  override def filter[B >: A](f: B => Boolean): Maybe[A] = if (f(value)) this else Nothing
}

object TestMaybe extends App {
  val just3 = Just(3)
  println(just3)
  println(just3.map(_ + 1))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(_ % 2 == 1))
  println(just3.filter(_ % 2 == 0))


}
