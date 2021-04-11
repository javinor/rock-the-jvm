package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(5)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "Double or nothing"
    case 3 => "third time's a charm"
    case _ => "something else..."
  }

  println(x)
  println(description)


  // 1. Decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)


  // PM on sealed classes
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  def describeAnimal(animal: Animal): Unit = {
    animal match {
      case Dog(breed) => println(s"Matched a dog of the $breed breed")
    }
  }

  describeAnimal(Dog("Terra Nova"))


  // Exercises
  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr


  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def withParens(e: Expr) = e match {
        case Number(_) | Prod(_, _) => show(e)
        case Sum(_, _) => s"(${show(e)})"
      }

      s"${withParens(e1)} * ${withParens(e2)}"
    }
  }

  def printExpr(e: Expr): Unit = println(s"$e: ${show(e)}")

  printExpr(Sum(Number(2), Number(3)))
  printExpr(Sum(Sum(Number(2), Number(3)), Number(4)))
  printExpr(Prod(Sum(Number(2), Number(1)), Number(3)))
  printExpr(Sum(Prod(Number(2), Number(1)), Number(3)))
}
