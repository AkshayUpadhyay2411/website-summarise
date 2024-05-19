package org.akshay.backendscala
import slick.jdbc.MySQLProfile.api._
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

object Database {
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("slick.dbs.default")
  val db: JdbcProfile#Backend#Database = dbConfig.db

  def createSchema()(implicit ec: ExecutionContext): Future[Unit] = {
    val setup = DBIO.seq(
      SummaryTable.summaries.schema.createIfNotExists
    )
    db.run(setup)
  }

  def insertSummary(summary: Summary)(implicit ec: ExecutionContext): Future[Long] = {
    db.run(SummaryTable.summaries returning SummaryTable.summaries.map(_.id) += summary)
  }

  def getSummaries()(implicit ec: ExecutionContext): Future[Seq[Summary]] = {
    db.run(SummaryTable.summaries.result)
  }
}

