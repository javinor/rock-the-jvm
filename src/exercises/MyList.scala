package exercises

import java.util.NoSuchElementException


abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def toString: String

  def filter(p: MyPredicate[A]): MyList[A]

  def map[B](t: MyTransformer[A, B]): MyList[B]

  def concat[B >: A](other: MyList[B]): MyList[B]

  def flatMap[B](f: A => MyList[B]): MyList[B]
}


class Nil extends MyList[Nothing] {
  override def head = throw new NoSuchElementException

  override def tail = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B /* :> Nothing*/ ](element: B): MyList[B] = new Cons(element, this)

  override def toString: String = "Nil"


  override def filter(p: MyPredicate[Nothing]): MyList[Nothing] = this

  override def map[B](t: MyTransformer[Nothing, B]): MyList[B] = this

  override def concat[B >: Nothing](other: MyList[B]): MyList[B] = other

  override def flatMap[B](f: Nothing => MyList[B]): MyList[B] = this

}


class Cons[+A](val head: A, val tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def toString = s"$head :: ${tail.toString}"


  override def filter(p: MyPredicate[A]): MyList[A] = {
    if (p.test(head)) new Cons(head, tail.filter(p))
    else tail.filter(p)
  }

  override def map[B](t: MyTransformer[A, B]): MyList[B] =
    new Cons(t.transform(head), tail.map(t))

  override def concat[B >: A](other: MyList[B]): MyList[B] =
    new Cons(head, tail.concat(other))

  override def flatMap[B](f: A => MyList[B]): MyList[B] =
    f(head).concat(tail.flatMap(f))

}


trait MyPredicate[-A] {
  def test(x: A): Boolean
}

trait MyTransformer[-A, B] {
  def transform(x: A): B
}


object ListTest extends App {

  val l0 = new Nil
  val l1 = l0 add 1
  val l2 = l1 add 2.2f
  val l3 = l2 add 'a'

  println(l3)
  println(l3 head)
  println(l3 tail)
  println(l3 isEmpty)

  val evenPredicate = new MyPredicate[Int] {
    override def test(x: Int): Boolean = x % 2 == 0
  }

  val batmanTransformer = new MyTransformer[Int, String] {
    override def transform(n: Int): String = s"TA${"NA" * n} BATMAN!"
  }

  val four = new Cons(4, new Cons(3, new Cons(2, new Cons(1, new Nil))))
  println(four.toString)
  println(four.filter(evenPredicate))
  println(four.map(batmanTransformer))
  println(four.flatMap(n => new Cons(n.toString * n, new Nil)))


}
