package com.epam.controllers;

import com.epam.exceptions.PersonNotFoundException;
import com.epam.model.Person;
import com.epam.repository.PersonRepository;
import com.epam.utils.TrackTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/persons/")
class PersonController {

    @Autowired
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @TrackTime
    @GetMapping("/")
    public List<Person> getAll() {
        return repository.findAll();
    }

    @TrackTime
    @PostMapping("/")
    public Person createPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @TrackTime
    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return repository.findById(id)
            .map(person -> {
                person.setFirstName(newPerson.getFirstName());
                person.setLastName(newPerson.getLastName());
                person.setMarried(newPerson.getMarried());
                person.setAge(newPerson.getAge());
                return repository.save(person);
            })
            .orElseGet(() -> {
                newPerson.setId(id);
                return repository.save(newPerson);
            });
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PersonNotFoundException(id);
        }
    }
}
