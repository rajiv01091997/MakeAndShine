package com.stackroute.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
