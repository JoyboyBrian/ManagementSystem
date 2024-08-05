package com.backend.management.exception;

public class ApartmentNotExistException extends RuntimeException {

    public ApartmentNotExistException(String message) {
        super(message);
    }
}
