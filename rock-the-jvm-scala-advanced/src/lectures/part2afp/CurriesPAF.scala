package lectures.part2afp

object CurriesPAF extends App {

  val superAdder: Int => Int => Int =
    x => y => x + y

  val add3 = superAdder(3)
  println(add3(5))
  println(superAdder(3)(5))

  def curriedAdder(x: Int)(y: Int) = x + y

  // must have the type annotation
  // must pass in all parameter lists into methods
  val add4 : Int => Int = curriedAdder(4)

  val add5 = curriedAdder(5) _ 
  println(add5(5))
}
