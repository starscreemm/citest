package org.example;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

// Ми вказуємо Spring, що треба використати наш ініціалізатор
@ContextConfiguration(initializers = TestContainersBase.Initializer.class)
public abstract class TestContainersBase {

    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        postgres.start();
    }

    // Спеціальний клас для чистого Spring, щоб прокинути налаштування
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            MapPropertySource testcontainers = new MapPropertySource("testcontainers", Map.of(
                    "jdbc.url", postgres.getJdbcUrl(),
                    "jdbc.username", postgres.getUsername(),
                    "jdbc.password", postgres.getPassword(),
                    "jdbc.driverClassName", postgres.getDriverClassName()
            ));
            configurableApplicationContext.getEnvironment().getPropertySources().addFirst(testcontainers);
        }
    }
}