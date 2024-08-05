package com.backend.management.exception;

public class ReservationCollisionException extends RuntimeException{
    public ReservationCollisionException(String message){
        super(message);
//        super("Ops, your reservation did not successfully submit");
    }
}
