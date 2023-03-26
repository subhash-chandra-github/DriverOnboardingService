package com.intuit.driverService.dto;

import lombok.Data;

@Data
public class VerificationResponseDto {

    private Long driverId;
    private String status;
    private String reports;
}

