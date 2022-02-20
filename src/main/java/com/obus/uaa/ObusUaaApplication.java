package com.obus.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ObusUaaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObusUaaApplication.class, args);
	}
}
