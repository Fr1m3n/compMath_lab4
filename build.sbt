name := "CompMath3"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "org.scalanlp" %% "breeze" % "1.1"
libraryDependencies += "org.scalanlp" %% "breeze-viz" % "1.1"

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.11.8",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.specs2" %% "specs2-core" % "3.7.2" % "test"
)

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")

scalacOptions in Test ++= Seq("-Yrangepos")