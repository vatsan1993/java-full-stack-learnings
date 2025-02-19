package com.example.service;

public class WelcomeServiceEnhancedImpl implements WelcomeService {
	
	private MessageProvider msgProvider;

	public WelcomeServiceEnhancedImpl() {
		
	}
	
	
	public WelcomeServiceEnhancedImpl(MessageProvider msgProvider) {
		this.msgProvider = msgProvider;
	}
	
	@Override
	public String doWelcome(String userName) {
		// TODO Auto-generated method stub
		return msgProvider.getMessage() +  " "+ userName;
	}


	public MessageProvider getMsgProvider() {
		return msgProvider;
	}


	public void setMsgProvider(MessageProvider msgProvider) {
		this.msgProvider = msgProvider;
	}

}
