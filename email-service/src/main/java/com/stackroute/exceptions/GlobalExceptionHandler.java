package com.stackroute.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorInfo>> exceptionForValidation(MethodArgumentNotValidException exception, WebRequest webRequest)
    {
        List<ErrorInfo> errorLog=new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error)->
        {
            ErrorInfo errorInfo=new ErrorInfo();
            errorInfo.setTimestamp(LocalDateTime.now());
            errorInfo.setStatus(HttpStatus.NOT_ACCEPTABLE);
            errorInfo.setMessage(error.getDefaultMessage());
            errorInfo.setPath(webRequest.getDescription(false));
            errorLog.add(errorInfo);
        });
        return new ResponseEntity<>(errorLog,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(AuthenticationFailureException.class)
    public ResponseEntity<ErrorInfo> authenticationException(AuthenticationFailureException exception, WebRequest webRequest)
    {
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setMessage(exception.getMsg());
        errorInfo.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionNotCaught(Exception exception, WebRequest webRequest)
    {
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setMessage(exception.getMessage());
        errorInfo.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
    }
}
