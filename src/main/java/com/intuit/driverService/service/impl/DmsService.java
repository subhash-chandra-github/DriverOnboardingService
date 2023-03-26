package com.intuit.driverService.service.impl;


import com.intuit.driverService.common.exceptions.GenericException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DmsService {

    @Value("${dms.baseUrl}")
    private String baseUrl;

    @Value("${dms.upload}")
    private String uploadUrl;

    @Value("${dms.get}")
    private String fetchUrl;


    @Autowired
    private RestTemplate restTemplate;


    public String upload(MultipartFile file, String documentUploadDto) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);
        uriComponentsBuilder.path(uploadUrl);

        String tmpFileName = "/tmp/" + file.getOriginalFilename();
        try {
            ResponseEntity<String> result = null;
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            InputStream io = file.getInputStream();
            try (FileOutputStream fo = new FileOutputStream(tmpFileName)) {
                fo.write(IOUtils.toByteArray(io));
            } catch (IOException e) {
                return null;
            }
            map.add("file", new FileSystemResource(tmpFileName));
            map.add("documentUploadDto", documentUploadDto);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            result = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(map, headers),
                    String.class);
            return result.getBody();
        } catch (IOException ioException) {
            throw new GenericException("Unable to upload the document. Please try again ");
        }
    }

    public String getUrlByDocumentId(String documentId) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);
        uriComponentsBuilder.path(fetchUrl);
        uriComponentsBuilder.queryParam("documentId",documentId);
        try {
            ResponseEntity<String> result = null;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            result = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                    HttpMethod.GET,
                    null,
                    String.class);
            return result.getBody();
        } catch (Exception ex) {
            throw new GenericException("Unable to upload the document. Please try again ");
        }
    }
}
