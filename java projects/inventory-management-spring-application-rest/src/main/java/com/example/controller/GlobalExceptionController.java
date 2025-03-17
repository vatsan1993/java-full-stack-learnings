package com.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.exception.ImsException;

@RestControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(ImsException.class)
	public ResponseEntity<String> handleImsException(ImsException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(ImsException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
