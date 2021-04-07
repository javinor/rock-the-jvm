package exercises

import java.util.NoSuchElementException


abstract class MyList {

  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(n: Int): MyList

  def toString: String
}


object Nil extends MyList {
  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(n: Int): MyList = new Cons(n, this)

  override def toString: String = "Nil"
}


class Cons(val head: Int, val tail: MyList) extends MyList {
  override def isEmpty: Boolean = false

  override def add(n: Int): MyList = new Cons(n, this)

  override def toString = s"$head :: ${tail.toString}"
}


object ListTest extends App {

  val l0 = Nil
  val l1 = l0 add 1
  val l2 = l1 add 2
  val l3 = l2 add 3

  println(l3)
  println(l3 head)
  println(l3 tail)
  println(l3 isEmpty)

}
