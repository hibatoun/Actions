package TestActionsHiba.example.ActionsHiba;

import org.springframework.boot.SpringApplication;

public class TestActionsHibaApplication {

	public static void main(String[] args) {
		SpringApplication.from(ActionsHibaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
