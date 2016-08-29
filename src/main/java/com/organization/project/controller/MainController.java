package com.organization.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController extends BaseController {

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("appTitle", getPropertiesValue("application.title"));
		model.addAttribute("appContent", getPropertiesValue("application.content"));
		return "index";
	}

}
