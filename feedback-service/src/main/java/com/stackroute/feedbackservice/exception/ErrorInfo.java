package com.stackroute.feedbackservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorInfo {

	private LocalDateTime timestamp;
	private HttpStatus httpStatus;
	private String message;
	private String details;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(LocalDateTime timestamp, HttpStatus httpStatus, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
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

}