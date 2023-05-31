package com.pepe.carserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarservApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarservApplication.class, args);
	}

}
