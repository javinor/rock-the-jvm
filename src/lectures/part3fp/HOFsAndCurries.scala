package lectures.part3fp

object HOFsAndCurries extends App {

  def nTimes[A]: (A => A, Int, A) => A =
    (f, n, x) =>
      if (n == 0) x
      else nTimes(f, n - 1, f(x))

  val inc: Int => Int = _ + 1

  println(nTimes(inc, 7, 0))
  println(nTimes(inc, 7, 3))


  def nTimesBetter[A]: (A => A, Int) => A => A =
    (f, n) =>
      if (n <= 0) x => x
      else x => nTimesBetter(f, n - 1)(f(x))

  val plus7 = nTimesBetter(inc, 7)
  println(plus7(0))
  println(plus7(3))


  // multiple parameters list
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormatter: Double => String = curriedFormatter("%4.2f")
  //  val standardFormatter = curriedFormatter("%4.2f") // why doesn't this work?
  val preciseFormatter: Double => String = curriedFormatter("%10.8f")

  println(standardFormatter(Math.PI))
  println(preciseFormatter(Math.PI))


  /////////////////
  // Exercises

  def curry[A, B, C]: ((A, B) => C) => A => B => C =
    f => a => b => f(a, b)

  def uncurry[A, B, C]: (A => B => C) => (A, B) => C =
    f => (a, b) => f(a)(b)

  val adder = (x: Int, y: Int) => x + y
  println(curry(adder)(3)(4))
  val curriedAdder = (x: Int) => (y: Int) => x + y
  println(uncurry(curriedAdder)(3, 4))


  val add10 = (x: Int) => x + 10
  val doubler = (x: Int) => x * 2

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))
  println(compose(add10, doubler)(1))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))
  println(andThen(add10, doubler)(1))

}
