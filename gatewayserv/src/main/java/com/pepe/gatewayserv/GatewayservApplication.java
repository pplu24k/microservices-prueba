package com.pepe.gatewayserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayservApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayservApplication.class, args);
	}

}
