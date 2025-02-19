package com.example.service;

import java.time.LocalTime;

public class MessageProviderImpl implements MessageProvider{
	@Override
	public String getMessage() {
		String message = null;
		int h = LocalTime.now().getHour();
		
		if(h>=4 && h<=11) {
			message = "Good Morning";
		}else if(h >= 12 && h <= 16) {
			message = "Good AfterNoon";
		}else {
			message = "Good Evening";
		}	
		return message;
	}
}
