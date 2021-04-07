package exercises

import java.util.NoSuchElementException


abstract class MyList[A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add(element: A): MyList[A]

  def toString: String
}


class Nil[A] extends MyList[A] {
  override def head = throw new NoSuchElementException

  override def tail = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(element: A): MyList[A] = new Cons(element, this)

  override def toString: String = "Nil"
}


class Cons[A](val head: A, val tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false

  override def add(element: A): MyList[A] = new Cons(element, this)

  override def toString = s"$head :: ${tail.toString}"
}


object ListTest extends App {

  val l0 = new Nil[Int]
  val l1 = l0 add 1
  val l2 = l1 add 2
  val l3 = l2 add 3

  println(l3)
  println(l3 head)
  println(l3 tail)
  println(l3 isEmpty)

}
