package com.intuit.driverService.service;

import com.intuit.driverService.common.enums.DriverProfileStatus;
import com.intuit.driverService.dto.DriverDetails;
import com.intuit.driverService.dto.LoginRequestDto;

public interface DriverOnboardingService {

    String register( DriverDetails driverDetails);

    void login(LoginRequestDto request);

    void updateStatus(Long driverId, String status);

    void markStatus(Long driverId, String status);
}
