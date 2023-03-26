package com.intuit.driverService.core.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "driver_details")
public class DriverDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "mobile_no", unique = true)
    private String mobileNo;

    @Column(name = "email_id",unique = true)
    private String emailId;

    @Column(name = "mobile_verified")
    boolean isMobileVerified;

    @Column(name = "email_verified")
    boolean isEmailVerified;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedDate;



}
