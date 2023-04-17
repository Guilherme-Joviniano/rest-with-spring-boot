package br.com.joviniano.restwithspringboot.controllers

import br.com.joviniano.restwithspringboot.models.Person
import br.com.joviniano.restwithspringboot.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/person")

// Default producer and consume is JSON
class PersonController {

    val counter: AtomicLong = AtomicLong()

    @Autowired
    private lateinit var service: PersonService

    @GetMapping()
    fun findAll(): List<Person> {
        return service.findAll()
    }

    @GetMapping(value = ["/{id}"])
    fun findById(@PathVariable(value="id") id: Long): Person {
        return service.findById(id)
    }

    @PostMapping()
    fun create(@RequestBody person: Person): Person {
        return service.create(person)
    }

    @PutMapping()
    fun update(@RequestBody person: Person): Person {
        return service.update(person)
    }

    // For change the default status in a not content response do this ->
    @DeleteMapping(value = ["/{id}"])
    fun delete(@PathVariable(value= "id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }




}