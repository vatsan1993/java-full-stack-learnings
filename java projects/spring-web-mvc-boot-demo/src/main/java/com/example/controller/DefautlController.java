package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefautlController {
	
	@RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
	public String homeAction() {
		return "home";
	}
}
