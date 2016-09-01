package com.organization.project.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.organization.project.model.GenericResponse;

@PropertySource("classpath:config.properties")
public class CommonController {

	final static Logger LOGGER = Logger.getLogger(CommonController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Environment environment;

	private Gson gson;

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

	protected <T> Object getDataFromJson(String jString, Class<?> clazz) {
		Object result = null;
		try {
			Object value = (new JSONTokener(jString)).nextValue();
			if (value instanceof String || value instanceof Boolean || value instanceof Integer) {
				return value;
			} else if (value instanceof JsonObject){
				result = convertJsonToObject(jString, clazz);
			} else if (value instanceof JsonArray){
				result = getListFromJsonArray((JSONArray)value, clazz);
			}
		} catch (Exception e) {
			LOGGER.info("error converting fromJson to " + clazz.getName(), e);
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}
		return result;
	}

	private List<Class<?>> getListFromJsonArray(JSONArray jArray, Class<?> clazz) throws JSONException {
		List<Class<?>> returnList = new ArrayList<Class<?>>();
		for (int i = 0; i < jArray.length(); i++) {
			returnList.add((Class<?>) convertJsonToObject(jArray.get(i).toString(), clazz));
		}
		return returnList;
	}
	
	private Object convertJsonToObject(String jString, Class<?> clazz){
		gson = new Gson();
		return gson.fromJson(jString, clazz);
	}

}
