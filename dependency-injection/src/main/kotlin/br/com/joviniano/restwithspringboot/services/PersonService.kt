package br.com.joviniano.restwithspringboot.services

import br.com.joviniano.restwithspringboot.exceptions.ResourceNotFoundException
import br.com.joviniano.restwithspringboot.models.Person
import br.com.joviniano.restwithspringboot.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger


@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("finding one person!")
        return repository.findById(id).orElseThrow { ResourceNotFoundException("No records found to this id") }
    }

    fun findAll(): List<Person> {
        logger.info("finding all people")
        return repository.findAll()
    }

    fun create(person: Person): Person {
        logger.info("create person")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        val entity = repository.findById(person.id).orElseThrow { ResourceNotFoundException("No records found to this id") }
        logger.info("updating a person")

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    fun delete(id: Long) {
        val entity = repository.findById(id).orElseThrow { ResourceNotFoundException("No records found to this id") }
        repository.delete(entity)
    }
}