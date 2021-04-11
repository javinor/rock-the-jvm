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


  ///////////////////////////
  // Exercises

  println(Map(("Jim", true), ("jiM", false)).map(p => p.copy(_1 = p._1.toLowerCase)))


  def add(network: Map[String, Set[String]], name: String): Map[String, Set[String]] = {
    network + (name -> Set())
  }

  def remove(network: Map[String, Set[String]], name: String): Map[String, Set[String]] = {
    //    (network - name).mapValues(friends => friends - name) // Naive solution

    def removeRec(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeRec(friends.tail, unfriend(networkAcc, name, friends.head))
    }

    removeRec(network(name), network) - name
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val aFriends = network(a)
    val bFriends = network(b)

    network + (a -> (aFriends + b)) + (b -> (bFriends + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val aFriends = network(a)
    val bFriends = network(b)

    network + (a -> (aFriends - b)) + (b -> (bFriends - a))
  }

  def friendCount(network: Map[String, Set[String]], name: String) = {
    network(name).size
  }

  def mostFriends(network: Map[String, Set[String]]): String = {
    //    network
    //      .reduce((p1, p2) => if (p1._2.size >= p2._2.size) p1 else p2)
    //      ._1
    network.maxBy(_._2.size)._1
  }

  def noFriends(network: Map[String, Set[String]]): Int = {
    //    network.filterKeys(name => network(name).size == 0).size
    network.count(_._2.isEmpty)
  }

  def areConnected(network: Map[String, Set[String]], start: String, target: String): Boolean = {
    def dfs(potential: Set[String], visited: Set[String]): Boolean = {
      if (potential.isEmpty) false
      else if (potential.head == target) true
      else if (visited.contains(potential.head)) dfs(potential.tail, visited)
      else dfs(potential.tail ++ network(potential.head), visited + potential.head)
    }

    dfs(Set(start), Set())
  }

  val n0 = Map[String, Set[String]]()
  val n1 = add(n0, "Alice")
  val n2 = add(n1, "Bob")
  val n3 = add(n2, "Chang")
  println("No Friends: " + n3)

  val n4 = friend(n3, "Alice", "Bob")
  val n5 = friend(n4, "Alice", "Chang")
  println("Alice has friends: " + n5)

  val n6 = remove(n5, "Bob")
  println("removed Bob: " + n6)

  val n7 = unfriend(n5, "Alice", "Bob")
  println("Alice broke up with Bob: " + n7)

  val n8 = add(n5, "Dale")
  println("Add Dale: " + n8)
  println(friendCount(n8, "Alice"))
  println(friendCount(n8, "Chang"))

  println(mostFriends(n8))

  println(noFriends(n8))

  val n9 = friend(n8, "Chang", "Dale")
  val n10 = add(n9, "Eric")
  println("areConnected in: " + n10)
  println("Alice & Bob: " + areConnected(n10, "Alice", "Bob"))
  println("Alice & Dale: " + areConnected(n10, "Alice", "Dale"))
  println("Alice & Eric: " + areConnected(n10, "Alice", "Eric"))

  
}
