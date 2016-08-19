package com.organization.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:config.properties")
public class RootController {

	@Autowired
	private Environment environment;

	public String getPropertiesValue(String propertiesName) {
		return environment.getProperty(propertiesName);
	}
}
