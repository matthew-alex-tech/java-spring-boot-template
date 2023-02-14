package org.mtech.controller;

import org.mtech.dto.NewPerson;
import org.mtech.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class PersonController implements PersonApi {

    // TODO: add a service and repository
    private static final List<Person> personList = new ArrayList<>();
    private static final Object lock = new Object();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personList);
    }

    @Override
    public ResponseEntity<Person> getPerson(Integer id) {
        return ResponseEntity.of(findPersonById(id));
    }

    @Override
    public ResponseEntity<Person> addPerson(NewPerson body) {
        if (body == null || body.getFirstName() == null || body.getLastName() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        synchronized (lock) {
            Person person = Person.builder()
                    .id(nextId.getAndIncrement())
                    .firstName(body.getFirstName())
                    .lastName(body.getLastName())
                    .build();
            personList.add(person);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Person> updatePerson(Integer id, NewPerson body) {
        synchronized (lock) {
            Optional<Person> personOptional = findPersonById(id);
            if (personOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (body == null || body.getFirstName() == null || body.getLastName() == null) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            Person person = personOptional.get();
            person.setFirstName(body.getFirstName());
            person.setLastName(body.getLastName());
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Void> deletePerson(Integer id) {
        synchronized (lock) {
            Optional<Person> personOptional = findPersonById(id);
            if (personOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            personList.removeIf(p -> p.getId().equals(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private Optional<Person> findPersonById(Integer id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
