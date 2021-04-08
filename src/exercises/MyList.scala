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

  def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B]
}


case object Nil extends MyList[Nothing] {
  override def head = throw new NoSuchElementException

  override def tail = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B /* :> Nothing*/ ](element: B): MyList[B] = Cons(element, this)

  override def toString: String = "Nil"


  override def filter(p: MyPredicate[Nothing]): MyList[Nothing] = this

  override def map[B](t: MyTransformer[Nothing, B]): MyList[B] = this

  override def concat[B >: Nothing](other: MyList[B]): MyList[B] = other

  override def flatMap[B](t: MyTransformer[Nothing, MyList[B]]): MyList[B] = this

}


case class Cons[+A](val head: A, val tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = Cons(element, this)

  override def toString = s"$head :: ${tail.toString}"


  override def filter(p: MyPredicate[A]): MyList[A] = {
    if (p.test(head)) Cons(head, tail.filter(p))
    else tail.filter(p)
  }

  override def map[B](t: MyTransformer[A, B]): MyList[B] =
    Cons(t.transform(head), tail.map(t))

  override def concat[B >: A](other: MyList[B]): MyList[B] =
    Cons(head, tail.concat(other))

  override def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B] =
    t.transform(head).concat(tail.flatMap(t))

}


trait MyPredicate[-A] {
  def test(x: A): Boolean
}

trait MyTransformer[-A, B] {
  def transform(x: A): B
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

  val evenPredicate = new MyPredicate[Int] {
    override def test(x: Int): Boolean = x % 2 == 0
  }
  println(four.filter(evenPredicate))

  val batmanTransformer = new MyTransformer[Int, String] {
    override def transform(n: Int): String = s"TA${"NA" * n} BATMAN!"
  }
  println(four.map(batmanTransformer))

  val stringListTransformer = new MyTransformer[Int, MyList[String]] {
    override def transform(x: Int): MyList[String] = Cons(x.toString * x, Cons((x * 2).toString * x * 2, Nil))
  }
  println(four.flatMap(stringListTransformer))


  val anotherL1 = Cons(1, Nil)
  println(l1 == anotherL1)


}
