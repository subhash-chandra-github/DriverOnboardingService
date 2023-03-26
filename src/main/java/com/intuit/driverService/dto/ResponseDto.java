package com.intuit.driverService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private int status;
    private String message;
    private T data;




}
