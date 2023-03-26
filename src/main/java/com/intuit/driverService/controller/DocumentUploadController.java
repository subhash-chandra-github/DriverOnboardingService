package com.intuit.driverService.controller;


import com.intuit.driverService.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentUploadController {


    @Autowired
    private DocumentService documentService;

    @PostMapping("/uploadDocument")
    public ResponseEntity<String> uploadDocument(@RequestPart(value = "documentDetails") String documentDetails,
                                                 @RequestPart(value = "file") MultipartFile file) {

        String resp = documentService.uploadDocument(documentDetails, file);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


}
