package com.organization.project.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.organization.project.common.CommonController;

@Controller
public class MainController extends CommonController {

	@RequestMapping({"","/"})
	public String index(Model model, Locale local) {
		model.addAttribute("appTitle", getMessageValue("app.title", null, local));
		model.addAttribute("appContent", getMessageValue("app.content", null, local));
		return "index";
	}

}
