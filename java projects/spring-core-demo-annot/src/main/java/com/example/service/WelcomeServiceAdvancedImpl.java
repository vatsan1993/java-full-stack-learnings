package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("wsa")
public class WelcomeServiceAdvancedImpl implements WelcomeService {
//	injecting a premitive datatype 
//	@Value("Hello! Welcome!")
	
	@Value("#{'${welcome.message}'}")
	private String welcomeMessage;
	
	public WelcomeServiceAdvancedImpl() {
		
	}
	
	public WelcomeServiceAdvancedImpl(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	
	@Override
	public String doWelcome(String userName) {
		// TODO Auto-generated method stub
		return welcomeMessage + userName;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
}
