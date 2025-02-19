package com.example.service;

public class WelcomeServiceAdvancedImpl implements WelcomeService {
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
