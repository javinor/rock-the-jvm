package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(author.fullname())
  println(novel.authorAgeAtRelease())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(new Writer("Ram", "Oren", 1970)))
  println(novel.copy(1871).authorAgeAtRelease())

}

//class Person(name: String, age: Int) // constructor
// class parameters are NOT FIELDS


// constructor with age as class field
class Person(name: String, val age: Int) {
  val x = 2

  println(1 + 3)

  def greet(name: String) = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet() = println(s"Hi, I am $name")


  // multiple constructors - redundant as main constructor's parameters can be assigned default values
  def this(name: String) = this(name, 0)

  def this() = this("George")
}


// Exercises

class Writer(firstname: String, surname: String, val yearOfBirth: Int) {
  def fullname() = firstname + " " + surname
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAgeAtRelease() = yearOfRelease - author.yearOfBirth

  def isWrittenBy(writer: Writer) = writer == author

  def copy(newYearOfRelease: Int) = new Novel(name, newYearOfRelease, author)
}


class Counter(val n: Int) {
  def current() = n

  def increment() = new Counter(n + 1)

  def increment(m: Int) = new Counter(n + m)

  def decrement() = new Counter(n - 1)

  def decrement(m: Int) = new Counter(n - m)
}






