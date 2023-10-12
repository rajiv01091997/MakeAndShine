package com.stackroute.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorInfo> idNotFound(ResourceNotFoundException resourceNotFoundException , WebRequest webRequest)
	{
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setMessage(resourceNotFoundException.getMessage());
		errorInfo.setPath(webRequest.getDescription(false));
		errorInfo.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyPresentException.class)
	public ResponseEntity<ErrorInfo> idAlreadyPresent(ResourceAlreadyPresentException resourceNotFoundException , WebRequest webRequest)
	{
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setMessage(resourceNotFoundException.getMessage());
		errorInfo.setPath(webRequest.getDescription(false));
		errorInfo.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exception(Exception exception , WebRequest webRequest)
	{
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setPath(webRequest.getDescription(false));
		errorInfo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
