scalaVersion in Global := "2.12.11"

scalacOptions += "-Ypartial-unification"

lazy val `event-processing` = project.in(file(".")).aggregate(
    core, 
    `upload-service`,
    processing,
    producer
)    

lazy val core = project

lazy val `upload-service` = project.dependsOn(core)

lazy val processing = project.dependsOn(core)

lazy val producer = project.dependsOn(core)

settings.common
