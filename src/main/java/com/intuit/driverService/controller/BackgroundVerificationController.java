package com.intuit.driverService.controller;


import com.intuit.driverService.dto.DocumentVerificationDto;
import com.intuit.driverService.dto.VerificationResponseDto;
import com.intuit.driverService.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackgroundVerificationController {

    @Autowired
    private VerificationService verificationService;


    @PostMapping("/update-verification-status")
    public String updateVerificationStatus(@RequestBody VerificationResponseDto verificationDto) {

        verificationService.updateVerificationStatus(verificationDto);
        return "Update successful";
    }

    @GetMapping("/background-verification")
    public void updateVerificationStatus() {
        verificationService.triggerBackgroundVerification();
    }

}
