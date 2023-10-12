package com.stackroute.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	String message;

	public ResourceNotFoundException(String message)
	{
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
