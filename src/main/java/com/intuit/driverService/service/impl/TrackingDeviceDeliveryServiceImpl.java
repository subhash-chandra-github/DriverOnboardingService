package com.intuit.driverService.service.impl;

import com.intuit.driverService.common.enums.DriverProfileStatus;
import com.intuit.driverService.core.model.DriverDetailsModel;
import com.intuit.driverService.core.repository.DriverDetailsRepository;
import com.intuit.driverService.service.TrackingDeviceDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TrackingDeviceDeliveryServiceImpl implements TrackingDeviceDeliveryService {


    @Autowired
    private DriverDetailsRepository driverDetailsRepository;


    @Override
    public void sendTrackingDevice() {

        List<DriverDetailsModel> driverDetailsModelList = driverDetailsRepository.findAllByStatus(DriverProfileStatus.BACKGROUND_VERIFICATION_SUCCESSFUL.name());
        for (DriverDetailsModel model : driverDetailsModelList) {
            sendTrackingDevicetoDriver(driverDetailsModelList);
            model.setStatus(DriverProfileStatus.PROFILE_ACTIVATED.name());
        }
        driverDetailsModelList = driverDetailsRepository.saveAll(driverDetailsModelList);
        driverDetailsModelList = driverDetailsModelList.stream().filter(e->e.getStatus().equalsIgnoreCase(DriverProfileStatus.PROFILE_ACTIVATED.name())).collect(Collectors.toList());
        sendNotificationToDrivers(driverDetailsModelList);
    }

    private void sendNotificationToDrivers(List<DriverDetailsModel> model) {

        //send notifications to drivers that their profile has been activated
    }

    private void sendTrackingDevicetoDriver(List<DriverDetailsModel> driverDetailsModelList) {
        //send tracking device to driver's address
    }
}
