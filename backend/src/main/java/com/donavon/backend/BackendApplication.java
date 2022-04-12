package com.donavon.backend;

import com.donavon.backend.model.User;
import com.donavon.backend.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(UserRepository repository) {
	// 	return args -> {
	// 		String username = "notARealPerson";
	// 		String email = "fakeEmail@gmail.com";
	// 		String password = "aGreatPassword";
	// 		User user = new User(username, email, password);

	// 		repository.insert(user);
	// 	};
	// }

}
