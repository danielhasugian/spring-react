package com.organization.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping("/")
	public String index() {
		return "<h1><center>Greetings from Spring Boot!</center></h1>";
	}
	
}
