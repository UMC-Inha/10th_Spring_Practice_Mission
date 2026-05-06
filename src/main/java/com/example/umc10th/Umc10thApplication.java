package com.example.umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EntityListeners 정상 이용을 위해 붙임
public class Umc10thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc10thApplication.class, args);
	}

}
