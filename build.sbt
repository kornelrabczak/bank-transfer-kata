import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.thecookiezen"
ThisBuild / organizationName := "thecookiezen.com"

lazy val root = (project in file("."))
  .settings(
    name := "bank-transfer-kata",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalaCheck % Test
  )