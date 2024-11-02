package TestActionsHiba.example.ActionsHiba;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class ActionsHibaApplicationTests {

	@Container
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.33")
			.withDatabaseName("test_db")
			.withUsername("test")
			.withPassword("test")
			.withReuse(true)
			.withCommand(
					"--character-set-server=utf8mb4",
					"--collation-server=utf8mb4_unicode_ci",
					"--default-authentication-plugin=mysql_native_password"
			)
			.withStartupTimeout(Duration.ofSeconds(60));

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
	}

	@BeforeAll
	static void beforeAll() {
		mysqlContainer.start();
	}

	@Test
	void contextLoads() {
		assertTrue(mysqlContainer.isRunning());
		assertNotNull(mysqlContainer.getJdbcUrl());
	}
}