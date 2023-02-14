package org.mtech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mtech.dto.NewPerson;
import org.mtech.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RequiredArgsConstructor
public class ApiClient {
    private final MockMvc mvc;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public Person createPerson(NewPerson newPerson) {
        MockHttpServletResponse response = attemptCreatePerson(newPerson);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        return objectMapper.readValue(response.getContentAsString(), Person.class);
    }

    @SneakyThrows
    public MockHttpServletResponse attemptCreatePerson(NewPerson newPerson) {
        return mvc.perform(
                        post("/api/v1/persons")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(newPerson)))
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}
