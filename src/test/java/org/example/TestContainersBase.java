package org.example;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;


public abstract class TestContainersBase {

    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", postgreSQLContainer::getJdbcUrl);
        registry.add("jdbc.username", postgreSQLContainer::getUsername);
        registry.add("jdbc.password", postgreSQLContainer::getPassword);
        registry.add("jdbc.driverClassName", postgreSQLContainer::getDriverClassName);
    }
}
