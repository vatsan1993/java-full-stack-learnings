package com.example.service;

public class WelcomeServiceStandarImpl implements WelcomeService {

	@Override
	public String doWelcome(String userName) {
		return "Hello! Welcome! "+ userName ;
	}

}
