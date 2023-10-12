package com.stackroute.exceptions;

public class ResourceAlreadyPresentException extends RuntimeException {
	

	String message;

	public ResourceAlreadyPresentException(String message)
	{
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
