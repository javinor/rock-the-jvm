package lectures.part4pm

object PatternsEverywhere extends App {

  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
    // catches are matches
  }


  // big idea #2
  val list = List(1, 2, 3, 4)
  val evens = for {
    x <- list if x % 2 == 0
  } yield 10 * x


  val tuples = List((1, 2), (3, 4))
  val filteredTuples = for {
    (first, second) <- tuples
  } yield first * second
  println(filteredTuples)


  val unmatchedTuples = for {
    (3, x) <- tuples
  } yield x
  println(unmatchedTuples)


  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  // multiple value definition based on pattern matching

  //  val (2, x, y) = tuple // causes a match error!


  // big idea #4
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  // same as writing
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

}
