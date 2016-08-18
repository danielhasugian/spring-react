package com.organization.project.controller;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.pojo.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private HashMap<String, Object> mapOut;
	
	@RequestMapping(method = RequestMethod.GET, value = "/coba")
	public HashMap<String, Object> greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		mapOut = new HashMap<>();
		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		mapOut.put("failed", false);
		mapOut.put("result", greeting);
		return mapOut;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public HashMap<?, ?> halo(@RequestBody HashMap<?, ?> coba) {
		mapOut = new HashMap<>();
		mapOut.put("failed", false);
		mapOut.put("result", coba);
		return mapOut;
	}
}
