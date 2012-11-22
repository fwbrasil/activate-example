package com.example.foo

import activateExampleContext._
import net.fwbrasil.activate.migration.Migration

class CreateSchemaMigration extends Migration {

	def timestamp = 201225081211l

	def up {

		table[NaturalPerson]
			.createTable(
				_.column[String]("name"),
				_.column[String]("motherName"))

		table[LegalPerson]
			.createTable(
				_.column[String]("name"),
				_.column[NaturalPerson]("director"))
	}

}