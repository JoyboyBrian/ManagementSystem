package com.backend.management.exception;

public class InvalidPostDateException extends RuntimeException{
    public InvalidPostDateException(String message) {
        super(message);
    }
}
