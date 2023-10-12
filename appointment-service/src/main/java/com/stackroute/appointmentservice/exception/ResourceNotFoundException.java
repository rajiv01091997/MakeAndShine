package com.stackroute.appointmentservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	String message;
	 public ResourceNotFoundException(String message)
	 {
		 this.message=message;
	 }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
