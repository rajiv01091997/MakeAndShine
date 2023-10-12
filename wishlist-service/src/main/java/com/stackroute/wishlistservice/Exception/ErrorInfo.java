package com.stackroute.wishlistservice.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
	private LocalDateTime timeStamp;
	private String message;
	private String details;
	private HttpStatus httpStatus;
	
	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ErrorInfo(LocalDateTime timeStamp, String message, String details, HttpStatus httpStatus) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
