package com.organization.project.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.domain.postgresql.User;
import com.organization.project.model.GenericResponse;
import com.organization.project.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

	final static Logger LOGGER = Logger.getLogger(LoginController.class);
	@Autowired
	private UserRepository userRepository;
	private String token;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public GenericResponse login(@RequestBody HashMap<String, ?> userLogin, HttpServletRequest request) throws ServletException {
		String username = (String) userLogin.get("username");
		String password = (String) userLogin.get("password");
		try {
			User user = userRepository.findByUsername(username);
			if (password.equals(user.getPassword())) {
				token = Jwts.builder().setSubject(user.getUsername())
						.claim("roles", user.getUsername())
						.setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, "secretkey")
						.compact();
				
				return sendResponse(Boolean.FALSE, "Success Generate", token, request);
			}else{
				return sendResponse(Boolean.FALSE, "Invalid Password", null, request);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return sendResponse(Boolean.FALSE, "Failed Generate", null, request);
		}
	}

}
