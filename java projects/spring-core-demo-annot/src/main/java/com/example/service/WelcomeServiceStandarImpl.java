package com.example.service;

import org.springframework.stereotype.Service;

@Service("ws")
public class WelcomeServiceStandarImpl implements WelcomeService {

	@Override
	public String doWelcome(String userName) {
		return "Hello! Welcome! "+ userName ;
	}

}
