package lectures.part4implicits

import java.util.Date

object JSONSerialization extends App {

  case class User(name: String, age: Int, email: String)

  case class Post(content: String, createdAt: Date)

  case class Feed(user: User, posts: List[Post])

  /*
      1 - intermediate data types
      2 - create type classes for conversion to intermediate data types
      3 - serialize the intermediate data types

   */

  sealed trait JSONValue {
    def stringify: String
  }

  final case class JSONString(value: String) extends JSONValue {
    override def stringify: String = s""""$value"""" // TODO escapse quotes in $value
  }

  final case class JSONNumber(value: Int) extends JSONValue {
    override def stringify: String = value.toString
  }

  final case class JSONArray(values: List[JSONValue]) extends JSONValue {
    override def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue {
    override def stringify: String = values.map {
      case (key, value) => s""""$key\":${value.stringify}""""
    }.mkString("{", ",", "}")
  }


  val data = JSONObject(Map(
    "user" -> JSONString("Daniel"),
    "posts" -> JSONArray(List(
      JSONString("Scala Rocks!"),
      JSONNumber(123)
    ))
  ))

  println(data.stringify)


  /*
    type classes
      1 - type class
      2 - type class instances (implicit)
      3 - pimp library to use type class instances
   */

  // 2.1
  trait JSONConverter[T] {
    def convert(value: T): JSONValue
  }

  // 2.3
  implicit class JSONOps[T](value: T) {
    def toJSON(implicit converter: JSONConverter[T]): JSONValue =
      converter.convert(value)
  }

  // 2.2
  // existing data types
  implicit object StringConverter extends JSONConverter[String] {
    override def convert(value: String): JSONValue = JSONString(value)
  }

  implicit object IntConverter extends JSONConverter[Int] {
    override def convert(value: Int): JSONValue = JSONNumber(value)
  }

  // custom data types
  implicit object UserConverter extends JSONConverter[User] {
    override def convert(user: User): JSONValue =
      JSONObject(Map(
        "name" -> JSONString(user.name),
        "age" -> JSONNumber(user.age),
        "email" -> JSONString(user.email),
      ))
  }

  implicit object PostConverter extends JSONConverter[Post] {
    override def convert(post: Post): JSONValue =
      JSONObject(Map(
        "content" -> JSONString(post.content),
        "createdAt" -> JSONString(post.createdAt.toString)
      ))
  }

  implicit object FeedConverter extends JSONConverter[Feed] {
    override def convert(value: Feed): JSONValue =
      JSONObject(Map(
        //        "user" -> UserConverter.convert(value.user), // TODO
        //        "posts" -> JSONArray(value.posts.map(p => PostConverter.convert(p))) // TODO
        "user" -> value.user.toJSON,
        "posts" -> JSONArray(value.posts.map(_.toJSON))
      ))
  }


  val now = new Date(System.currentTimeMillis())
  val john = User("John", 34, "john@qwe.com")
  val feed = Feed(
    john,
    List(
      Post("hello", now),
      Post("Look here", now)
    )
  )

  println(feed.toJSON.stringify)
}
