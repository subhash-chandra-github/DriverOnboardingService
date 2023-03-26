package com.intuit.driverService.dto;


import lombok.Data;

@Data
public class VerificationRequestDto {


    private String name;

    private Long customerId;

    private String requestType;

    private String contactNo;

    private String emailId;

    private String address;

    private String city;

    private String state;

    private String pinCode;

    private String documents;

    private String callBackUrl;

    private String uploadedBy;

    private String info1;

    private String info2;

}
