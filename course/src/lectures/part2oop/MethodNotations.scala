package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, age: Int = 27) {
    def likes(movie: String) = movie == favoriteMovie

    def hangOutWith(person: Person) = s"${this.name} is hanging out with ${person.name}"

    def <#>(person: Person) = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck!?"

    def isAlive = true // can be used as a suffix operator - no parameters

    def apply() = s"Hi, my name is $name and I apply myself well!"


    // Exercise Solutions
    def +(nickname: String) = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+() = new Person(name, favoriteMovie, age + 1)

    def learns(subject: String) = s"$name learns $subject"

    def learnsScala() = learns("Scala")

    def apply(n: Int) = s"$name watched $favoriteMovie $n times"

    def whoAreYou() = s"Hi, my name is $name, I am $age years old and my favorite movie is $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")


  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary <#> tom)


  // prefix operator
  println(!mary)

  // postfix operator
  println(mary.isAlive)
  println(mary isAlive)


  // apply method
  println(mary.apply())
  println(mary())


  /*
    Exercises
    1. Overload the + operator:
        mary + "The rockstar" => new person "Mary (the rockstar)"
    2. Add an age, and unary + that increments age by one
    3. Add "learns" method, and "learnsScala" method as suffix
    4. overload apply: int => name watched movie int times
   */
  println(mary + "The rockstar" whoAreYou)
  println(+mary whoAreYou)
  println(mary learnsScala)
  println(mary(4))


}
