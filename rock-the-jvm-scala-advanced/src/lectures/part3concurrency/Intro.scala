package lectures.part3concurrency

import java.util.concurrent.Executors

object Intro extends App {

  // JVM threads
  val runnable = new Runnable {
    override def run(): Unit = println("running in parallel...")
  }

  val aThread = new Thread(runnable)
  aThread.start()
  aThread.join()

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 5).foreach(_ => println("goodbye")))

  threadHello.start()
  threadGoodbye.start()

  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => "this is from the thread pool....")
  pool.execute(() => {
    Thread.sleep(1000)
    println("done sleeping for 1 second")
  })
  pool.execute(() => {
    Thread.sleep(1000)
    println("almost done...")
    Thread.sleep(2000)
    println("done sleeping for 2 seconds")
  })

//  pool.shutdown()
//  pool.isShutdown() // will return true even if threads are still running
//  pool.execute(() => println("the pool already shutdown")) // throws an exception in the calling thread (main in this case)

//    pool.shutdownNow() // will throw exceptions in threads


  def runInParallel = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })

    val thread2 = new Thread(() => {
      x = 2
    })

    thread1.start()
    thread2.start()

    if (x != 0) println(x) else print(x)
  }

  for (_ <- 1 to 10000) runInParallel
}
