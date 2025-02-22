package com.example.ui;



import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.example.config.MyConfig;
import com.example.service.WelcomeService;
import com.example.service.WelcomeServiceStandarImpl;

public class WelcomeApplication1 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		WelcomeService ws = (WelcomeService) context.getBean("ws");
		System.out.println(ws.doWelcome("Srivatsan"));
		
		
		WelcomeService ws2 = (WelcomeService) context.getBean("wsa");
		System.out.println(ws2.doWelcome("Srivatsan"));
		
		WelcomeService ws3 = (WelcomeService) context.getBean("wse");
		System.out.println(ws3.doWelcome("Srivatsan"));
		
		System.out.println(context.getBean("today"));
		
	}

}
