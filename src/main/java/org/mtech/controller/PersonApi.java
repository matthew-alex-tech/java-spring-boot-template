package org.mtech.controller;

import org.mtech.dto.NewPerson;
import org.mtech.dto.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/api/v1")
public interface PersonApi {

    @RequestMapping(value = "/persons",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Person>> getPersons();

    @RequestMapping(value = "/persons/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Person> getPerson(@PathVariable("id") Integer id);

    @RequestMapping(value = "/persons",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Person> addPerson(@RequestBody NewPerson body);

    @RequestMapping(value = "/persons/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Person> updatePerson(@PathVariable("id") Integer id, @RequestBody NewPerson body);

    @RequestMapping(value = "/persons/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id);
}
