package com.organization.project.controller;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.model.GenericResponse;
import com.organization.project.model.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController extends BaseController{

	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(method = RequestMethod.GET, value = "/getname")
	public GenericResponse greeting(@RequestParam(value = "name", defaultValue = "world") String name, HttpServletRequest request) {
		genericResponse = new GenericResponse();
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
		return sendResponse(Boolean.FALSE, "success", greeting, request);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public GenericResponse halo(@RequestBody HashMap<?, ?> coba, HttpServletRequest request) {
		return sendResponse(Boolean.FALSE, "success", coba, request);
	}
}
