package org.mtech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.TestInstance;
import org.mtech.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseMockMvcTest {

    @Autowired
    protected MockMvc mvc;

    protected ApiClient apiClient;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setup() {
        apiClient = new ApiClient(mvc, objectMapper);
    }
}
