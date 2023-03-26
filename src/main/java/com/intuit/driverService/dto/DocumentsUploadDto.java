package com.intuit.driverService.dto;


import lombok.Data;

@Data
public class DocumentsUploadDto {


    private Long userId;
    private String uploadedBy ="SYSTEM";

}
