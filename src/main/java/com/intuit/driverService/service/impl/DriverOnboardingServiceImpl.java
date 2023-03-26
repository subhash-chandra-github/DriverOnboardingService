package com.intuit.driverService.service.impl;

import com.intuit.driverService.common.constants.Constants;
import com.intuit.driverService.common.enums.DateFormats;
import com.intuit.driverService.common.enums.DriverProfileStatus;
import com.intuit.driverService.common.exceptions.BadRequestException;
import com.intuit.driverService.common.exceptions.GenericException;
import com.intuit.driverService.common.utils.CommonDateUtils;
import com.intuit.driverService.common.utils.PasswordUtils;
import com.intuit.driverService.core.model.DriverDetailsModel;
import com.intuit.driverService.core.repository.DriverDetailsRepository;
import com.intuit.driverService.dto.DriverDetails;
import com.intuit.driverService.dto.LoginRequestDto;
import com.intuit.driverService.service.DriverOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverOnboardingServiceImpl implements DriverOnboardingService {


    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Override
    public String register(DriverDetails driverDetails) {

        try {
            DriverDetailsModel driverDetailsModel = toDriverDetailModel(driverDetails);
            driverDetailsRepository.save(driverDetailsModel);
            return "Driver account created successfully";
        }catch (Exception ex){
            throw new GenericException(ex.getMessage());
        }
    }

    @Override
    public void login(LoginRequestDto request) {
        try{
            DriverDetailsModel model = driverDetailsRepository.findByMobileNo(request.getMobileNo());
            if(Objects.nonNull(model)){
                if(PasswordUtils.verifyUserPassword(request.getPassword(),model.getPassword(), Constants.salt)){
                    return;
                }
            }
            throw new BadRequestException("Invalid Mobile no");
        }catch (Exception ex){
            throw new GenericException(ex.getMessage());
        }
    }

    @Override
    public void updateStatus(Long driverId, String status) {
        Optional<DriverDetailsModel> optionalDriverDetailsModel = driverDetailsRepository.findById(driverId);
        if(!optionalDriverDetailsModel.isPresent()){
            throw new RuntimeException("Driver do not exists for driverId"+driverId);
        }

        if(!optionalDriverDetailsModel.get().getStatus().equalsIgnoreCase(status)){
           driverDetailsRepository.updateStatus(driverId,status);
        }

    }

    @Override
    public void markStatus(Long driverId, String status) {
        Optional<DriverDetailsModel> optionalDriverDetailsModel = driverDetailsRepository.findById(driverId);
        if(!optionalDriverDetailsModel.isPresent()){
            throw new RuntimeException("Driver do not exists for driverId"+driverId);
        }

        if(!optionalDriverDetailsModel.get().getStatus().equalsIgnoreCase(status)){
            driverDetailsRepository.updateStatus(driverId,status);
        }

    }

    private DriverDetailsModel toDriverDetailModel(DriverDetails driverDetails) {

        DriverDetailsModel driverDetailsModel = new DriverDetailsModel();
        driverDetailsModel.setFirstName(driverDetails.getFirstName());
        driverDetailsModel.setLastName(driverDetails.getLastName());
        driverDetailsModel.setEmailId(driverDetails.getEmailId());
        driverDetailsModel.setMobileNo(driverDetails.getMobileNo());
        driverDetailsModel.setCity(driverDetails.getCity());
        driverDetailsModel.setPassword(PasswordUtils.generateSecurePassword(driverDetails.getPassword(),Constants.salt));
        driverDetailsModel.setState(driverDetails.getState());
        driverDetailsModel.setAddress(driverDetails.getAddress());
        driverDetailsModel.setPinCode(driverDetails.getPinCode());
        driverDetailsModel.setStatus(DriverProfileStatus.DOCUMENT_UPLOAD_PENDING.name());
        //call verification service to verify mobile
        boolean verified = true;
        if (!verified) {
            throw new GenericException("Mobile no not verified !! retry");
        }
        driverDetailsModel.setMobileVerified(true);
        try {
            driverDetailsModel.setDateOfBirth(CommonDateUtils.getDateFromStringWithFormat(driverDetails.getDateOfBirth(), DateFormats.DD_MM_YYYY.getDateFormat()));
        } catch (ParseException exception) {
            throw new GenericException("Not able parse date of birth");
        }

        return driverDetailsModel;
    }


}
