package com.likhith.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FashionServiceFlipkartDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionServiceFlipkartDemoApplication.class, args);
	}

}