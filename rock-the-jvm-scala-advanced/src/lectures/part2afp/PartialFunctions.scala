package lectures.part2afp

object PartialFunctions extends App {

  val inc = (x: Int) => x + 1

  val aFussyFunction = (x: Int) => {
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new RuntimeException
  }

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }

  println(aPartialFunction(2))


  // PF utilities
  println(aPartialFunction.isDefinedAt(123))
  println(aPartialFunction.lift(123))
  println(aPartialFunction.lift(2))


  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))


  // PF extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 999
  }


  // HOF accept partial functions as well
  println(List(1, 2, 3).map {
    case 1 => 10
    case 2 => 20
    case 3 => 30
  })


  // PF can only have one parameter type


  // 






}
