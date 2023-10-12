package com.stackroute.appointmentservice.exception;



public class InvalideDateException extends RuntimeException {
      
	String message;
	public InvalideDateException(String message) {
		
		this.message = message;
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
