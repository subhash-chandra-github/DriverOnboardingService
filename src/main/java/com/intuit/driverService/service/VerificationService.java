package com.intuit.driverService.service;

import com.intuit.driverService.core.model.DriverDetailsModel;
import com.intuit.driverService.dto.VerificationResponseDto;

public interface VerificationService {

   void sendBackGroundVerificationRequest(DriverDetailsModel driverDetails);

   void updateVerificationStatus (VerificationResponseDto responseDto);

   void triggerBackgroundVerification();
}
