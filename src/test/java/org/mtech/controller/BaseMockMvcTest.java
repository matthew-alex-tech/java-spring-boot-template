package org.mtech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.TestInstance;
import org.mtech.app.Application;
import org.mtech.app.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {Application.class, WebConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@WebAppConfiguration
//@ContextConfiguration(classes = WebConfig.class)
@ActiveProfiles("test")
public class BaseMockMvcTest {

    @Value("${server.host}")
    protected String host;

    @Value("${server.port}")

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    protected ApiClient apiClient;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        apiClient = new ApiClient(mvc, objectMapper);
    }
}
