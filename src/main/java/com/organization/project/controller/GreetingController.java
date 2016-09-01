package com.organization.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.common.CommonController;
import com.organization.project.model.GenericResponse;
import com.organization.project.model.Greeting;
import com.organization.project.util.JsonUtil;

@RestController
@RequestMapping("/greeting")
public class GreetingController extends CommonController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = RequestMethod.GET, value = "/getname")
	public GenericResponse greeting(@RequestParam(value = "name", defaultValue = "world") String name,
			HttpServletRequest request) {
		genericResponse = new GenericResponse();
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
		return sendResponseSuccess(greeting, request.getRequestURI());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public GenericResponse halo(@RequestBody HashMap<?, ?> coba, HttpServletRequest request) {
		return sendResponseSuccess(coba, request.getRequestURI());
	}

	@RequestMapping(value = "/example1")
	public String example1(HttpServletRequest request) {
		throw new NullPointerException("error sampling");
	}

	@RequestMapping(value = "/example2")
	public String example2(@RequestParam(value = "name", defaultValue = "world") String name, HttpServletRequest request) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("error sampling");
		}
		return "";
	}
	
	@RequestMapping(value = "/example3")
	public GenericResponse example3(@Valid @RequestBody Greeting greeting, HttpServletRequest request) {
		return sendResponseSuccess(greeting, request.getRequestURI());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/example4")
	public GenericResponse example4(@RequestBody String jsonString, HttpServletRequest request) {
		List<Greeting> greetings = (List<Greeting>) JsonUtil.getDataFromJson(jsonString);
		
		return sendResponseSuccess(greetings, request.getRequestURI());
	}
}
