package org.akshay.backendscala
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

case class Summary(id: Option[Long], url: String, summary: String)

class SummaryTable(tag: Tag) extends Table[Summary](tag, "summaries") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def url: Rep[String] = column[String]("url")
  def summary: Rep[String] = column[String]("summary")

  def * : ProvenShape[Summary] = (id.?, url, summary) <> (Summary.tupled, Summary.unapply)
}

object SummaryTable {
  val summaries = TableQuery[SummaryTable]
}
