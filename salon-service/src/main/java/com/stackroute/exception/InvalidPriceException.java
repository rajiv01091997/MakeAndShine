package com.stackroute.exception;

public class InvalidPriceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidPriceException() {
		super();
	}

	public InvalidPriceException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
