package com.multipledbcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MultipleDbConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbConnectionApplication.class, args);
	}

}
