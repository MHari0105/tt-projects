package com.myproject.BackendApp.exception;

public class UserNotFound extends Exception {
    
    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
