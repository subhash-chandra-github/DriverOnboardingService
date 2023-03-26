package com.intuit.driverService.common.exceptions;



public class GenericException extends RuntimeException {
    public GenericException() {
    }

    public GenericException(String s) {
        super(s);
    }

    public GenericException(String s, Throwable throwable) {
        super(s, throwable);

    }
}
