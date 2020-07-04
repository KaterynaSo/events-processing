
import sbt._, Keys._
import dependency._

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  http4s.core,
  http4s.circe,
  http4s.dsl,
  logback.core
)

settings.common