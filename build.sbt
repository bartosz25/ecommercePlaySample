name := """testApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

// TODO : two last deps must be moved away from here (placed in appDependencies var)
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "mysql" % "mysql-connector-java" % "5.1.31",
  "com.novocode" % "junit-interface" % "0.9" % "test"
)
 