package com.stackroute.exceptions;

public class AuthenticationFailureException extends RuntimeException{
    String msg;

    public AuthenticationFailureException(String message) {

        this.msg = message;
    }


    public String getMsg() {
        return msg;
    }
}
