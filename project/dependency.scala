import sbt._,Keys._

object dependency {

    private object versions {
        val scalatest     = "3.0.5"
        val shapeless     = "2.3.2"
        val simulacrum    = "0.10.0"
        val commons       = "3.5"
        val circe         = "0.13.0"
        val cats          = "2.1.1"
        val `cats-effect` = "2.1.3"
        val sl4j          = "1.7.25"
        val logback       = "1.2.3"
        val fs2           = "2.3.0"
        val config        = "1.3.1"
        val logging       = "3.9.0"
        val refined       = "0.9.14"
        val groupon       = "1.1.1"
        val http4s        = "0.21.4"
        val caliban       = "0.8.3"
        object zio {
            val core = "1.0.0-RC21-1"
            val cats = "2.1.3.0-RC16"
        }
    }
    
    object typesafe {
        val config  = "com.typesafe"               %  "config"        % versions.config
        val logging = "com.typesafe.scala-logging" %% "scala-logging" % versions.logging
    }

    object refined {
        val core = "eu.timepit" %% "refined"      % versions.refined
        val cats = "eu.timepit" %% "refined-cats" % versions.refined
    }

    object sl4j {
        val simple = "org.slf4j" % "slf4j-simple" % versions.sl4j
    }

    object fs2 {
        val core = "co.fs2" %% "fs2-core" % versions.fs2
        val io   = "co.fs2" %% "fs2-io"   % versions.fs2
    }

    object cats {
        val core   = "org.typelevel" %% "cats-core"   % versions.cats
        val effect = "org.typelevel" %% "cats-effect" % versions.`cats-effect`
    }

    object http4s {
        val client = "org.http4s" %% "http4s-blaze-client" % versions.http4s
        val server = "org.http4s" %% "http4s-blaze-server" % versions.http4s
        val core   = "org.http4s" %% "http4s-core"         % versions.http4s
        val circe  = "org.http4s" %% "http4s-circe"        % versions.http4s
        val dsl    = "org.http4s" %% "http4s-dsl"          % versions.http4s
    }

    object circe {
        val core    = "io.circe" %% "circe-core"    % versions.circe
        val parser  = "io.circe" %% "circe-parser"  % versions.circe
        val generic = "io.circe" %% "circe-generic" % versions.circe
        val optics  = "io.circe" %% "circe-optics"  % versions.circe
    }

    object simulacrum {
        val core = "com.github.mpilquist" %% "simulacrum" % versions.simulacrum
    }

    object apache {
        val lang = "org.apache.commons" % "commons-lang3" % versions.commons
    }


    object caliban {
        val core   = "com.github.ghostdogpr" %% "caliban"        % versions.caliban
        val http4s = "com.github.ghostdogpr" %% "caliban-http4s" % versions.caliban
        val cats   = "com.github.ghostdogpr" %% "caliban-cats"   % versions.caliban
    }

    object zio {
        val core = "dev.zio" %% "zio"              % versions.zio.core
        val cats = "dev.zio" %% "zio-interop-cats" % versions.zio.cats
    }

    object logback {
        val core = "ch.qos.logback" % "logback-classic" % versions.logback
    }

    object groupon {
        val uuid = "com.groupon" % "locality-uuid" % versions.groupon
    }



}