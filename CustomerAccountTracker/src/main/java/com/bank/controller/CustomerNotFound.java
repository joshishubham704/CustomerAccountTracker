package com.bank.controller;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException {
	
	public CustomerNotFound(String message) {
		super(message);
		}
}
