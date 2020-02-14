name := "shEx2Code"

version := "0.1"

scalaVersion := "2.12.7"

Compile / scalaSource := baseDirectory.value / "src/main/scala-2.13"
Compile / javaSource := baseDirectory.value / "src/main/java"

libraryDependencies += "org.antlr" % "antlr4" % "4.8-1"

lazy val shexliteImport = ProjectRef(file("../shex-lite"), "shexlite")
lazy val root = (project in file (".")).dependsOn(shexliteImport)