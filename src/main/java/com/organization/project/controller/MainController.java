package com.organization.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController extends RootController {
	
	@RequestMapping("/*")
	public String index() {
		 return "index";
	}
	
}
