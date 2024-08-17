package com.solidvessel.order.integrationtest;

import com.solidvessel.shared.Profiles;
import com.solidvessel.shared.test.database.TestEntityHelper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Repository.class}))
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles(Profiles.INTEGRATION_TEST)
public class BaseDatabaseTest extends TestEntityHelper {

    public static final PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:16.4")
            .withDatabaseName("order")
            .withUsername("nihilus")
            .withPassword("nihilus");

    @BeforeAll
    public static void initializeContainer() {
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresqlContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }
}
