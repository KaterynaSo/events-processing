
import sbt._, Keys._
import dependency._

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  apache.lang,
  cats.core,
  cats.effect,
  sl4j.simple,
  typesafe.config,
  typesafe.logging,
  fs2.core,
  refined.core,
  refined.cats
)

settings.common