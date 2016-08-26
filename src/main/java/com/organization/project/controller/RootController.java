package com.organization.project.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.organization.project.model.GenericResponse;

@PropertySource("classpath:config.properties")
public class RootController {

	@Autowired
	private Environment environment;
	
	protected GenericResponse genericResponse;

	protected String getPropertiesValue(String propertiesName) {
		return environment.getProperty(propertiesName);
	}
	
	protected GenericResponse sendResponse(Boolean failed, String message, Object result){
		return new GenericResponse(new Date(), failed, message, result, "path");
	}
	
}
