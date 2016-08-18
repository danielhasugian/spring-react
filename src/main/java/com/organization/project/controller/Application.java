package com.organization.project.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final int LISTENEDPORT = 99;

	public static void main(String[] args) {
		System.getProperties().put("server.port", LISTENEDPORT);
		SpringApplication.run(Application.class, args);
	}

}
