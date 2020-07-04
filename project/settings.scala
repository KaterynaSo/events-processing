import sbt._, Keys._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease._

import scala.Console._

object settings {

  import extensions._
  import systemprops._

  def common = compiler ++ console ++ release ++ resolution

  private lazy val resolution = Seq()

  private lazy val compilerFlags = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-unchecked",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Xfatal-warnings",
    "-Ypartial-unification"
  )

  private lazy val compiler = Seq(
    scalacOptions in Compile ++= compilerFlags,
    scalaVersion in Global := "2.12.11"
  )


  private lazy val prompt = { (s: State) => s"[${green(Project.extract(s).currentProject.id)}] λ " }

  private def green (prefix: String): String = color(GREEN)(prefix)

  private def red   (prefix: String): String = color(RED)(prefix)

  private def color (color: String)(value: String): String = if (ansiSupported) color + value + RESET else value

  private def attachChangePush: Seq[ReleaseStep] =  Seq.empty[ReleaseStep]

  private lazy val console = Seq(
    shellPrompt := prompt
  )

  private lazy val release = Seq(
    releaseCrossBuild := false,
    releaseCommitMessage := s"Releasing ${(version in ThisBuild).value}",
    releaseVersion := { current =>
      Version(current).map(v => v.withoutQualifier.string).getOrElse(versionFormatError(current))
    },
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runTest,
      setReleaseVersion,
      publishArtifacts
    )
  )
}

object extensions {

  implicit def _extend(version: Version): VersionExtensions = new VersionExtensions(version)

  private def getIndex(octet: Version.Bump): Option[Int] = octet match {
    case Version.Bump.Minor  => Some(0)
    case Version.Bump.Bugfix => Some(1)
    case Version.Bump.Nano   => Some(2)
    case _                   => None
  }

  class VersionExtensions(val self: Version) extends AnyVal {

    def set(octet: Version.Bump, value: Int): Version = getIndex(octet) match {
      case Some(index) => self.copy(subversions = self.subversions.updated(index, value))
      case None        => sys.error(s"Unable to set an octet '$octet'")
    }
  }

}

object systemprops {

  private val WINDOWS = "windows"
  private val TRUE    = "true"
  private val FALSE   = "false"

  def isNoFormat    = Option(System.getProperty("sbt.log.noformat")).map(_ != TRUE)

  def isWindows     = os.map(_.toLowerCase).filter(_.contains(WINDOWS)).map(_ => false)

  def os            = Option(System.getProperty("os.name"))

  def ansiSupported = isNoFormat orElse isWindows getOrElse true
}

