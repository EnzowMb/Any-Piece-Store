package com.enzomb.anypiecestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories //Seria como o JPA, que o banco de dados SQL, no caso do mongo é NoSQL, por isso precisa
public class  AnyPieceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnyPieceStoreApplication.class, args);
	}

}
