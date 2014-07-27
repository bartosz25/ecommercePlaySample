// If you are in trouble, you can find great sample of build configuration here:
// http://mikeslinn.blogspot.fr/2013/09/sample-play-22x-buildsbt.html 

name := """testApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

javaOptions in Test ++= Seq( "-Dconfig.file=conf/test.conf" )

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
 