package com.organization.project.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
