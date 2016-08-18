package com.organization.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.organization.project.server.ServletContainerCustomizer;

@Configuration
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(new Object[] { Application.class, ServletContainerCustomizer.class }, args);
	}

}
