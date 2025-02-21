package com.example.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.WelcomeService;
import com.example.service.WelcomeServiceAdvancedImpl;

public class WelcomeApplication3 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
		WelcomeService ws = (WelcomeService) context.getBean("wsabProto");
		System.out.println(ws.doWelcome("Srivatsan"));
		
		// changing the parameter value of the bean using java.
		((WelcomeServiceAdvancedImpl ) ws).setWelcomeMessage("Hello");
		System.out.println(ws.doWelcome("Srivatsan")); // Hello Srivastan
		
		// requesting the same bean again
		WelcomeService ws2 = (WelcomeService) context.getBean("wsabProto");
		System.out.println(ws2.doWelcome("Srivatsan"));// Hello Srivastan
	}
}
