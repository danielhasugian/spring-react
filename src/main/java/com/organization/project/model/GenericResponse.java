package com.organization.project.model;

import java.util.Date;

public class GenericResponse {

	private Date timestamp;
	private Boolean failed;
	private String message;
	private Object result;
	private String path;
		
	public GenericResponse() {
	}
	
	public GenericResponse(Date timestamp, Boolean failed, String message, Object result, String path) {
		this.timestamp = timestamp;
		this.failed = failed;
		this.message = message;
		this.result = result;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getFailed() {
		return failed;
	}
	public void setFailed(Boolean failed) {
		this.failed = failed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
