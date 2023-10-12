package com.stackroute.feedbackservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> Exception(IdNotFoundException exception, WebRequest webRequest) {

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);

	}

	/*
	 * Application Exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exception(Exception exception, WebRequest webRequest) {

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}