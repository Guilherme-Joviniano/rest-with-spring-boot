package br.com.joviniano.restwithspringboot.services

import br.com.joviniano.restwithspringboot.data.vo.v1.PersonVO
import br.com.joviniano.restwithspringboot.exceptions.ResourceNotFoundException
import br.com.joviniano.restwithspringboot.mapper.DozerMapper
import br.com.joviniano.restwithspringboot.models.Person
import br.com.joviniano.restwithspringboot.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger


@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): PersonVO {
        logger.info("finding one person!")
        val person = repository.findById(id).orElseThrow { ResourceNotFoundException("No records found to this id") }
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun findAll(): List<PersonVO> {
        logger.info("finding all people")
        val peoples = repository.findAll()
        return DozerMapper.parseListObjects(peoples, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("create person")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO {
        val entity = repository.findById(person.id).orElseThrow { ResourceNotFoundException("No records found to this id") }
        logger.info("updating a person")

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        val entity = repository.findById(id).orElseThrow { ResourceNotFoundException("No records found to this id") }
        repository.delete(entity)
    }
}