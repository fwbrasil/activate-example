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
  /* 
    Install oracle in your local repo:
    mvn install:install-file -Dfile={ORACLE_HOME}/jdbc/lib/ojdbc6.jar -Dpackaging=jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 
  */
  val objbd6 = "com.oracle" % "ojdbc6" % "11.2.0"
		
  lazy val activateExample = 
  	Project(
  		id = "activate-example",
  		base = file("."),
  		settings = Defaults.defaultSettings ++ Seq(
        libraryDependencies ++= Seq(activateCore, /*activatePrevayler, activateJdbc, activateMongo, mysql, objbd6*/),
        organization := "com.example.foo",
	      scalaVersion := "2.9.1",
	      version := "1.0",
	      resolvers += fwbrasilRepo
      )
  	)

}