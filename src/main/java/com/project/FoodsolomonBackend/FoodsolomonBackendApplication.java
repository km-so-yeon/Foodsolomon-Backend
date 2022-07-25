package com.project.FoodsolomonBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class FoodsolomonBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodsolomonBackendApplication.class, args);
	}

}