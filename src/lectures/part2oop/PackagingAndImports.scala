package lectures.part2oop

import java.sql.{Date => SqlDate}
import java.util.Date

import playground.{Cinderella, PrinceCharming}
// import playground._ // will import everything from the package

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val princess = new Cinderella

  // Fully Qualified Name (FQN)
  val otherPrincess = new playground.SnowWhite


  // packages are an ordered hierarchy
  //  - matching folder structure is optional in Scala


  // package object
  //  - only one per package
  sayHello
  println(SPEED_OF_LIGHT)


  // imports
  val prince = new PrinceCharming


  // if imported names collide
  val date = new Date
  val sqlDate = new SqlDate(2021, 4, 1)
  val fqnSqlDate = new java.sql.Date(2021, 4, 1)


  // default imports:
  // 1. java.lang
  // 2. scala
  // 3. scala.predef
}
