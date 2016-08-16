package com.organization.project.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class Application {
	private static final int LISTENEDPORT = 88;

	public static void main(String[] args) {
		System.getProperties().put("server.port", LISTENEDPORT);
		SpringApplication.run(Application.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
	}

}
