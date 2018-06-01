import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val http4sServer = "org.http4s" %% "http4s-blaze-server" % "0.18.11"
  lazy val http4sCirce = "org.http4s" %% "http4s-circe" % "0.18.11"
  lazy val http4sDsl = "org.http4s" %% "http4s-dsl" % "0.18.11"
  lazy val circeCore = "io.circe" %% "circe-core" % "0.9.3"
  lazy val circeGeneric = "io.circe" %% "circe-generic" % "0.9.3"
  lazy val circeParser = "io.circe" %% "circe-parser" % "0.9.3"
}
