package com.intuit.driverService.controller;


import com.intuit.driverService.dto.DriverDetails;
import com.intuit.driverService.dto.LoginRequestDto;
import com.intuit.driverService.service.DriverOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverProfileController {


    @Autowired
    private DriverOnboardingService driverOnboardingService;

    @PostMapping("/login")
    public ResponseEntity<String> register(@RequestBody LoginRequestDto request) {

        driverOnboardingService.login(request);
        String token = request.getMobileNo() + request.getPassword().hashCode();
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@RequestBody DriverDetails driverDetails) {

        String response = driverOnboardingService.register(driverDetails);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/driver")  //get drivers data by id
//    public ResponseEntity<String> register(@RequestParam long id) {
//
//        //String response = driverOnboardingService.register();
//       // return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/update-status")
//    public String updateStatus(@RequestParam Long driverId, @RequestParam String staus){
//        driverOnboardingService.updateStatus();
//    }




}
