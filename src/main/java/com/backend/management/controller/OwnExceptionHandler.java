package com.backend.management.controller;

import com.backend.management.exception.InvalidAnnouncementDateException;
import com.backend.management.exception.InvalidPostDateException;
import com.backend.management.exception.UserAlreadyExistException;
import com.backend.management.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
// 这里不能直接叫ExceptionHandler，会和default的ExceptionHandler重名，intellj不知道用哪一个
public class OwnExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<String>handleUserAlreadyExistExceptions(Exception ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotExistException.class)
    public final ResponseEntity<String> handleUserNotExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(InvalidAnnouncementDateException.class)
    public final ResponseEntity<String>handleInvalidAnnouncementDateException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPostDateException.class)
    public final ResponseEntity<String>handleInvalidPostDateException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
