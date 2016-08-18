package com.organization.project.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.domain.User;
import com.organization.project.model.GenericResponse;
import com.organization.project.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	private GenericResponse genericResponse;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public GenericResponse getUser() {
		genericResponse = new GenericResponse();
		genericResponse.setFailed(false);
		genericResponse.setResult(userRepository.findAll());
		genericResponse.setDate(new Date());
		genericResponse.setPath("/user/");
		genericResponse.setMessage("success");
		return genericResponse;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public GenericResponse saveUser(@RequestBody HashMap<?, ?> jsonUser) {
		user = new User();
		user.setUserName((String) jsonUser.get("username"));
		user.setPassword((String) jsonUser.get("password"));
		user.setDivision((String) jsonUser.get("division"));
		genericResponse = new GenericResponse();
		try {
			userRepository.save(user);
			genericResponse.setFailed(false);
			genericResponse.setResult("Save success");
			genericResponse.setDate(new Date());
			genericResponse.setPath("/user/");
			genericResponse.setMessage("success");
		} catch (Exception e) {
			genericResponse.setFailed(true);
			genericResponse.setResult("Save failed");
			genericResponse.setDate(new Date());
			genericResponse.setPath("/user/");
			genericResponse.setMessage(e.getMessage());
		}
		return genericResponse;
	}

}
