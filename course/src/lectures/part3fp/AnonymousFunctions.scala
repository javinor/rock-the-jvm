package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function a.k.a. lambda
  val doubler = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2

  // multiple params
  val adder: (Int, Int) => Int = (x, y) => x + y

  // no params
  val justDoSomething: () => Int = () => 7
  println(justDoSomething)
  println(justDoSomething())

  // curly braces
  val stringToInt = { (str: String) =>
    str.toInt
  }


  // MOAR syntactic sugar
  val niceInc : Int => Int = _ + 1
  val niceAdder : (Int, Int) => Int = _ + _



}
