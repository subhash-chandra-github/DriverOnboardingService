package com.intuit.driverService.common.exceptions;

public class BadRequestException  extends RuntimeException {

    public BadRequestException() {
    }

    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
