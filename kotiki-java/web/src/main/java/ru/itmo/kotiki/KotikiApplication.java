package ru.itmo.kotiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KotikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotikiApplication.class, args);
	}
}
