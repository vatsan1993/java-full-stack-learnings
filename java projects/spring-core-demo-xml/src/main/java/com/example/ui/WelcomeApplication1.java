package com.example.ui;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.WelcomeService;
import com.example.service.WelcomeServiceStandarImpl;

public class WelcomeApplication1 {
	public static void main(String[] args) {
		// this is a service that we created.
//		WelcomeService ws = new WelcomeServiceStandarImpl();
//		System.out.println(ws.doWelcome("Srivatsan"));
		
		// this is how we can use spring to create the service.
		ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");
		WelcomeService ws = (WelcomeService) context.getBean("wsb");
		System.out.println(ws.doWelcome("Srivatsan"));
		
		
		WelcomeService ws2 = (WelcomeService) context.getBean("wsab1");
		System.out.println(ws2.doWelcome("Srivatsan"));
		
		WelcomeService ws3 = (WelcomeService) context.getBean("wseb1");
		System.out.println(ws3.doWelcome("Srivatsan"));
		
		
	}

}
