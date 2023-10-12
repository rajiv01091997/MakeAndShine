package com.stackroute.wishlistservice.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
	public class GlobalException {
		/*
		 * 
		 * custom exception
		 */

		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<ErrorInfo> idNotFoundException(IdNotFoundException exception, WebRequest webRequest){
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setTimeStamp(LocalDateTime.now());
			errorInfo.setMessage(exception.getMessage());
			errorInfo.setDetails(webRequest.getDescription(false));
			errorInfo.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorInfo,HttpStatus.OK);
		}
		
		/*
		 * 
		 * Applocation exception
		 */

		@ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorInfo> exception(Exception exception, WebRequest webRequest){
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setTimeStamp(LocalDateTime.now());
			errorInfo.setMessage(exception.getMessage());
			errorInfo.setDetails(webRequest.getDescription(false));
			errorInfo.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(errorInfo,HttpStatus.OK);
		}

}
