package com.project.FoodsolomonBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//db연결시 (exclude) 삭제
public class FoodsolomonBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodsolomonBackendApplication.class, args);
	}

}
