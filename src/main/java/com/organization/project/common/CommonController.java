package com.organization.project.common;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.organization.project.model.GenericResponse;


@PropertySource("classpath:config.properties")
public class CommonController {

	final static Logger LOGGER = Logger.getLogger(CommonController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Environment environment;

	protected GenericResponse genericResponse;

	protected String getPropertiesValue(String propertiesCode) {
		return environment.getProperty(propertiesCode);
	}

	protected String getMessageValue(String messageCode) {
		return getMessageValue(messageCode, null, Locale.ENGLISH);
	}

	protected String getMessageValue(String messageCode, Locale locale) {
		return getMessageValue(messageCode, null, locale);
	}

	protected String getMessageValue(String messageCode, String[] params, Locale locale) {
		String result = "";
		try {
			result = messageSource.getMessage(messageCode, params, locale);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	protected GenericResponse sendResponseSuccess(Object result, String path) {
		return sendResponse(Boolean.FALSE, "Success", result, path);
	}

	protected GenericResponse sendResponseSuccess(Object result, String message, String path) {
		return sendResponse(Boolean.FALSE, message, result, path);
	}

	protected GenericResponse sendResponseFailed(Object result, String path) {
		return sendResponse(Boolean.TRUE, "Failed", result, path);
	}

	protected GenericResponse sendResponseFailed(Object result, String message, String path) {
		return sendResponse(Boolean.TRUE, message, result, path);
	}

	private GenericResponse sendResponse(Boolean failed, String message, Object result, String path) {
		return new GenericResponse(new Date(), failed, message, result, path);
	}

}
