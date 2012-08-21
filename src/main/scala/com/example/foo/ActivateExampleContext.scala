package com.example.foo

import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.memory.TransientMemoryStorage
import net.fwbrasil.activate.storage.prevayler.PrevaylerStorage
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.oracleDialect
import net.fwbrasil.activate.storage.relational.idiom.mySqlDialect
import net.fwbrasil.activate.storage.relational.idiom.postgresqlDialect
import net.fwbrasil.activate.storage.mongo.MongoStorage

// Initially, must be created the context of Activate. The context must be a singleton, so it makes sense to declare as "object".
object activateExampleContext extends ActivateContext {

	// It is important that the context name is unique, but you can have multiple contexts in the same VM.
	def contextName = "activateExampleContext"

	val storage = new TransientMemoryStorage

	//	val storage = new PrevaylerStorage

	//	val storage = new MongoStorage {
	//		val host = "localhost"
	//		override val port = 27017
	//		val db = "dbName"
	//		override val authentication = Option("user", "pass")
	//	}

	//	val storage = new PooledJdbcRelationalStorage {
	//		val jdbcDriver = "com.mysql.jdbc.Driver"
	//		val user = "root"
	//		val password = "root"
	//		val url = "jdbc:mysql://127.0.0.1/test"
	//		val dialect = mySqlDialect
	//	}

	//	val storage = new PooledJdbcRelationalStorage {
	//		val jdbcDriver = "oracle.jdbc.driver.OracleDriver"
	//		val user = "USER"
	//		val password = "PASS"
	//		val url = "jdbc:oracle:thin:@localhost:1521:oracle"
	//		val dialect = oracleDialect
	//	}

	//	val storage = new PooledJdbcRelationalStorage {
	//		val jdbcDriver = "org.postgresql.Driver"
	//		val user = "postgres"
	//		val password = "teste"
	//		val url = "jdbc:postgresql://127.0.0.1/postgres"
	//		val dialect = postgresqlDialect
	//	}

}