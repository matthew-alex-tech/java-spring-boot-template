package org.mtech.controller;

import org.junit.jupiter.api.Test;
import org.mtech.dto.NewPerson;
import org.mtech.dto.Person;

import static org.assertj.core.api.Assertions.assertThat;

class PersonControllerTest extends BaseMockMvcTest {

    @Test
    void should_create_person() {
        // GIVEN
        NewPerson newPerson = NewPerson.builder()
                .firstName("Matt")
                .lastName("Tech")
                .build();

        // WHEN
        Person person = apiClient.createPerson(newPerson);

        // THEN
        assertThat(person.getId()).isGreaterThanOrEqualTo(1);
        assertThat(person.getFirstName()).isEqualTo("Matt");
        assertThat(person.getLastName()).isEqualTo("Tech");

        // WHEN
        Person person2 = apiClient.createPerson(NewPerson.builder()
                .firstName("John")
                .lastName("Doe")
                .build());

        // THEN
        assertThat(person2.getId()).isGreaterThan(person.getId());
        assertThat(person2.getFirstName()).isEqualTo("John");
        assertThat(person2.getLastName()).isEqualTo("Doe");
    }
}
