package com.revature.exceptions;

public class EmailDoesntExistException extends RuntimeException{
    public EmailDoesntExistException() {
    }

    public EmailDoesntExistException(String message) {
        super(message);
    }

    public EmailDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailDoesntExistException(Throwable cause) {
        super(cause);
    }

    public EmailDoesntExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
