package com.organization.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.model.GenericResponse;
import com.organization.project.model.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController extends RootController{

	private final AtomicLong counter = new AtomicLong();
	private GenericResponse genericResponse;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getname")
	public GenericResponse greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		genericResponse = new GenericResponse();
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
		genericResponse.setFailed(false);
		genericResponse.setResult(greeting);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/coba");
		genericResponse.setMessage("success");
		return genericResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public GenericResponse halo(@RequestBody HashMap<?, ?> coba) {
		genericResponse = new GenericResponse();
		genericResponse.setFailed(false);
		genericResponse.setResult(coba);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/coba");
		genericResponse.setMessage("success");
		return genericResponse;
	}
}
