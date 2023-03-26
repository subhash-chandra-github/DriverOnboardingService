package com.intuit.driverService.service.impl;

import com.intuit.driverService.common.enums.DriverProfileStatus;
import com.intuit.driverService.core.model.DriverDetailsModel;
import com.intuit.driverService.core.repository.DriverDetailsRepository;
import com.intuit.driverService.dto.ResponseDto;
import com.intuit.driverService.dto.VerificationRequestDto;
import com.intuit.driverService.dto.VerificationResponseDto;
import com.intuit.driverService.service.DriverOnboardingService;
import com.intuit.driverService.service.VerificationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class VerificationServiceImpl implements VerificationService {


    @Value("${verification.baseUrl}")
    private String baseUrl;

    @Value("${verification.backgroundVerification}")
    private String backgroundVerification;

    @Value("${verification.mobileVerification}")
    private String mobileVerification;

    @Value("${verification.callBackUrl}")
    private String callBackUrl;

    @Autowired
    private DmsService dmsService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DriverOnboardingService driverOnboardingService;

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Override
    public void sendBackGroundVerificationRequest(DriverDetailsModel driverDetails) {
        VerificationRequestDto verificationRequest = new VerificationRequestDto();

        verificationRequest.setAddress(driverDetails.getAddress());
        verificationRequest.setCallBackUrl(callBackUrl);
        verificationRequest.setCustomerId(driverDetails.getId());
        verificationRequest.setRequestType("BackgroundVerification");
        verificationRequest.setCity(driverDetails.getCity());
        verificationRequest.setEmailId(driverDetails.getEmailId());
        verificationRequest.setState(driverDetails.getState());
        verificationRequest.setContactNo(driverDetails.getMobileNo());
        verificationRequest.setPinCode(driverDetails.getPinCode());
        verificationRequest.setName(driverDetails.getFirstName()+driverDetails.getLastName());
        verificationRequest.setUploadedBy("Intuit");
        verificationRequest.setInfo1("background check");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);
        uriComponentsBuilder.path(backgroundVerification);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            ResponseEntity<ResponseDto<String>> response = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(verificationRequest, headers),
                    new ParameterizedTypeReference<ResponseDto<String>>() {
                    });
            if(response.getStatusCode()==HttpStatus.OK && response.getBody().getStatus()==200){
                driverDetails.setStatus(DriverProfileStatus.BACKGROUND_VERIFICATION_IN_PROGRESS.name());
            }
        }catch (Exception ex) {
            //Log error details
        }
    }

    @Override
    public void updateVerificationStatus(VerificationResponseDto responseDto) {
        driverOnboardingService.updateStatus(responseDto.getDriverId(), responseDto.getStatus());
    }

    @Override
    public void triggerBackgroundVerification(){
        List<DriverDetailsModel> driverDetailsModels = driverDetailsRepository.findAllByStatus(DriverProfileStatus.BACKGROUND_VERIFICATION_PENDING.name());
        if(CollectionUtils.isNotEmpty(driverDetailsModels)){
            for(DriverDetailsModel driverDetailsModel : driverDetailsModels){
                sendBackGroundVerificationRequest(driverDetailsModel);
            }
            driverDetailsRepository.saveAll(driverDetailsModels);
        }

    }
}
