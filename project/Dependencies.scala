import sbt._

object Dependencies {
  lazy val scalaTest =          "org.scalatest"     %% "scalatest"              % "3.0.5"
  lazy val http4sServer =       "org.http4s"        %% "http4s-blaze-server"    % "0.18.11"
  lazy val http4sCirce =        "org.http4s"        %% "http4s-circe"           % "0.18.11"
  lazy val http4sDsl =          "org.http4s"        %% "http4s-dsl"             % "0.18.11"
  lazy val circeCore =          "io.circe"          %% "circe-core"             % "0.9.3"
  lazy val circeGeneric =       "io.circe"          %% "circe-generic"          % "0.9.3"
  lazy val circeParser =        "io.circe"          %% "circe-parser"           % "0.9.3"
  lazy val scalaCacheCaffeine = "com.github.cb372"  %% "scalacache-caffeine"    % "0.22.0"
  lazy val scalaCacheCats =     "com.github.cb372"  %% "scalacache-cats-effect" % "0.22.0"
  lazy val logback =            "ch.qos.logback"    % "logback-classic"         % "1.2.3"
  lazy val slf4j =              "org.slf4j"         % "slf4j-api"               % "1.7.21"
}
