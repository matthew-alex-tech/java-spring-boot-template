import org.example.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    void should_create_person() {
        // GIVEN
        Person person = Person.builder()
                .firstName("Matt")
                .lastName("Tech")
                .build();

        // EXPECT
        assertThat(person.getFirstName()).isEqualTo("Matt");
        assertThat(person.getLastName()).isEqualTo("Tech");
    }
}
