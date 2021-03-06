import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "iirc",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      http4sServer,
      http4sCirce,
      http4sDsl,
      circeCore,
      circeGeneric,
      circeParser,
      scalaCacheCaffeine,
      scalaCacheCats,
      logback,
      slf4j
    ),
  ).enablePlugins(JavaAppPackaging)
