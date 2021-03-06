package com.organization.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.common.CommonController;
import com.organization.project.domain.postgresql.User;
import com.organization.project.model.GenericResponse;
import com.organization.project.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController extends CommonController {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public GenericResponse getUser(HttpServletRequest request) {
		return sendResponseSuccess(userRepository.findAll(), request.getRequestURI());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public GenericResponse saveUser(@RequestBody HashMap<?, ?> jsonUser, HttpServletRequest request) {
		user = new User();
		user.setUsername((String) jsonUser.get("username"));
		user.setPassword((String) jsonUser.get("password"));
		user.setDivision((String) jsonUser.get("division"));
		try {
			userRepository.save(user);
			return sendResponseSuccess(userRepository.findAll(), request.getRequestURI());
		} catch (Exception e) {
			return sendResponseSuccess(null, request.getRequestURI());
		}
	}

}
