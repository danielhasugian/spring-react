package com.organization.project.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.organization.project.model.GenericResponse;

@PropertySource("classpath:config.properties")
public class CommonController {
	
	final static Logger LOGGER = Logger.getLogger(CommonController.class);
		
	@Autowired
	private Environment environment;
	
	protected GenericResponse genericResponse;

	protected String getPropertiesValue(String propertiesName) {
		return environment.getProperty(propertiesName);
	}
	
	protected GenericResponse sendResponseSuccess(Object result, HttpServletRequest request){
		return sendResponse(Boolean.FALSE, "Success", result, request);
	}
	
	protected GenericResponse sendResponseSuccess(Object result,String message, HttpServletRequest request){
		return sendResponse(Boolean.FALSE, message, result, request);
	}
	
	protected GenericResponse sendResponseFailed(Object result, HttpServletRequest request){
		return sendResponse(Boolean.TRUE, "Failed", result, request);
	}
	
	protected GenericResponse sendResponseFailed(Object result, String message, HttpServletRequest request){
		return sendResponse(Boolean.TRUE, message, result, request);
	}
	
	private GenericResponse sendResponse(Boolean failed, String message, Object result, HttpServletRequest request){
		return new GenericResponse(new Date(), failed, message, result, request.getRequestURI());
	}
	
}
