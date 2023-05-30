package com.pepe.eurekaserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaservApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaservApplication.class, args);
	}

}
