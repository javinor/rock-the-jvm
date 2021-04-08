package lectures.part3fp

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))


  val stringToIntConverter = new Function[String, Int] {
    override def apply(str: String): Int = str.toInt
  }
  println(stringToIntConverter("3") + 4)


  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(2, 3))


  //////////////////////////
  // Exercises

  val concat = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concat("Hello, ", "World!"))

  val curriedAdd: Int => Int => Int = x => y => x + y
  println(curriedAdd(2)(3))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}