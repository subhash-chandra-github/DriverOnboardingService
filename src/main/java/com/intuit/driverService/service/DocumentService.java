package com.intuit.driverService.service;

import com.intuit.driverService.dto.DocumentDto;
import com.intuit.driverService.dto.DocumentVerificationDto;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {


    String uploadDocument(String documentsDetail, MultipartFile file);
}
