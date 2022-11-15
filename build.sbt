name := """klubi-manager"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    watchSources ++= (baseDirectory.value / "public/ui" ** "*").get
  )

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "com.softwaremill.macwire" %% "macros" % "2.5.8" % "provided",
  "com.h2database" % "h2" % "2.1.214",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
"org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

addCommandAlias(
  "validateCode",
  "scalafmtSbtCheck; scalafmtCheckAll; uiCodeStyleCheck"
)
