package com.example.foo

import com.example.foo.activateExampleContext._

// An entity shall extend the trait "Entity"
// You can declare the properties as val or var, where they are immutable or not.
abstract class Person(var name: String) extends Entity
class NaturalPerson(name: String, var motherName: String) extends Person(name)
class LegalPerson(name: String, var director: NaturalPerson) extends Person(name)

object ActivateExample extends App {

	// Use whenever entities within transactions
	// It is not necessary to call a method like "store" or "save" to add the entity. 
	// Just create, use, and it will be persisted. 
	transactional {
		val person = new NaturalPerson("John", "Marie")
		person.name = "John2"
		println(person.name)
	}

	// Queries
	// The query operators available are: ==, <,:>, <=,> =, isNone, isSome,: | | and: &&. 
	// Note that the queries can be made about abstract entities (abstract trait and class).
	val q = query {
		(person: Person) => where(person.name :== "John2") select (person)
	}

	// Perform queries within transactions
	transactional {
		val result = q.execute
		for (person <- result)
			println(person.name)
	}

	// There are alternative forms of query. 
	// With the allWhere you can use a list of criterias.
	transactional {
		val personList1 = all[Person]
		val personList2 = allWhere[NaturalPerson](_.name :== "John2", _.motherName :== "Marie")
		println(personList1, personList2)
	}

	// Queries using more than one entity or nested properties
	// Note: Queries involving more than one entity are not supported by MongoStorage.
	transactional {
		new LegalPerson("comp", all[NaturalPerson].head)
		val res1 = executeQuery {
			(company: LegalPerson, director: NaturalPerson) => where(company.director :== director) select (company, director)
		}
		println(res1)
		val res2 = executeQuery {
			(company: LegalPerson) => where(company.director.name :== "John2") select (company)
		}
		println(res2)
	}

	// To delete an entity
	transactional {
		for (person <- all[Person])
			person.delete
	}

	// Typically transactional blocks are controlled by the framework. 
	// But you can control the transaction as follows
	val transaction = new Transaction
	transactional(transaction) {
		new NaturalPerson("Test", "Mother")
	}
	transaction.commit

	// Defining the propagation of the transaction
	transactional {
		val person = new NaturalPerson("Test", "Mother")
		transactional(mandatory) {
			person.name = "Test2"
		}
		println(person.name)
	}

	// Nested transactions are a type of propagation
	transactional {
		val person = new NaturalPerson("Test", "Mother")
		transactional(nested) {
			person.name = "Test2"
		}
		println(person.name)
	}
}