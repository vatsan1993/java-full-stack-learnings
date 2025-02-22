package com.example.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.WelcomeService;

public class WelcomeApplication4 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
		
		WelcomeService ws = (WelcomeService) context.getBean("wseb");
		System.out.println(ws.doWelcome("Srivatsan"));
		
	}
}
