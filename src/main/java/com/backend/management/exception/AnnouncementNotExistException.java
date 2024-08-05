package com.backend.management.exception;

public class AnnouncementNotExistException extends RuntimeException{
    public AnnouncementNotExistException(String message) {
        super(message);
    }
}
