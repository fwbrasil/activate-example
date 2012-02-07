import sbt._
import Keys._

object ActivateExampleBuild extends Build {
  
	/* Dependencies */
  val fwbrasilRepo = "fwbrasil.net" at "http://fwbrasil.net/maven/"
  val activateCore = "net.fwbrasil" %% "activate-core" % "0.6"
  val activatePrevayler = "net.fwbrasil" %% "activate-prevayler" % "0.6"
  val activateJdbc = "net.fwbrasil" %% "activate-jdbc" % "0.6"
  val activateMongo = "net.fwbrasil" %% "activate-mongo" % "0.6"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.16"
		
  lazy val activateExample = 
  	Project(
  		id = "activate-example",
  		base = file("."),
  		settings = Defaults.defaultSettings ++ Seq(
        libraryDependencies ++= Seq(activateCore, activatePrevayler, activateJdbc, activateMongo, mysql),
        organization := "com.example.foo",
	      scalaVersion := "2.9.1",
	      version := "1.0",
	      resolvers += fwbrasilRepo
      )
  	)

}