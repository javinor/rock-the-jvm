package exercises

import java.util.NoSuchElementException

import scala.annotation.tailrec


abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def toString: String

  def filter[B >: A](pred: B => Boolean /*Function1[B, Boolean]*/): MyList[A]

  def map[B](t: A => B): MyList[B]

  def concat[B >: A](other: MyList[B]): MyList[B]

  def flatMap[B](t: A => MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(f: (B, A) => B): B
}


case object Nil extends MyList[Nothing] {
  override def head = throw new NoSuchElementException

  override def tail = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B /* :> Nothing*/ ](element: B): MyList[B] = Cons(element, this)

  override def toString: String = "Nil"


  override def filter[B /*>: Nothing*/ ](pred: B => Boolean): MyList[Nothing] = this

  override def map[B](t: Nothing => B): MyList[B] = this

  override def concat[B >: Nothing](other: MyList[B]): MyList[B] = other

  override def flatMap[B](t: Nothing => MyList[B]): MyList[B] = this

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = this

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = this

  override def fold[B](start: B)(f: (B, Nothing) => B): B = start
}


case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = Cons(element, this)

  override def toString = s"$head :: ${tail.toString}"


  override def filter[B >: A](pred: B => Boolean): MyList[A] = {
    if (pred(head)) Cons(head, tail.filter(pred))
    else tail.filter(pred)
  }

  override def map[B](t: A => B): MyList[B] =
    Cons(t(head), tail.map(t))

  override def concat[B >: A](other: MyList[B]): MyList[B] =
    Cons(head, tail.concat(other))

  override def flatMap[B](t: A => MyList[B]): MyList[B] =
    t(head).concat(tail.flatMap(t))

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    @tailrec
    def pour(xs: MyList[A], ys: MyList[A]): MyList[A] =
      if (xs.isEmpty) ys
      else pour(xs.tail, Cons(xs.head, ys))

    @tailrec
    def insert(x: A, sorted: MyList[A], reversedInit: MyList[A] = Nil): MyList[A] =
      if (sorted.isEmpty) pour(reversedInit, Cons(x, Nil))
      else if (compare(x, sorted.head) < 0) pour(reversedInit, Cons(x, sorted))
      else insert(x, sorted.tail, Cons(sorted.head, reversedInit))

    val sortedTail = tail.sort(compare)
    insert(head, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    Cons(zip(head, list.head), tail.zipWith(list.tail, zip))

  override def fold[B](start: B)(f: (B, A) => B): B = tail.fold(f(start, head))(f)
}

object ListTest extends App {

  val l0 = Nil
  val l1 = l0 add 1
  val l2 = l1 add 2.2f
  val l3 = l2 add 'a'

  println(l3)
  println(l3 head)
  println(l3 tail)
  println(l3 isEmpty)


  val four = Cons(4, Cons(3, Cons(2, Cons(1, Nil))))
  println(four.toString)
  println(four.filter(_ % 2 == 0))
  println(four.map(x => s"TA${"NA" * x} BATMAN!"))
  println(four.flatMap(x => Cons(x.toString * x, Cons((x * 2).toString * x * 2, Nil))))


  val anotherL1 = Cons(1, Nil)
  println(l1 == anotherL1)


  four.foreach(println)
  println(four.sort(_ - _))
  println(four.zipWith(four, (x: Int, y: Int) => x + y))
  println(four.fold(0)((x: Int, y: Int) => x + y))

}
