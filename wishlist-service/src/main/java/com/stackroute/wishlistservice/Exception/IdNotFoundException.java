package com.stackroute.wishlistservice.Exception;



public class IdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public String message;

	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}

}
