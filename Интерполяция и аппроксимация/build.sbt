import scala.language.postfixOps

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "CompMathLab4",
    libraryDependencies += "org.knowm.xchart" % "xchart" % "3.8.0" exclude("de.erichseifert.vectorgraphics2d", "VectorGraphics2D") withSources()
  )
