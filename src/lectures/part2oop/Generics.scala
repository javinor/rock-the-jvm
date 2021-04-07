package lectures.part2oop

object Generics extends App {

  class MyList[A] {

  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  // objects can be parameterized, but its methods can be
  object MyList {
    def empty[A]: MyList[A] = ???

  }

  //  val emptyListOfInt = MyList.empty[Int]


  //////////////////////////////////////
  // Variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal


  // QUESTION: Does List[Cat] extend List[Animal]?

  //  option 1 - yes: Covariance
  class CovarianceList[+A]

  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat]

  //   animalList.add(new Dog) ???


  // option 2 - no: Invariance
  class InvariantList[A]

  //  val invariantAnimalList : InvariantList[Animal] = new InvariantList[Cat] // doesn't work!


  // option 3 - hell, no!: Contravariance
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // example
  class Trainer[-A]

  val catTrainer: Trainer[Cat] = new Trainer[Animal]


  // bounded types
  class Cage[A <: Animal](Animal: A)

  val cage = new Cage(new Dog)


  class Car
  //  val newCage = new Cage(new Car) // doesn't work!

  class MyListBounded[+A] {
    def add[B >: A](element: B) : MyList[B] = ???
    // so adding an Animal to a List of Cat will turn into a list of Animal
    // But what to do when adding a Dog to a list of Cats?
  }


}
