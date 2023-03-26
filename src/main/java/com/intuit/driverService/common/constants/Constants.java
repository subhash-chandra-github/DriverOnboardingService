package com.intuit.driverService.common.constants;

import com.intuit.driverService.common.enums.DocumentType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    private Constants(){};
    public static final String salt = "AHASGVHV";
    public static final List<String> requiredDocument = Collections.unmodifiableList(Arrays.asList(DocumentType.AADHAAR_CARD.name(),
            DocumentType.DRIVING_LICENSE.name(),
            DocumentType.PAN_CARD.name(),
            DocumentType.PROFILE_PHOTO.name()));
}
