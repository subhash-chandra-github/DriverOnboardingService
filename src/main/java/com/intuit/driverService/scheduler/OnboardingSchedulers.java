package com.intuit.driverService.scheduler;

import com.intuit.driverService.core.repository.DriverDetailsRepository;
import com.intuit.driverService.service.TrackingDeviceDeliveryService;
import com.intuit.driverService.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OnboardingSchedulers {

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private TrackingDeviceDeliveryService trackingDeviceDeliveryService;

    @Scheduled(cron = "0 */30 * * * ?")
    public void triggerBackgroundVerification() {

        verificationService.triggerBackgroundVerification();

    }

    @Scheduled(cron = "0 */30 * * * ?")
    public void sendTrackingDevice() {
        trackingDeviceDeliveryService.sendTrackingDevice();
    }
}
