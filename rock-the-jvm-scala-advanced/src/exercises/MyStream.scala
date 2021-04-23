package exercises

abstract class MyStream[+A] {
  def isEmpty: Boolean

  def head: A

  def tail: MyStream[A]

  def #::[B >: A](elem: B): MyStream[B] // prepend
  def ++[B >: A](other: => MyStream[B]): MyStream[B]

  def foreach(f: A => Unit): Unit

  def map[B](f: A => B): MyStream[B]

  def flatMap[B](f: A => MyStream[B]): MyStream[B]

  def filter(f: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A]

  def takeAsList(n: Int): List[A]
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons(start, from(generator(start))(generator))
}

object EmptyStream extends MyStream[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyStream[Nothing] = throw new NoSuchElementException

  override def #::[B >: Nothing](elem: B): MyStream[B] = new Cons(elem, this)

  override def ++[B >: Nothing](other: => MyStream[B]): MyStream[B] = other

  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = this

  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this

  override def filter(f: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this

  override def takeAsList(n: Int): List[Nothing] = Nil
}

class Cons[+A](hd: A, tl: => MyStream[A]) extends MyStream[A] {
  override def isEmpty: Boolean = false

  override val head: A = hd
  override lazy val tail: MyStream[A] = tl

  override def #::[B >: A](elem: B): MyStream[B] = new Cons(elem, this)

  override def ++[B >: A](other: => MyStream[B]): MyStream[B] = new Cons(hd, tail ++ other)

  override def foreach(f: A => Unit): Unit = {
    f(hd)
    tail.foreach(f)
  }

  override def map[B](f: A => B): MyStream[B] = new Cons(f(hd), tail.map(f))

  override def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(hd) ++ tail.flatMap(f)

  override def filter(f: A => Boolean): MyStream[A] =
    if (f(hd)) new Cons(hd, tail.filter(f))
    else tail.filter(f)

  override def take(n: Int): MyStream[A] =
    if (n <= 0) EmptyStream
    else new Cons(hd, tail.take(n - 1))

  override def takeAsList(n: Int): List[A] =
    if (n <= 0) Nil
    else hd :: tail.takeAsList(n - 1)


}

object StreamPlayground extends App {
  val nums = 1 #:: 2 #:: 3 #:: EmptyStream
  nums.foreach(println)
  nums.map(_ * 2).foreach(println)

  val naturals = MyStream.from(0)(_ + 1)
  val evens = naturals.map(_ * 2)
  println(evens.takeAsList(10))

  naturals.take(10).foreach(print)
  println

  naturals.take(10000).foreach(print)
  println


  naturals.filter(_ % 2 != 0).take(10).foreach(print)
  println

  naturals.flatMap(x => x #:: (-x) #:: EmptyStream).take(10).foreach(print)
  println


  // exercises
  def fibo(first: Int, second: Int): MyStream[Int] = {
    new Cons(first, fibo(second, first + second))
  }
  fibo(0, 1).take(20).foreach(println)



  val fromTwo = MyStream.from(2)(_ + 1)

  def eratosthenes(stream: MyStream[Int]) : MyStream[Int] = {
    new Cons(stream.head, eratosthenes(stream.tail.filter(_ % stream.head != 0)))
  }

  val primes = eratosthenes(fromTwo)
  primes.take(1000).foreach(println)

}
