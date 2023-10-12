
package com.stackroute.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		errorResponse.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setDetails(webRequest.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
