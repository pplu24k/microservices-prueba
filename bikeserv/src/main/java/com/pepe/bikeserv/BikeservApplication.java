package com.pepe.bikeserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BikeservApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeservApplication.class, args);
	}

}
