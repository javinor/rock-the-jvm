package lectures.part3concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object FuturesAndPromises extends App {

  val delayedInt = {
    Thread.sleep(1000)
    123
  }

  val aFuture = Future {
    delayedInt
  }

  println(aFuture.value)

  println("waiting on the future...")

  aFuture.onComplete {
    case Failure(exception) => println(s"failed with $exception")
    case Success(value) => println(s"succeeded with $value")
  } // we don't know which thread will call the onComplete callback


  case class Profile(id: String, name: String) {
    def poke(other: Profile): Unit = {
      println(s"$name poking ${other.name}")
    }
  }


  object SocialNetwork {
    val names = Map(
      "fb.id.1-zuck" -> "Mark",
      "fb.id.2-bill" -> "Bill",
      "fb.id.0-dummy" -> "Dummy",
    )

    val friends = Map(
      "fb.id.1-zuck" -> "fb.id.2-bill",
    )

    val random = new Random()

    def fetchProfile(id: String): Future[Profile] = Future {
      Thread.sleep(random.nextInt(300))
      Profile(id, names(id))
    }

    def fetchBestFriend(profile: Profile): Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val bfId = friends(profile.id)
      Profile(bfId, names(bfId))
    }
  }


  val mark = SocialNetwork.fetchProfile("fb.id.1-zuck")
  mark.onComplete {
    case Failure(exception) => exception.printStackTrace()
    case Success(markProfile) => {
      val bill = SocialNetwork.fetchBestFriend(markProfile)
      bill.onComplete {
        case Failure(exception) => exception.printStackTrace()
        case Success(billProfile) => markProfile.poke(billProfile)
      }
    }
  }

  // using for comprehension
  for {
    mark <- SocialNetwork.fetchProfile("fb.id.1-zuck")
    bill <- SocialNetwork.fetchBestFriend(mark)
  } yield mark.poke(bill)


  // fallbacks
  val aProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => Profile("fallback.id", "Bob")
  }

  val aFetchedProfileNoMatterWhat = SocialNetwork.fetchProfile("unknown id").recover {
    case e: Throwable => SocialNetwork.fetchProfile("fb.id.0-dummy")
  }

  val aFallbackResult = SocialNetwork.fetchProfile("unknown id").fallbackTo(SocialNetwork.fetchProfile("fb.id.0-dummy"))

  Thread.sleep(2000)
}
