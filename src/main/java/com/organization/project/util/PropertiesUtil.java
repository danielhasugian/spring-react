package com.organization.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:config.properties")
public class PropertiesUtil {

	@Autowired
	private Environment environment;

	public String getPropertiesValue(String propertiesName) {
		return environment.getProperty(propertiesName);
	}
	

}
