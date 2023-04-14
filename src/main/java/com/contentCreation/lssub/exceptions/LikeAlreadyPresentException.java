package com.contentCreation.lssub.exceptions;


public class LikeAlreadyPresentException extends RuntimeException {

    public LikeAlreadyPresentException(String message) {
        super(message);
    }

    public LikeAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
