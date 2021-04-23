package lectures.part2afp

object LazyEvaluation extends App {

  lazy val doesNotExplode = ???

  lazy val x = {
    println("evaluating x")
    42
  }

  println(x)
  println(x)


  // examples
  def sideEffectCondition: Boolean = {
    println("boo!")
    true
  }

  def simpleCondition = false

  lazy val lazyCondition = sideEffectCondition
  println(if (simpleCondition && lazyCondition) "yes" else "no")
  println(if (simpleCondition && sideEffectCondition) "yes" else "no")


  // in conjunction with call by name
  def byValueMethod(n: Int) = n + n + n + 1

  def byNameMethod(n: => Int) = n + n + n + 1

  def retrieveMagicValue = {
    println("retrieving magic number...")
    3
  }

  println(byValueMethod(retrieveMagicValue))
  println(byNameMethod(retrieveMagicValue))

  def byNeedMethod(n: => Int) = {
    lazy val t = n
    t + t + t + 1
  }

  println(byNeedMethod(retrieveMagicValue))


  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"$i is greater than 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30)
  val gt20 = lt30.filter(greaterThan20)

  println(gt20)

  val lt30Lazy = numbers.withFilter(lessThan30)
  val gt20Lazy = lt30Lazy.withFilter(greaterThan20)
  gt20Lazy.foreach(println)


  for {
    a <- List(1, 2, 3) if a % 2 == 0 // use lazy vals!
  } yield a + 1
  // translates into
  List(1, 2, 3).withFilter(_ % 2 == 0).map(_ + 1)
  

}
