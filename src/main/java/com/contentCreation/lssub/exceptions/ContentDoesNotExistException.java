package com.contentCreation.lssub.exceptions;

public class ContentDoesNotExistException extends RuntimeException {
    public ContentDoesNotExistException(String message) {
        super(message);
    }
}
