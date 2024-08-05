package com.backend.management.exception;

public class InvalidAnnouncementDateException extends RuntimeException{
    public InvalidAnnouncementDateException(String message) {
        super(message);
    }
}
