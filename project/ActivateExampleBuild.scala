import sbt._
import Keys._

object ActivateExampleBuild extends Build {

	/* Dependencies */
	val localMavenRepo = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
	val fwbrasilRepo = "fwbrasil.net" at "http://fwbrasil.net/maven/"

	val activateVersion = "1.6"
	val activateCore = "net.fwbrasil" %% "activate-core" % activateVersion
	val activatePrevayler = "net.fwbrasil" %% "activate-prevayler" % activateVersion
	val activateJdbc = "net.fwbrasil" %% "activate-jdbc" % activateVersion
	val activateMongo = "net.fwbrasil" %% "activate-mongo" % activateVersion
	val mysql = "mysql" % "mysql-connector-java" % "5.1.16"
	/* 
    Install oracle in your local repo:
    mvn install:install-file -Dfile={ORACLE_HOME}/jdbc/lib/ojdbc6.jar -Dpackaging=jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 
  */
	val objbd6 = "com.oracle" % "ojdbc6" % "11.2.0"
	val postgresql = "org.postgresql" % "postgresql" % "9.2-1003-jdbc4"

	lazy val activateExample =
		Project(
			id = "activate-example",
			base = file("."),
			settings = Defaults.defaultSettings ++ Seq(
				/* ADD THE DEPENDENCY TO YOUR STORAGE HERE */
				libraryDependencies ++= Seq(activateCore, activateJdbc, activatePrevayler, activateMongo, mysql, postgresql/*, objbd6*/),
				organization := "com.example.foo",
				scalaVersion := "2.10.1",
				version := "1.0",
				resolvers ++= Seq(localMavenRepo, fwbrasilRepo)))

}