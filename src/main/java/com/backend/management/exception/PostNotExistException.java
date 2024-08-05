package com.backend.management.exception;

public class PostNotExistException extends RuntimeException{
    public PostNotExistException(String message) {
        super(message);
    }
}
