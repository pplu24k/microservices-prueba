package com.pepe.userserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserservApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservApplication.class, args);
	}

}
