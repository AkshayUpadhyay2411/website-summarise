package org.akshay.backendscala

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.stream.Materializer
import spray.json._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

// Define request and response case classes
case class SummaryRequest(url: String)
case class SummaryResponse(url: String, summary: String)

trait JsonSupport extends spray.json.DefaultJsonProtocol {
  implicit val summaryRequestFormat = jsonFormat1(SummaryRequest)
  implicit val summaryResponseFormat = jsonFormat2(SummaryResponse)
  implicit val summaryFormat = jsonFormat3(Summary)
}

class SummaryRoutes(implicit val system: ActorSystem, val materializer: Materializer, val ec: ExecutionContext) extends JsonSupport {

  val routes: Route =
    pathPrefix("summaries") {
      concat(
        pathEnd {
          get {
            onComplete(Database.getSummaries) {
              case Success(summaries) => complete(summaries)
              case Failure(ex) => complete(StatusCodes.InternalServerError -> ex.getMessage)
            }
          } ~
            post {
              entity(as[Map[String, String]]) { body =>
                val url = body("url")
                onComplete(fetchSummary(url)) {
                  case Success(summaryResponse) =>
                    val summaryRecord = Summary(None, summaryResponse.url, summaryResponse.summary)
                    onComplete(Database.insertSummary(summaryRecord)) {
                      case Success(_) => complete(StatusCodes.Created -> summaryRecord)
                      case Failure(ex) => complete(StatusCodes.InternalServerError -> ex.getMessage)
                    }
                  case Failure(ex) => complete(StatusCodes.InternalServerError -> ex.getMessage)
                }
              }
            }
        }
      )
    }

  private def fetchSummary(url: String): Future[SummaryResponse] = {
    import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
    import akka.http.scaladsl.unmarshalling.Unmarshal
    import spray.json._

    val summaryRequest = SummaryRequest(url)
    val jsonRequest = summaryRequest.toJson.compactPrint
    val httpRequest = HttpRequest(
      method = HttpMethods.POST,
      uri = "http://localhost:8000/summarise",
      entity = HttpEntity(ContentTypes.`application/json`, jsonRequest)
    )

    Http().singleRequest(httpRequest).flatMap {
      case HttpResponse(StatusCodes.OK, _, entity, _) =>
        Unmarshal(entity).to[SummaryResponse]
      case HttpResponse(statusCode, _, entity, _) =>
        Unmarshal(entity).to[String].flatMap { body =>
          Future.failed(new Exception(s"Request failed with status code $statusCode and body $body"))
        }
    }
  }
}
