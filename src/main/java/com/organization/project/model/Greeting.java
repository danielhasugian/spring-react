package com.organization.project.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Greeting {

	@NotNull(message = "tidak boleh null")
	private long id;
	
	@NotNull(message = "tidak boleh null")
	@NotBlank(message = "tidak boleh kosong")
	private String content;

	public Greeting() {
	}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", content=" + content + "]";
	}

}
