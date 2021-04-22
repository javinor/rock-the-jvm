package exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {

  def apply(elem: A): Boolean = contains(elem)

  def contains(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(other: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter[B](f: A => Boolean): MySet[A]

  def foreach(f: A => Unit): Unit
}

class EmptySet[A] extends MySet[A] {
  override def contains(elem: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet(elem, this)

  override def ++(other: MySet[A]): MySet[A] = other

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter[B](f: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contains(elem: A): Boolean = elem == head || tail.contains(elem)

  override def +(elem: A): MySet[A] = {
    if (contains(elem)) this
    else new NonEmptySet(elem, this)
  }

  override def ++(other: MySet[A]): MySet[A] =
    tail ++ other + head

  override def map[B](f: A => B): MySet[B] =
    tail.map(f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] =
    f(head) ++ tail.flatMap(f)

  override def filter[B](f: A => Boolean): MySet[A] = {
    val filteredTail = tail.filter(f)
    if (f(head)) filteredTail + head
    else filteredTail
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }
}

object MySet {
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] = {
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)
    }

    buildSet(values.toSeq, new EmptySet)
  }
}

object MySetPlayground extends App {
  println("testing MySet:")

  val s = MySet(1, 2, 3)
  s.foreach(println)

  s + 5 ++ MySet(8, 9) + 3 map (x => x * 10) flatMap (x => MySet(-x, x)) filter(x => x % 15 == 0) foreach println


}