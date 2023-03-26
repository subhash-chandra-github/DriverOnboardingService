package com.intuit.driverService.service.impl;

import com.google.gson.Gson;
import com.intuit.driverService.common.constants.Constants;
import com.intuit.driverService.common.enums.DriverProfileStatus;
import com.intuit.driverService.common.exceptions.BadRequestException;
import com.intuit.driverService.common.exceptions.GenericException;
import com.intuit.driverService.core.model.DocumentModel;
import com.intuit.driverService.core.repository.DocumentDetailsRepository;
import com.intuit.driverService.dto.DocumentDto;
import com.intuit.driverService.dto.DocumentVerificationDto;
import com.intuit.driverService.dto.DocumentsUploadDto;
import com.intuit.driverService.service.DocumentService;
import com.intuit.driverService.service.DriverOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DocumentServiceImpl implements DocumentService {


    @Autowired
    private Gson gson;

    @Autowired
    private DmsService dmsService;

    @Autowired
    private DriverOnboardingService driverOnboardingService;

    @Autowired
    private DocumentDetailsRepository documentRepository;

    @Override
    public String uploadDocument(String documentsDetail, MultipartFile file) {
        DocumentDto documentDto = gson.fromJson(documentsDetail, DocumentDto.class);
        try {
            DocumentsUploadDto uploadDto = new DocumentsUploadDto();
            uploadDto.setUploadedBy("SYSTEM");
            uploadDto.setUserId(documentDto.getUserId());
            String documentId = saveInDms(gson.toJson(uploadDto), file);
            DocumentModel documentModel = toDocumentModel(documentId, documentDto);
            List<DocumentModel> documentModels = documentRepository.findAllByDriverId(documentDto.getUserId());
            List<String> uploadedDocuments = documentModels.stream().map(DocumentModel::getDocumentType).collect(Collectors.toList());
            uploadedDocuments.add(documentDto.getType());
            boolean flag = Constants.requiredDocument.stream().allMatch(element -> uploadedDocuments.contains(element));
            if(flag){
                driverOnboardingService.updateStatus(documentDto.getUserId(), DriverProfileStatus.BACKGROUND_VERIFICATION_PENDING.name());
            }
          //  documentRepository.save(documentModel);

        }catch (Exception ex){
            throw new RuntimeException("Exception in uploading document for userId "+documentDto.getUserId());
        }
        return documentDto.getUserId()+" uploaded successfully";
    }

    private DocumentModel toDocumentModel(String documentId, DocumentDto documentDto) {

        DocumentModel documentModel = new DocumentModel();
        documentModel.setDocumentId(documentId);
        documentModel.setDriverId(documentDto.getUserId());
        documentModel.setDocumentType(documentDto.getType());
        return documentModel;
    }

    private String saveInDms(String documentsDetail, MultipartFile file) {
        return dmsService.upload(file,documentsDetail);
    }
}
