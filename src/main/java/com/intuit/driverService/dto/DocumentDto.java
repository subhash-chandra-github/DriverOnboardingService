package com.intuit.driverService.dto;


import lombok.Data;

@Data
public class DocumentDto {

    private String fileName;
    private Long userId;
    private String type;
    private String documentId;

}
