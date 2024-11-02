package TestActionsHiba.example.ActionsHiba;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@Configuration
public class TestcontainersConfiguration {

	@Bean(initMethod = "start")
	public MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>("mysql:8.0.33")
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
	}
}