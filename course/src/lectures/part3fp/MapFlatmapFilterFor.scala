package lectures.part3fp

import exercises.{Cons, Nil}

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  val thrice = (x: Int) => List(x, x, x)
  println(list.flatMap(thrice))

  // print all combinations
  val chars = List('a', 'b', 'c', 'd')
  val nums = List(1,2,3,4)
  val combo = chars.flatMap(c => nums.map(n => c.toString + n))
  println(combo)

  val forCombo = for {
    c <- chars
    n <- nums
  } yield "" + c + n
  println(forCombo)
  println(combo == forCombo)

  val evenCombos = for {
    c <- chars
    n <- nums if n % 2 == 0
  } yield "" + c + n
  println(evenCombos)


  val myChars = Cons('a', Cons('b', Cons('c', Nil)))
  val myNums = Cons(1, Cons(2, Cons(3, Nil)))
  val myCombo = for {
    c <- chars
    n <- nums
  } yield "" + c + n
  println(myCombo)

}
