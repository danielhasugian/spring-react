package com.organization.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.organization.project.security.ApplicationSecurity;
import com.organization.project.server.ServletContainerCustomizer;

@Configuration
@SpringBootApplication
public class Application {

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new ApplicationSecurity();
	}

	public static void main(String[] args) {
		SpringApplication.run(new Object[] { Application.class, ServletContainerCustomizer.class }, args);
	}

}
