package com.bms.dummyrestapi.exceptions;

public class AllReadyExistsException extends RuntimeException {
    public AllReadyExistsException(String message) {
        super(message);
    }
}