package com.eventapi.eventapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Event API"), servers = @Server(url = "/", description = "API"))
@SpringBootApplication
public class EventApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApiApplication.class, args);
	}

}
