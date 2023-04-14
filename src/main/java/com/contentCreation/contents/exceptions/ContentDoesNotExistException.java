package com.contentCreation.contents.exceptions;

public class ContentDoesNotExistException extends RuntimeException {


    public ContentDoesNotExistException(String message) {
        super(message);
    }

    public ContentDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
