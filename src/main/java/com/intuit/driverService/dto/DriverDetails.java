package com.intuit.driverService.dto;


import lombok.Data;


@Data
public class DriverDetails {

    private String firstName;

    private String lastName;

    private String mobileNo;

    private String emailId;

    private String password;

    private String dateOfBirth;

    private String city;

    private String state;

    private String pinCode;

    private String address;
}
