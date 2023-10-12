package com.stackroute.packageservice.Exception;

public class IdNotFoundException extends RuntimeException {
	private String message;

	public IdNotFoundException(String message) {
		super();
		this.message= message;
	}

	public IdNotFoundException() {
		super();
	}

	public String getMessageString() {
		return message;
	}
	
	
}
