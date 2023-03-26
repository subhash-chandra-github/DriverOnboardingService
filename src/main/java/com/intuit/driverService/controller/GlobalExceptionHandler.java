package com.intuit.driverService.controller;


import com.intuit.driverService.common.exceptions.BadRequestException;
import com.intuit.driverService.common.exceptions.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(BadRequestException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(GenericException ex) {
        return ex.getMessage();
    }
}
