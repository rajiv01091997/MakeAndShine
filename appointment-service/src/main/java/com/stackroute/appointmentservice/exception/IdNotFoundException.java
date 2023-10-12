package com.stackroute.appointmentservice.exception;

public class IdNotFoundException extends Exception {

      String message;
      public IdNotFoundException(String message) {
    	  
    	   this.message =  message;
      }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
      
}

