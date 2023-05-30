package com.pepe.configserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigservApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigservApplication.class, args);
	}

}
