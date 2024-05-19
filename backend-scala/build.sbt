name := "backend-scala"
version := "0.1"
scalaVersion := "2.13.14"
idePackagePrefix := Some("org.akshay.backendscala")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.9",
  "com.typesafe.akka" %% "akka-stream" % "2.6.18",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.9",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "mysql" % "mysql-connector-java" % "8.0.28",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "ch.qos.logback" % "logback-classic" % "1.2.10"
)