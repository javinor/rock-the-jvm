package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples
  val aTuple = (7, "Hello")
  println(aTuple)
  println(aTuple._1)
  println(aTuple._2)
  println(aTuple.copy(_2 = "goodbye!"))
  println(aTuple.swap)

  // Maps
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Dan" -> 678)
  println(phonebook)

  // map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  //  println(phonebook("Jane")) // will throw, unless initiating Map.withDefaultValue
  val newPairing = "Mary" -> 123
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)


  println(phonebook.map(pair => pair._1.toUpperCase -> pair._2))
  println(phonebook.filterKeys(_.startsWith("J")))
  println(phonebook.mapValues(_ * 10))


  // conversions
  println(phonebook.toList)
  println(List(("Qwe", 444)).toMap)

  val names = List("Bob", "Jim", "Rick")
  println(names.groupBy(_.length))

}
