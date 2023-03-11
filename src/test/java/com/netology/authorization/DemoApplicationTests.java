package com.netology.authorization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    private static final String ENDPOINT = "/profile";

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();

    }

    @Test
    void contextLoadsDev() {
        final String expected = "Current profile is dev";
        ResponseEntity<String> devResponse = restTemplate.getForEntity("http://localhost"
                + devapp.getMappedPort(8080) + ENDPOINT, String.class);
        System.out.println(devResponse.getBody());
        Assertions.assertEquals(expected, devResponse.getBody());
    }

    @Test
    void contextLoadsProd() {
        final String expected = "Current profile is production";
        ResponseEntity<String> prodResponse = restTemplate.getForEntity("http://localhost"
                + prodapp.getMappedPort(8081) + ENDPOINT, String.class);
        System.out.println(prodResponse.getBody());
        Assertions.assertEquals(expected, prodResponse.getBody());
    }
}