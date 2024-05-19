package org.akshay.backendscala
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("scala-backend")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val routes = new SummaryRoutes().routes

  val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)

  bindingFuture.onComplete {
    case Success(binding) =>
      val address = binding.localAddress
      println(s"Server online at http://${address.getHostString}:${address.getPort}/")
      Database.createSchema()
    case Failure(ex) =>
      println(s"Failed to bind HTTP server: ${ex.getMessage}")
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}