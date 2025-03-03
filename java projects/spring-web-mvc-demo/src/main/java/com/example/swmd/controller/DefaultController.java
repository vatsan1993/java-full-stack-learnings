package com.example.swmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller

public class DefaultController {
	// maps to 
	// localhost:9090/spring-web-mvc-demo1
	// localhost:9090/spring-web-mvc-demo1/
	// localhost:9090/spring-web-mvc-demo1/home
	@RequestMapping(value= {"", "/", "/home"}, method = RequestMethod.GET)
	public String getName() {
		// return the view name 
		// the view needs to be placed in the folder specified in the viewResolver
		// in this case its views which needs to be created inside the webApps.
		// name of the file will be home.jsp
		return "home";
	}
	
}
